package com.cloudlanes.service;

import java.util.List;

import com.cloudlanes.db.entities.Alert;

public interface AlertService {
	public boolean add(Alert a);
	public boolean update(Alert a);
	public boolean delete(Alert a);
	public List<Alert> listAlert();
}
