package net.tools.search;

import static java.lang.String.format;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.tools.search.config.SearchOptions;
import net.tools.search.strategy.nio2.NIO2FilenameMatcher;

/**
 * Main entry point for directory search application.
 */
public class DirectorySearcher {
	
	// TODO: Need to implement:
	//		- text matching
	//		- regex support
	// TODO: Test command line interface works
	
	private final SearchOptions searchOptions;
	private final FileMatcher fileMatcher;
	private List<String> matchingFilePaths;

	public DirectorySearcher(SearchOptions searchOptions) {
		// TODO: Factory to get default? Where should Path be created?
		// TODO: *** Get default FileMatcher from a Factory**
		this(searchOptions, new NIO2FilenameMatcher(searchOptions));
	}
	
	public DirectorySearcher(SearchOptions searchOptions, FileMatcher fileMatcher) {
		this.searchOptions = searchOptions;
		this.fileMatcher = fileMatcher;
		// this.matchingFilePaths = new ArrayList<>();
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
