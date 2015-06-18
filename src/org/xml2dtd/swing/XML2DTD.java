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
package org.xml2dtd.swing;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;


import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

/**
 * The Class XML2DTD.
 */
public class XML2DTD extends JFrame implements ActionListener {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1348572582033247910L;

	/** The Constant COMMAND_INPUT. */
	private static final String COMMAND_INPUT="SINPUT";
	
	/** The Constant COMMAND_OUTPUT. */
	private static final String COMMAND_OUTPUT="SOUTPUT";
	
	/** The Constant COMMAND_CREATE. */
	private static final String COMMAND_CREATE="CREATE";
	
	
	/** The label input file. */
	private JLabel labelInputFile;
	
	/** The input file. */
	private JTextField inputFile;
	
	/** The search input file. */
	private JButton searchInputFile;
	
	/** The label output file. */
	private JLabel labelOutputFile;
	
	/** The output file. */
	private JTextField outputFile;
	
	/** The search output file. */
	private JButton searchOutputFile;
	
	/** The execute button. */
	private JButton executeButton;
	
	
	
	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		}catch (Exception e) { }
		
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	XML2DTD frame = new XML2DTD();
				frame.setDefaultCloseOperation(XML2DTD.EXIT_ON_CLOSE);
				frame.setTitle("XML to DTD");
				frame.getContentPane().setPreferredSize(frame.getSize());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);            	
            }
        });
	}
	
	/**
	 * Instantiates a new xM l2 dtd.
	 */
	public XML2DTD() {
		initComponents();
	}
	

	
	
	/**
	 * Inits the components.
	 */
	private void initComponents(){
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setResizable(false);
		setForeground(Color.black);
		setSize(360, 110);
		
        Container contentPane = this.getContentPane();
        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);
        
        
        
        labelInputFile = new JLabel("Input file");

        inputFile = new JTextField();
        inputFile.setEditable(false);
        inputFile.setColumns(20);
        
        searchInputFile = new JButton("...");
        searchInputFile.setActionCommand(COMMAND_INPUT);
        searchInputFile.addActionListener(this);
        
        labelOutputFile = new JLabel("Output file");
        outputFile = new JTextField();
        outputFile.setEditable(false);
        outputFile.setColumns(20);
        
        
        searchOutputFile = new JButton("...");
        searchOutputFile.setActionCommand(COMMAND_OUTPUT);
        searchOutputFile.addActionListener(this);
        
        executeButton = new JButton("Create");
        executeButton.setMnemonic(KeyEvent.VK_C);
        executeButton.setActionCommand(COMMAND_CREATE);
        executeButton.addActionListener(this);
        
        add(labelInputFile);
        add(inputFile);
        add(searchInputFile);
        
        add(labelOutputFile);
        add(outputFile);
        add(searchOutputFile);
        add(executeButton);
        
		layout.putConstraint(SpringLayout.WEST, labelInputFile,5,SpringLayout.WEST, contentPane);
		layout.putConstraint(SpringLayout.NORTH, labelInputFile,5,SpringLayout.NORTH, contentPane);
		
		layout.putConstraint(SpringLayout.WEST, inputFile,20,SpringLayout.EAST, labelInputFile);
		layout.putConstraint(SpringLayout.NORTH, inputFile,5,SpringLayout.NORTH, contentPane);
		
		layout.putConstraint(SpringLayout.WEST, searchInputFile,5,SpringLayout.EAST, inputFile);
		layout.putConstraint(SpringLayout.NORTH, searchInputFile,5,SpringLayout.NORTH, contentPane);
		
		
		layout.putConstraint(SpringLayout.WEST, labelOutputFile,0,SpringLayout.WEST, labelInputFile);
		layout.putConstraint(SpringLayout.NORTH, labelOutputFile,15,SpringLayout.SOUTH, labelInputFile);		

		layout.putConstraint(SpringLayout.WEST, outputFile,0,SpringLayout.WEST, inputFile);
		layout.putConstraint(SpringLayout.NORTH, outputFile,15,SpringLayout.SOUTH, inputFile);		
		
		layout.putConstraint(SpringLayout.WEST, searchOutputFile,0,SpringLayout.WEST, searchInputFile);
		layout.putConstraint(SpringLayout.NORTH, searchOutputFile,10,SpringLayout.SOUTH, searchInputFile);				
		
		layout.putConstraint(SpringLayout.WEST, executeButton,0,SpringLayout.WEST, outputFile);
		layout.putConstraint(SpringLayout.NORTH, executeButton,10,SpringLayout.SOUTH, outputFile);	
		

	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (COMMAND_INPUT.equals(e.getActionCommand())){
			JFileChooser fileChooser = new JFileChooser();
			int returnVal = fileChooser.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
		        File file = fileChooser.getSelectedFile();
		        this.inputFile.setText(file.getPath());
			}
		}else if (COMMAND_OUTPUT.equals(e.getActionCommand())){
			JFileChooser fileChooser = new JFileChooser();
			int returnVal = fileChooser.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
		        File file = fileChooser.getSelectedFile();
		        this.outputFile.setText(file.getPath());
			}
		}else if (COMMAND_CREATE.equals(e.getActionCommand())){
			org.xml2dtd.XML2DTD dtd = new org.xml2dtd.XML2DTD();
			try {
				String content = dtd.run(this.inputFile.getText());
				PrintWriter out = new PrintWriter(new FileWriter(new File(this.outputFile.getText())));
		        out.print(content);
		        out.close();
		        JOptionPane.showMessageDialog(this, "DTD File is generated in " + this.outputFile.getText(),"DTD Generated",JOptionPane.INFORMATION_MESSAGE);
			} catch (Throwable e1) {
				 JOptionPane.showMessageDialog(this, e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
}
