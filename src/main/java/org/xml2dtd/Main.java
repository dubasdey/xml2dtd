/*
 	This file is part of XML2DTD.

    XML2DTD is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as 
    published by the Free Software Foundation. 

    XML2DTD is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General 
    Public License along with XML2DTD.  If not, see 
    <http://www.gnu.org/licenses/>.
    
 */
package org.xml2dtd;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;


/**
 * The Class Main.
 */
public class Main {


	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		// Check requierd arguments
        if(args.length != 1){
            System.err.println("Usage: java " + Main.class.getName() +" input-file > output-file");
            System.exit(1);
        }
        
        // Start process
        XML2DTD xml2Dtd = new XML2DTD();
        try {
			String result = xml2Dtd.run(args[0]);
			System.out.print(result);
		} catch (SAXException e) {
			e.printStackTrace(System.err);
		} catch (ParserConfigurationException e) {
			e.printStackTrace(System.err);
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
	}

}
