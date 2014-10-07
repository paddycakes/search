package net.tools.search.strategy.nio2.matcher;

import java.nio.file.Path;

public class FileNameMatcher implements Matcher {

	private final String fileName;
	
	public FileNameMatcher(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public boolean matches(Path path) {
		return path.getFileName().toString().matches(fileName);
	}

}
