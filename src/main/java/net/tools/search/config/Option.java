package net.tools.search.config;

import static java.lang.String.format;

public class Option<T> {
	
	public static final String FILE_NAME_FLAG = "-f";
	public static final String TEXT_FLAG = "-p";
	
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
	

	public static Option create(String flag, String value) {
		switch (flag) {
		case FILE_NAME_FLAG:
			return fileNameOption(value);
		case TEXT_FLAG:
			return textOption(value);
		default:
			throw new RuntimeException(format("Unknown flag '%s'", flag));
		}
	}
	
	public static Option<String> fileNameOption(String fileName) {
		return new Option<String>(FILE_NAME_FLAG, "File name to match during search", String.class, fileName);
	}
	
	public static Option<String> textOption(String text) {
		return new Option<String>(TEXT_FLAG, "Text to match during search", String.class, text);
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
	
	@Override 
	public String toString() {
	    StringBuilder result = new StringBuilder();
	    String NEW_LINE = System.getProperty("line.separator");

	    result.append(this.getClass().getName() + " {" + NEW_LINE);
	    result.append(" Key: " + key + NEW_LINE);
	    result.append(" Description: " + description + NEW_LINE);
	    result.append(" Value: " + value + NEW_LINE);
	    result.append(" Value Class: " + valueClass + NEW_LINE );
	    result.append("}");

	    return result.toString();
	  }

}
