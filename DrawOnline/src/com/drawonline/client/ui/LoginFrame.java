package com.drawonline.client.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.drawonline.client.client.MyClient;
/**
 * xxx
 * @author lw
 *
 */
public class LoginFrame extends JFrame{
	
	public static void main(String[] args) {
		new LoginFrame();
	}
	
	private static final long serialVersionUID = 1L;
	
	public static LoginFrame loginFrame;
	
	private MyClient client;
	
	public LoginFrame(){
		initUI();
		initLayout();
		setVisible(true);
		loginFrame = this;
	}
	
	private void initUI(){
		setSize(new Dimension(300,300));
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initLayout(){
		setLayout(null);
		JLabel lbl = new JLabel(" ‰»ÎÍ«≥∆:");
		final JTextField txtInput = new JTextField();
		JButton btnLogin = new JButton("µ«¬Ω");
		
		lbl.setBounds(50, 100, 65, 25);
		txtInput.setBounds(115, 100, 150, 25);
		btnLogin.setBounds(220, 220, 60, 30);
		
		add(lbl);
		add(txtInput);
		add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nickName = txtInput.getText();
				if(nickName.length() <= 0){
					JOptionPane.showMessageDialog(null, "Í«≥∆≤ªƒ‹Œ™ø’");
					return;
				}else{
					client = new MyClient(nickName);
				}
			}
		});
		
	}
	
	public void sendsmg(String cmd,Object obj){
		client.sendData(cmd, obj);
	}
	
	public String getNickName(){
		return client.nickName;
	}
}
