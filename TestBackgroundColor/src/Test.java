import java.awt.Button;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Test extends JFrame {
	public Test() {
		// TODO Auto-generated constructor stub
		JPanel panel = new JPanel();
		JButton btn = new JButton("fuck");
		panel.add(btn);
		add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Test();
	}

}
