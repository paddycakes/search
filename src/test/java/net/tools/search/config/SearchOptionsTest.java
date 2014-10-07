package net.tools.search.config;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SearchOptionsTest {
	
	private static final String DIRECTORY = "test";
	private static final String FILE_NAME = "pom.xml";
	private static final String TEXT = "<build>";
	private static final String EMPTY_STRING = "";
	private static final String NULL = null;

	
	
	@Test(expected=IllegalStateException.class)
	public void should_throw_illegal_state_exception_if_empty_arguments_array() {
		String[] noArguments = new String[] { };
		SearchOptions.from(noArguments);
	}

	@Test(expected=IllegalStateException.class)
	public void should_throw_illegal_state_exception_if_only_drectory_specfied_in_arguments_array() {
		String[] programArguments = new String[] { DIRECTORY };
		SearchOptions.from(programArguments);
	}
	
	@Test
	public void should_parse_directory_from_arguments_array() {
		String[] fileNameOptionsAndDirectoryArguments = new String[] { "-f", FILE_NAME, DIRECTORY };
		SearchOptions searchOptions = SearchOptions.from(fileNameOptionsAndDirectoryArguments);
		assertThat(searchOptions.getDirectory(), is(DIRECTORY));
	}	
	
	@Test(expected=IllegalArgumentException.class)
	public void should_throw_illegal_argument_exception_if_directory_value_is_empty_string_in_arguments_array() {
		String[] emptyStringDirectoryArguments = new String[] { "-f", FILE_NAME, EMPTY_STRING };
		SearchOptions.from(emptyStringDirectoryArguments);
	}
	
	@Test
	public void should_parse_filename_option_from_arguments_array() {
		String[] fileNameOptionsAndDirectoryArguments = new String[] { "-f", FILE_NAME, DIRECTORY };
		SearchOptions searchOptions = SearchOptions.from(fileNameOptionsAndDirectoryArguments);
		assertThat(searchOptions.getFileName(), is(FILE_NAME));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void should_throw_illegal_argument_exception_if_filename_value_is_empty_string_in_arguments_array() {
		String[] emptyStringFileNameArguments = new String[] { "-f", EMPTY_STRING, DIRECTORY };
		SearchOptions.from(emptyStringFileNameArguments);
	}
	
	@Test
	public void should_parse_filename_and_text_options_from_arguments_array() {
		String[] fileNameAndTextOptionsAndDirectoryArguments = new String[] { "-f", FILE_NAME, "-p", TEXT, DIRECTORY };
		SearchOptions searchOptions = SearchOptions.from(fileNameAndTextOptionsAndDirectoryArguments);
		assertThat(searchOptions.getFileName(), is(FILE_NAME));
		assertThat((String) searchOptions.get("-p").getValue(), is(TEXT));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void should_throw_illegal_argument_exception_if_program_arguments_do_not_have_matching_flag_and_value_option_pairs() {
		String[] nonMatchingOptionPairsArguments = new String[] { "-f", FILE_NAME, "-p", DIRECTORY };
		SearchOptions.from(nonMatchingOptionPairsArguments);
	}
	
	@Test
	public void should_parse_directory_from_builder() {
		SearchOptions searchOptions = new SearchOptions.Builder(DIRECTORY, FILE_NAME).build();
		assertThat(searchOptions.getDirectory(), is(DIRECTORY));
	}
	
	@Test
	public void should_parse_filename_from_builder() {
		SearchOptions searchOptions = new SearchOptions.Builder(DIRECTORY, FILE_NAME).build();
		assertThat(searchOptions.getFileName(), is(FILE_NAME));
	}
	
	@Test
	public void should_parse_text_from_builder() {
		SearchOptions searchOptions = new SearchOptions.Builder(DIRECTORY, FILE_NAME).text(TEXT).build();
		assertThat((String) searchOptions.get("-p").getValue(), is(TEXT));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void should_throw_illegal_argument_exception_if_directory_value_is_empty_string_in_builder() {
		new SearchOptions.Builder(EMPTY_STRING, FILE_NAME).build();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void should_throw_illegal_argument_exception_if_directory_value_is_null_in_builder() {
		new SearchOptions.Builder(NULL, FILE_NAME).build();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void should_throw_illegal_argument_exception_if_filename_value_is_empty_string_in_builder() {
		new SearchOptions.Builder(DIRECTORY, EMPTY_STRING).build();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void should_throw_illegal_argument_exception_if_filename_value_is_null_in_builder() {
		new SearchOptions.Builder(DIRECTORY, NULL).build();
	}

}
