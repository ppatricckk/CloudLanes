package com.cloudlanes.service;

import java.util.List;

import com.cloudlanes.db.entities.Tape;
import com.cloudlanes.rest.inobject.TapeDto;
import com.cloudlanes.rest.outobject.TapeStatusDto;


public interface TapeService {
	public TapeStatusDto createTape(TapeDto tapeDto);
	
	public List<Tape> getTapesForLibrary(int libraryId);
	
	public List<Tape> getAllTapes();
}
