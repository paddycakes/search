package net.tools.search.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/*
 * Contains a collection of search options.
 */
public class SearchOptions {

	private final Map<String, Option> options;
	private final String directory;
	
	public SearchOptions(String directory) {
		this(directory, Collections.<String, Option>emptyMap());
	}

	public SearchOptions(String directory, Map<String, Option> options) {
		this.directory = directory;
		this.options = options;
	}

	public static SearchOptions from(String[] args) {
		validateAtLeastOneArgument(args);
		validateMatchingOptionPairs(args);
		if (args.length == 1) {
			return new SearchOptions(args[0]);
		} else {
			Map<String, Option> options = createOptions(args);
			String directory = args[args.length -1];
			return new SearchOptions(directory, options);
		}
	}

	public Option get(String key) {
		return options.get(key);
	}
	
	public String getDirectory() {
		return directory;
	}

	private static void validateAtLeastOneArgument(String[] args) {
		if (args.length == 0) throw new IllegalStateException("Must specify a directory");
	}

	private static void validateMatchingOptionPairs(String[] args) {
		if (args.length % 2 == 0) throw new IllegalArgumentException(
				"Must specify matching option flag and value pairs: <Usage>: -flag value");
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
}
