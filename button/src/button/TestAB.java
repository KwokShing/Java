package button;

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.JFrame;

public class TestAB {
	public JFrame jf = new JFrame("≤‚ ‘");

	public TestAB() {
		jf.setSize(300, 300);
		jf.setLayout(new FlowLayout());
		initAB();
		jf.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		jf.setVisible(true);
	}

	private void initAB() {
		AJPanel a = new AJPanel();
		a.setBorder(BorderFactory.createTitledBorder("A√Ê∞Â"));
		BJPanel b = BJPanel.getSingletonBJPanel();
		b.setBorder(BorderFactory.createTitledBorder("B√Ê∞Â"));
		jf.add(a);
		jf.add(b);
	}

	public static void main(String[] args) {
		new TestAB();
	}
}