package net.tools.search;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import net.tools.search.utils.DirectoryTreeBuilder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * TODO: Document purpose
 */
public class DirectorySearcherEndToEndTest {

	private static final String testDirectory = "build/directorysearcher/";
	private DirectoryTreeBuilder builder;
	private DirectorySearcher application;;
	
	@Before
	public void setUp() throws IOException {
		builder = new DirectoryTreeBuilder(testDirectory);
		application = new DirectorySearcher(testDirectory);
	}
	
	@After
	public void tearDown() throws IOException {
		builder.tearDown();
	}
	
	// NOTE: This is not an end-to-end test. Move it elsewhere?
	@Test public void
	accepts_directory_parameters_as_root_search_directory() {
		assertRootDirectoryIs(testDirectory);
	}
	
	// TEST: for directory not existing

	
	// TODO: Need to supply fileName...
	@Test
	public void searches_specified_directory_hierarchy_and_returns_only_files_that_match_filename_option() throws IOException {
		createFile("pom.xml");
		// createFile("subDirectory/pom.xml");
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
	public void searches_specified_directory_hierarchy_and_returns_only_files_that_match_both_filename_and_text_options() {
	
	}
	
	@Test
	public void searches_specified_directory_hierarchy_and_does_not_return_files_that_match_filename_option_but_do_not_match_text_option() {
	
	}
	
	@Test
	public void searches_specified_directory_hierarchy_and_does_not_return_files_that_do_not_match_filename_option_but_do_match_text_option() {
	
	}
	
	@Test
	public void searches_specified_directory_hierarchy_and_returns_only_files_that_match_regex_for_filename_option() {
		
	}
	
	@Test
	public void searches_specified_directory_hierarchy_and_does_not_return_files_that_do_not_match_regex_for_filename_option() {
		
	}
	
	@Test
	public void searches_specified_directory_hierarchy_and_returns_only_files_that_match_regex_for_both_filename_and_text_options() {
	
	}
	
	@Test
	public void searches_specified_directory_hierarchy_and_does_not_return_files_that_match_regex_for_filename_option_but_do_not_match_regex_for_text_option() {
		
	}
	
	// TODO: Test of adding and analysing new options

	// Private helpers
	
	private void createFile(String fileName) throws IOException {
		builder.createFile(testDirectory + fileName);
	}
	
	private void createFile(String directory, String fileName) throws IOException {
		builder.createDirectory(testDirectory + directory);
		builder.createFile(testDirectory + "/" + directory + "/" + fileName);
	}
	
	private void assertNoMatchingFiles() {
		assertThat(application.listMatchingFiles(), is(notNullValue()));
		assertTrue(application.listMatchingFiles().isEmpty());
	}
	
	private void assertRootDirectoryIs(String directory) {
		assertThat(application.rootDirectory(), is(directory));
	}
}