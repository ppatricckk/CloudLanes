package com.cloudlanes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudlanes.db.alertdao.AlertDao;
import com.cloudlanes.db.entities.Alert;

@Service
public class AlertServiceImpl implements AlertService {

	@Autowired
	private AlertDao alertDao;

	@Override
	@Transactional
	public boolean add(Alert a) {
		return alertDao.addAlert(a);
	}

	@Override
	@Transactional
	public boolean update(Alert a) {
		return alertDao.updateAlert(a);
	}

	@Override
	@Transactional
	public boolean delete(Alert a) {
		return alertDao.removeAlert(a);
	}

	@Override
	@Transactional
	public List<Alert> listAlert() {
		return alertDao.listAlert();
	}

}
