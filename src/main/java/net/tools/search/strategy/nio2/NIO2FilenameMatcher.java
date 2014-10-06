package net.tools.search.strategy.nio2;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import net.tools.search.FileMatcher;

public class NIO2FilenameMatcher implements FileMatcher {
	
	private final MatchingFileNameVisitor fileVisitor;
	private final NIO2DirectoryVisitor directoryVisitor;
	private List<File> matchingFiles;
	
	
	public NIO2FilenameMatcher(String fileName, String rootDirectory) {
		fileVisitor = new MatchingFileNameVisitor(fileName);
		directoryVisitor = new NIO2DirectoryVisitor(Paths.get(rootDirectory), fileVisitor);
	}
	
	@Override
	public List<File> getMatchingFiles() {
		if (matchingFiles == null) {
			directoryVisitor.visit();
			matchingFiles = fileVisitor.matchingFiles();
		}
		return matchingFiles;
	}

}
