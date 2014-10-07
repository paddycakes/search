package net.tools.search.strategy.nio2;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import net.tools.search.config.SearchOptions;
import net.tools.search.strategy.nio2.MatchingFileVisitor;
import net.tools.search.strategy.nio2.matcher.FileContentMatcher;
import net.tools.search.strategy.nio2.matcher.FileNameMatcher;
import net.tools.search.strategy.nio2.matcher.Matcher;

import org.junit.Before;
import org.junit.Test;

public class MatchingFileVisitorTest {
	
	private static final String TEST_DIRECTORY = "build/directorysearcher/";
	public static final String FILE_TO_MATCH = "pom.xml";
	
	private MatchingFileVisitor visitor;
	
	@Before
	public void setUp() {
		SearchOptions searchOptions = new SearchOptions.Builder(TEST_DIRECTORY, FILE_TO_MATCH).build();
		visitor = new MatchingFileVisitor(searchOptions, createMatchers(searchOptions));
	}
	
	@Test
	public void should_match_valid_file_paths() throws IOException {
		visitFilePath(visitor, "matching/path/pom.xml");
		visitFilePath(visitor, "another/matching/path/pom.xml");
		
		assertThat(visitor.matchingFiles().size(), is(2));
	}
	
	@Test
	public void should_not_match_invalid_file_paths() throws IOException {
		visitFilePath(visitor, "invalid/path/build.xml");
		visitFilePath(visitor, "invalid/as/pom/in/path/not/filename.txt");
		
		assertThat(visitor.matchingFiles().size(), is(0));
	}
	
	
	// Private helpers
	
	private void visitFilePath(MatchingFileVisitor visitor, String filePath) throws IOException {
		Path path = Paths.get(filePath);
		BasicFileAttributes attrs = null;
		visitor.visitFile(path, attrs);
	}
	
	private List<Matcher> createMatchers(SearchOptions searchOptions) {
		List<Matcher> matchers = new ArrayList<>();
		matchers.add(new FileNameMatcher(searchOptions.getFileName()));
		if (searchOptions.getText() != null) {
			matchers.add(new FileContentMatcher(searchOptions.getText()));
		}
		return matchers;
	}
}
