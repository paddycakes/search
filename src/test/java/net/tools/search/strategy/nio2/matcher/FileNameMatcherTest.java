package net.tools.search.strategy.nio2.matcher;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import net.tools.search.utils.DirectoryTreeBuilder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileNameMatcherTest {
	
	private static final String TEST_DIRECTORY = "build/directorysearcher/";
	public static final String FILE_NAME = "pom.xml";
	private static final String FILE_NAME_REGEX = "p.*m.xml";
	
	private DirectoryTreeBuilder builder;
	
	private FileNameMatcher matcher;
	
	@Before
	public void setUp() throws IOException {
		builder = new DirectoryTreeBuilder(TEST_DIRECTORY);
	}
	
	@After
	public void tearDown() throws IOException {
		builder.tearDown();
	}
	
	@Test
	public void should_match_file_that_has_filename_string() throws IOException {
		matcher = new FileNameMatcher(FILE_NAME);
		assertThat(matcher.matches(path(FILE_NAME)), is(true));
	}
	
	@Test
	public void should_not_match_file_that_does_not_have_filename_string() throws IOException {
		matcher = new FileNameMatcher(FILE_NAME);
		assertThat(matcher.matches(path("file1.txt")), is(false));
	}
	
	@Test
	public void should_match_file_that_has_filename_regex() throws IOException {
		matcher = new FileNameMatcher(FILE_NAME_REGEX);
		assertThat(matcher.matches(path(FILE_NAME)), is(true));
	}
	
	@Test
	public void should_not_match_file_that_does_not_have_filename_regex() throws IOException {
		matcher = new FileNameMatcher(FILE_NAME_REGEX);
		assertThat(matcher.matches(path("pom1.xml")), is(false));
	}
	
	private Path path(String fileName) {
		return Paths.get(createPath(fileName));
	}
	
	private String createPath(String fileName) {
		return TEST_DIRECTORY + fileName;
	}

}
