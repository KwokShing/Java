package draw3.copy;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ToolMove extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x, y;
	private ImageIcon imageIcon = new ImageIcon("1.png");

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ToolMove() {
		// TODO Auto-generated constructor stub

	}

	public void drawTool(int x, int y) {
		setX(x);
		setY(y);
		repaint();

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imageIcon.getImage(), x, y, null);

	}
}
