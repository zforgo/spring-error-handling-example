package io.github.zforgo.spring.services;

import io.github.zforgo.spring.exception.logging.LogLevel;
import io.github.zforgo.spring.exception.logging.Logged;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SampleService {

	@Logged(msg = "Hello {}", cls = RuntimeException.class, level = LogLevel.ERROR)
	@Logged(msg = "Hello", cls = IOException.class, level = LogLevel.INFO)
	public void foo() {
		MDC.put("mdcKey","mdcValue");
		System.out.println("bar");
		throw new RuntimeException("Hehe");
	}
}
