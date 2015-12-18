package com.cloudlanes.storageaccess;

import static com.cloudlanes.utils.AppConstants.BARCODE_PLACE_HOLDER;
import static com.cloudlanes.utils.AppConstants.CHANNEL_ID;
import static com.cloudlanes.utils.AppConstants.CHANNEL_PLACE_HOLDER;
import static com.cloudlanes.utils.AppConstants.DEVICE_CONF_BAK_FILE;
import static com.cloudlanes.utils.AppConstants.DEVICE_CONF_FILE;
import static com.cloudlanes.utils.AppConstants.DRIVE_NO;
import static com.cloudlanes.utils.AppConstants.DRIVE_PLACE_HOLDER;
import static com.cloudlanes.utils.AppConstants.DRIVE_SLOT_PLACE_HOLDER;
import static com.cloudlanes.utils.AppConstants.DRIVE_TEMPLATE;
import static com.cloudlanes.utils.AppConstants.LIBRARY_CONTENTS_FILE;
import static com.cloudlanes.utils.AppConstants.LIBRARY_TEMPLATE;
import static com.cloudlanes.utils.AppConstants.LIB_CONTENT_DRIVE;
import static com.cloudlanes.utils.AppConstants.LIB_CONTENT_MAP;
import static com.cloudlanes.utils.AppConstants.LIB_CONTENT_PICKER;
import static com.cloudlanes.utils.AppConstants.LIB_CONTENT_SLOT;
import static com.cloudlanes.utils.AppConstants.LIB_ID_PLACE_HOLDER;
import static com.cloudlanes.utils.AppConstants.LUN_PLACE_HOLDER;
import static com.cloudlanes.utils.AppConstants.MHVTL_ROOT;
import static com.cloudlanes.utils.AppConstants.NAA_PLACE_HOLDER;
import static com.cloudlanes.utils.AppConstants.PRODUCT_PLACE_HOLDER;
import static com.cloudlanes.utils.AppConstants.SLOT_NO;
import static com.cloudlanes.utils.AppConstants.TARGET_ID;
import static com.cloudlanes.utils.AppConstants.TARGET_PLACE_HOLDER;
import static com.cloudlanes.utils.AppConstants.UNIT_SERIAL_ID_PREFIX;
import static com.cloudlanes.utils.AppConstants.UNIT_SR_NO_PLACE_HOLDER;
import static com.cloudlanes.utils.AppConstants.VENDOR_PLACE_HOLDER;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cloudlanes.db.entities.Drive;
import com.cloudlanes.db.entities.DriveModel;
import com.cloudlanes.db.entities.DriveVendor;
import com.cloudlanes.db.entities.MediaType;
import com.cloudlanes.db.entities.Slot;
import com.cloudlanes.db.entities.Tape;
import com.cloudlanes.db.entities.Vtl;
import com.cloudlanes.db.entities.VtlBrand;
import com.cloudlanes.db.entities.VtlModel;
import com.cloudlanes.rest.inobject.TapeDto;
import com.cloudlanes.rest.inobject.VtlDto;
import com.cloudlanes.utils.AppConstants;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

@Component
public class VTLImpl implements StorageInterface {

	@Autowired
	private RemoteConnectionInfo conInfo;
	
	public VTLImpl() {
		super();
	}
	
	private SequenceNumberGenerator libIdGenerator;

	private NaaSequenceGenerator naaIdGenerator;
	
	private TapeBarcodeGenerator barcodeGenerator;

	public void setSequenceIdGenerator(SequenceNumberGenerator gen) {
		this.libIdGenerator = gen;
	}

	public void setNaaIdGenerator(NaaSequenceGenerator gen) {
		this.naaIdGenerator = gen;
	}
	
	public void setBarcodeGenerator(TapeBarcodeGenerator gen) {
		this.barcodeGenerator = gen;
	}

