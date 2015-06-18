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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The Class ElementDetails.
 */
public class ElementDetails {
	
	/** The name. */
	private String name;
	
	/** The occurrences. */
	private int occurrences;
	
    /** The character content. */
    private boolean characterContent;
    
    /** The sequenced. */
    private boolean sequenced;
    
    /** The children. */
    private Map<String,ChildDetails>  children;
    
    /** The childseq. */
    private List<ChildDetails> childseq;
    
    /** The attributes. */
    private Map<String,AttributeDetails> attributes;

    /**
     * Instantiates a new element details.
     * 
     * @param s the s
     */
    public ElementDetails(String s)
    {
        name = s;
        occurrences = 0;
        characterContent = false;
        sequenced = true;
        children = new TreeMap<String,ChildDetails> ();
        childseq = new ArrayList<ChildDetails>();
        attributes = new TreeMap<String,AttributeDetails> ();
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
	 * Checks if is character content.
	 * 
	 * @return true, if is character content
	 */
	public boolean isCharacterContent() {
		return characterContent;
	}

	/**
	 * Sets the character content.
	 * 
	 * @param hasCharacterContent the new character content
	 */
	public void setCharacterContent(boolean hasCharacterContent) {
		this.characterContent = hasCharacterContent;
	}

	/**
	 * Checks if is sequenced.
	 * 
	 * @return true, if is sequenced
	 */
	public boolean isSequenced() {
		return sequenced;
	}

	/**
	 * Sets the sequenced.
	 * 
	 * @param sequenced the new sequenced
	 */
	public void setSequenced(boolean sequenced) {
		this.sequenced = sequenced;
	}

	/**
	 * Gets the children.
	 * 
	 * @return the children
	 */
	public Map<String,ChildDetails> getChildren() {
		return children;
	}

	/**
	 * Sets the children.
	 * 
	 * @param children the children
	 */
	public void setChildren(Map<String,ChildDetails>  children) {
		this.children = children;
	}

	/**
	 * Gets the childseq.
	 * 
	 * @return the childseq
	 */
	public List<ChildDetails> getChildseq() {
		return childseq;
	}

	/**
	 * Sets the childseq.
	 * 
	 * @param childseq the new childseq
	 */
	public void setChildseq(List<ChildDetails> childseq) {
		this.childseq = childseq;
	}

	/**
	 * Gets the attributes.
	 * 
	 * @return the attributes
	 */
	public Map<String,AttributeDetails>  getAttributes() {
		return attributes;
	}

	/**
	 * Sets the attributes.
	 * 
	 * @param attributes the attributes
	 */
	public void setAttributes(Map<String,AttributeDetails>  attributes) {
		this.attributes = attributes;
	}
    
    
}
