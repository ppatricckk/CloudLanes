package com.cloudlanes.utils;

public class AppConstants {
	
	//App Settings DB Keys
	public static final String DB_KEY_NEXT_ID = "nextId";
	public static final String DB_KEY_ID_MAX_LIMIT = "maxIdLimit";
	public static final String DB_KEY_NAA_NEXT_ID_1 = "naaNextId1";
	public static final String DB_KEY_NAA_NEXT_ID_2 = "naaNextId2";
	public static final String DB_KEY_NAA_NEXT_ID_3 = "naaNextId3";
	public static final String DB_KEY_NEXT_BARCODE_ID = "nextBarcodeId";
	
	
	public static final String MHVTL_ROOT = "/etc/mhvtl";
	public static final String TAPE_HOME_DIR = "/opt/mhvtl";
	public static final String DRIVE_FIFO_DIR = "/var/tmp/mhvtl";
	
	public static final String CHANNEL_ID = "4";
	public static final String TARGET_ID = "00";

	public static final String DEVICE_CONF_FILE = "device.conf";
	public static final String DEVICE_CONF_BAK_FILE = "deviceconf.bak";

	public static final String LIB_ID_PLACE_HOLDER = "{lib_id}";
	public static final String CHANNEL_PLACE_HOLDER = "{channel_id}";
	public static final String TARGET_PLACE_HOLDER = "{target_id}";
	public static final String LUN_PLACE_HOLDER = "{lun}";
	public static final String VENDOR_PLACE_HOLDER = "{vendor_id}";
	public static final String PRODUCT_PLACE_HOLDER = "{product_id}";
	public static final String UNIT_SR_NO_PLACE_HOLDER = "{unit_srno}";
	public static final String NAA_PLACE_HOLDER = "{naa}";
	

	public static final String LIBRARY_TEMPLATE = "Library: "
			+ LIB_ID_PLACE_HOLDER + " CHANNEL: " + CHANNEL_PLACE_HOLDER
			+ " TARGET: " + TARGET_PLACE_HOLDER + " LUN: " + LUN_PLACE_HOLDER
			+ "\n Vendor identification: " + VENDOR_PLACE_HOLDER
			+ "\n Product identification: " + PRODUCT_PLACE_HOLDER
			+ "\n Unit serial number: " + UNIT_SR_NO_PLACE_HOLDER + "\n NAA: "
			+ NAA_PLACE_HOLDER + "\n Home directory: " + TAPE_HOME_DIR
			+ "/" + LIB_ID_PLACE_HOLDER;

	public static final String DRIVE_PLACE_HOLDER = "{drive_id}";
	public static final String DRIVE_SLOT_PLACE_HOLDER = "{slot_id}";
	public static final String DRIVE_COMPRESSION_FACTOR_PLACE_HOLDER = "{compression_factor}";
	public static final String DRIVE_COMPRESSION_ENABLED_PLACE_HOLDER = "{compression_enabled}";

	public static final String DRIVE_TEMPLATE = "Drive: " + DRIVE_PLACE_HOLDER
			+ " CHANNEL: " + CHANNEL_PLACE_HOLDER + " TARGET: "
			+ TARGET_PLACE_HOLDER + " LUN: " + LUN_PLACE_HOLDER
			+ "\n Library ID: " + LIB_ID_PLACE_HOLDER + " Slot: "
			+ DRIVE_SLOT_PLACE_HOLDER + "\n Vendor identification: "
			+ VENDOR_PLACE_HOLDER + "\n Product identification: "
			+ PRODUCT_PLACE_HOLDER + "\n Unit serial number: "
			+ UNIT_SR_NO_PLACE_HOLDER + "\n NAA: " + NAA_PLACE_HOLDER
			+ "\n Compression: factor "+DRIVE_COMPRESSION_FACTOR_PLACE_HOLDER+" enabled "+DRIVE_COMPRESSION_ENABLED_PLACE_HOLDER+"\n fifo: " + DRIVE_FIFO_DIR;

	public static final String BARCODE_PLACE_HOLDER = "{barcode}";
	public static final String DRIVE_NO = "{drive_no}";
	public static final String SLOT_NO = "{slot_no}";
	public static final String PICKER_NO = "{picker_no}";
	public static final String LIB_SLOT = "Slot ";

	public static final String LIB_CONTENT_DRIVE = "Drive " + DRIVE_NO
			+ ":\n";
	public static final String LIB_CONTENT_MAP = "MAP " + DRIVE_NO + ":\n";
	public static final String LIB_CONTENT_PICKER = "Picker " + PICKER_NO + ":\n";
	public static final String LIB_CONTENT_SLOT = LIB_SLOT + SLOT_NO + ": "
			+ BARCODE_PLACE_HOLDER + "\n";

	public static final String LIBRARY_CONTENTS_FILE = "library_contents."
			+ LIB_ID_PLACE_HOLDER;
	public static final String UNIT_SERIAL_ID_PREFIX = "CLDLNS_";
	public static final String NAA_ID_FORMAT = LIB_ID_PLACE_HOLDER + ":22:33:44:ab:"+DB_KEY_NAA_NEXT_ID_1+":"+DB_KEY_NAA_NEXT_ID_2+":"+DB_KEY_NAA_NEXT_ID_3;
	
	public static final String TAPE_STATUS_IN_LIBRARY = "In Library";
	public static final String LIBRARY_SCSI_TYPE = "mediumx";
	public static final String DRIVE_SCSI_TYPE = "tape";
	
}