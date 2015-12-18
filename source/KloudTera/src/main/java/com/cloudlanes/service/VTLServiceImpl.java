package com.cloudlanes.service;

import static com.cloudlanes.utils.AppConstants.DB_KEY_NAA_NEXT_ID_1;
import static com.cloudlanes.utils.AppConstants.DB_KEY_NAA_NEXT_ID_2;
import static com.cloudlanes.utils.AppConstants.DB_KEY_NAA_NEXT_ID_3;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudlanes.db.appsettingsdao.AppSettingsDao;
import com.cloudlanes.db.drivemodeldao.DriveModelDao;
import com.cloudlanes.db.drivevendordao.DriveVendorDao;
import com.cloudlanes.db.entities.AppSettings;
import com.cloudlanes.db.entities.DriveModel;
import com.cloudlanes.db.entities.DriveVendor;
import com.cloudlanes.db.entities.MediaType;
import com.cloudlanes.db.entities.Vtl;
import com.cloudlanes.db.entities.VtlBrand;
import com.cloudlanes.db.entities.VtlModel;
import com.cloudlanes.db.mediatypedao.MediaTypeDao;
import com.cloudlanes.db.vtlbranddao.VtlBrandDao;
import com.cloudlanes.db.vtldao.VtlDao;
import com.cloudlanes.db.vtlmodeldao.VtlModelDao;
import com.cloudlanes.rest.inobject.VtlDto;
import com.cloudlanes.rest.outobject.VtlStatusDto;
import com.cloudlanes.storageaccess.NaaSequenceGenerator;
import com.cloudlanes.storageaccess.SequenceNumberGenerator;
import com.cloudlanes.storageaccess.StorageInterface;
import com.cloudlanes.storageaccess.TapeBarcodeGenerator;
import com.cloudlanes.storageaccess.VTLImpl;
import com.cloudlanes.utils.AppConstants;

@Service
public class VTLServiceImpl implements VtlService {

	@Autowired
	private VtlDao vtlDao;

	@Autowired
	private AppSettingsDao appSettingsDao;

	@Autowired
	private VtlBrandDao vtlBrandDao;

	@Autowired
	private VtlModelDao vtlModelDao;

	@Autowired
	private DriveVendorDao driveVendorDao;

	@Autowired
	private DriveModelDao driveModelDao;

	@Autowired
	private MediaTypeDao mediaTypeDao;

	@Autowired
	private StorageInterface vtlInterface;

