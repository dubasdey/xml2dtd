package org.xml2dtd;

import java.io.InputStream;
import java.util.Scanner;

public class TestUtils {
	public static String convertStreamToString(InputStream is) {
	    Scanner s = new Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}
}
