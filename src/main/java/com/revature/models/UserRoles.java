package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userroles")
public class UserRoles {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // this will make our PK serial
	@Column(name = "role_id")
	private int roleID;

	@Column(name = "role_title", nullable = false)
	private String roles;

	public UserRoles() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRoles(int roleID, String roles) {
		super();
		this.roleID = roleID;
		this.roles = roles;
	}

	public UserRoles(String roles) {
		super();
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "UserRoles [roleID=" + roleID + ", roles=" + roles + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + roleID;
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
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
		UserRoles other = (UserRoles) obj;
		if (roleID != other.roleID)
			return false;
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		return true;
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	
}
