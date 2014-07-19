import java.awt.*;

import javax.swing.*;

public class TestBackgroundColor extends JFrame {

	private JPanel imagePanel = new JPanel();
	private ImageIcon background;
	private JButton btn = new JButton("test");

	public TestBackgroundColor() {
		background = new ImageIcon("2.jpg");

		JLabel label = new JLabel(background);

		label.setBounds(0, 0, background.getIconWidth(),
				background.getIconHeight());

		imagePanel.add(label);
		imagePanel.setBounds(0, 0, 300,500);
		 //imagePanel.setOpaque(false);

		JLayeredPane layer = new JLayeredPane();
		
		

		JPanel btnPanel = new JPanel();
		JPanel testPanel = new JPanel();
		testPanel.add(new JButton("damn"));
		// panel.setOpaque(false);
		btnPanel.add(btn);
		btnPanel.setBounds(10, 10, 50, 50);
		btnPanel.setOpaque(false);
		layer.add(imagePanel, new Integer(0));
		layer.add(btnPanel, new Integer(1));

		JPanel ttt = new JPanel(new GridLayout(2,1));
		ttt.add(layer);
		ttt.add(testPanel);

		add(ttt);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(background.getIconWidth(), background.getIconHeight());
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestBackgroundColor tbc = new TestBackgroundColor();
		tbc.setVisible(true);
	}
}
