package net.tools.search.config;

import java.util.Collections;
import java.util.Map;

/*
 * Contains a collection of search options.
 */
public class SearchOptions {
	
	// TODO: Is Set the correct container - equals/hashCode?
	
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
		if (args.length == 0) throw new IllegalStateException("Must specify a directory");
		return new SearchOptions(args[0]);
	}
	
	public Option get(String key) {
		return options.get(key);
	}
	
	public String getDirectory() {
		return directory;
	}

}
