package draw3.copy;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Tool extends JPanel implements ActionListener {
	private penViewController viewCtrl;
	private Color tmpColor;
	private JButton penCol = new JButton();
	private JButton eraser = new JButton();
	private JButton penSize = new JButton();
	private JButton pen = new JButton();
	private ImageIcon ipen = new ImageIcon("3.png");
	private ImageIcon isize = new ImageIcon("4.png");
	private ImageIcon ipalette = new ImageIcon("5.png");
	private ImageIcon ieraser = new ImageIcon("6.png");
	private JSlider slider = new JSlider();
	private JDialog sizeDialog = new JDialog();

	public Tool() {
		// TODO Auto-generated constructor stub
		super();
		setBounds(0, 0, 100, 500);
		setLayout(new GridLayout(4, 1));
		pen.setIcon(ipen);
		penCol.setIcon(ipalette);
		eraser.setIcon(ieraser);
		penSize.setIcon(isize);
		pen.addActionListener(this);
		penSize.addActionListener(this);
		penCol.addActionListener(this);
		eraser.addActionListener(this);
		add(pen);
		add(penCol);
		add(penSize);
		add(eraser);
	}

	public Tool(penViewController viewCtrl) {
		this();
		this.viewCtrl = viewCtrl;
		slider.setValue(5);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == pen) {
			viewCtrl.changeColor(tmpColor);
		} else if (e.getSource() == penCol) {
			selectColor();
			viewCtrl.changeColor(tmpColor);
		} else if (e.getSource() == penSize) {
			setSlider();
			setDialog();
		} else if (e.getSource() == eraser) {
			viewCtrl.useEraser();
		}
	}

	public void selectColor() {
		tmpColor = JColorChooser.showDialog(this, "color", Color.BLACK);
	}

	public void setSlider() {
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				float size = slider.getValue();
				viewCtrl.changeSize(size);
			}
		});
		slider.setMaximum(10);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(1);
		slider.setMinorTickSpacing(1);
		slider.setPaintTrack(false);
	}

	public void setDialog() {
		sizeDialog.add(slider);
		sizeDialog.setSize(200, 100);
		sizeDialog.setVisible(true);
	}
}
