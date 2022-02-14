package com.eshop.repository;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="Accounts")
public class Account {
	@Id
	@NotBlank(message = "This field cannot be left blank")
	private String username;
	@NotBlank(message = "This field cannot be left blank")
	private String password;
	private String fullname, mobile, email,address;
	private String photo = "new.jpg";
	private boolean activated;
	@OneToMany(mappedBy = "account")
	private List<Order> orders;
	
	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	private List<Authority> authorities;
	
	public boolean hasRole(Role role) {
		if(this.authorities != null) {
			return this.authorities.stream()
					.anyMatch(a -> a.getRole().getId().equals(role.getId()));
		}
		return false;
	}
}
