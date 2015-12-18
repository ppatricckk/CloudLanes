package com.cloudlanes.storageaccess;

public class StorageInterfaceFactory {
	public StorageInterface getStorageInterface(String storageType){

		if(storageType == null){
			return null;
		}		
		if(storageType.equalsIgnoreCase("TESTVTL")){
			return new TestVTLImpl();

		} else if(storageType.equalsIgnoreCase("VTL")){
			return new VTLImpl();

		}
		return null;
	}

}
