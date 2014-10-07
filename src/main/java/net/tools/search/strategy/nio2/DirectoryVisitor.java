package net.tools.search.strategy.nio2;

import static java.lang.String.format;

import java.io.IOException;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Traverses the directory hierarchy from the specified root.
 * The injected FileVisitor implementation will inspect each file.
 */
public class DirectoryVisitor {

	private final Path directoryRoot;
	private final FileVisitor<Path> fileVisitor;

	public DirectoryVisitor(Path directoryPath, FileVisitor<Path> fileVisitor) {
		this.directoryRoot = validateDirectoryExists(directoryPath);
		this.fileVisitor = fileVisitor;
	}
	
	public void visit() {
		try {
			Files.walkFileTree(directoryRoot, fileVisitor);
		} catch (IOException e) {
			// TODO: Implement proper logging
			e.printStackTrace();
		}	
	}

	private Path validateDirectoryExists(Path directoryPath) {
		if (Files.notExists(directoryPath)) {
			throw new IllegalArgumentException(
					format("The specified directory '%s' does not exist", directoryPath.toString()));
		}
		return directoryPath;
	}

}
