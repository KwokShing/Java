package button;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class AJPanel extends JPanel {
	public JButton b = new JButton("改变B上的内容");

	public AJPanel() {
		this.setLayout(new FlowLayout());
		this.add(b);
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BJPanel.getSingletonBJPanel().changeContent("新的内容");
			}
		});
	}
}