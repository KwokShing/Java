import java.awt.GridLayout;

import java.awt.event.MouseEvent;

import java.awt.event.MouseListener;

import javax.swing.JLabel;

import javax.swing.JPanel;

public class addTab implements MouseListener {
	JPanel jp;
	JLabel lab;
	JLabel lab3 = new JLabel();

	public addTab() {
		lab = new JLabel("ѡ�1");
		JLabel lab1 = new JLabel("ѡ�");
		jp = new JPanel();
		GridLayout gl = new GridLayout(1, 1, 10, 0);
		jp.setLayout(gl);
		lab1.setHorizontalAlignment(JLabel.LEFT);// ����������ʾ�������
		lab3.setHorizontalAlignment(JLabel.RIGHT);// ����������ʾ�����ұ�
		jp.add(lab1);
		jp.add(lab3);
		tab.pane.addTab("i", lab);
		tab.pane.setTabComponentAt(tab.pane.indexOfComponent(lab), jp);// ʵ��������ܵľ���һ������Ҫ�����
		lab3.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		tab.pane.remove(tab.pane.indexOfTabComponent(jp));
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		lab3.setText("x ");
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		lab3.setText("");
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

}