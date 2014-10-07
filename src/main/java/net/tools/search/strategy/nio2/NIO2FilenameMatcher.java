package net.tools.search.strategy.nio2;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import net.tools.search.FileMatcher;
import net.tools.search.config.SearchOptions;

public class NIO2FilenameMatcher implements FileMatcher {
	
	private final MatchingFileNameVisitor fileVisitor;
	private final NIO2DirectoryVisitor directoryVisitor;
	private List<File> matchingFiles;
	
	
	public NIO2FilenameMatcher(SearchOptions searchOptions) {
		fileVisitor = new MatchingFileNameVisitor(searchOptions);
		directoryVisitor = new NIO2DirectoryVisitor(Paths.get(searchOptions.getDirectory()), fileVisitor);
	}
	
	@Override
	public List<File> getMatchingFiles() {
		if (matchingFiles == null) {
			directoryVisitor.visit();
			matchingFiles = fileVisitor.matchingFiles();
		}
		return matchingFiles;
	}

}
