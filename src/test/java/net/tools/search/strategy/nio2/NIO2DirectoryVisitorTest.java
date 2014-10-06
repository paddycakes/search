package net.tools.search.strategy.nio2;

import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;

import org.junit.Before;
import org.junit.Test;

public class NIO2DirectoryVisitorTest {
	
	public static final String testDirectoryPath = "src/test/resources/searchtest";
	
	private Path rootDirectory;
	private FileVisitor<Path> fileVisitor;
	private NIO2DirectoryVisitor directoryVisitor;
	
	@Before
	public void setUp() {
		rootDirectory = Paths.get(testDirectoryPath);
		fileVisitor = new SimpleFileVisitor<Path>() { };
	}
	
	@Test
	public void should_be_rooted_at_directory_path() {
		directoryVisitor = new NIO2DirectoryVisitor(rootDirectory, fileVisitor);
		// assertThat(directoryVisitor.getRoot(), is(rootDirectory));
	}

	@Test(expected=IllegalArgumentException.class)
	public void should_throw_illegal_argument_exception_when_root_directory_does_not_exist() {
		Path nonExistentDirectory = Paths.get("/non/existent/directory/path");
		new NIO2DirectoryVisitor(nonExistentDirectory, fileVisitor);
	}
	
	// TODO: What is this test doing apart from validating 
	//       a method can be called without exception?
	@Test
	public void should_visit_directories() {
		directoryVisitor = new NIO2DirectoryVisitor(rootDirectory, fileVisitor);
		directoryVisitor.visit();
	}
}
