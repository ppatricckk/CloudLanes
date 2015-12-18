package com.cloudlanes.db.tapedao;

import java.util.List;

import com.cloudlanes.db.dao.GenericDao;
import com.cloudlanes.db.entities.Tape;

public interface TapeDao extends GenericDao<Tape, Integer> {
	public List<Tape> getTapesForLibrary(int libraryId); 
}
