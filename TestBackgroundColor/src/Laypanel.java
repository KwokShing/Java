import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

class Laypanel extends JFrame {
	Laypanel() {
		JPanel pane2 = new JPanel();// ��ͨ���
		JButton btn3 = new JButton("deng ");
		pane2.add(btn3);
		JLayeredPane pane = new JLayeredPane();// �ֲ����
		JButton btn1 = new JButton("ȷ��");
		JButton btn2 = new JButton("ȡ��");
		pane.add(btn1, (Integer) (JLayeredPane.PALETTE_LAYER + 50));
		pane.add(btn2, (Integer) (JLayeredPane.PALETTE_LAYER + 50));
		btn1.setBounds(30, 50, 80, 80);
		btn2.setBounds(50, 80, 80, 80);

		// ���������˲���
		JPanel pnlMain = new JPanel(new GridLayout());
		pnlMain.add(pane);
		pnlMain.add(pane2);
		this.add(pnlMain);

		// this.add(pane);// �򴰿���ӷֲ����
		// this.add(pane2);// �򴰿������ͨ���
		this.setBounds(100, 100, 500, 300);
		this.setVisible(true);
		this.validate();
	}
	public static void main(String[] args) {
		new Laypanel();
	}
}