package net.tools.search;

import static java.lang.String.format;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.tools.search.strategy.nio2.NIO2FilenameMatcher;

/*
 * Note: This class could also be called Main by convention.
 */
public class DirectorySearcher {
	
	private final String rootDirectory;
	private final FileMatcher fileMatcher;
	private List<String> matchingFilePaths;

	public DirectorySearcher(String directory) {
		// TODO: Factory to get default? Where should Path be created?
		// TODO: *** Get default FileMatcher from a Factory**
		this(directory, new NIO2FilenameMatcher("pom.xml", directory));
	}
	
	public DirectorySearcher(String directory, FileMatcher fileMatcher) {
		this.rootDirectory = directory;
		this.fileMatcher = fileMatcher;
		// this.matchingFilePaths = new ArrayList<>();
	}
	
	public static void main(String[] args) {
		DirectorySearcher application = new DirectorySearcher(args[0]);
		List<String> matchingFiles = application.listMatchingFiles();
		for (String file : matchingFiles) {
			System.out.println(format("Matching path is '%s'", file.toString()));
		}
	}

	public String rootDirectory() {
		return rootDirectory;
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
