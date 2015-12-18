package com.cloudlanes.db.entities;
// default package
// Generated 4 Dec, 2015 5:58:08 PM by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Drive generated by hbm2java
 */
@Entity
@Table(name = "drive", catalog = "cloudlanes")
public class Drive implements java.io.Serializable {

	private int driveId;
	private DriveVendor driveVendor;
	private Vtl vtl;
	private DriveModel driveModel;
	private String driveName;
	private Byte isEmpty;
	private Set<Tape> tapes = new HashSet<Tape>();
	private String scsiDevFilePath;

	public Drive() {
	}

	public Drive(int driveId) {
		this.driveId = driveId;
	}

	public Drive(int driveId, DriveVendor driveVendor, Vtl vtl,
			DriveModel driveModel, String driveName, Byte isEmpty, Set<Tape> tapes) {
		this.driveId = driveId;
		this.driveVendor = driveVendor;
		this.vtl = vtl;
		this.driveModel = driveModel;
		this.driveName = driveName;
		this.isEmpty = isEmpty;
		this.tapes = tapes;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "drive_id", unique = true, nullable = false)
	public int getDriveId() {
		return this.driveId;
	}

	public void setDriveId(int driveId) {
		this.driveId = driveId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vendor_id")
	public DriveVendor getDriveVendor() {
		return this.driveVendor;
	}

	public void setDriveVendor(DriveVendor driveVendor) {
		this.driveVendor = driveVendor;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vtl_id")
	public Vtl getVtl() {
		return this.vtl;
	}

	public void setVtl(Vtl vtl) {
		this.vtl = vtl;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "model_id")
	public DriveModel getDriveModel() {
		return this.driveModel;
	}

	public void setDriveModel(DriveModel driveModel) {
		this.driveModel = driveModel;
	}

	@Column(name = "drive_name", length = 50)
	public String getDriveName() {
		return this.driveName;
	}

	public void setDriveName(String driveName) {
		this.driveName = driveName;
	}

	@Column(name = "is_empty")
	public Byte getIsEmpty() {
		return this.isEmpty;
	}

	public void setIsEmpty(Byte isEmpty) {
		this.isEmpty = isEmpty;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "drive", cascade = CascadeType.ALL)
	public Set<Tape> getTapes() {
		return this.tapes;
	}

	public void setTapes(Set<Tape> tapes) {
		this.tapes = tapes;
	}

	@Column(name = "scsi_dev_file_path")
	public String getScsiDevFilePath() {
		return scsiDevFilePath;
	}

	public void setScsiDevFilePath(String scsiDevFilePath) {
		this.scsiDevFilePath = scsiDevFilePath;
	}
	
	
}