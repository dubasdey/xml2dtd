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


/**
 * The Class ChildDetails.
 */
public class ChildDetails {
	
    /** The name. */
    private String name;
    
    /** The position. */
    private int position;
    
    /** The repeatable. */
    private boolean repeatable;
    
    /** The optional. */
    private boolean optional;
    
    
    /**
     * Instantiates a new child details.
     */
    public ChildDetails(){}
    
    /**
     * Instantiates a new child details.
     * 
     * @param name the name
     * @param position the position
     * @param repeatable the repeatable
     * @param optional the optional
     */
    public ChildDetails(String name,int position,boolean repeatable,boolean optional){
    	this.name =name;
    	this.position= position;
    	this.repeatable=repeatable;
    	this.optional = optional;
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
	 * Gets the position.
	 * 
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}
	
	/**
	 * Sets the position.
	 * 
	 * @param position the new position
	 */
	public void setPosition(int position) {
		this.position = position;
	}
	
	/**
	 * Checks if is repeatable.
	 * 
	 * @return true, if is repeatable
	 */
	public boolean isRepeatable() {
		return repeatable;
	}
	
	/**
	 * Sets the repeatable.
	 * 
	 * @param repeatable the new repeatable
	 */
	public void setRepeatable(boolean repeatable) {
		this.repeatable = repeatable;
	}
	
	/**
	 * Checks if is optional.
	 * 
	 * @return true, if is optional
	 */
	public boolean isOptional() {
		return optional;
	}
	
	/**
	 * Sets the optional.
	 * 
	 * @param optional the new optional
	 */
	public void setOptional(boolean optional) {
		this.optional = optional;
	}

    
}
