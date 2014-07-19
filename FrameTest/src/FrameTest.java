import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.dyno.visual.swing.layouts.GroupLayout;

//VS4E -- DO NOT REMOVE THIS LINE!
public class FrameTest extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel0;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JButton jButton0;
	private JButton jButton1;
	public FrameTest() {
		initComponents();
	}
	private void initComponents() {
		add(getJPanel0(), BorderLayout.NORTH);
		add(getJPanel1(), BorderLayout.EAST);
		add(getJPanel2(), BorderLayout.CENTER);
		setSize(800, 500);
	}
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("jButton1");
		}
		return jButton1;
	}
	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("jButton0");
		}
		return jButton0;
	}
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setBackground(Color.GREEN);
			jPanel2.setLayout(new GroupLayout());
		}
		return jPanel2;
	}
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setBackground(Color.blue);
			jPanel1.setPreferredSize(new Dimension(200, 4));
			jPanel1.setLayout(new GridLayout(2,1));
			jPanel1.add(getJButton0());
			jPanel1.add(getJButton1());
		}
		return jPanel1;
	}
	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBackground(Color.WHITE);
			jPanel0.setPreferredSize(new Dimension(300, 100));
			//jPanel0.setLayout(new GroupLayout());
		}
		return jPanel0;
	}
	
	
}
