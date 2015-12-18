package com.cloudlanes.db.appsettingsdao;

import org.springframework.stereotype.Repository;


import com.cloudlanes.db.dao.GenericDaoImpl;
import com.cloudlanes.db.entities.AppSettings;

@Repository
public class AppSettingsDaoImpl  extends GenericDaoImpl<AppSettings, Integer> implements AppSettingsDao  {

	public AppSettingsDaoImpl() {
		daoType = AppSettings.class;
	}
}
