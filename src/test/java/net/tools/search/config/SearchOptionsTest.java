package net.tools.search.config;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SearchOptionsTest {
	
	private static final String DIRECTORY = "test";
	private static final String FILE_NAME = "pom.xml";
	private static final String TEXT = "<build>";

	
	@Test
	public void should_parse_search_directory() {
		String[] programArguments = new String[] { DIRECTORY };
		SearchOptions searchOptions = SearchOptions.from(programArguments);
		assertThat(searchOptions.getDirectory(), is(DIRECTORY));
	}
	
	@Test(expected=IllegalStateException.class)
	public void should_throw_illegal_state_exception_if_no_arguments() {
		String[] noArguments = new String[] { };
		SearchOptions.from(noArguments);
	}
	
	@Test
	public void should_parse_filename_option() {
		String[] fileNameOptionsAndDirectoryArguments = new String[] { "-f", FILE_NAME, DIRECTORY };
		SearchOptions searchOptions = SearchOptions.from(fileNameOptionsAndDirectoryArguments);
		// TODO: Sort out casting here ..
		assertThat((String) searchOptions.get("-f").getValue(), is(FILE_NAME));
	}
	
	@Test
	public void should_parse_filename_and_text_options() {
		String[] fileNameAndTextOptionsAndDirectoryArguments = new String[] { "-f", FILE_NAME, "-p", TEXT, DIRECTORY };
		SearchOptions searchOptions = SearchOptions.from(fileNameAndTextOptionsAndDirectoryArguments);
		// TODO: Sort out casting here ..
		assertThat((String) searchOptions.get("-f").getValue(), is(FILE_NAME));
		assertThat((String) searchOptions.get("-p").getValue(), is(TEXT));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void should_throw_illegal_argument_exception_if_program_arguments_do_not_have_matching_flag_and_value_option_pairs() {
		String[] nonMatchingOptionPairs = new String[] { "-f", DIRECTORY };
		SearchOptions.from(nonMatchingOptionPairs);
	}

}
