package com.acme.architecture.core.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class SwaggerUtilTest {

	private String TEMPORARY_FILE_NAME = "test.txt";

	@TempDir
	public Path temporaryFolder;

	File temporaryFileTest;

	String temporaryFileTestFullPath;
	String temporaryFileTestName;
	String temporaryFileTestPah;

	@BeforeEach
	public final void setUp() throws IOException {

	}
	
	@Test
	public void shouldCreateDefaultConstructor_ThenTrowIllegalStateException() {
		assertThrows(IllegalStateException.class, ()->{
			new SwaggerUtil();
		});
	}

	@Test
	public void whenCallASaveFile_thenReturnTrue() throws Exception {
		temporaryFileTest = temporaryFolder.resolve(TEMPORARY_FILE_NAME).toFile();

		temporaryFileTestFullPath = temporaryFileTest.getPath();
		temporaryFileTestName = temporaryFileTestFullPath
				.substring(temporaryFileTestFullPath.lastIndexOf(File.separator) + 1);
		temporaryFileTestPah = temporaryFileTestFullPath.substring(0,
				temporaryFileTestFullPath.lastIndexOf(File.separator) + 1);

		assertTrue(SwaggerUtil.saveFile(temporaryFileTestPah, temporaryFileTestName, "TEST Content"));
	}
	
	@Test
	public void whenCallASaveFile_thenReturnFalse() throws Exception {
		
		assertFalse(SwaggerUtil.saveFile(temporaryFileTestPah, temporaryFileTestName, null));
	}

}
