package com.acme.architecture.core.util;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class AcmeSwaggerUtilTest {
	
	private String CURRENT_DIRECTORY = System.getProperty("user.dir");

	private String TEMPORARY_FILE_NAME = "test.txt";
	
	@TempDir
	File classTempDirAsFile;
	
	File temporaryFileTest;
	
	String temporaryFileTestFullPath;
	String temporaryFileTestName;
	String temporaryFileTestPah;
	
	@BeforeEach
	public final void setUp() throws IOException {
		
	}
	
	@Test
	public void shouldCreateDefaultConstructor_ThenTrowIllegalStateException() {

		assertThrows(IllegalStateException.class, () -> {
			new AcmeSwaggerUtil();
		});
	}
	
	@Test
	public void whenCallASaveFile_thenReturnTrue() throws Exception {
		
		temporaryFileTest = new File(classTempDirAsFile, TEMPORARY_FILE_NAME);
		
		temporaryFileTestFullPath = temporaryFileTest.getPath();
		temporaryFileTestName = temporaryFileTestFullPath.substring(temporaryFileTestFullPath.lastIndexOf(File.separator)+1);
		temporaryFileTestPah = temporaryFileTestFullPath.substring(0,temporaryFileTestFullPath.lastIndexOf(File.separator)+1);

		assertTrue(AcmeSwaggerUtil.saveFile(temporaryFileTestPah,temporaryFileTestName, "TEST Content"));
	}
	
}
