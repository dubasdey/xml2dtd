package org.xml2dtd;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

public class Xml2DtdTest {

	@Test
	public void testConvert() throws IOException, SAXException, ParserConfigurationException{
		XML2DTD xml2dtd = new XML2DTD();
		InputStream xml = getClass().getResourceAsStream("basic.xml");
		String dtd = TestUtils.convertStreamToString(getClass().getResourceAsStream("basic.dtd"));
		dtd = dtd.replaceAll("\r\n", "\n");
		String dtdGenerated = xml2dtd.run(xml);	
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println(dtd);
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println(dtdGenerated);
		System.out.println("-------------------------------------------------------------------------------");
		assertEquals("Generated DTD not equals to template",dtd,dtdGenerated);
	}
}
