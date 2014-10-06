package net.tools.search;

import java.nio.file.Path;

// TODO: Should this be an interface
public class DirectoryVisitor {

	private Path directoryRoot;

	public DirectoryVisitor(Path directoryPath) {
		this.directoryRoot = directoryPath;
	}

	public Path getRoot() {
		return directoryRoot;
	}

}
