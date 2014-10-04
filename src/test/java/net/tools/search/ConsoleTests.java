package net.tools.search;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ConsoleTests {

	@Test
	public void system_should_accept_input_options() {
		String[] options = new String[] {"opt1", "opt2", "opt3"};
		Console console = new Console(options);
		assertThat(console.getOptions(), is(options));
	}
}
