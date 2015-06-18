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


/**
 * The Class Utils.
 */
public abstract class Utils {
	
	/**
	 * Checks if is valid name.
	 * 
	 * @param inputString the input string
	 * 
	 * @return true, if is valid name
	 */
	public static boolean isValidName(String inputString) {
        if(!isValidNMTOKEN(inputString)) {
            return false;
        } else {
            char c = inputString.charAt(0);
            return (c < '0' || c > '9') && c != '.' && c != '-';
        }
    }

    /**
     * Checks if is valid nmtoken.
     * 
     * @param inputString the input string
     * 
     * @return true, if is valid nmtoken
     */
    public static boolean isValidNMTOKEN(String inputString) {
        if(inputString == null || inputString.isEmpty()) {
            return false;
        }
        
        for(int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);
            if((c < 'A' || c > 'Z') && (c < 'a' || c > 'z') && (c < '0' || c > '9') && c != '.' && c != '_' && c != '-' && c != ':' && c <= '\200') {
                return false;
            }
        }

        return true;
    }
    
    /**
     * Escape.
     * 
     * @param ac the ac
     * @param i the i
     * @param j the j
     * @param ac1 the ac1
     * 
     * @return the int
     */
    public static int escape(char ac[], int i, int j, char ac1[]) {
        int k = 0;
        for(int l = i; l < i + j; l++) {
            if(ac[l] == '<') {
                "&lt;".getChars(0, 4, ac1, k);
                k += 4;
            } else if(ac[l] == '>') {
                "&gt;".getChars(0, 4, ac1, k);
                k += 4;
            } else if(ac[l] == '&') {
                "&amp;".getChars(0, 5, ac1, k);
                k += 5;
            } else if(ac[l] == '"') {
                "&#34;".getChars(0, 5, ac1, k);
                k += 5;
            } else if(ac[l] == '\'') {
                "&#39;".getChars(0, 5, ac1, k);
                k += 5;
            } else if(ac[l] <= '\177') {
                ac1[k++] = ac[l];
            } else {
                String s = "&#" + Integer.toString(ac[l]) + ';';
                s.getChars(0, s.length(), ac1, k);
                k += s.length();
            }
        }
        return k;
    }

    /**
     * Escape.
     * 
     * @param s the s
     * 
     * @return the string
     */
    public static String escape(String s) {
        char ac[] = new char[s.length() * 8];
        int i = escape(s.toCharArray(), 0, s.length(), ac);
        return new String(ac, 0, i);
    }
}
