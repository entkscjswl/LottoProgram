package com.lotto;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;

public class MyButton extends JButton{

	public MyButton(String btnLabel) {
		super(btnLabel);
		setBorderPainted(false);  // false / true   
		setOpaque(false);
	}
	
	@Override
	public void paint(Graphics g){
		int width = getWidth();
		int height = getHeight();
		g.setColor(Color.red);
		
		g.fillRoundRect(0, 0, width, height, 50, 50);
		g.setColor(Color.white);
		g.drawString(getText(), 
				(int)getSize().getWidth()/2-10, (int)getSize().getHeight()/2+5);
  }

}
