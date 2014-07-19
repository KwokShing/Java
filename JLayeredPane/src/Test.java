import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Test extends JFrame {
	private static long serialVersionUID = 4785452373598819719L;

	public Test() {
		JFrame frame = new JFrame();
		JLayeredPane layeredPane = new JLayeredPane();

		// layeredPane.setBounds(0,0,300,300);
		JPanel panelBg = new JPanel();

		ImageIcon imageIcon = new ImageIcon("4.png");

		JLabel bg = new JLabel(imageIcon);

		panelBg.setBounds(0, 0, 300, 300);
		panelBg.add(bg);

		JPanel panelContent = new JPanel();
		JButton button = new JButton("button 1");

		panelContent.setBounds(0, 0, 300, 300);
		panelContent.setOpaque(false);
		panelContent.add(button);

		layeredPane.add(panelBg, new Integer(0));
		layeredPane.add(panelContent, new Integer(1));

		frame.add(layeredPane, BorderLayout.CENTER);

		// frame.pack();
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public static void main(String args[]) {
		Test frame = new Test();
	}
}
