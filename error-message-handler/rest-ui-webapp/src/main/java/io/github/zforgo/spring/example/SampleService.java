package io.github.zforgo.spring.example;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Service
@Validated
public class SampleService {
	public void handleInteger(@NotNull @Min(10) @Max(20) Integer input) {
		System.out.println("valid: " + input);
	}
}
