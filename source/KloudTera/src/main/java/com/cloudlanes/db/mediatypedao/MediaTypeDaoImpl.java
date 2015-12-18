package com.cloudlanes.db.mediatypedao;

import org.springframework.stereotype.Repository;

import com.cloudlanes.db.dao.GenericDaoImpl;
import com.cloudlanes.db.entities.MediaType;

@Repository
public class MediaTypeDaoImpl  extends GenericDaoImpl<MediaType, Integer> implements MediaTypeDao {
	
	public MediaTypeDaoImpl() {
			daoType = MediaType.class;
	}
}
