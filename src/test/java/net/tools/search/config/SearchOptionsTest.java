package net.tools.search.config;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SearchOptionsTest {
	
	private static final String DIRECTORY = "test";

	@Test
	public void should_parse_search_directory() {
		String[] programArguments = new String[] { DIRECTORY };
		SearchOptions searchOptions = SearchOptions.from(programArguments);
		assertThat(searchOptions.getDirectory(), is(DIRECTORY));
	}
	
	@Test(expected=IllegalStateException.class)
	public void should_throw_illegal_state_exception_if_no_directory_argument() {
		String[] noArguments = new String[] { };
		SearchOptions.from(noArguments);
	}

}
