package com.cloudlanes.db.tapedao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cloudlanes.db.dao.GenericDaoImpl;
import com.cloudlanes.db.entities.Tape;

@Repository
public class TapeDaoImpl extends GenericDaoImpl<Tape, Integer> implements TapeDao {

	public TapeDaoImpl() {
		daoType = Tape.class;
	}

	@Override
	public List<Tape> getTapesForLibrary(int libraryId) {
		Query q = currentSession().createQuery("FROM Tape a WHERE a.vtl.vtlId = :id");
		q.setParameter("id", libraryId);
		@SuppressWarnings("unchecked")
		List<Tape> tapes = (List<Tape>) q.list();
		return tapes;
	} 
}
