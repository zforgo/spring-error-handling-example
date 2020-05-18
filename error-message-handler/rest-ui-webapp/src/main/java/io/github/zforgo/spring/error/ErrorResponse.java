package io.github.zforgo.spring.error;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ErrorResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	public List<Item> items = new ArrayList<>();

	private ErrorResponse(List<Item> items) {
		this.items = items;
	}

	public static ErrorResponse of(List<Item> items) {
		return new ErrorResponse(items);
	}

	public static class Item implements Serializable {
		private static final long serialVersionUID = 1L;
		private String field;
		private String messageKey;

		public Item withField(String field) {
			this.field = field;
			return this;
		}

		public Item withMessageKey(String messageKey) {
			this.messageKey = messageKey;
			return this;
		}

		public String getField() {
			return field;
		}

		public void setField(String field) {
			this.field = field;
		}

		public String getMessageKey() {
			return messageKey;
		}

		public void setMessageKey(String messageKey) {
			this.messageKey = messageKey;
		}

		//		TODO getters / setters
	}

	public void addItem(Item item) {
		items.add(item);
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}
