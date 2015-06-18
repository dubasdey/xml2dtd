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
package org.xml2dtd.model;

import java.util.TreeSet;




/**
 * The Class AttributeDetails.
 */
public class AttributeDetails {
	
	/** The name. */
	private String name;
	
	/** The occurrences. */
	private int occurrences;
	
	/** The unique. */
	private boolean unique;
	
	/** The values. */
	private TreeSet<String> values;
	
	/** The all names. */
	private boolean allNames;
	
	/** The all nmtoke ns. */
	private boolean allNMTOKENs;

    /**
     * Instantiates a new attribute details.
     * 
     * @param s the s
     */
    public AttributeDetails(String s){
        name = s;
        occurrences = 0;
        unique = true;
        values = new TreeSet<String>();
        allNames = true;
        allNMTOKENs = true;
    }

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the occurrences.
	 * 
	 * @return the occurrences
	 */
	public int getOccurrences() {
		return occurrences;
	}

	/**
	 * Sets the occurrences.
	 * 
	 * @param occurrences the new occurrences
	 */
	public void setOccurrences(int occurrences) {
		this.occurrences = occurrences;
	}

	/**
	 * Checks if is unique.
	 * 
	 * @return true, if is unique
	 */
	public boolean isUnique() {
		return unique;
	}

	/**
	 * Sets the unique.
	 * 
	 * @param unique the new unique
	 */
	public void setUnique(boolean unique) {
		this.unique = unique;
	}

	/**
	 * Gets the values.
	 * 
	 * @return the values
	 */
	public TreeSet<String> getValues() {
		return values;
	}

	/**
	 * Sets the values.
	 * 
	 * @param values the new values
	 */
	public void setValues(TreeSet<String> values) {
		this.values = values;
	}

	/**
	 * Checks if is all names.
	 * 
	 * @return true, if is all names
	 */
	public boolean isAllNames() {
		return allNames;
	}

	/**
	 * Sets the all names.
	 * 
	 * @param allNames the new all names
	 */
	public void setAllNames(boolean allNames) {
		this.allNames = allNames;
	}

	/**
	 * Checks if is all nmtoke ns.
	 * 
	 * @return true, if is all nmtoke ns
	 */
	public boolean isAllNMTOKENs() {
		return allNMTOKENs;
	}

	/**
	 * Sets the all nmtoke ns.
	 * 
	 * @param allNMTOKENs the new all nmtoke ns
	 */
	public void setAllNMTOKENs(boolean allNMTOKENs) {
		this.allNMTOKENs = allNMTOKENs;
	}
    
    
}
