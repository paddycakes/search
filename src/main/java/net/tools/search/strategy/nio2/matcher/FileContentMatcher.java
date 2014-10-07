package net.tools.search.strategy.nio2.matcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileContentMatcher implements Matcher {
	
	private final String text;

	public FileContentMatcher(String text) {
		this.text = text;
	}

	@Override
	public boolean matches(Path path) {
		boolean isMatch = false;
		try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.matches(text)) {
					isMatch = true;
					break;
				}
			}
		} catch (IOException e) {
			// TODO: Implement proper logging
			e.printStackTrace();
		}
		return isMatch;
	}

}
