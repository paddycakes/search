package net.tools.search.strategy;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

public class MatchingFileNameVisitor extends SimpleFileVisitor<Path> {
	
	private static final Object EMPTY_STRING = "";
	
	private final String fileToMatch;
	private final Set<Path> matchingFilePaths;

	public MatchingFileNameVisitor(String fileToMatch) {
		if (fileToMatch == null || fileToMatch.equals(EMPTY_STRING)) {
			throw new IllegalArgumentException("Must provide a valid filename to match");
		}
		this.fileToMatch = fileToMatch;
		this.matchingFilePaths = new HashSet<>();
	}
	
	public String getFileToMatch() {
		return fileToMatch;
	}
	
	public Set<Path> getMatchingFilePaths() {
		return matchingFilePaths;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
		if (file.getFileName().toString().equals(fileToMatch)) {
			matchingFilePaths.add(file);
		}
		return FileVisitResult.CONTINUE;
	}
	
}

