package net.tools.search.strategy;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

public class MatchingFileNameVisitorTest {
	
	public static final String fileToMatch = "pom.xml";
	
	private MatchingFileNameVisitor visitor;
	
	@Before
	public void setUp() {
		visitor = new MatchingFileNameVisitor(fileToMatch);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void should_throw_illegal_argument_exception_when_initialised_with_null_file_name() {
		String nullFileName = null;
		new MatchingFileNameVisitor(nullFileName);
	}

	@Test(expected=IllegalArgumentException.class)
	public void should_throw_illegal_argument_exception_when_initialised_with_empty_string_file_name() {
		String emptyStringFileName = "";
		new MatchingFileNameVisitor(emptyStringFileName);
	}
	
	@Test
	public void should_match_valid_file_paths() throws IOException {
		visitFilePath(visitor, "matching/path/pom.xml");
		visitFilePath(visitor, "another/matching/path/pom.xml");
		
		assertThat(visitor.getMatchingFilePaths().size(), is(2));
	}
	
	@Test
	public void should_not_match_invalid_file_paths() throws IOException {
		visitFilePath(visitor, "invalid/path/build.xml");
		visitFilePath(visitor, "invalid/as/pom/in/path/not/filename.txt");
		
		assertThat(visitor.getMatchingFilePaths().size(), is(0));
	}
	
	
	// TODO: Do I need a Mock for BasicFileAttributes or is null okay?
	private void visitFilePath(MatchingFileNameVisitor visitor, String filePath) throws IOException {
		Path path = Paths.get(filePath);
		// BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);
		visitor.visitFile(path, null);
	}
}
