import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Label;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;


public class ITest {
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JPanel panel2 = new JPanel();
	private JLabel label = new JLabel(new ImageIcon("4.png"));

	JLayeredPane layer = new JLayeredPane();
	JButton button = new JButton("fuck");
	public ITest() {
		// TODO Auto-generated constructor stub
		
		
		
		panel2.setOpaque(false);
		panel2.add(button);

		//panel.add(label);
		panel.setBounds(0,0,300,300);
		panel2.setBounds(0,0,300,300);
		//frame.add(panel,new Integer(0));
		//frame.add(panel2,new Integer(1));
		
		layer.add(panel,new Integer(0));
		layer.add(panel2,new Integer(1));
		frame.add(layer);
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	public static void main(String[] args) {
		ITest test = new ITest();
		JButton btn = new JButton("damn");
		ImageIcon i = new ImageIcon("4.png");
		JLabel l = new JLabel(i);
		Graphics g = test.panel.getGraphics();
		g.drawLine(0, 0, 10, 10);
		//JLabel l = new JLabel(g);
		//test.panel.add(g);
		
	}
}
