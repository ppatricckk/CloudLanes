package com.cloudlanes.db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "alert_recommendation", catalog = "cloudlanes")
@XmlRootElement
public class AlertRecommendation {

	@XmlElement
	private Integer id;
	@XmlElement
	private Double code;
	@XmlElement
	private String recommendation;

	public AlertRecommendation() {

	}

	public AlertRecommendation(String recommendation, Double code) {
		super();
		this.code = code;
		this.recommendation = recommendation;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "err_code", nullable = false)
	public Double getCode() {
		return code;
	}

	public void setCode(Double code) {
		this.code = code;
	}

	@Column(name = "recommendation", nullable = false)
	public String getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}
}
