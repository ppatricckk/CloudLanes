package com.cloudlanes.storageaccess;

import static com.cloudlanes.utils.AppConstants.DB_KEY_NAA_NEXT_ID_1;
import static com.cloudlanes.utils.AppConstants.DB_KEY_NAA_NEXT_ID_2;
import static com.cloudlanes.utils.AppConstants.DB_KEY_NAA_NEXT_ID_3;
import static com.cloudlanes.utils.AppConstants.NAA_ID_FORMAT;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;

import com.cloudlanes.db.appsettingsdao.AppSettingsDao;
import com.cloudlanes.db.appsettingsdao.AppSettingsDaoImpl;
import com.cloudlanes.utils.AppConstants;

public class NaaSequenceGenerator {
	
	private AtomicInteger nextNaaId1;
	private AtomicInteger nextNaaId2;
	private AtomicInteger nextNaaId3;
	
	private int maxLimit = 99;
	
	@Autowired
	private AppSettingsDao appSettingsDao = new AppSettingsDaoImpl();
	
	public NaaSequenceGenerator(int id1, int id2, int id3) {
		nextNaaId1 = new AtomicInteger(id1);		
		nextNaaId2 = new AtomicInteger(id2);
		nextNaaId3 = new AtomicInteger(id3);
	}
	
	public String getNextNaaId(int libId) {
		int id1 = nextNaaId1.get();
		int id2 = nextNaaId2.get();
		int id3 = nextNaaId3.getAndIncrement();
		if(id3 > maxLimit) {
			id3 = 0;
			id2 = nextNaaId2.incrementAndGet();
			if(id2 > maxLimit) {
				id2 = 0;
				id1 = nextNaaId1.incrementAndGet();
				if(id1 > maxLimit) {
					throw new RuntimeException("NAA max Limit reached.");
				}
			}
		}
		String id1Str = id1 + "";
		if(id1Str.length() == 1) {
			id1Str = "0" + id1Str;
		}
		String id2Str = id2 + "";
		if(id2Str.length() == 1) {
			id2Str = "0" + id2Str;
		}
		String id3Str = id3 + "";
		if(id3Str.length() == 1) {
			id3Str = "0" + id3Str;
		}
		String naaId = NAA_ID_FORMAT.replace(AppConstants.LIB_ID_PLACE_HOLDER, libId + "");
		naaId = naaId.replace(DB_KEY_NAA_NEXT_ID_1, id1Str);
		naaId = naaId.replace(DB_KEY_NAA_NEXT_ID_2, id2Str);
		naaId = naaId.replace(DB_KEY_NAA_NEXT_ID_3, id3Str);
		
		return naaId;
	}
	
	public int getCurrentNaaId1() {
		return nextNaaId1.get();
	}
	
	public int getCurrentNaaId2() {
		return nextNaaId2.get();
	}
	
	public int getCurrentNaaId3() {
		return nextNaaId3.get();
	}
	
	/*public static void main(String[] args) {
		NaaSequenceGenerator gen = new NaaSequenceGenerator(0, 0, 0);
		for(int i = 1; i <=300; i++) {
			System.out.println(gen.getNextNaaId(1));
		}
	}*/
}
