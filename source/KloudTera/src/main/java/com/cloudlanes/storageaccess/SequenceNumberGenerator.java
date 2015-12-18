package com.cloudlanes.storageaccess;

import java.util.concurrent.atomic.AtomicInteger;

public class SequenceNumberGenerator {
	private AtomicInteger seq;
	private int maxLimit;
	
	
	public SequenceNumberGenerator(int initialValue, int maxLimit) {
		seq = new AtomicInteger(initialValue);
		this.maxLimit = maxLimit;
	}
	
	public int getNextId() {
		int id = seq.getAndIncrement();
		if(id > maxLimit)
			throw new RuntimeException("Sequence Generator exceeded max limit: " + maxLimit);
		return id;
	}
	
	public int getCurrentId() {
		return seq.get();
	}
}
