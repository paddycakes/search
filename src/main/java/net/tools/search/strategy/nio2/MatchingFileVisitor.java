package net.tools.search.strategy.nio2;

import java.io.File;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import net.tools.search.config.SearchOptions;
import net.tools.search.strategy.nio2.matcher.Matcher;

/**
 * FileVisitor that determines matching files
 * based on all matchers returning true.
 */
public class MatchingFileVisitor extends SimpleFileVisitor<Path> {
	
	private final SearchOptions searchOptions;
	private final List<Matcher> matchers;
	private final List<File> matchedFiles;

	public MatchingFileVisitor(SearchOptions searchOptions, List<Matcher> matchers) {
		this.searchOptions = searchOptions;
		this.matchers = matchers;
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
		return FileVisitResult.CONTINUE;
	}
	
	private boolean matches(Path path) {
		boolean isMatch = true;
		for (Matcher matcher : matchers) {
			if (!matcher.matches(path)) {
				isMatch = false;
				break;
			}
		}
		return isMatch;
	}
	
}

