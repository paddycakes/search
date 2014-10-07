package net.tools.search;

import static java.lang.String.format;

import java.io.File;
import java.util.List;

import net.tools.search.config.SearchOptions;

/**
 * Main entry point for directory search application.
 */
public class DirectorySearcher {
	
	private final SearchOptions searchOptions;
	private final FileMatcher fileMatcher;

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
		List<File> matchingFiles = application.matchingFiles();
		if (matchingFiles.size() == 0) printNoMatches(searchOptions.getFileName());
		else printMatches(matchingFiles);
	}

	public String rootDirectory() {
		return searchOptions.getDirectory();
	}
	
	public String getFileName() {
		return searchOptions.getFileName();
	}

	public List<File> matchingFiles() {
		return fileMatcher.getMatchingFiles();
	}
	
	private static void printMatches(List<File> matchingFiles) {
		for (File file : matchingFiles) {
			System.out.println(format("Matching file path is '%s'", file.getPath().toString()));
		}
	}

	private static void printNoMatches(String fileName) {
		System.out.println(format("No matching files for '%s'", fileName));
	}

}
