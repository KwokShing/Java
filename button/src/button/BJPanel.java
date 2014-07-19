package button;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BJPanel extends JPanel {
	public static BJPanel bJpanel = null;
	private JLabel label = null;

	private BJPanel() {
		this.setLayout(new FlowLayout());
		label = new JLabel("³õÊ¼ÄÚÈÝ");
		this.add(label);
	};

	public static BJPanel getSingletonBJPanel() {
		synchronized (BJPanel.class) {
			if (bJpanel == null) {
				bJpanel = new BJPanel();
			}
			return bJpanel;
		}
	}

	public void changeContent(String newContent) {
		label.setText(newContent);
	}
}
