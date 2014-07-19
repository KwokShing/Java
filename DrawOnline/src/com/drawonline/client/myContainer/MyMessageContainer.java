package com.drawonline.client.myContainer;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import com.drawonline.client.ui.LoginFrame;

/**
 * 
 * @author Administrator
 * 
 */
public class MyMessageContainer extends JPanel {

	private static final long serialVersionUID = 1L;

	private JTextArea textAre;

	private JTextField txtField;

	private JButton btnClick;
	
	private int count = 3;
	public MyMessageContainer() {
		initLayout();
	}

	private void initLayout() {
		setLayout(new FlowLayout());
		textAre = new JTextArea();
		textAre.setPreferredSize(new Dimension(170, 3900));
		textAre.setLineWrap(true);
		JScrollPane scroPnl = new JScrollPane(textAre);
		textAre.setEditable(false);
		scroPnl.setPreferredSize(new Dimension(170, 360));
		txtField = new JTextField();
		txtField.setPreferredSize(new Dimension(100, 25));
		btnClick = new JButton("send");
		add(scroPnl);
		add(txtField);
		add(btnClick);
		btnClick.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				count--;
				if(count<=0){
					btnClick.setEnabled(false);
				}
				String inputText = txtField.getText();
				if (inputText.length() > 0) {
					LoginFrame.loginFrame.sendsmg("guess",
							LoginFrame.loginFrame.getNickName() + ","
									+ inputText);
					txtField.setText("");
				}
			}
		});
	}

	public void appendMsg(String msg) {
		textAre.append(msg + "\n");
	}
	
	public void setEable(boolean result){
		btnClick.setEnabled(result);
		txtField.setEnabled(result);
	}
}
