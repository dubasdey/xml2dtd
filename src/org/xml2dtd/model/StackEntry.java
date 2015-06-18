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
 * The Class StackEntry.
 */
public class StackEntry {
	
	/** The element details. */
	private ElementDetails elementDetails;
	
	/** The sequence number. */
	private int sequenceNumber;
	
	/** The latest child. */
	private String latestChild;
	
	/**
	 * Gets the element details.
	 * 
	 * @return the element details
	 */
	public ElementDetails getElementDetails() {
		return elementDetails;
	}
	
	/**
	 * Sets the element details.
	 * 
	 * @param elementDetails the new element details
	 */
	public void setElementDetails(ElementDetails elementDetails) {
		this.elementDetails = elementDetails;
	}
	
	/**
	 * Gets the sequence number.
	 * 
	 * @return the sequence number
	 */
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	
	/**
	 * Sets the sequence number.
	 * 
	 * @param sequenceNumber the new sequence number
	 */
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	
	/**
	 * Gets the latest child.
	 * 
	 * @return the latest child
	 */
	public String getLatestChild() {
		return latestChild;
	}
	
	/**
	 * Sets the latest child.
	 * 
	 * @param latestChild the new latest child
	 */
	public void setLatestChild(String latestChild) {
		this.latestChild = latestChild;
	}


}
