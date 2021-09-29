package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "re_status")
public class ReStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "re_status_id")
	private int re_status_id;
	
	@Column(name = "re_status", nullable = false)
	private String re_status;


	//constructors
	public ReStatus() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ReStatus(int re_status_id, String re_status) {
		super();
		this.re_status_id = re_status_id;
		this.re_status = re_status;
	}


	public ReStatus(String re_status) {
		super();
		this.re_status = re_status;
	}


	@Override
	public String toString() {
		return "ReStatus [re_status_id=" + re_status_id + ", re_status=" + re_status + "]";
	}

	//getters & setters
	public int getRe_status_id() {
		return re_status_id;
	}


	public void setRe_status_id(int re_status_id) {
		this.re_status_id = re_status_id;
	}


	public String getRe_status() {
		return re_status;
	}


	public void setRe_status(String re_status) {
		this.re_status = re_status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((re_status == null) ? 0 : re_status.hashCode());
		result = prime * result + re_status_id;
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
		ReStatus other = (ReStatus) obj;
		if (re_status == null) {
			if (other.re_status != null)
				return false;
		} else if (!re_status.equals(other.re_status))
			return false;
		if (re_status_id != other.re_status_id)
			return false;
		return true;
	}



	
	
	

}
