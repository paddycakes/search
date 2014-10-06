package net.tools.search.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryTreeBuilder {

	private final Path rootDirectory;

	public DirectoryTreeBuilder(String directory) throws IOException {
		this.rootDirectory = Paths.get(directory);
		Files.createDirectories(rootDirectory);
	}
	
	public DirectoryTreeBuilder createFile(String filePath) throws IOException {
		Files.createFile(Paths.get(filePath));
		return this;
	}
	
	public DirectoryTreeBuilder createDirectory(String directoryPath) throws IOException {
		Files.createDirectories(Paths.get(directoryPath));
		return this;
	}
	
	public void tearDown() throws IOException {
		Files.walkFileTree(rootDirectory, new DeleteFileVisitor());
	}

}
