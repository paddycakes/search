package net.tools.search;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class DirectoryVisitorTest {
	
	public static final String directoryPath = "/home/paddy/development/search";
	
	@Test
	public void should_be_rooted_at_directory_path() {
		DirectoryVisitor visitor = new DirectoryVisitor(directoryPath);
		assertThat(visitor.getRoot(), is(directoryPath));
	}

}
