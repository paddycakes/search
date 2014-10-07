package net.tools.search.strategy.nio2;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

import net.tools.search.utils.DirectoryTreeBuilder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DirectoryVisitorTest {
	
	private static final String TEST_DIRECTORY = "build/directorysearcher/";
	
	private final Path rootDirectoryPath = Paths.get(TEST_DIRECTORY);;
	private FileVisitor<Path> fileVisitor;
	private DirectoryTreeBuilder builder;
	
	private DirectoryVisitor directoryVisitor;
	
	@Before
	public void setUp() throws IOException {
		builder = new DirectoryTreeBuilder(TEST_DIRECTORY);
		setUpFileVisitorMocking();
		directoryVisitor = new DirectoryVisitor(rootDirectoryPath, fileVisitor);
	}
	
	@After
	public void tearDown() throws IOException {
		builder.tearDown();
	}

	
	@Test(expected=IllegalArgumentException.class)
	public void should_throw_illegal_argument_exception_when_root_directory_does_not_exist() {
		Path nonExistentDirectory = Paths.get("/non/existent/directory/path");
		new DirectoryVisitor(nonExistentDirectory, fileVisitor);
	}
	
	@Test
	public void should_visit_all_files_in_root_directory() throws IOException {
		createFile("file1.txt");
		createFile("file2.txt");
		createFile("pom.xml");
		directoryVisitor.visit();
		
		verify(fileVisitor, times(3)).visitFile(any(Path.class), any(BasicFileAttributes.class));
	}
	
	@Test
	public void should_not_visit_any_files_in_empty_directory() throws IOException {
		directoryVisitor.visit();
		
		verify(fileVisitor, never()).visitFile(any(Path.class), any(BasicFileAttributes.class));
	}
	
	
	// Private helpers
	
	@SuppressWarnings("unchecked")
	private void setUpFileVisitorMocking() throws IOException {
		fileVisitor = mock(FileVisitor.class);
		setUpVisitFileMocking();
		setUpPreVisitDirectoryMocking();
		setUpPostVisitDirectoryMocking();
	}

	private void setUpPostVisitDirectoryMocking() throws IOException {
		when(fileVisitor.preVisitDirectory(
				any(Path.class), 
				any(BasicFileAttributes.class)))
				.thenReturn(FileVisitResult.CONTINUE);
	}

	private void setUpVisitFileMocking() throws IOException {
		when(fileVisitor.visitFile(
				any(Path.class), 
				any(BasicFileAttributes.class)))
				.thenReturn(FileVisitResult.CONTINUE);
	}

	private void setUpPreVisitDirectoryMocking() throws IOException {
		when(fileVisitor.postVisitDirectory(
				any(Path.class), 
				any(IOException.class)))
				.thenReturn(FileVisitResult.CONTINUE);
	}

	private void createFile(String fileName) throws IOException {
		builder.createFile(TEST_DIRECTORY + fileName);
	}
}
