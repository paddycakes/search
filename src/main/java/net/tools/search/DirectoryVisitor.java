package net.tools.search;

// TODO: Should this be an interface
public class DirectoryVisitor {

	private String directoryRoot;

	public DirectoryVisitor(String directoryRoot) {
		this.directoryRoot = directoryRoot;
	}

	public String getRoot() {
		return directoryRoot;
	}

}
