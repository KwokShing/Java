package button;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class AJPanel extends JPanel {
	public JButton b = new JButton("�ı�B�ϵ�����");

	public AJPanel() {
		this.setLayout(new FlowLayout());
		this.add(b);
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BJPanel.getSingletonBJPanel().changeContent("�µ�����");
			}
		});
	}
}