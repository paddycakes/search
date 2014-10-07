package net.tools.search.strategy.nio2;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import net.tools.search.FileMatcher;
import net.tools.search.config.SearchOptions;
import net.tools.search.strategy.nio2.matcher.FileContentMatcher;
import net.tools.search.strategy.nio2.matcher.FileNameMatcher;
import net.tools.search.strategy.nio2.matcher.PathMatcher;

public class NIO2FileMatcher implements FileMatcher {
	
	private final MatchingFileVisitor fileVisitor;
	private final NIO2DirectoryVisitor directoryVisitor;
	private List<File> matchingFiles;
	
	public NIO2FileMatcher(SearchOptions searchOptions) {
		List<PathMatcher> pathMatchers = createMatchers(searchOptions);
		fileVisitor = new MatchingFileVisitor(searchOptions, pathMatchers);
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
	
	private List<PathMatcher> createMatchers(SearchOptions searchOptions) {
		List<PathMatcher> pathMatchers = new ArrayList<>();
		pathMatchers.add(new FileNameMatcher(searchOptions.getFileName()));
		if (searchOptions.getText() != null) {
			pathMatchers.add(new FileContentMatcher(searchOptions.getText()));
		}
		return pathMatchers;
	}
	
}
