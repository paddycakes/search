package net.tools.search.strategy.nio2.matcher;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.nio.file.Paths;

import net.tools.search.utils.DirectoryTreeBuilder;
import net.tools.search.utils.FileWriter;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FileContentMatcherTest {
	
	private static final String TEST_DIRECTORY = "build/directorysearcher/";
	public static final String FILE_NAME = "pom.xml";
	
	private DirectoryTreeBuilder builder;
	private FileWriter fileWriter;
	
	private FileContentMatcher matcher;
	
	@Before
	public void setUp() throws IOException {
		builder = new DirectoryTreeBuilder(TEST_DIRECTORY);
		fileWriter = new FileWriter();
	}
	
	@After
	public void tearDown() throws IOException {
		builder.tearDown();
	}
	
	@Test
	public void should_match_file_that_contains_search_text() throws IOException {
		String searchText = "<artifact-id>";
		createAndWriteToFile(FILE_NAME, searchText);
		matcher = new FileContentMatcher(searchText);
		
		assertThat(matcher.matches(Paths.get(createFilePath(FILE_NAME))), is(true));
	}
	

	// Private helpers

	private void createAndWriteToFile(String fileName, String content) throws IOException {
		String filePath = createFilePath(fileName);
		builder.createFile(filePath);
		fileWriter.write(filePath, content);
	}
	
	private String createFilePath(String fileName) {
		return TEST_DIRECTORY + fileName;
	}

}
