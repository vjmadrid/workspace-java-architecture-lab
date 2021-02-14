package com.acme.architecture.common.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.acme.architecture.common.exception.AcmeException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class APIDummyUtil {

	private static ObjectMapper mapper = new ObjectMapper();

	protected APIDummyUtil() {
		throw new IllegalStateException("APIDummyUtil");
	}

	// Load file from src/test/resources
	public static <T> T createDummyInstanceClassFromResourceTest(Class<T> clzz, String fileName) throws AcmeException {

		try {

			return clzz.cast(mapper
					.readValue(new String(Files.readAllBytes(Paths.get("src", "test", "resources" + fileName))), clzz));

		} catch (IOException e) {

			throw new AcmeException("Error creating dummy instance class", e);
		}
	}

	// Load file from src/resources
	public static <T> T createDummyInstanceClassResources(Class<T> clzz, String fileName) throws AcmeException {

		try {

			return clzz.cast(
					mapper.readValue(new String(Files.readAllBytes(Paths.get("src", "main", "resources" + fileName))), clzz));

		} catch (IOException e) {

			throw new AcmeException("Error creating dummy instance class", e);
		}
	}

}
