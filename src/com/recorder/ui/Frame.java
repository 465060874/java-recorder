package com.recorder.ui;

import javax.swing.JFrame;


public class Frame extends JFrame {
	
	/***
	 * Construct the Frame class
	 * @param name - title of the frame
	 */
	public Frame(String name) {
		initialize(name);
	}
	
	private void initialize(String name) {
		this.setTitle(name);
		this.setSize(800, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
