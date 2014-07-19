package draw3;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ToolMove extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3488667491853412469L;
	private int x, y;
	private ImageIcon imageIcon = new ImageIcon("1.png");

	public ToolMove(){
		super();
		setOpaque(false);
		//setBounds(0, 0, 500, 500);
		setSize(500, 500);
	}
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

	public void drawTool(int x, int y) {
		//setX(x);
		//setY(y);
		
		Graphics g = this.getGraphics();
		//update(g);
		g.drawImage(imageIcon.getImage(), x, y, null);
		this.repaint();
	}

//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		g.drawImage(imageIcon.getImage(), 0, 0, null);
//	}
}
