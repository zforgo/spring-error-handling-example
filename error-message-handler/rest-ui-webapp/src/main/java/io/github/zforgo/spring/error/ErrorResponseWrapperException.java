package io.github.zforgo.spring.error;

import java.util.Collections;
import java.util.List;

public class ErrorResponseWrapperException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final ErrorResponse errorResponse;

	public ErrorResponseWrapperException(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}

	public ErrorResponseWrapperException(ErrorResponse.Item item) {
		this(Collections.singletonList(item));
	}

	public ErrorResponseWrapperException(List<ErrorResponse.Item> items) {
		this(ErrorResponse.of(items));
	}

	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}
}
