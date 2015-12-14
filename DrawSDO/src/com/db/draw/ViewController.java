package com.db.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ViewController extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 778172202104077060L;
	public static JCheckBox checkBox = null;
	private Panel panel = null;
	private ActionListener actionListener = null;
	private Model model = null;

	public ViewController(Model m) {
		this.model = m;
		checkBox = new JCheckBox("show lions and ponds in the selected region", true);
		checkBox.setBounds(0, 530, 500, 20);
		actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panel.repaint();
			}
		};
		checkBox.addActionListener(actionListener);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				model.closeDB();
			}
		});

		panel = new Panel(m);
		panel.setBounds(0, 0, 500, 500);
		setLayout(null);
		add(checkBox);
		add(panel);
		setTitle("SDO");
		setSize(600, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	static class Panel extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = -8847224311485777239L;
		private Model model;
		private int x = 0;
		private int y = 0;
		private ArrayList<double[]> newLionPos = new ArrayList<double[]>();
		private ArrayList<double[]> newPondPos = new ArrayList<double[]>();

		public Panel(Model m) {
			this.model = m;
			addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (checkBox.isSelected() == true) {
						x = e.getX();
						y = e.getY();
						newLionPos = m.sendMousePosition(x, y, "Lion");
						newPondPos = m.sendMousePosition(x, y, "Pond");
						repaint();
					}
				}
			});
		}

		protected void paintComponent(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			super.paintComponent(g2d);
			g2d.setColor(Color.GREEN);
			double[] pointCordinate;
			if (checkBox.isSelected() == false) {
				newPondPos.clear();
				newLionPos.clear();
			}
			for (Entry<String, double[]> entry : model.getGeoLionMap().entrySet()) {
				pointCordinate = entry.getValue();
				int x = (int) pointCordinate[0];
				int y = (int) pointCordinate[1];
				g2d.fillOval(x, y, 5, 5);
			}
			for (Entry<String, double[]> entry2 : model.getGeoPondMap().entrySet()) {
				pointCordinate = entry2.getValue();
				int x = (int) pointCordinate[2];
				int y = (int) pointCordinate[3];
				g2d.setColor(Color.BLACK);
				g2d.drawOval(x - 15, y, 30, 30);
				g2d.setColor(Color.BLUE);
				g2d.fillOval(x - 15, y, 30, 30);
			}
			g2d.setColor(Color.BLACK);
			for (Entry<String, double[]> entry3 : model.getGeoRegionMap().entrySet()) {
				pointCordinate = entry3.getValue();
				int[] xCordinate = new int[4];
				int[] yCordinate = new int[4];
				for (int i = 0, j = 0; i < pointCordinate.length - 2; i += 2) {
					xCordinate[j] = (int) pointCordinate[i];
					yCordinate[j++] = (int) pointCordinate[i + 1];
				}
				g2d.drawPolygon(xCordinate, yCordinate, 4);
			}
			g2d.setColor(Color.RED);
			for (int idx = 0; idx < newLionPos.size(); ++idx) {
				for (int i = 0; i < newLionPos.get(idx).length; i += 2) {
					int x = (int) newLionPos.get(idx)[i];
					int y = (int) newLionPos.get(idx)[i + 1];
					g2d.fillOval(x, y, 5, 5);
				}
			}
			for (int idx = 0; idx < newPondPos.size(); ++idx) {
				for (int i = 2; i < 4; i += 6) {
					int x = (int) newPondPos.get(idx)[i];
					int y = (int) newPondPos.get(idx)[i + 1];
					g2d.fillOval(x - 15, y, 30, 30);
				}
			}
		}
	}
}