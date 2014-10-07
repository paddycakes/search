package net.tools.search.strategy.nio2;

import java.io.File;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import net.tools.search.config.SearchOptions;
import net.tools.search.strategy.nio2.matcher.PathMatcher;

public class MatchingFileVisitor extends SimpleFileVisitor<Path> {
	
	private final SearchOptions searchOptions;
	private final List<PathMatcher> pathMatchers;
	private final List<File> matchedFiles;

	public MatchingFileVisitor(SearchOptions searchOptions, List<PathMatcher> pathMatchers) {
		this.searchOptions = searchOptions;
		this.pathMatchers = pathMatchers;
		this.matchedFiles = new ArrayList<>();
	}
	
	public String getFileToMatch() {
		return searchOptions.getFileName();
	}
	
	public List<File> matchingFiles() {
		return matchedFiles;
	}

	@Override
	public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
		if (matches(path)) {
			matchedFiles.add(path.toFile());	
		}
/*		if (path.getFileName().toString().matches(searchOptions.getFileName())) {
			matchedFiles.add(path.toFile());
		}*/
		return FileVisitResult.CONTINUE;
	}
	
	private boolean matches(Path path) {
		boolean isMatch = true;
		for (PathMatcher matcher : pathMatchers) {
			if (!matcher.matches(path)) {
				isMatch = false;
				break;
			}
		}
		return isMatch;
	}
	
}

