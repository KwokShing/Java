package draw3;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 * The class penView is to change the view once the model is changed
 * 
 * @author Kross
 * 
 */
public class penView extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	/**
	 * The model is to save the current model
	 */
	protected PenModel model = new PenModel();

	/**
	 * The penView's constructor, setting the background to white
	 */
	public penView() {
		// TODO Auto-generated constructor stub
		super();
		//setSize(500, 500);
		setBounds(0, 0, 500, 500);
		setBackground(Color.WHITE);
	}

	/**
	 * The penView's constructor with the model parameter
	 * 
	 * @param model
	 */
	public penView(PenModel model) {
		this();
		this.model = model;
	}

	/**
	 * The override method is corresponded to the controller
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// Graphics tmp = this.getGraphics();
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
	/**
	 * The getModel method is to change the model once the socket sends the data
	 * 
	 * @param model
	 */
	public void changeModel(PenModel model) {
		this.model = model;
		actionPerformed(null);
	}

	/**
	 * The setModel method is to obtain the model from the initial frame
	 * 
	 * @param newmodel
	 * @param words
	 */
	public void setModel(PenModel newmodel, String words) {
		model = newmodel;
		model.setWords(words);
		if (model != null)
			model.addActionListener(this);
	}

	/**
	 * The getModel is to get the model
	 * 
	 * @return model
	 */
	public PenModel getModel() {
		return model;
	}
}
