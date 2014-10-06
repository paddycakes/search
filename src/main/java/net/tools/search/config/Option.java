package net.tools.search.config;

public class Option<T> {
	
	private final String key;
	private final String description;
	private final Class<T> valueClass;
	private final T value;

	public Option(String key, String description, Class<T> valueClass, T value) {
		this.key = key;
		this.description = description;
		this.valueClass = valueClass;
		this.value = value;
	}
	
	public static Option<String> fileNameOption(String fileName) {
		return new Option<String>("-f", "", String.class, fileName);
	}
	
	public static Option<String> textOption(String text) {
		return new Option<String>("-p", "", String.class, text);
	}

	public String getKey() {
		return key;
	}

	public String getDescription() {
		return description;
	}

	public Class<T> getValueClass() {
		return valueClass;
	}

	public T getValue() {
		return value;
	}
	
}
