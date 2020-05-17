package io.github.zforgo.spring;

import io.github.zforgo.spring.services.SampleService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		var context = SpringApplication.run(Application.class, args);
		var service = context.getBean(SampleService.class);
		try {
			service.foo();
		} catch (Exception ignored){}
	}

}
