package com.flightReservation.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role extends AbstractEntity implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4614283940954824036L;

	private String name;

	@ManyToMany(mappedBy = "roles")
	private Set<User> users;

	public Role() {
	}
	
	public Role(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String getAuthority() {
		return name;
	}

/*	@Override
	public String toString() {
		return "Role [name=" + name + ", users=" + users + "]";
	}*/
	
	

}
