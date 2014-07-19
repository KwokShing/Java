package draw3.copy;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Words extends JPanel {
	private Copy_2_of_penViewController copyviewCtrl = null;
	private String words = null;
	private JTextField text;

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}

	public Words() {
		super();
		setBackground(Color.WHITE);
	}

	public Words(Copy_2_of_penViewController viewCtrl) {
		this();
		this.copyviewCtrl = viewCtrl;
		setBackground(Color.WHITE);
		JLabel label = new JLabel("words");
		text = new JTextField(10);
		add(label);
		add(text);
		text.addActionListener(new printWords());
	}

	class printWords implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			text.requestFocusInWindow();
			System.out.println("damn");
			if (copyviewCtrl.checkWords(text.getText().toString())) {
				System.out.println("fuck right");
				copyviewCtrl.setGuess(text.getText().toString());
			}
		}
	}
}
