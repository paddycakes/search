package net.tools.search;

import java.io.File;
import java.util.List;

/**
 * Interface to allow plug-able implementations
 * of different file matching mechanisms.
 */
public interface FileMatcher {
	
	List<File> getMatchingFiles();

}