	@Override
	@Transactional
	public VtlStatusDto createCtl(VtlDto dto) {
		VtlStatusDto response = new VtlStatusDto();

		AppSettings setting = getAppSettings(AppConstants.DB_KEY_NEXT_ID);
		AppSettings maxLimitSetting = getAppSettings(AppConstants.DB_KEY_ID_MAX_LIMIT);
		int maxLimit = 0;
		if (maxLimitSetting != null) {
			maxLimit = Integer.parseInt(maxLimitSetting.getSettingValue());
		}
		SequenceNumberGenerator seqIdGen = new SequenceNumberGenerator(
				Integer.parseInt(setting.getSettingValue()), maxLimit);

		setting = getAppSettings(DB_KEY_NAA_NEXT_ID_1);
		int naaId1 = Integer.parseInt(setting.getSettingValue());
		setting = getAppSettings(DB_KEY_NAA_NEXT_ID_2);
		int naaId2 = Integer.parseInt(setting.getSettingValue());
		setting = getAppSettings(DB_KEY_NAA_NEXT_ID_3);
		int naaId3 = Integer.parseInt(setting.getSettingValue());

		NaaSequenceGenerator naaIdGen = new NaaSequenceGenerator(naaId1,
				naaId2, naaId3);
		
		AppSettings barcodeSetting = getAppSettings(AppConstants.DB_KEY_NEXT_BARCODE_ID);
		int nextBarcodeId = Integer.parseInt(barcodeSetting.getSettingValue());
		
		TapeBarcodeGenerator barcodeGen = new TapeBarcodeGenerator(nextBarcodeId);
		
		((VTLImpl) vtlInterface).setSequenceIdGenerator(seqIdGen);
		((VTLImpl) vtlInterface).setNaaIdGenerator(naaIdGen);
		((VTLImpl) vtlInterface).setBarcodeGenerator(barcodeGen);

		VtlBrand vtlBrand = vtlBrandDao.getByPrimaryKeyId(dto.getVtlBrand().getBrandId());
		VtlModel vtlModel = vtlModelDao.getByPrimaryKeyId(dto.getVtlModel().getVtlModelId());
		DriveVendor driveVendor = driveVendorDao.getByPrimaryKeyId(dto.getDriveVendor().getDriveVendorId());
		DriveModel driveModel = driveModelDao.getByPrimaryKeyId(dto.getDriveModel().getDriveModelId());
		MediaType mediaType = mediaTypeDao.getByPrimaryKeyId(dto.getMediaType().getMediaTypeId());

		Vtl vtlData = vtlInterface.createVtl(dto, vtlBrand, vtlModel,
				driveVendor, driveModel, mediaType);

		vtlDao.add(vtlData);

		updateAppSettings(AppConstants.DB_KEY_NEXT_ID, seqIdGen.getCurrentId()
				+ "");
		updateAppSettings(AppConstants.DB_KEY_NAA_NEXT_ID_1,
				naaIdGen.getCurrentNaaId1() + "");
		updateAppSettings(AppConstants.DB_KEY_NAA_NEXT_ID_2,
				naaIdGen.getCurrentNaaId2() + "");
		updateAppSettings(AppConstants.DB_KEY_NAA_NEXT_ID_3,
				naaIdGen.getCurrentNaaId3() + "");
		updateAppSettings(AppConstants.DB_KEY_NEXT_BARCODE_ID, barcodeGen.getCurrentNumber()
				+ "");

		boolean hasError = vtlData.getSlots().size() != dto.getNoOfSlots();
		response.setHasError(hasError);
		response.setLibraryBrand(vtlData.getVtlName());
		response.setLibraryModel(vtlData.getVtlModel().getModelName());
		response.setLibraryName(vtlData.getVtlName());
		response.setNoOfDrives(vtlData.getDriveCount());
		response.setNoOfEmptySlots(vtlData.getEmptySlotCount());
		response.setNoOfMaps(vtlData.getIePortCount());
		response.setNoOfPickers(vtlData.getPickerCount());
		response.setNoOfSlots(vtlData.getSlotCount());

		String statusMessage = hasError ? "Failed to created VTL with name: "
				+ dto.getLibraryName() : "VTL with library Name: "
				+ vtlData.getVtlName() + " created successfully!";
		response.setStatusMessage(statusMessage);
		return response;
	}

	@Override
	@Transactional
	public List<VtlBrand> getVtlBrands() {
		return vtlDao.getVtlBrands();
	}

	@Override
	@Transactional
	public List<VtlModel> getSupportedVtlModels(int vtlBrandId) {
		return vtlDao.getSupportedVtlModels(vtlBrandId);
	}

	@Override
	@Transactional
	public List<DriveVendor> getSupportedDriveVendors(int vtlBrandId) {
		return vtlDao.getSupportedDriveVendors(vtlBrandId);
	}

	@Override
	@Transactional
	public List<DriveModel> getSupportedDriveModels(int vtlBrandId) {
		return vtlDao.getSupportedDriveModels(vtlBrandId);
	}

	@Override
	@Transactional
	public List<MediaType> getSupportedMediaTypes(int vtlBrandId) {
		return vtlDao.getSupportedMediaTypes(vtlBrandId);
	}

	@Override
	@Transactional
	public List<Vtl> getVtlList() {
		return vtlDao.getAll();
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

	@Override
	@Transactional
	public Vtl getVtlByName(String libraryName) {
		return vtlDao.getFirstByMatchedColumnValue(Vtl.class, "vtlName", libraryName);
	}
}
