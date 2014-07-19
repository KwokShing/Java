package draw3;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * class Words use to obtain player's input and judge whether it is equal to the
 * actual words
 * 
 * @author Kross
 * 
 */
public class Words extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -527700957962220190L;
	/** The controller to change the model's guess words */
	private penViewController viewCtrl = null;
	/** The words string variable to save player's input */
	private String words = null;
	/** The text field to get the player's input */
	private JTextField text;

	/** class Words Constructor to set the background color */
	public Words() {
		super();
		setBackground(Color.WHITE);
	}

	/**
	 * The class Words Constructor
	 * 
	 * @param viewCtrl
	 *            the controller object
	 */
	public Words(penViewController viewCtrl) {
		this();
		this.viewCtrl = viewCtrl;
		setBackground(Color.WHITE);
		JLabel label = new JLabel("words");
		text = new JTextField(10);
		add(label);
		add(text);
		text.addActionListener(new printWords());
	}

	/**
	 * get the words
	 * 
	 * @return words
	 */
	public String getWords() {
		return words;
	}

	/**
	 * to save the words in the private variable words
	 * 
	 * @param words
	 */
	public void setWords(String words) {
		this.words = words;
	}

	/**
	 * Inner class printWords, to get the user's guess words and judge whether
	 * the guess words is equal to the actual words. If it matches to the actual
	 * words, use the controller to change the model's guess
	 * 
	 * @author Kross
	 */
	class printWords implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			text.requestFocusInWindow();
			if (viewCtrl.checkWords(text.getText().toString())) {
				viewCtrl.setGuess(text.getText().toString());
				viewCtrl.selfWin();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				viewCtrl.isEnd();
			} else {
				viewCtrl.isWrong();
			}
		}
	}
}
