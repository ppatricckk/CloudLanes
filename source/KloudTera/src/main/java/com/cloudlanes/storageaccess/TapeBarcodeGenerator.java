package com.cloudlanes.storageaccess;

import com.cloudlanes.db.entities.MediaType;


public class TapeBarcodeGenerator {
	private SequenceNumberGenerator numberGenerator;
	private static final int MAX_LIMIT = 99999;
	private static final int MAX_DIGITS = (MAX_LIMIT + "").length();
	
	public TapeBarcodeGenerator(int initialValue) {
		numberGenerator = new SequenceNumberGenerator(initialValue, MAX_LIMIT);
	}
	
	public String getTapeBarcode(MediaType mediaType) {
		int number = numberGenerator.getNextId();
		String barcode = addLeadingZeros(number, MAX_DIGITS);
		barcode = mediaType.getBarcodePrefix() + barcode + mediaType.getBarcodeSuffix();
		return barcode;
	}
	
	private String addLeadingZeros(int number, int totalDigits) {
		String numString = number + "";
		if(numString.length() < totalDigits) {
			int zeroCount = totalDigits - numString.length();
			for(int i = 1; i <= zeroCount; i++) {
				numString = "0" + numString;
			}
		}
		return numString;
	}
	
	public int getCurrentNumber() {
		return numberGenerator.getCurrentId();
	}
	/*public static void main(String[] args) {
		TapeBarcodeGenerator gen = new TapeBarcodeGenerator(1);
		
		MediaType mediaType = new MediaType();
		mediaType.setBarcodePrefix("E");
		mediaType.setBarcodeSuffix("L4");
		for(int i = 1; i <= 100000; i++) {
			System.out.println(gen.getTapeBarcode(mediaType));
		}
	}*/
}
