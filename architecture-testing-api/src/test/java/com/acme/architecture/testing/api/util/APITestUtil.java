package com.acme.architecture.testing.api.util;

import java.io.InputStream;
import java.util.Scanner;

public class APITestUtil {
	
	protected APITestUtil() {
		throw new IllegalStateException("APITestUtil");
	}
	
	public static String inputStreamToString(InputStream is) {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}