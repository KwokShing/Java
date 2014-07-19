import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class JLayeredPane_1 extends JFrame {

	public JLayeredPane_1() {
		Container container = getContentPane();

		final JLayeredPane layeredPane = new JLayeredPane();

		Dimension dimension = new Dimension(300, 310);
		layeredPane.setPreferredSize(dimension);

		final JPanel panelBg = new JPanel();
		final Image imageBg = Toolkit.getDefaultToolkit().getImage(
				this.getClass().getResource("4.png"));
		ImageIcon imageIcon = new ImageIcon(imageBg.getScaledInstance(
				dimension.width, dimension.height, Image.SCALE_FAST));
		final JLabel bg = new JLabel(imageIcon);
		final Point origin = new Point(10, 30);
		final Rectangle rectangle = new Rectangle(origin, dimension);
		panelBg.setBounds(rectangle);
		panelBg.add(bg);

		final JPanel panelContent = new JPanel();
		JButton button = new JButton("button 1");

		panelContent.setBounds(rectangle);
		panelContent.setOpaque(false); // ÉèÖÃÎªÍ¸Ã÷
		panelContent.add(button);


		layeredPane.add(panelBg, new Integer(0)); // the same to
													// layeredPane.add(panelBg);
		layeredPane.add(panelContent, new Integer(1));

		container.add(layeredPane, BorderLayout.CENTER);

		addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				Dimension dimensionNew = layeredPane.getSize();
				Rectangle rectangleNew = new Rectangle(origin, dimensionNew);
				panelBg.setBounds(rectangleNew);
				panelContent.setBounds(rectangleNew);
				bg.setIcon(new ImageIcon(imageBg.getScaledInstance(
						dimensionNew.width, dimensionNew.height,
						Image.SCALE_FAST)));
			}
		});
	}

	public static void main(String[] args) {

		JLayeredPane_1 frame = new JLayeredPane_1();
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
