package io.github.zforgo.spring.example;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class Child implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(min = 2, max = 10)
	@Email
	String key;
	@NotNull
	@Min(10) @Max(20)
	Integer value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
}