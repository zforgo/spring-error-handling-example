package io.github.zforgo.spring.example;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
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
}