	public Vtl createVtl(VtlDto vtlDto, VtlBrand vtlBrand, VtlModel vtlModel,
			DriveVendor driveVendor, DriveModel driveModel, MediaType mediaType) {

		Vtl vtlData = new Vtl();

		vtlData.setCompressionEnabled((byte) (vtlDto.isCompressionEnabled() ? 1
				: 0));
		vtlData.setCompressionFactor(vtlDto.getCompressionFactor());
		vtlData.setDriveCount(vtlDto.getNoOfDrives());
		vtlData.setEmptySlotCount(vtlDto.getNoOfEmptySlots());
		vtlData.setIePortCount(vtlDto.getNoOfMaps());
		vtlData.setPickerCount(vtlDto.getNoOfPickers());
		vtlData.setSlotCount(vtlDto.getNoOfSlots());
		vtlData.setVtlBrand(vtlBrand);

		vtlData.setVtlModel(vtlModel);

		vtlData.setVtlName(vtlDto.getLibraryName());

		// Read and update device.conf file content
		String deviceFileContent = readRemoteFile(MHVTL_ROOT, DEVICE_CONF_FILE);
		String templateData = LIBRARY_TEMPLATE;

		int libId = libIdGenerator.getNextId();
		vtlData.setVtlLibId(libId);
		String libIdStr = libId + "";
		templateData = templateData.replace(LIB_ID_PLACE_HOLDER, libIdStr);
		templateData = templateData.replace(CHANNEL_PLACE_HOLDER, CHANNEL_ID);
		templateData = templateData.replace(TARGET_PLACE_HOLDER, TARGET_ID);
		templateData = templateData.replace(LUN_PLACE_HOLDER, libIdStr);
		templateData = templateData.replace(VENDOR_PLACE_HOLDER,
				vtlBrand.getBrandName());
		templateData = templateData.replace(PRODUCT_PLACE_HOLDER,
				vtlModel.getModelName());
		templateData = templateData.replace(UNIT_SR_NO_PLACE_HOLDER,
				UNIT_SERIAL_ID_PREFIX + libIdStr);
		templateData = templateData.replace(NAA_PLACE_HOLDER,
				naaIdGenerator.getNextNaaId((int) libId));

		templateData = templateData.concat("\n\n");

		deviceFileContent = deviceFileContent
				.concat("\n# BEGIN CloudLanes system generated config.\n");

		deviceFileContent = deviceFileContent.concat(templateData);

		Set<Drive> drives = new HashSet<Drive>();

		for (int i = 1; i <= vtlDto.getNoOfDrives(); i++) {

			Drive drive = new Drive();
			drive.setDriveVendor(driveVendor);
			drive.setDriveModel(driveModel);

			drive.setDriveName("" + i);

			// by default, drive would be empty on creation.
			drive.setIsEmpty((byte) 1);
			drive.setVtl(vtlData);
			drives.add(drive);

			templateData = DRIVE_TEMPLATE;
			long driveId = libIdGenerator.getNextId();
			String driveIdStr = driveId + "";
			templateData = templateData.replace(DRIVE_PLACE_HOLDER, driveIdStr);
			templateData = templateData.replace(CHANNEL_PLACE_HOLDER,
					CHANNEL_ID);
			templateData = templateData.replace(TARGET_PLACE_HOLDER, TARGET_ID);
			templateData = templateData.replace(LUN_PLACE_HOLDER, driveIdStr
					+ "");
			templateData = templateData.replace(LIB_ID_PLACE_HOLDER, libIdStr);
			templateData = templateData
					.replace(DRIVE_SLOT_PLACE_HOLDER, i + "");
			templateData = templateData.replace(VENDOR_PLACE_HOLDER,
					driveVendor.getVendorName());
			templateData = templateData.replace(PRODUCT_PLACE_HOLDER,
					driveModel.getModelName());
			templateData = templateData.replace(UNIT_SR_NO_PLACE_HOLDER,
					UNIT_SERIAL_ID_PREFIX + driveIdStr);
			templateData = templateData.replace(NAA_PLACE_HOLDER,
					naaIdGenerator.getNextNaaId((int) driveId));
			templateData = templateData.replace(
					AppConstants.DRIVE_COMPRESSION_FACTOR_PLACE_HOLDER,
					vtlDto.getCompressionFactor() + "");
			templateData = templateData.replace(
					AppConstants.DRIVE_COMPRESSION_ENABLED_PLACE_HOLDER,
					(vtlDto.isCompressionEnabled() ? 1 : 0) + "");

			if (i != vtlDto.getNoOfDrives()) {
				templateData = templateData.concat("\n\n");
			}

			deviceFileContent = deviceFileContent.concat(templateData);
		}
		if (drives.size() > 0) {
			vtlData.setDrives(drives);
		}

		deviceFileContent = deviceFileContent
				.concat("\n# END CloudLanes system generated config.\n");

		stopMhvtlService();
		copyFile(MHVTL_ROOT + "/" + DEVICE_CONF_FILE, MHVTL_ROOT + "/"
				+ DEVICE_CONF_BAK_FILE);
		writeRemoteFile(deviceFileContent, MHVTL_ROOT, DEVICE_CONF_FILE);
		createLibraryContentsFile(libIdStr, vtlDto, vtlData, mediaType);
		startMhvtlService();

		List<ScsiDeviceInfo> deviceInfoList = getLibraryDriveScsiInfo();
		if (deviceInfoList.size() == vtlDto.getNoOfDrives() + 1) {
			// Last will be lib device info as we added it to last..
			ScsiDeviceInfo libDeviceInfo = deviceInfoList.get(deviceInfoList
					.size() - 1);

			if (libDeviceInfo.getDeviceType().equals(
					AppConstants.LIBRARY_SCSI_TYPE)) {
				vtlData.setScsiDevFilePath(libDeviceInfo.getDeviceFilePath());
			}

			List<Drive> driveList = new ArrayList<>(drives);

			for (int i = driveList.size() - 1, devIndex = 0; i >= 0; i--, devIndex++) {
				Drive drive = driveList.get(i);
				drive.setScsiDevFilePath(deviceInfoList.get(devIndex)
						.getDeviceFilePath());
			}
		}

		return vtlData;
	}

