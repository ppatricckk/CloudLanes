package com.cloudlanes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudlanes.db.appsettingsdao.AppSettingsDao;
import com.cloudlanes.db.entities.AppSettings;
import com.cloudlanes.db.entities.MediaType;
import com.cloudlanes.db.entities.Tape;
import com.cloudlanes.db.entities.Vtl;
import com.cloudlanes.db.mediatypedao.MediaTypeDao;
import com.cloudlanes.db.tapedao.TapeDao;
import com.cloudlanes.rest.inobject.TapeDto;
import com.cloudlanes.rest.outobject.TapeStatusDto;
import com.cloudlanes.storageaccess.StorageInterface;
import com.cloudlanes.storageaccess.TapeBarcodeGenerator;
import com.cloudlanes.storageaccess.VTLImpl;
import com.cloudlanes.utils.AppConstants;

@Service
public class TapeServiceImpl implements TapeService {

	@Autowired
	private VtlService vtlService;

	@Autowired
	private TapeDao tapeDao;

	@Autowired
	private AppSettingsDao appSettingsDao;

	@Autowired
	private MediaTypeDao mediaTypeDao;

	@Autowired
	private StorageInterface vtlInterface;

	@Override
	@Transactional
	public TapeStatusDto createTape(TapeDto tapeDto) {
		TapeStatusDto response = new TapeStatusDto();

		Vtl vtl = vtlService.getVtlByName(tapeDto.getLibraryName());
		if (vtl != null) {
			MediaType mediaType = mediaTypeDao.getByPrimaryKeyId(tapeDto
					.getMediaType().getMediaTypeId());

			AppSettings barcodeSetting = getAppSettings(AppConstants.DB_KEY_NEXT_BARCODE_ID);
			int nextBarcodeId = Integer.parseInt(barcodeSetting
					.getSettingValue());

			TapeBarcodeGenerator barcodeGen = new TapeBarcodeGenerator(
					nextBarcodeId);
			((VTLImpl) vtlInterface).setBarcodeGenerator(barcodeGen);

			List<Tape> createdTapes = vtlInterface.createTapes(tapeDto, vtl,
					mediaType);

			for (Tape tapeData : createdTapes) {
				tapeDao.add(tapeData);
			}

			updateAppSettings(AppConstants.DB_KEY_NEXT_BARCODE_ID,
					barcodeGen.getCurrentNumber() + "");

			boolean hasError = createdTapes.size() != tapeDto.getNoOfTapes();
			response.setHasError(hasError);
			response.setLibraryName(vtl.getVtlName());
			response.setMediaType(mediaType.getTypeName());
			response.setNoOfTapes(createdTapes.size());
			response.setStatusMessage(hasError ? "Tape creation failed"
					: "Successfully created " + createdTapes.size()
							+ " tapes in Library: " + vtl.getVtlName());
		}
		return response;
	}

	@Override
	@Transactional
	public List<Tape> getTapesForLibrary(int libraryId) {
		return tapeDao.getTapesForLibrary(libraryId);
	}

	@Override
	@Transactional
	public List<Tape> getAllTapes() {
		return tapeDao.getAll();
	}

	private AppSettings getAppSettings(String settingName) {
		return appSettingsDao.getFirstByMatchedColumnValue(AppSettings.class,
				"settingName", settingName);
	}

	private void updateAppSettings(String settingName, String settingValue) {
		AppSettings setting = getAppSettings(settingName);
		if (setting != null) {
			setting.setSettingValue(settingValue);
			appSettingsDao.update(setting);
		}
	}
}
