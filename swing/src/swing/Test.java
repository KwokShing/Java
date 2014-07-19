package swing;

import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

//VS4E -- DO NOT REMOVE THIS LINE!
public class Test extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel0;
	private JButton jButton0;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

	public Test() {
		initComponents();
	}

	private void initComponents() {
		setTitle("fghf");
		setLayout(new GroupLayout());
		add(getJLabel0(), new Constraints(new Leading(75, 10, 10), new Leading(37, 10, 10)));
		add(getJButton0(), new Constraints(new Leading(144, 10, 10), new Leading(70, 12, 12)));
		addContainerListener(new ContainerAdapter() {
	
			public void componentAdded(ContainerEvent event) {
				containerComponentAdded(event);
			}
		});
		addMouseListener(new MouseAdapter() {
	
			public void mouseClicked(MouseEvent event) {
				mouseMouseClicked(event);
			}
		});
		setSize(320, 240);
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("jButton0");
		}
		return jButton0;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("jLabel0");
		}
		return jLabel0;
	}

	private void containerComponentAdded(ContainerEvent event) {
	}

	private void mouseMouseClicked(MouseEvent event) {
		System.out.println("fuck");
	}

}
