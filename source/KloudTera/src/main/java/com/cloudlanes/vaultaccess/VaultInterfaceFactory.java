package com.cloudlanes.vaultaccess;

public class VaultInterfaceFactory {
	public VaultInterface getVaultInterface(String vaultType){

		if(vaultType == null){
			return null;
		}		
		if(vaultType.equalsIgnoreCase("Amazon")){
			return new VaultAmazonImpl();

		} else if(vaultType.equalsIgnoreCase("Azure")){
			return new VaultAzureImpl();
		}
		return null;

	}
}
