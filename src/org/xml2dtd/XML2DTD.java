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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml2dtd.model.AttributeDetails;
import org.xml2dtd.model.ChildDetails;
import org.xml2dtd.model.ElementDetails;
import org.xml2dtd.model.StackEntry;


/**
 * The Class XML2DTD.
 */
public class XML2DTD extends DefaultHandler{
	

    protected static int MIN_ENUMERATION_INSTANCES = 10;
    
    protected static int MAX_ENUMERATION_VALUES = 20;
    
    protected static int MIN_ENUMERATION_RATIO = 3;
    
    protected static int MIN_FIXED = 5;
    
    protected static int MIN_ID_VALUES = 10;
    
    protected static int MAX_ID_VALUES = 0x186a0;
    
    
    /** The element list. */
    private Map<String,ElementDetails> elementList;
    
    /** The element stack. */
    private Stack<StackEntry> elementStack;

    /** The result buffer. */
    private StringBuffer resultBuffer;
    
    /**
     * Instantiates a new XML2DTD.
     */
    public XML2DTD() {
        elementList = new TreeMap<String,ElementDetails>();
        elementStack = new Stack<StackEntry>();
    }

    /**
     * Run parser
     * 
     * @param s File to be parser
     * 
     * @return DTD document (as String)
     * 
     * @throws SAXException						SAX Parser exception
     * @throws ParserConfigurationException 	the parser configuration exception
     * @throws IOException 						Signals that an I/O exception has occurred.
     */
    public String run(String s) throws SAXException, ParserConfigurationException, IOException  {
    	resultBuffer = new StringBuffer();
    	
    	// Read XML and prepare content as Objects
        InputSource inputsource = new InputSource(new FileInputStream(new File(s)));
        XMLReader xmlreader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
        xmlreader.setContentHandler(this);
        xmlreader.parse(inputsource);
        
        
        printDTD();
        return resultBuffer.toString();
    }

    
    /**
     * Prints the TDT content.
     */
    private void printDTD() {
    	
    	for(Map.Entry<String, ElementDetails> entry: elementList.entrySet()){
    		resultBuffer.append("\n");

            ElementDetails elementdetails = entry.getValue();
            Map<String,ChildDetails> elementDetailsChildrens = elementdetails.getChildren();
            Set<String> elementDetailsChildrensKeys = elementDetailsChildrens.keySet();
            
            if(elementDetailsChildrensKeys.size() == 0 && !elementdetails.isCharacterContent() ) {
            	resultBuffer.append("<!ELEMENT ").append(entry.getKey()).append(" EMPTY >");
                
            }else if(elementDetailsChildrensKeys.size() == 0 && elementdetails.isCharacterContent()) {
            	resultBuffer.append("<!ELEMENT ").append(entry.getKey()).append(" ( #PCDATA ) >");
                
            }else if(elementDetailsChildrensKeys.size() > 0 && !elementdetails.isCharacterContent()) {
            	resultBuffer.append("<!ELEMENT ").append(entry.getKey()).append(" ( ");
                
                if(elementdetails.isSequenced()){
                	for(int i=0;i<elementdetails.getChildseq().size();i++){
                		ChildDetails childdetails = elementdetails.getChildseq().get(i);
                		resultBuffer.append(childdetails.getName());

                        if(childdetails.isRepeatable() && !childdetails.isOptional()){
                        	resultBuffer.append("+");
                        }else  if(childdetails.isRepeatable() && childdetails.isOptional()) {
                        	resultBuffer.append("*");
                        } else  if(!childdetails.isRepeatable() && childdetails.isOptional()) {
                        	resultBuffer.append("?");
                        }
                        
                        // If not is the last add a "," to separate the elements
                        if (i<(elementdetails.getChildseq().size()-1)){
                        	resultBuffer.append(", ");
                        }
                        
                	}
                	resultBuffer.append(" ) >");
                	
                } else {
                    for(Iterator<String> iterator1 = elementDetailsChildrensKeys.iterator(); iterator1.hasNext();) {
                    	resultBuffer.append(iterator1.next());
                        if(iterator1.hasNext()) {
                        	resultBuffer.append(" | ");
                        }
                    }
                    resultBuffer.append(" )* >");
                }
            }
            
            
            if(elementDetailsChildrensKeys.size() > 0 && elementdetails.isCharacterContent()) {
            	resultBuffer.append("\n");
            	resultBuffer.append("<!ELEMENT ").append( entry.getKey()).append(" ( #PCDATA");
                for(Iterator<String> iterator2 = elementDetailsChildrensKeys.iterator(); iterator2.hasNext(); resultBuffer.append(" | " + iterator2.next())) {}
                resultBuffer.append(" )* >");
            }

            boolean flag = false;
            for(Map.Entry<String,AttributeDetails> entryAtribute : elementdetails.getAttributes().entrySet()){
            	 
            	AttributeDetails attributedetails = entryAtribute.getValue();
            	 
                 boolean flag1 = attributedetails.getOccurrences() == elementdetails.getOccurrences();
                 boolean flag2 = attributedetails.isAllNames() && !flag && attributedetails.isUnique() && attributedetails.getOccurrences() >= MIN_ID_VALUES;
                 boolean flag3 = flag1 && attributedetails.getValues().size() == 1 && attributedetails.getOccurrences() >= MIN_FIXED;
                 boolean flag4 = attributedetails.isAllNMTOKENs()  && attributedetails.getOccurrences() >= MIN_ENUMERATION_INSTANCES && attributedetails.getValues().size() <= attributedetails.getOccurrences() / MIN_ENUMERATION_RATIO && attributedetails.getValues().size() <= MAX_ENUMERATION_VALUES;
                 
                 resultBuffer.append("<!ATTLIST ").append( entry.getKey()).append(" ").append(entryAtribute.getKey()).append(" ");
                 
                 String nmTokenOrCData = attributedetails.isAllNMTOKENs() ? "NMTOKEN" : "CDATA";
                 
                 if(flag2) {
                	 resultBuffer.append("ID");
                     flag = true;
                 } else if(flag3) {
                     resultBuffer.append(nmTokenOrCData).append(" #FIXED \"").append(Utils.escape(attributedetails.getValues().first())).append("\" >\n");
                 } else if(flag4) {
                	 resultBuffer.append("( ");
                     for(Iterator<String> iterator4 = attributedetails.getValues().iterator(); iterator4.hasNext(); resultBuffer.append(" | ")) {
                    	 resultBuffer.append(iterator4.next());
                         if(!iterator4.hasNext()) {
                             break;
                         }
                     }
                     resultBuffer.append(" )");
                 } else {
                	 resultBuffer.append(nmTokenOrCData);
                 }
                 
                 if(!flag3) {
                     if(flag1) {
                    	 resultBuffer.append(" #REQUIRED >\n");
                     } else {
                    	 resultBuffer.append(" #IMPLIED >\n");
                     }
                 }
                 
                 
            }
    	}
    }


