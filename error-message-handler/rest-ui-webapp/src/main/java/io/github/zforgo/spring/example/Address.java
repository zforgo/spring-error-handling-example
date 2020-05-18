package io.github.zforgo.spring.example;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty
	private String country;
	@NotEmpty
	private String city;
	@Min(1000)
	@Max(9999)
	private Integer zipCode;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}
}