	private void createLibraryContentsFile(String libraryId, VtlDto vtlDto,
			Vtl vtlData, MediaType mediaType) {
		StringBuilder libraryContent = new StringBuilder(
				"# This is CloudLanes system generated library content config for Library ID: "
						+ libraryId + "\n");

		for (int i = 1; i <= vtlDto.getNoOfDrives(); i++) {
			libraryContent.append(LIB_CONTENT_DRIVE.replace(DRIVE_NO, "" + i));
		}

		libraryContent.append("\n");

		for (int i = 1; i <= vtlDto.getNoOfPickers(); i++) {
			libraryContent.append(LIB_CONTENT_PICKER.replace(AppConstants.PICKER_NO, "" + i));
		}

		libraryContent.append("\n");

		for (int i = 1; i <= vtlDto.getNoOfMaps(); i++) {
			libraryContent.append(LIB_CONTENT_MAP.replace(DRIVE_NO, "" + i));
		}

		libraryContent.append("\n");

		int barcodeId = 2000;
		int noOfLoadedSlots = vtlDto.getNoOfSlots()
				- vtlDto.getNoOfEmptySlots();

		Set<Slot> slots = new HashSet<>();
		Set<Tape> tapes = new HashSet<>();

		for (int i = 1; i <= vtlDto.getNoOfSlots(); i++) {
			Slot slot = new Slot();
			slot.setSlotName("" + i);
			slot.setVtl(vtlData);
			if (i <= noOfLoadedSlots) {
				slot.setIsEmpty((byte) 0);
				Tape tape = new Tape();

				String barcode = barcodeGenerator.getTapeBarcode(mediaType);
				libraryContent.append(LIB_CONTENT_SLOT.replace(SLOT_NO, "" + i)
						.replace(BARCODE_PLACE_HOLDER, barcode));
				tape.setBarcode(barcode);
				// by default, tape is in slot and hence not loaded into drive
				tape.setIsLoaded((byte) 0);
				tape.setLabel(barcode);
				tape.setSlot(slot);
				tape.setStatus(AppConstants.TAPE_STATUS_IN_LIBRARY);
				tape.setVtl(vtlData);
				tape.setMediaType(mediaType);
				tapes.add(tape);

			} else {
				slot.setIsEmpty((byte) 1);
				libraryContent.append(LIB_CONTENT_SLOT.replace(SLOT_NO, "" + i)
						.replace(BARCODE_PLACE_HOLDER, ""));
			}
			slots.add(slot);
		}

		if (slots.size() > 0) {
			vtlData.setSlots(slots);
		}
		if (tapes.size() > 0) {
			vtlData.setTapes(tapes);
		}

		writeRemoteFile(libraryContent.toString(), MHVTL_ROOT,
				LIBRARY_CONTENTS_FILE.replace(LIB_ID_PLACE_HOLDER, libraryId));
	}

