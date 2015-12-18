package com.cloudlanes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudlanes.db.entities.Policy;
import com.cloudlanes.db.policydao.PolicyDao;
@Service
public class PolicyServiceImpl implements PolicyService {
	@Autowired
	private PolicyDao policydao;

	@Override
	@Transactional
	public boolean add(Policy p) {
		// TODO Auto-generated method stub
		return policydao.addPolicy(p);
	}

	@Override
	@Transactional
	public boolean update(Policy p) {
		// TODO Auto-generated method stub
		return policydao.updatePolicy(p);
	}

	@Override
	@Transactional
	public boolean delete(Policy p) {
		// TODO Auto-generated method stub
		return policydao.removePolicy(p);
	}

	@Override
	@Transactional
	public List<Policy> listPolicy() {
		// TODO Auto-generated method stub
		return policydao.listPolicy();
	}




}
