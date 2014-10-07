package net.tools.search;

import static net.tools.search.FileMatcherFactory.MatcherType.NIO2;
import net.tools.search.config.SearchOptions;
import net.tools.search.strategy.nio2.NIO2FileMatcher;

public class FileMatcherFactory {
	
	public enum MatcherType {
		NIO,
		NIO2
	}
	
	public static FileMatcher defaultFileMatcher(SearchOptions searchOptions) {
		return createFileMatcher(NIO2, searchOptions);
	}
	
	public static FileMatcher createFileMatcher(MatcherType type,SearchOptions searchOptions) {
		switch (type) {
		case NIO:
			throw new UnsupportedOperationException("An NIO FileMatcher is not currently implemented.");
		default:
			return new NIO2FileMatcher(searchOptions);
		}
	}
}
