package net.tools.search.strategy.nio2;

import java.io.File;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class MatchingFileNameVisitor extends SimpleFileVisitor<Path> {
	
	private static final Object EMPTY_STRING = "";
	
	private final String fileToMatch;
	private final List<File> matchedFiles;

	public MatchingFileNameVisitor(String fileToMatch) {
		if (fileToMatch == null || fileToMatch.equals(EMPTY_STRING)) {
			throw new IllegalArgumentException("Must provide a valid filename to match");
		}
		this.fileToMatch = fileToMatch;
		this.matchedFiles = new ArrayList<>();
	}
	
	public String getFileToMatch() {
		return fileToMatch;
	}
	
	public List<File> matchingFiles() {
		return matchedFiles;
	}

	@Override
	public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) {
		if (path.getFileName().toString().matches(fileToMatch)) {
			matchedFiles.add(path.toFile());
		}
		return FileVisitResult.CONTINUE;
	}
	
}

