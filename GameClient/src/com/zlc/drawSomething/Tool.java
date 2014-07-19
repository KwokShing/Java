package com.zlc.drawSomething;

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

/**
 * The class tool is to handle the button action
 * 
 * @author Kross
 * 
 */
public class Tool extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1175104381021732309L;
	/** The viewCtrl is to control the view */
	private penViewController viewCtrl;
	/** Temporary color for pen */
	private Color tmpColor;
	/** Color for pen */
	private JButton penCol = new JButton();
	/** Eraser for pen */
	private JButton eraser = new JButton();
	/** Size for pen */
	private JButton penSize = new JButton();
	/** The pen entity */
	private JButton pen = new JButton();
	/** Pen's icon */
	private ImageIcon ipen = new ImageIcon("3.png");
	/** Size's icon */
	private ImageIcon isize = new ImageIcon("4.png");
	/** Palette's icon */
	private ImageIcon ipalette = new ImageIcon("5.png");
	/** Eraser's icon */
	private ImageIcon ieraser = new ImageIcon("6.png");
	/** The slider to choose pen's size */
	private JSlider slider = new JSlider();
	/** The dialog is for the slider */
	private JDialog sizeDialog = new JDialog();

	/**
	 * The class Tool's constructor, adding the button elements
	 */
	public Tool() {
		// TODO Auto-generated constructor stub
		super();
		// setBounds(0, 0, 100, 500);
		setLayout(new GridLayout(4, 1));
		pen.setIcon(ipen);
		penCol.setIcon(ipalette);
		eraser.setIcon(ieraser);
		penSize.setIcon(isize);
		add(pen);
		add(penCol);
		add(penSize);
		add(eraser);

	}

	/**
	 * The class Tool's constructor with the constoller
	 * 
	 * @param viewCtrl
	 */
	public Tool(penViewController viewCtrl) {
		this();
		this.viewCtrl = viewCtrl;
		if (viewCtrl.getActiveControl() == true) {
			pen.addActionListener(this);
			penSize.addActionListener(this);
			penCol.addActionListener(this);
			eraser.addActionListener(this);
		}
		slider.setValue(5);
	}

	/**
	 * The override actionPerformed method is to accomplish the button action
	 */
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

	/**
	 * The selectColor method is to choose the pen's color
	 */
	public void selectColor() {
		tmpColor = JColorChooser.showDialog(this, "color", Color.BLACK);
	}

	/**
	 * The setSlider method is to change the pen's size
	 */
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

	/** The dialog is for the slider */
	public void setDialog() {
		sizeDialog.add(slider);
		sizeDialog.setSize(200, 100);
		sizeDialog.setVisible(true);
	}
}
