package com.cloudlanes.db.entities;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "alert", catalog = "cloudlanes")
@XmlRootElement
public class Alert {

	@XmlElement
	private int id;
	@XmlElement
	private String title;
	@XmlElement
	private String type;
	@XmlElement
	private String severity;
	@XmlElement
	private Timestamp time_stamp;
	@XmlElement
	private String detail_message;
	@XmlElement
	private AlertRecommendation recommendation;
	@XmlElement
	private String status;

	public Alert() {

	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "title", unique = false, nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "type", unique = false, nullable = false)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "severity", unique = false, nullable = false)
	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	@Column(name = "time_stamp", unique = false, nullable = true)
	public Timestamp getTime_stamp() {
		return time_stamp;
	}

	public void setTime_stamp(Timestamp time_stamp) {
		this.time_stamp = time_stamp;
	}

	@Column(name = "detail_message", unique = false, nullable = true)
	public String getDetail_message() {
		return detail_message;
	}

	public void setDetail_message(String detail_message) {
		this.detail_message = detail_message;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "err_code_id")
	public AlertRecommendation getRecommendation() {
		return recommendation;
	}

	public void setRecommendation(AlertRecommendation recommendation) {
		this.recommendation = recommendation;
	}

	@Column(name = "status", unique = false, nullable = true)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
