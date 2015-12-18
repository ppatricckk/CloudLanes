package com.cloudlanes.service;

import com.cloudlanes.db.entities.AlertRecommendation;

public interface AlertRecommendationService {

	public boolean add(AlertRecommendation a);

	public boolean update(AlertRecommendation a);

	public boolean delete(AlertRecommendation a);

	public AlertRecommendation getRecommendation(Double code);
}
