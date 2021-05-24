package com.revature.models;

import java.sql.Blob;
import java.time.LocalDate;

public class Reimb {
    
    private int id;
    private float reimb_amount;
    private LocalDate reimb_submitted;
    private LocalDate reimb_resolved;
    private String reimb_desc;
    private String status;
    private String type;
	private Blob reimb_receipt;
    // foreign keys
    private int reimb_author;
    private int reimb_resolver;
    private int reimb_status;
    private int reimb_type;
    
    
	public Reimb(int id, float reimb_amount, LocalDate reimb_submitted, LocalDate reimb_resolved, String reimb_desc, int reimb_author, int reimb_resolver, int reimb_status, int reimb_type) {
		super();
		this.id = id;
		this.reimb_amount = reimb_amount;
		this.reimb_submitted = reimb_submitted;
		this.reimb_resolved = reimb_resolved;
		this.reimb_desc = reimb_desc;
		this.reimb_author = reimb_author;
		this.reimb_resolver = reimb_resolver;
		this.reimb_status = reimb_status;
		this.reimb_type = reimb_type;
	}
	

	public Reimb(int id, float reimb_amount, LocalDate reimb_submitted, LocalDate reimb_resolved, String reimb_desc,
			String status, String type, Blob reimb_receipt, int reimb_author, int reimb_resolver, int reimb_status,
			int reimb_type) {
		super();
		this.id = id;
		this.reimb_amount = reimb_amount;
		this.reimb_submitted = reimb_submitted;
		this.reimb_resolved = reimb_resolved;
		this.reimb_desc = reimb_desc;
		this.status = status;
		this.type = type;
		this.reimb_receipt = reimb_receipt;
		this.reimb_author = reimb_author;
		this.reimb_resolver = reimb_resolver;
		this.reimb_status = reimb_status;
		this.reimb_type = reimb_type;
	}


	public Reimb(int id, float reimb_amount, LocalDate reimb_submitted, LocalDate reimb_resolved, String reimb_desc,
			String status, Blob reimb_receipt, int reimb_author, int reimb_resolver, int reimb_status, int reimb_type) {
		super();
		this.id = id;
		this.reimb_amount = reimb_amount;
		this.reimb_submitted = reimb_submitted;
		this.reimb_resolved = reimb_resolved;
		this.reimb_desc = reimb_desc;
		this.status = status;
		this.reimb_receipt = reimb_receipt;
		this.reimb_author = reimb_author;
		this.reimb_resolver = reimb_resolver;
		this.reimb_status = reimb_status;
		this.reimb_type = reimb_type;
	}


	public Reimb() {
		super();
	}

	public int getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getReimb_amount() {
		return reimb_amount;
	}

	public void setReimb_amount(float reimb_amount) {
		this.reimb_amount = reimb_amount;
	}

	public LocalDate getReimb_submitted() {
		return reimb_submitted;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public void setReimb_submitted(LocalDate reimb_submitted) {
		this.reimb_submitted = reimb_submitted;
	}

	public LocalDate getReimb_resolved() {
		return reimb_resolved;
	}

	public void setReimb_resolved(LocalDate reimb_resolved) {
		this.reimb_resolved = reimb_resolved;
	}

	public String getReimb_desc() {
		return reimb_desc;
	}

	public void setReimb_desc(String reimb_desc) {
		this.reimb_desc = reimb_desc;
	}

	public Blob getReimb_receipt() {
		return reimb_receipt;
	}

	public void setReimb_receipt(Blob reimb_receipt) {
		this.reimb_receipt = reimb_receipt;
	}

	public int getReimb_author() {
		return reimb_author;
	}

	public void setReimb_author(int reimb_author) {
		this.reimb_author = reimb_author;
	}

	public int getReimb_resolver() {
		return reimb_resolver;
	}

	public void setReimb_resolver(int reimb_resolver) {
		this.reimb_resolver = reimb_resolver;
	}

	public int getReimb_status() {
		return reimb_status;
	}

	public void setReimb_status(int reimb_status) {
		this.reimb_status = reimb_status;
	}

	public int getReimb_type() {
		return reimb_type;
	}

	public void setReimb_type(int reimb_type) {
		this.reimb_type = reimb_type;
	}

	@Override
	public String toString() {
		return "Reimb [id=" + id + ", reimb_amount=" + reimb_amount + ", reimb_submitted=" + reimb_submitted
				+ ", reimb_resolved=" + reimb_resolved + ", reimb_desc=" + reimb_desc + ", status=" + status
				+ ", reimb_receipt=" + reimb_receipt + ", reimb_author=" + reimb_author + ", reimb_resolver="
				+ reimb_resolver + ", reimb_status=" + reimb_status + ", reimb_type=" + reimb_type + "]";
	}
}
