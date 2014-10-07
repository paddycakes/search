package net.tools.search.strategy.nio2.matcher;

import java.nio.file.Path;

public interface PathMatcher {
	
	boolean matches(Path path);

}
