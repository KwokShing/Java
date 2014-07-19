package com.drawonline.client.myContainer;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
/**
 * xxx
 * @author lw
 *
 */
public class MyShowUserConainer extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public MyShowUserConainer(){
		setBorder(new TitledBorder("µ±Ç°Íæ¼Ò"));
		setLayout(new FlowLayout(FlowLayout.LEFT,20,20));
	}
	
	public void addUser(String nickName){
		JLabel lblName = new JLabel(nickName);
		add(lblName);
		repaint();
		validate();
	}
	
	public void removeUser(){
		removeAll();
		repaint();
		validate();
	}
	
	public void updatePreColor(String nickName){
		Component[] components = this.getComponents();
		for(int i = 0 ;i < components.length ; i++){
			Component component = components[i];
			if(component instanceof JLabel){
				JLabel lbl = (JLabel)component;
				String showText = lbl.getText();
				if(showText.equals(nickName)){
					lbl.setForeground(Color.RED);
					lbl.repaint();
					lbl.validate();
					break;
				}
			}
		}
	}
}
