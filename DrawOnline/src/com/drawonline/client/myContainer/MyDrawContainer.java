package com.drawonline.client.myContainer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.drawonline.client.buffered.MyBufferedImage;
import com.drawonline.client.ui.LoginFrame;

/**
 * xxx
 * 
 * @author lw
 * 
 */
public class MyDrawContainer extends JPanel implements MouseListener,
		MouseMotionListener, Runnable {

	private static final long serialVersionUID = 1L;

	private BufferedImage bufferedImg;

	public static boolean allowDraw;

	private JLabel lblShowTime;

	private JLabel lblMidi;

	private boolean isUsePen;

	private JPanel pnlRect;

	private JButton btnPen, btnCazi;

	private int count;

	private boolean isDrawer;

	private String midi;

	private Point lastPoint;

	public void setNull() {
		lastPoint = null;
	}

	public MyDrawContainer() {
		count = 60;
		isUsePen = true;
		addMouseListener(this);
		addMouseMotionListener(this);
		bufferedImg = MyBufferedImage.getBufferedImage();
		UIlayout();
	}

	public void run() {
		while (count >= 0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			lblShowTime.setText("倒计时: " + count);
			count--;
			if (count == 50 && !isDrawer) {
				lblMidi.setText("提示2:" + midi.length() + "个字");
			} else if (count == 0) {
				btnPen.setEnabled(false);
				btnCazi.setEnabled(false);
				isDrawer = false;
			}
		}
	}

	public void initMidi(String midi, boolean isDrawer, String smg) {
		this.isDrawer = isDrawer;
		this.midi = smg;
		if (isDrawer) {
			lblMidi.setText("谜底:" + midi);
			btnPen.setEnabled(true);
			btnCazi.setEnabled(true);
		} else {
			lblMidi.setText("提示1:" + midi);
			btnPen.setEnabled(false);
			btnCazi.setEnabled(false);
		}
		new Thread(this).start();
	}

	public void UIlayout() {
		setLayout(null);
		JPanel pnlShowInfo = new JPanel();
		pnlShowInfo.setBounds(0, 0, 600, 50);
		pnlShowInfo.setBorder(new TitledBorder("操作栏"));
		showInfoLayout(pnlShowInfo);

		pnlRect = new JPanel();
		pnlRect.setBackground(Color.white);
		pnlRect.setBorder(new LineBorder(Color.black));
		pnlRect.setBounds(0, 0, 20, 20);
		pnlRect.setVisible(false);

		add(pnlShowInfo);
		add(pnlRect);
	}

	private void showInfoLayout(JPanel pnlShowInfo) {
		pnlShowInfo.setLayout(new FlowLayout(FlowLayout.LEFT, 30, -6));
		lblShowTime = new JLabel("倒计时: " + count);
		lblMidi = new JLabel("谜底: 水果");
		btnPen = new JButton("画笔");
		btnCazi = new JButton("橡皮檫");
		JButton btnStart = new JButton("准备");
		pnlShowInfo.add(lblShowTime);
		pnlShowInfo.add(lblMidi);
		pnlShowInfo.add(btnPen);
		pnlShowInfo.add(btnCazi);
		pnlShowInfo.add(btnStart);
		btnPen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				isUsePen = true;
				pnlRect.setVisible(!isUsePen);
				setNull();
			}
		});

		btnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton resButton = (JButton) e.getSource();
				resButton.setEnabled(false);
				LoginFrame.loginFrame.sendsmg("ready",
						LoginFrame.loginFrame.getNickName());
				setNull();
			}
		});

		btnCazi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				isUsePen = false;
				pnlRect.setVisible(!isUsePen);
				setNull();
			}
		});
	}

	public void paint(Graphics g) {
		super.paint(g);
		if (bufferedImg != null) {
			g.drawImage(bufferedImg, 0, 50, bufferedImg.getWidth(),
					bufferedImg.getHeight(), null);
		}
	}

	public void clearPaintImg(Point point) {
		Graphics g = bufferedImg.getGraphics();
		Graphics2D g2d = (Graphics2D) g;
		BasicStroke size = new BasicStroke(16.0f, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_BEVEL);
		g2d.setColor(Color.WHITE);
		g2d.setStroke(size);
		Line2D line1 = null;
		if (lastPoint == null) {
			line1 = new Line2D.Double(point.x, point.y - 50, point.x,
					point.y - 50);
		} else {
			line1 = new Line2D.Double(lastPoint.x, lastPoint.y - 50, point.x,
					point.y - 50);
		}
		g2d.draw(line1);
		lastPoint = point;
		repaint();
	}

	public void paintImg(Point point) {
		Graphics g = bufferedImg.getGraphics();
		Graphics2D g2d = (Graphics2D) g;
		BasicStroke size = new BasicStroke(4.0f, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_BEVEL);
		g2d.setColor(Color.black);
		g2d.setStroke(size);
		Line2D line1 = null;
		if (lastPoint == null) {
			line1 = new Line2D.Double(point.x, point.y - 50, point.x,
					point.y - 50);
		} else {
			line1 = new Line2D.Double(lastPoint.x, lastPoint.y - 50, point.x,
					point.y - 50);
		}
		g2d.draw(line1);
		lastPoint = point;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		setNull();
		LoginFrame.loginFrame.sendsmg("NULL", "");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		setNull();
		LoginFrame.loginFrame.sendsmg("NULL", "");
	}

	@Override
	public void mouseDragged(final MouseEvent e) {
		if (allowDraw) {
			if (isUsePen) {
				paintImg(e.getPoint());
				repaint();
				String value = e.getX() + "," + e.getY();
				LoginFrame.loginFrame.sendsmg("point", value);
			} else {
				clearPaintImg(e.getPoint());
				pnlRect.setLocation(e.getPoint().x - 10, e.getPoint().y - 10);
				String value = e.getX() + "," + e.getY();
				LoginFrame.loginFrame.sendsmg("cz", value);
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		setNull();
		LoginFrame.loginFrame.sendsmg("NULL", "");
	}
}
