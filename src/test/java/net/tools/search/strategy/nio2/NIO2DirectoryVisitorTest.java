package net.tools.search;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

public class DirectoryVisitorTest {
	
	public static final String testDirectory = "/tmp/test";
	
	private Path directoryPath;
	private DirectoryVisitor visitor;
	
	@Before
	public void setUp() {
		directoryPath = Paths.get(testDirectory);
		visitor = new DirectoryVisitor(directoryPath);
	}
	
	@Test
	public void should_be_rooted_at_directory_path() {
		DirectoryVisitor visitor = new DirectoryVisitor(directoryPath);
		assertThat(visitor.getRoot(), is(directoryPath));
	}

}
