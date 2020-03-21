package com.acme.architecture.core.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AcmeSwaggerUtil {

	protected AcmeSwaggerUtil() {
		throw new IllegalStateException("SwaggerUtil");

	}

	public static Boolean saveFile(String outputDirectory, String filePath, String content) {

		try {
			Files.createDirectories(Paths.get(outputDirectory));
			try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputDirectory, filePath),
					StandardCharsets.UTF_8)) {
				writer.write(content);
			}
		} catch (IOException e) {
			return false;
		}
		
		return true;
	}


}
