package io.github.zforgo.spring.example;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty
	private String country;
	@NotEmpty
	private String city;
	@Min(1000)
	@Max(9999)
	private Integer zipCode;

}
