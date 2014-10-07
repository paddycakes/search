package net.tools.search;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import net.tools.search.config.SearchOptions;
import net.tools.search.utils.DirectoryTreeBuilder;
import net.tools.search.utils.FileWriter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Acceptance tests for end-to-end application functionality.
 */
public class DirectorySearcherEndToEndTest {

	private static final String TEST_DIRECTORY = "build/directorysearcher";
	private static final String FILE_NAME = "pom.xml";
	private static final String SEARCH_TEXT = "<artifact-id>searcher</artifact-id>";
	
	private DirectoryTreeBuilder builder;
	private FileWriter fileWriter;
	
	private SearchOptions searchOptions;
	private DirectorySearcher application;
	
	@Before
	public void setUp() throws IOException {
		builder = new DirectoryTreeBuilder(TEST_DIRECTORY);
		fileWriter = new FileWriter();
		searchOptions = new SearchOptions.Builder(TEST_DIRECTORY, FILE_NAME).build();
		application = new DirectorySearcher(searchOptions);
	}
	
	@After
	public void tearDown() throws IOException {
		builder.tearDown();
	}

	
	@Test
	public void searches_specified_directory_hierarchy_and_returns_only_files_that_match_filename_option() throws IOException {
		createFile("pom.xml");
		createFile("subDirectory", "pom.xml");
		assertThat(application.listMatchingFiles().size(), is(2));
	}

	@Test
	public void searches_specified_directory_hierarchy_and__does_not_return_files_that_only_partially_match_filename_option() throws IOException {
		createFile("pom.txt");
		createFile("om.xml");
		assertThat(application.listMatchingFiles().size(), is(0));
	}
	
	@Test
	public void searches_specified_directory_hierarchy_and_returns_only_files_that_match_both_filename_and_text_options() throws IOException {
		String nonMatchingText = "randomtext";
		createAndWriteToFile(FILE_NAME, SEARCH_TEXT);
		createAndWriteToFile("build.gradle", nonMatchingText);
		
		searchOptions = new SearchOptions.Builder(TEST_DIRECTORY, FILE_NAME).text(SEARCH_TEXT).build();
		application = new DirectorySearcher(searchOptions);
		
		assertThat(application.listMatchingFiles().size(), is(1));
	}
	
	@Test
	public void searches_specified_directory_hierarchy_and_does_not_return_files_that_match_filename_option_but_do_not_match_text_option() throws IOException {
		String nonMatchingText = "randomtext";
		createAndWriteToFile(FILE_NAME, SEARCH_TEXT);
		createAndWriteToFile("subdirectory", FILE_NAME, nonMatchingText);
		
		searchOptions = new SearchOptions.Builder(TEST_DIRECTORY, FILE_NAME).text(SEARCH_TEXT).build();
		application = new DirectorySearcher(searchOptions);
		
		assertThat(application.listMatchingFiles().size(), is(1));
	}
	
	@Test
	public void searches_specified_directory_hierarchy_and_does_not_return_files_that_do_not_match_filename_option_but_do_match_text_option() throws IOException {
		createAndWriteToFile(FILE_NAME, SEARCH_TEXT);
		createAndWriteToFile("build.xml", SEARCH_TEXT);
		
		searchOptions = new SearchOptions.Builder(TEST_DIRECTORY, FILE_NAME).text(SEARCH_TEXT).build();
		application = new DirectorySearcher(searchOptions);
		
		assertThat(application.listMatchingFiles().size(), is(1));
	}
	
	@Test
	public void searches_specified_directory_hierarchy_and_returns_only_files_that_match_regex_for_filename_option() throws IOException {
		String matchAnyNumberOfCharactersBetweenPandM = "p.*m.xml";
		createFile("pom.xml");
		createFile("pam.xml");
		createFile("piiiim.xml");
		createFile("subDirectory", "pum.xml");
		searchOptions = new SearchOptions.Builder(TEST_DIRECTORY, matchAnyNumberOfCharactersBetweenPandM).build();
		application = new DirectorySearcher(searchOptions);
		
		assertThat(application.listMatchingFiles().size(), is(4));
	}
	
	@Test
	public void searches_specified_directory_hierarchy_and_does_not_return_files_that_do_not_match_regex_for_filename_option() throws IOException {
		String matchOnlyZeroOrSingleOBetweenPandM = "po?m.xml";
		createFile("pm.xml");	// Match
		createFile("pom.xml");	// Match
		createFile("poom.xml");
		createFile("subDirectory", "poom.xml");
		createFile("pam.xml");
		createFile("poam.xml");		
		createFile("po?m.xml");		
		searchOptions = new SearchOptions.Builder(TEST_DIRECTORY, matchOnlyZeroOrSingleOBetweenPandM).build();
		application = new DirectorySearcher(searchOptions);
		
		assertThat(application.listMatchingFiles().size(), is(2));
	}
	
	@Test
	public void searches_specified_directory_hierarchy_and_returns_only_files_that_match_regex_for_both_filename_and_text_options() throws IOException {
	}
	
	@Test
	public void searches_specified_directory_hierarchy_and_does_not_return_files_that_match_regex_for_filename_option_but_do_not_match_regex_for_text_option() {
		
	}
	
	@Test public void
	accepts_directory_parameters_as_root_search_directory() {
		assertRootDirectoryIs(TEST_DIRECTORY);
	}
	
	// TEST: for directory not existing
	
	// TODO: Test of adding and analysing new options

	// Private helpers
	
	private void createFile(String fileName) throws IOException {
		builder.createFile(createPath(fileName));
	}
	
	private void createFile(String directory, String fileName) throws IOException {
		builder.createDirectory(createPath(directory));
		builder.createFile(createPath(directory, fileName));
	}
	
	private void createAndWriteToFile(String fileName, String content) throws IOException {
		createFile(fileName);
		fileWriter.write(createPath(fileName), content);
	}
	
	private void createAndWriteToFile(String directory, String fileName, String content) throws IOException {
		createFile(directory, fileName);
		fileWriter.write(createPath(directory, fileName), content);
	}
	
	private String createPath(String... pathElements) {
		StringBuilder pathBdr = new StringBuilder(TEST_DIRECTORY);
		for (String pathElement : pathElements) {
			pathBdr.append("/");
			pathBdr.append(pathElement);
		}
		return pathBdr.toString();
	}
	
	private void assertRootDirectoryIs(String directory) {
		assertThat(application.rootDirectory(), is(directory));
	}
}