    /* (non-Javadoc)
     * @see org.xml.sax.helpers.DefaultHandler#startElement(java.lang.String, java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        StackEntry stackentry = new StackEntry();
        ElementDetails elementdetails = elementList.get(qName);
        
        // Element exists or is new?
        if(elementdetails == null) {
            elementdetails = new ElementDetails(qName);
            elementList.put(qName, elementdetails);
        }
        
        // 
        stackentry.setElementDetails(elementdetails);
        stackentry.setSequenceNumber(-1);
        
        // Add ocurences to element (to determine if is 1 or 1+)
        elementdetails.setOccurrences(elementdetails.getOccurrences()+1);
        
        // Read element attributes
        for(int i = 0; i < attributes.getLength(); i++) {
            String atributeQname = attributes.getQName(i);
            String atributeValue = attributes.getValue(i);
            
            AttributeDetails attributedetails = elementdetails.getAttributes().get(atributeQname);
            
            if(attributedetails == null) {
                attributedetails = new AttributeDetails(atributeQname);
                elementdetails.getAttributes().put(atributeQname, attributedetails);
            }
            
            if(!attributedetails.getValues().contains(atributeValue)) {
                attributedetails.getValues().add(atributeValue);
                
                if(attributedetails.isAllNames()  && !Utils.isValidName(atributeValue)) {
                    attributedetails.setAllNames(false);
                }
                
                if(attributedetails.isAllNMTOKENs()  && !Utils.isValidNMTOKEN(atributeValue)){
                    attributedetails.setAllNMTOKENs(false);
                }
                
                if(attributedetails.isUnique() && attributedetails.isAllNames() && attributedetails.getOccurrences() <= MAX_ID_VALUES){
                    attributedetails.getValues().add(atributeValue);
                } else if(attributedetails.getValues().size() <= MAX_ENUMERATION_VALUES) {
                    attributedetails.getValues().add(atributeValue);
                }
            } else{
                attributedetails.setUnique(false);
            }
            
            
            attributedetails.setOccurrences(attributedetails.getOccurrences()+1);
        }

        if(!elementStack.isEmpty()) {
            StackEntry stackentry1 = elementStack.peek();
            
            ElementDetails elementdetails1 = stackentry1.getElementDetails();
            int j = stackentry1.getSequenceNumber();
            
            boolean flag = stackentry1.getLatestChild() == null || !stackentry1.getLatestChild().equals(qName);
            
            if(flag){
                j++;
                stackentry1.setSequenceNumber(stackentry1.getSequenceNumber()+1);
            }
            
            stackentry1.setLatestChild(qName);
            Map<String,ChildDetails> treemap = elementdetails1.getChildren();
            ChildDetails childdetails = treemap.get(qName);
            if(childdetails == null) {
                childdetails = new ChildDetails(qName,j,false,false);
                treemap.put(qName, childdetails);
                elementdetails1.getChildseq().add(childdetails);
                if(elementdetails1.getOccurrences() != 1){
                    childdetails.setOptional(true);
                }
                
            } else{
                if(elementdetails1.getOccurrences() == 1 && flag){
                    elementdetails1.setSequenced(false);
                }
                
                if(elementdetails1.getChildseq().size() <= j || !((ChildDetails)elementdetails1.getChildseq().get(j)).getName().equals(qName)) {
                    elementdetails1.setSequenced(false);
                }
            }
            
            if(!flag){
                childdetails.setRepeatable(true);
            }
            
        }
        elementStack.push(stackentry);
    }

    /* (non-Javadoc)
     * @see org.xml.sax.helpers.DefaultHandler#endElement(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        ElementDetails elementdetails = elementList.get(qName);
        if(elementdetails.isSequenced()){
            StackEntry stackentry = elementStack.peek();
            int i = stackentry.getSequenceNumber();
            for(int j = i + 1; j < elementdetails.getChildseq().size(); j++) {
                elementdetails.getChildseq().get(j).setOptional(true);
            }
        }
        elementStack.pop();
    }

    /* (non-Javadoc)
     * @see org.xml.sax.helpers.DefaultHandler#characters(char[], int, int)
     */
    public void characters(char ac[], int i, int j) throws SAXException {
        ElementDetails elementdetails = elementStack.peek().getElementDetails() ;
        if(!elementdetails.isCharacterContent()) {
            for(int k = i; k < i + j; k++) {
                if(ac[k] <= ' '){
                    continue;
                }
                elementdetails.setCharacterContent(true);
                break;
            }

        }
    }
}
