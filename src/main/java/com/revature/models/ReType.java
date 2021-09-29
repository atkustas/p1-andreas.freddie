package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "re_type")
public class ReType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "re_type_id")
	private int re_type_id;
	
	@Column(name = "re_type", nullable = false)
	private String re_type;

	//constructors
	public ReType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReType(int re_type_id, String re_type) {
		super();
		this.re_type_id = re_type_id;
		this.re_type = re_type;
	}
	
	public ReType(String re_type) {
		super();
		this.re_type = re_type;
	}

	@Override
	public String toString() {
		return "ReType [re_type_id=" + re_type_id + ", re_type=" + re_type + "]";
	}

	//getters & setters
	public int getRe_type_id() {
		return re_type_id;
	}

	public void setRe_type_id(int re_type_id) {
		this.re_type_id = re_type_id;
	}

	public String getRe_type() {
		return re_type;
	}

	public void setRe_type(String re_type) {
		this.re_type = re_type;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((re_type == null) ? 0 : re_type.hashCode());
		result = prime * result + re_type_id;
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
		ReType other = (ReType) obj;
		if (re_type == null) {
			if (other.re_type != null)
				return false;
		} else if (!re_type.equals(other.re_type))
			return false;
		if (re_type_id != other.re_type_id)
			return false;
		return true;
	}


	

}
