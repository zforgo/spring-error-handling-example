package io.github.zforgo.spring.error;


import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
public class ErrorResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	public List<Item> items;

	private ErrorResponse(List<Item> items) {
		this.items = items;
	}

	public static ErrorResponse of(List<Item> items) {
		return new ErrorResponse(items);
	}

	@Data
	@Accessors(fluent = true)
	@Builder
	public static class Item implements Serializable {
		private static final long serialVersionUID = 1L;
		private String field;
		private String messageKey;

	}
}
