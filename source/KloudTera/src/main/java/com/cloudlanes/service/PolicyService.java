package com.cloudlanes.service;

import java.util.List;

import com.cloudlanes.db.entities.Policy;

public interface PolicyService {
	public boolean add(Policy p);
	public boolean update(Policy p);
	public boolean delete(Policy p);
	public List<Policy> listPolicy();
}
