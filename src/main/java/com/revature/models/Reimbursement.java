package com.revature.models;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reimbursements")
public class Reimbursement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "re_id")
	private int re_id;
	
	private int re_amount;
	private String re_submitted;
	private String re_resolved;
	private String re_desc;
	private byte[] re_receipt;
	private User re_author;
	private User re_resolver;
	private ReStatus re_status_id;
	private ReType re_type_id;
	
	//constructors
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}

	//all-args
	public Reimbursement(int re_id, int re_amount, String re_submitted, String re_resolved, String re_desc,
			byte[] re_receipt, User re_author, User re_resolver, ReStatus re_status_id, ReType re_type_id) {
		super();
		this.re_id = re_id;
		this.re_amount = re_amount;
		this.re_submitted = re_submitted;
		this.re_resolved = re_resolved;
		this.re_desc = re_desc;
		this.re_receipt = re_receipt;
		this.re_author = re_author;
		this.re_resolver = re_resolver;
		this.re_status_id = re_status_id;
		this.re_type_id = re_type_id;
	}

	//no id
	public Reimbursement(int re_amount, String re_submitted, String re_resolved, String re_desc, byte[] re_receipt,
			User re_author, User re_resolver, ReStatus re_status_id, ReType re_type_id) {
		super();
		this.re_amount = re_amount;
		this.re_submitted = re_submitted;
		this.re_resolved = re_resolved;
		this.re_desc = re_desc;
		this.re_receipt = re_receipt;
		this.re_author = re_author;
		this.re_resolver = re_resolver;
		this.re_status_id = re_status_id;
		this.re_type_id = re_type_id;
	}

	@Override
	public String toString() {
		return "Reimbursement [re_id=" + re_id + ", re_amount=" + re_amount + ", re_submitted=" + re_submitted
				+ ", re_resolved=" + re_resolved + ", re_desc=" + re_desc + ", re_receipt="
				+ Arrays.toString(re_receipt) + ", re_author=" + re_author + ", re_resolver=" + re_resolver
				+ ", re_status_id=" + re_status_id + ", re_type_id=" + re_type_id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + re_amount;
		result = prime * result + ((re_author == null) ? 0 : re_author.hashCode());
		result = prime * result + ((re_desc == null) ? 0 : re_desc.hashCode());
		result = prime * result + re_id;
		result = prime * result + Arrays.hashCode(re_receipt);
		result = prime * result + ((re_resolved == null) ? 0 : re_resolved.hashCode());
		result = prime * result + ((re_resolver == null) ? 0 : re_resolver.hashCode());
		result = prime * result + ((re_status_id == null) ? 0 : re_status_id.hashCode());
		result = prime * result + ((re_submitted == null) ? 0 : re_submitted.hashCode());
		result = prime * result + ((re_type_id == null) ? 0 : re_type_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (re_amount != other.re_amount)
			return false;
		if (re_author == null) {
			if (other.re_author != null)
				return false;
		} else if (!re_author.equals(other.re_author))
			return false;
		if (re_desc == null) {
			if (other.re_desc != null)
				return false;
		} else if (!re_desc.equals(other.re_desc))
			return false;
		if (re_id != other.re_id)
			return false;
		if (!Arrays.equals(re_receipt, other.re_receipt))
			return false;
		if (re_resolved == null) {
			if (other.re_resolved != null)
				return false;
		} else if (!re_resolved.equals(other.re_resolved))
			return false;
		if (re_resolver == null) {
			if (other.re_resolver != null)
				return false;
		} else if (!re_resolver.equals(other.re_resolver))
			return false;
		if (re_status_id == null) {
			if (other.re_status_id != null)
				return false;
		} else if (!re_status_id.equals(other.re_status_id))
			return false;
		if (re_submitted == null) {
			if (other.re_submitted != null)
				return false;
		} else if (!re_submitted.equals(other.re_submitted))
			return false;
		if (re_type_id == null) {
			if (other.re_type_id != null)
				return false;
		} else if (!re_type_id.equals(other.re_type_id))
			return false;
		return true;
	}
	
	

	
	

}
