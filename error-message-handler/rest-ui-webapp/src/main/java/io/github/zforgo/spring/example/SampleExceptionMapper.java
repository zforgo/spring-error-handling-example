package io.github.zforgo.spring.example;

import io.github.zforgo.spring.error.ErrorResponse;
import io.github.zforgo.spring.error.ErrorResponseWrapperException;
import io.github.zforgo.spring.logging.ExceptionPostMapper;
import org.springframework.stereotype.Component;

@Component
public class SampleExceptionMapper implements ExceptionPostMapper<IllegalArgumentException> {

	@Override
	public Throwable remap(IllegalArgumentException throwable) {
		var item = ErrorResponse.Item.builder()
				.field("ezmegaz")
				.messageKey(throwable.getMessage())
				.build();
		return new ErrorResponseWrapperException(item);
	}
}
