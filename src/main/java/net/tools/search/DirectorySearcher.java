package net.tools.search;

import static java.lang.String.format;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.tools.search.config.SearchOptions;

/**
 * Main entry point for directory search application.
 */
public class DirectorySearcher {
	
	// TODO: Test command line interface works
	
	private final SearchOptions searchOptions;
	private final FileMatcher fileMatcher;
	private List<String> matchingFilePaths;

	public DirectorySearcher(SearchOptions searchOptions) {
		this(searchOptions, FileMatcherFactory.defaultFileMatcher(searchOptions));
	}
	
	public DirectorySearcher(SearchOptions searchOptions, FileMatcher fileMatcher) {
		this.searchOptions = searchOptions;
		this.fileMatcher = fileMatcher;
	}
	
	public static void main(String[] args) {
		SearchOptions searchOptions = SearchOptions.from(args);
		DirectorySearcher application = new DirectorySearcher(searchOptions);
		List<String> matchingFiles = application.listMatchingFiles();
		for (String file : matchingFiles) {
			System.out.println(format("Matching path is '%s'", file.toString()));
		}
	}

	public String rootDirectory() {
		return searchOptions.getDirectory();
	}
	
	public String getFileName() {
		return searchOptions.getFileName();
	}

	// TODO: Convert from Files to String paths
	public List<String> listMatchingFiles() {
		if (matchingFilePaths == null) {
			matchingFilePaths = new ArrayList<>();
			for (File file : fileMatcher.getMatchingFiles()) {
				matchingFilePaths.add(file.getPath());
			}
		}
		return matchingFilePaths;
	}

}
