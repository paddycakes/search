package net.tools.search.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileWriter {

	public void write(String filePath, String text) throws IOException {
		Path path = Paths.get(filePath);
		try (BufferedWriter writer = 
				Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.WRITE)) {
			writer.write(text);
		}
	}
}
