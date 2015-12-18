package com.cloudlanes.db.entities;
// default package
// Generated 4 Dec, 2015 5:58:08 PM by Hibernate Tools 4.0.0

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Tape generated by hbm2java
 */
@Entity
@Table(name = "tape", catalog = "cloudlanes")
public class Tape implements java.io.Serializable {

	private int tapeId;
	private Slot slot;
	private MediaType mediaType;
	private Vtl vtl;
	private Drive drive;
	private String barcode;
	private String label;
	private String status;
	private Byte isLoaded;
	private Long candidateTs;
	private Long vaultTs;
	private int sizeMB;

	public Tape() {
	}

	public Tape(int tapeId) {
		this.tapeId = tapeId;
	}

	public Tape(int tapeId, Slot slot, MediaType mediaType, Vtl vtl,
			Drive drive, String barcode, String label,
			String status, Byte isLoaded, Long candidateTs, Long vaultTs) {
		this.tapeId = tapeId;
		this.slot = slot;
		this.mediaType = mediaType;
		this.vtl = vtl;
		this.drive = drive;
		this.barcode = barcode;
		this.label = label;
		this.status = status;
		this.isLoaded = isLoaded;
		this.candidateTs = candidateTs;
		this.vaultTs = vaultTs;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "tape_id", unique = true, nullable = false)
	public int getTapeId() {
		return this.tapeId;
	}

	public void setTapeId(int tapeId) {
		this.tapeId = tapeId;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "slot_id")
	public Slot getSlot() {
		return this.slot;
	}

	public void setSlot(Slot slot) {
		this.slot = slot;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type_id")
	public MediaType getMediaType() {
		return this.mediaType;
	}

	public void setMediaType(MediaType mediaType) {
		this.mediaType = mediaType;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vtl_id")
	public Vtl getVtl() {
		return this.vtl;
	}

	public void setVtl(Vtl vtl) {
		this.vtl = vtl;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "drive_id")
	public Drive getDrive() {
		return this.drive;
	}

	public void setDrive(Drive drive) {
		this.drive = drive;
	}

	@Column(name = "barcode", length = 45)
	public String getBarcode() {
		return this.barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	@Column(name = "label", length = 45)
	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Column(name = "status", length = 45)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "is_loaded")
	public Byte getIsLoaded() {
		return this.isLoaded;
	}

	public void setIsLoaded(Byte isLoaded) {
		this.isLoaded = isLoaded;
	}

	@Column(name = "candidate_ts")
	public Long getCandidateTs() {
		return this.candidateTs;
	}

	public void setCandidateTs(Long candidateTs) {
		this.candidateTs = candidateTs;
	}

	@Column(name = "vault_ts")
	public Long getVaultTs() {
		return this.vaultTs;
	}

	public void setVaultTs(Long vaultTs) {
		this.vaultTs = vaultTs;
	}
	
	@Column(name = "size_mb")
	public int getSizeMB() {
		return sizeMB;
	}

	public void setSizeMB(int sizeMB) {
		this.sizeMB = sizeMB;
	}
}