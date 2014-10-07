package net.tools.search.strategy.nio2;

import java.io.File;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import net.tools.search.config.SearchOptions;

public class MatchingFileNameVisitor extends SimpleFileVisitor<Path> {
	
	private final SearchOptions searchOptions;
	private final List<File> matchedFiles;

	public MatchingFileNameVisitor(SearchOptions searchOptions) {
		this.searchOptions = searchOptions;
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
		if (path.getFileName().toString().matches(searchOptions.getFileName())) {
			matchedFiles.add(path.toFile());
		}
		return FileVisitResult.CONTINUE;
	}
	
}

