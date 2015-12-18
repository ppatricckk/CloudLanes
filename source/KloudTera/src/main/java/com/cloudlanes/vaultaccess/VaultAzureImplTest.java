package com.cloudlanes.vaultaccess;

import junit.framework.TestCase;

import org.junit.Test;

public class VaultAzureImplTest extends TestCase {
	@Test
	public void testUploadtape() {
		VaultAzureImpl v1=new VaultAzureImpl();
		v1.uploadTape();
	}

}