	private void startMhvtlService() {
		try {
			/**
			 * Create a new Jsch object This object will execute shell commands
			 * or scripts on server
			 */
			JSch jsch = new JSch();

			/*
			 * Open a new session, with your username, host and port Set the
			 * password and call connect. session.connect() opens a new
			 * connection to remote SSH server. Once the connection is
			 * established, you can initiate a new channel. this channel is
			 * needed to connect to remotely executing program
			 */
			Session session = jsch.getSession(conInfo.getUserName(),
					conInfo.getHost(), conInfo.getPort());
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(conInfo.getPassword());
			session.connect();

			// create the excution channel over the session
			ChannelExec channelExec = (ChannelExec) session.openChannel("exec");

			// Gets an InputStream for this channel. All data arriving in as
			// messages from the remote side can be read from this
			// stream.
			InputStream in = channelExec.getInputStream();

			// Set the command that you want to execute
			// In our case its the remote shell script
			// channelExec.setCommand("sh "+scriptFileName);
			channelExec.setCommand("service mhvtl start");

			// Execute the command
			channelExec.connect();

			/*
			 * // Read the output from the input stream we set above
			 * BufferedReader reader = new BufferedReader( new
			 * InputStreamReader(in)); String line;
			 * 
			 * // Read each line from the buffered reader and add it to result
			 * list // You can also simple print the result here while ((line =
			 * reader.readLine()) != null) { result.add(line); }
			 */
			StringBuilder sb = new StringBuilder();
			char[] buffer = new char[0x10000];
			Reader reader = new InputStreamReader(in, "UTF-8");
			int bytesRead = 0;
			do {
				if (in.available() > 0) {
					bytesRead = reader.read(buffer, 0, buffer.length);
					if (bytesRead > 0) {
						sb.append(buffer, 0, bytesRead);
					}
				}
			} while (bytesRead > 0 && in.available() > 0);
			reader.close();
			in.close();

			// retrieve the exit status of the remote command corresponding to
			// this channel
			int exitStatus = channelExec.getExitStatus();

			// Safely disconnect channel and disconnect session. If not done
			// then it may cause resource leak
			channelExec.disconnect();
			session.disconnect();

			if (exitStatus < 0) {
				// System.out.println("Done, but exit status not set!");
			} else if (exitStatus > 0) {
				// System.out.println("Done, but with error!");
			} else {
				// System.out.println("Done!");
			}
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void stopMhvtlService() {
		try {
			/**
			 * Create a new Jsch object This object will execute shell commands
			 * or scripts on server
			 */
			JSch jsch = new JSch();

			/*
			 * Open a new session, with your username, host and port Set the
			 * password and call connect. session.connect() opens a new
			 * connection to remote SSH server. Once the connection is
			 * established, you can initiate a new channel. this channel is
			 * needed to connect to remotely executing program
			 */
			Session session = jsch.getSession(conInfo.getUserName(),
					conInfo.getHost(), conInfo.getPort());
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(conInfo.getPassword());
			session.connect();

			// create the excution channel over the session
			ChannelExec channelExec = (ChannelExec) session.openChannel("exec");

			// Gets an InputStream for this channel. All data arriving in as
			// messages from the remote side can be read from this
			// stream.
			InputStream in = channelExec.getInputStream();

			// Set the command that you want to execute
			// In our case its the remote shell script
			// channelExec.setCommand("sh "+scriptFileName);
			channelExec.setCommand("service mhvtl stop");

			// Execute the command
			channelExec.connect();

			/*
			 * // Read the output from the input stream we set above
			 * BufferedReader reader = new BufferedReader( new
			 * InputStreamReader(in)); String line;
			 * 
			 * // Read each line from the buffered reader and add it to result
			 * list // You can also simple print the result here while ((line =
			 * reader.readLine()) != null) { result.add(line); }
			 */
			StringBuilder sb = new StringBuilder();
			char[] buffer = new char[0x10000];
			Reader reader = new InputStreamReader(in, "UTF-8");
			int bytesRead = 0;
			do {
				if (in.available() > 0) {
					bytesRead = reader.read(buffer, 0, buffer.length);
					if (bytesRead > 0) {
						sb.append(buffer, 0, bytesRead);
					}
				}
			} while (bytesRead > 0 && in.available() > 0);
			reader.close();
			in.close();

			// retrieve the exit status of the remote command corresponding to
			// this channel
			int exitStatus = channelExec.getExitStatus();

			// Safely disconnect channel and disconnect session. If not done
			// then it may cause resource leak
			channelExec.disconnect();
			session.disconnect();

			if (exitStatus < 0) {
				// System.out.println("Done, but exit status not set!");
			} else if (exitStatus > 0) {
				// System.out.println("Done, but with error!");
			} else {
				// System.out.println("Done!");
			}
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private List<ScsiDeviceInfo> getLibraryDriveScsiInfo() {
		List<ScsiDeviceInfo> deviceList = new ArrayList<>();
		try {
			/**
			 * Create a new Jsch object This object will execute shell commands
			 * or scripts on server
			 */
			JSch jsch = new JSch();

			/*
			 * Open a new session, with your username, host and port Set the
			 * password and call connect. session.connect() opens a new
			 * connection to remote SSH server. Once the connection is
			 * established, you can initiate a new channel. this channel is
			 * needed to connect to remotely executing program
			 */
			Session session = jsch.getSession(conInfo.getUserName(),
					conInfo.getHost(), conInfo.getPort());
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(conInfo.getPassword());
			session.connect();

			// create the excution channel over the session
			ChannelExec channelExec = (ChannelExec) session.openChannel("exec");

			// Gets an InputStream for this channel. All data arriving in as
			// messages from the remote side can be read from this
			// stream.
			InputStream in = channelExec.getInputStream();

			// Set the command that you want to execute
			// In our case its the remote shell script
			// channelExec.setCommand("sh "+scriptFileName);
			channelExec.setCommand("lsscsi -g");

			// Execute the command
			channelExec.connect();

			/*
			 * // Read the output from the input stream we set above
			 * BufferedReader reader = new BufferedReader( new
			 * InputStreamReader(in)); String line;
			 * 
			 * // Read each line from the buffered reader and add it to result
			 * list // You can also simple print the result here while ((line =
			 * reader.readLine()) != null) { result.add(line); }
			 */
			StringBuilder sb = new StringBuilder();
			char[] buffer = new char[0x10000];
			Reader reader = new InputStreamReader(in, "UTF-8");
			int bytesRead = 0;
			do {
				if (in.available() > 0) {
					bytesRead = reader.read(buffer, 0, buffer.length);
					if (bytesRead > 0) {
						sb.append(buffer, 0, bytesRead);
					}
				}
			} while (bytesRead > 0 && in.available() > 0);
			reader.close();
			in.close();

			String output = sb.toString();

			String[] devices = output.split("\n");
			// logic is to traverse from last listed device till we find
			// library info.
			// assumption: lastest lib creation gets listed last with
			// library info first followed by drive info for each drive
			for (int i = devices.length - 1; i >= 0; i--) {
				ScsiDeviceInfo deviceInfo = new ScsiDeviceInfo();

				String deviceStr = devices[i];
				if (deviceStr.contains(AppConstants.LIBRARY_SCSI_TYPE)) {
					deviceInfo.setDeviceType(AppConstants.LIBRARY_SCSI_TYPE);
					deviceInfo.setDeviceFilePath(deviceStr.substring(
							deviceStr.lastIndexOf("/dev")).trim());
					break;
				} else if (deviceStr.contains(AppConstants.DRIVE_SCSI_TYPE)) {
					deviceInfo.setDeviceType(AppConstants.DRIVE_SCSI_TYPE);
					deviceInfo.setDeviceFilePath(deviceStr.substring(
							deviceStr.lastIndexOf("/dev")).trim());
				}
				deviceList.add(deviceInfo);
			}

			// retrieve the exit status of the remote command corresponding to
			// this channel
			int exitStatus = channelExec.getExitStatus();

			// Safely disconnect channel and disconnect session. If not done
			// then it may cause resource leak
			channelExec.disconnect();
			session.disconnect();

			if (exitStatus < 0) {
				// System.out.println("Done, but exit status not set!");
			} else if (exitStatus > 0) {
				// System.out.println("Done, but with error!");
			} else {
				// System.out.println("Done!");
			}
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deviceList;
	}

	private void copyFile(String sourceFile, String destFile) {
		try {
			/**
			 * Create a new Jsch object This object will execute shell commands
			 * or scripts on server
			 */
			JSch jsch = new JSch();

			/*
			 * Open a new session, with your username, host and port Set the
			 * password and call connect. session.connect() opens a new
			 * connection to remote SSH server. Once the connection is
			 * established, you can initiate a new channel. this channel is
			 * needed to connect to remotely executing program
			 */
			Session session = jsch.getSession(conInfo.getUserName(),
					conInfo.getHost(), conInfo.getPort());
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(conInfo.getPassword());
			session.connect();

			// create the excution channel over the session
			ChannelExec channelExec = (ChannelExec) session.openChannel("exec");

			// Gets an InputStream for this channel. All data arriving in as
			// messages from the remote side can be read from this
			// stream.
			InputStream in = channelExec.getInputStream();

			// Set the command that you want to execute
			// In our case its the remote shell script
			// channelExec.setCommand("sh "+scriptFileName);
			channelExec.setCommand(" /bin/cp -rf " + sourceFile + " "
					+ destFile);

			// Execute the command
			channelExec.connect();

			/*
			 * // Read the output from the input stream we set above
			 * BufferedReader reader = new BufferedReader( new
			 * InputStreamReader(in)); String line;
			 * 
			 * // Read each line from the buffered reader and add it to result
			 * list // You can also simple print the result here while ((line =
			 * reader.readLine()) != null) { result.add(line); }
			 */
			StringBuilder sb = new StringBuilder();
			char[] buffer = new char[0x10000];
			Reader reader = new InputStreamReader(in, "UTF-8");
			int bytesRead = 0;
			do {
				if (in.available() > 0) {
					bytesRead = reader.read(buffer, 0, buffer.length);
					if (bytesRead > 0) {
						sb.append(buffer, 0, bytesRead);
					}
				}
			} while (bytesRead > 0 && in.available() > 0);
			reader.close();
			in.close();

			// retrieve the exit status of the remote command corresponding to
			// this channel
			int exitStatus = channelExec.getExitStatus();

			// Safely disconnect channel and disconnect session. If not done
			// then it may cause resource leak
			channelExec.disconnect();
			session.disconnect();

			if (exitStatus < 0) {
				// System.out.println("Done, but exit status not set!");
			} else if (exitStatus > 0) {
				// System.out.println("Done, but with error!");
			} else {
				// System.out.println("Done!");
			}
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteVtl() {
		// TODO Auto-generated method stub

	}

	public void listVtl() {
		// TODO Auto-generated method stub

	}

	public void listTape() {
		// TODO Auto-generated method stub

	}

	public void deleteTape() {
		// TODO Auto-generated method stub

	}

	public void loadtape() {
		// TODO Auto-generated method stub

	}

	public void unloadTape() {
		// TODO Auto-generated method stub

	}

	public void barcodeTape() {
		// TODO Auto-generated method stub

	}

	private String readRemoteFile(String fileParentDir, String fileName) {
		JSch jsch = new JSch();
		Session session = null;
		StringBuilder sb = new StringBuilder();
		try {
			session = jsch.getSession(conInfo.getUserName(), conInfo.getHost());
			session.setPort(conInfo.getPort());
			session.setPassword(conInfo.getPassword());
			Properties properties = new Properties();
			properties.put("StrictHostKeyChecking", "no");
			session.setConfig(properties);
			session.connect();
			Channel channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp sftpChannel = (ChannelSftp) channel;
			sftpChannel.cd(fileParentDir);
			InputStream inputStream = sftpChannel.get(fileName);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream, "UTF-8"));

			String line = "";
			try {
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			reader.close();
			inputStream.close();
			sftpChannel.exit();
			channel.disconnect();
			session.disconnect();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return sb.toString();
	}

	private void writeRemoteFile(String strContent, String fileParentDir,
			String fileName) {
		JSch jsch = new JSch();
		Session session = null;
		try {
			session = jsch.getSession(conInfo.getUserName(), conInfo.getHost());
			session.setPort(conInfo.getPort());
			session.setPassword(conInfo.getPassword());
			Properties properties = new Properties();
			properties.put("StrictHostKeyChecking", "no");
			session.setConfig(properties);
			session.connect();
			Channel channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp sftpChannel = (ChannelSftp) channel;
			sftpChannel.cd(fileParentDir);
			InputStream inputStream = new ByteArrayInputStream(
					strContent.getBytes());
			sftpChannel.put(inputStream, fileParentDir + "/" + fileName);
			sftpChannel.exit();
			inputStream.close();
			channel.disconnect();
			session.disconnect();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public List<Tape> createTapes(TapeDto tapeDto, Vtl vtl, MediaType mediaType) {

		return addSlotsToLibrary(vtl, tapeDto.getNoOfTapes(), mediaType);
		
	}

	private List<Tape> addSlotsToLibrary(Vtl vtl, int count, MediaType mediaType) {
		
		List<Tape> createdTapes = new ArrayList<>();
		
		String libraryFileName = LIBRARY_CONTENTS_FILE.replace(
				LIB_ID_PLACE_HOLDER, "" + vtl.getVtlLibId());

		String libFileContent = readRemoteFile(MHVTL_ROOT, libraryFileName);
		int slotId = getLastSlotId(libFileContent) + 1;

		for(int i = 1; i <=count; i++) {
			String barcode = barcodeGenerator.getTapeBarcode(mediaType);
			
			String newSlot = LIB_CONTENT_SLOT.replace(SLOT_NO, "" + slotId)
					.replace(BARCODE_PLACE_HOLDER, barcode);
			
			libFileContent = libFileContent.concat(newSlot);
			
			Slot slot = new Slot();
			slot.setSlotName("" + slotId);
			slot.setVtl(vtl);
			slot.setIsEmpty((byte) 0);
			
			Tape tapeData = new Tape();

			tapeData.setBarcode(barcode);
			tapeData.setIsLoaded((byte) 0);
			tapeData.setLabel(barcode);
			tapeData.setMediaType(mediaType);
			tapeData.setSizeMB(mediaType.getRawCapacityMB());
			tapeData.setSlot(slot);
			tapeData.setStatus(AppConstants.TAPE_STATUS_IN_LIBRARY);
			tapeData.setVtl(vtl);
			createdTapes.add(tapeData);
			
			slotId++;
		}
		
		stopMhvtlService();
		writeRemoteFile(libFileContent, MHVTL_ROOT, libraryFileName);
		startMhvtlService();

		return createdTapes;
	}

	private int getLastSlotId(String fileData) {
		String slotSubStr = fileData.substring(fileData
				.lastIndexOf(AppConstants.LIB_SLOT)
				+ AppConstants.LIB_SLOT.length());
		String slotIdStr = slotSubStr.substring(0, slotSubStr.indexOf(":"))
				.trim();
		return Integer.parseInt(slotIdStr);
	}

	/*
	 * public static void main(String[] args) { VtlDto dto = new VtlDto();
	 * dto.setCompressionEnabled(true); dto.setCompressionFactor(1);
	 * dto.setDriveModel("SDX-700C"); dto.setDriveVendor("Sony");
	 * dto.setLibraryBrand("Sony"); dto.setLibraryModel("LIB-152");
	 * dto.setLibraryName("myVTL"); dto.setMediaCapacityMB(500);
	 * dto.setMediaType("AIT3"); dto.setNoOfDrives(4); dto.setNoOfEmptySlots(3);
	 * dto.setNoOfMaps(4); dto.setNoOfPickers(1); dto.setNoOfSlots(10);
	 * dto.setVaultId(1); new VTLImpl(new RemoteConnectionInfo("root", "asdf",
	 * "172.17.222.38", 22)).createVtl(dto); }
	 */
	/*
	 * public static void main(String[] args) { System.out.println(new
	 * VTLImpl().getLibraryDriveScsiInfo()); }
	 */
}
