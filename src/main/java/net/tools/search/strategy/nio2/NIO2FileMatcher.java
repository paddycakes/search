package net.tools.search.strategy.nio2;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import net.tools.search.FileMatcher;
import net.tools.search.config.SearchOptions;
import net.tools.search.strategy.nio2.matcher.FileContentMatcher;
import net.tools.search.strategy.nio2.matcher.FileNameMatcher;
import net.tools.search.strategy.nio2.matcher.Matcher;

/**
 * Encapsulates an NIO2 implementation of the FileMatcher interface.
 * Contained collaborators use NIO2 features to find and match files..
 */
public class NIO2FileMatcher implements FileMatcher {
	
	private final MatchingFileVisitor fileVisitor;
	private final DirectoryVisitor directoryVisitor;
	private List<File> matchingFiles;
	
	public NIO2FileMatcher(SearchOptions searchOptions) {
		List<Matcher> matchers = createMatchers(searchOptions);
		fileVisitor = new MatchingFileVisitor(searchOptions, matchers);
		directoryVisitor = new DirectoryVisitor(Paths.get(searchOptions.getDirectory()), fileVisitor);
	}
	@Override
	public List<File> getMatchingFiles() {
		if (matchingFiles == null) {
			directoryVisitor.visit();
			matchingFiles = fileVisitor.matchingFiles();
		}
		return matchingFiles;
	}
	
	private List<Matcher> createMatchers(SearchOptions searchOptions) {
		List<Matcher> matchers = new ArrayList<>();
		matchers.add(new FileNameMatcher(searchOptions.getFileName()));
		if (searchOptions.getText() != null) {
			matchers.add(new FileContentMatcher(searchOptions.getText()));
		}
		return matchers;
	}
	
}
