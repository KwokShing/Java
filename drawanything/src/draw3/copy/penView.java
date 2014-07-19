package draw3.copy;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class penView extends JPanel implements ActionListener {
	protected PenModel model = new PenModel();

	public penView() {
		// TODO Auto-generated constructor stub
		super();
		setSize(500, 500);
		setBackground(Color.WHITE);
	}

	public penView(PenModel model) {
		this();
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Graphics tmp = this.getGraphics();
		Graphics g = this.getGraphics();
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(model.getPenSize(),
				BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g.setColor(model.getPenColor());
		g.drawLine(model.getOx(), model.getOy(), model.getX(), model.getY());
		// System.out.println(model.getOx() + " " + model.getOy() + " "
		// + model.getX() + " " + model.getY()+" "+model.getPenColor());
		// System.out.println("fuck");
		// tmp = this.getGraphics();
		// this.paint(tmp);
		// repaint();
	}

	// public void paintComponent(Graphics g) {
	// super.paintComponent(g);
	//
	// }

	// public void test(int ox, int oy, int x, int y){
	// Graphics g = this.getGraphics();
	// g.drawLine(ox,oy,x,y);
	// }
	public void getModel(PenModel model) {
		this.model = model;
		actionPerformed(null);
	}

	public void setModel(PenModel newmodel, String words) {
		model = newmodel;
		model.setWords(words);
		if (model != null)
			model.addActionListener(this);
	}

	public PenModel getModel() {
		return model;
	}
}
