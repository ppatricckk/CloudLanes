package com.cloudlanes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudlanes.db.alertdao.AlertRecommendationDAO;
import com.cloudlanes.db.entities.AlertRecommendation;

@Service
public class AlertRecommendationServiceImpl implements
		AlertRecommendationService {

	@Autowired
	private AlertRecommendationDAO recommendationDAO;

	@Override
	@Transactional
	public boolean add(AlertRecommendation a) {
		return recommendationDAO.addRecommendation(a);
	}

	@Override
	@Transactional
	public boolean update(AlertRecommendation a) {
		return recommendationDAO.updateRecommendation(a);
	}

	@Override
	@Transactional
	public boolean delete(AlertRecommendation a) {
		return recommendationDAO.removeRecommendation(a);
	}

	@Override
	@Transactional
	public AlertRecommendation getRecommendation(Double code) {
		return recommendationDAO.getRecommendation(code);
	}

}
