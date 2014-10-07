package net.tools.search.config;

import static java.lang.String.format;
import static net.tools.search.config.Option.FILE_NAME_FLAG;
import static net.tools.search.config.Option.TEXT_FLAG;

import java.util.HashMap;
import java.util.Map;

/*
 * Contains a collection of search options. Constructors
 * are private to enforce use of either:
 *   i. builder pattern
 *  ii. static factory method
 * for simpler object creation.
 */
public class SearchOptions {

	private static final String EMPTY_STRING = "";
	
	private final Map<String, Option> options;
	private final String directory;

	private SearchOptions(String directory, Map<String, Option> options) {
		this.directory = validateDirectoryInvariants(directory);
		this.options = validateOptionsInvariants(options);
	}

	private SearchOptions(Builder builder) {
		this(builder.directory, createOptions(builder));
	}
	

	/**
	 * Use of a Builder to allow easy creation
	 * of new search options.
	 */
	public static class Builder {
		// Optional parameters - initialise to defaults
		private String text = null;
		
		// Mandatory parameters
		private final String directory;
		private final String fileName;
		
		public Builder(String directory, String fileName) { 
			this.directory = directory;
			this.fileName = fileName;
		}
		
		public Builder text(String text) {
			this.text = text;
			return this;
		}
		
		public SearchOptions build() {
			return new SearchOptions(this);
		}
	}

	/**
	 * Factory method to create search options
	 * from an array of Strings.
	 * 
	 * @param args Array of strings representing options
	 * @return SearchOptions
	 */
	public static SearchOptions from(String[] args) {
		validateAtLeastThreeArguments(args);
		validateMatchingOptionPairs(args);
		return new SearchOptions(args[args.length -1], createOptions(args));
	}

	public Option get(String key) {
		return options.get(key);
	}
	
	public String getDirectory() {
		return directory;
	}
	
	public String getFileName() {
		return (String) options.get(FILE_NAME_FLAG).getValue();
	}
	
	public String getText() {
		if (options.get(TEXT_FLAG) != null) {
			return (String) options.get(TEXT_FLAG).getValue();
		}
		return null;
	}

	private static void validateAtLeastThreeArguments(String[] args) {
		if (args.length < 3) throw new IllegalStateException("Must specify as minimum filename flag/value and directory: Usage: -f filename [-flag value...] directory");
	}

	private static void validateMatchingOptionPairs(String[] args) {
		if (args.length % 2 == 0) throw new IllegalArgumentException(
				"Must specify matching option flag and value pairs: <Usage>: -flag value [-flag value...] directory");
	}
	
	private static String validateDirectoryInvariants(String directory) {
		if (directory == null || EMPTY_STRING.equals(directory)) {
			throw new IllegalArgumentException(
					format("Value for directory '%s' violates invariants. Cannot be null or empty.", directory));
		}
		return directory;
	}
	
	private Map<String, Option> validateOptionsInvariants(Map<String, Option> options) {
		Option<String> fileNameOption = options.get(Option.FILE_NAME_FLAG);
		if (fileNameOption == null || fileNameOption.getValue() == null 
				|| EMPTY_STRING.equals(fileNameOption.getValue())) {
			throw new IllegalArgumentException(
					"Value for filename option violates invariants. Cannot be null or empty.");
		}
		return options;
	}

	private static Map<String, Option> createOptions(String[] args) {
		Map<String, Option> options = new HashMap<>();
		for (int i=0; i<args.length-1; i+=2) {
			String flag = args[i];
			String value = args[i+1];
			options.put(flag, Option.create(flag, value));
		}
		return options;
	}
	
	private static Map<String, Option> createOptions(Builder builder) {
		Map<String, Option> options = new HashMap<>();
		Option<String> fileNameOption = Option.fileNameOption(builder.fileName);
		options.put(fileNameOption.getKey(), fileNameOption);
		if (builder.text != null || EMPTY_STRING.equals(builder.text)) {
			Option<String> textOption = Option.textOption(builder.text);
			options.put(textOption.getKey(), textOption);
		}
		return options;
	}
}
