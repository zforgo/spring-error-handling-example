package io.github.zforgo.spring.example;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class Parent implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank
	private String name;
	@Email
	private String email;

	@Valid
	private Address address;

	private List<@Email String> otherEmails;

	@NotNull
	private Boolean booleanField;
	@NotEmpty
	private List<@Valid Child> children;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<String> getOtherEmails() {
		return otherEmails;
	}

	public void setOtherEmails(List<String> otherEmails) {
		this.otherEmails = otherEmails;
	}

	public Boolean getBooleanField() {
		return booleanField;
	}

	public void setBooleanField(Boolean booleanField) {
		this.booleanField = booleanField;
	}

	public List<Child> getChildren() {
		return children;
	}

	public void setChildren(List<Child> children) {
		this.children = children;
	}
}
