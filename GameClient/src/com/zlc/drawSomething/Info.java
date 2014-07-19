package com.zlc.drawSomething;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The class Info is used to generate a random words and show it in the info
 * panel
 * 
 * @author Kross
 * 
 */
public class Info extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1613122995840716063L;
	/** The string is to save the random generating words */
	private String string;
	/** The words is to save all the text's words */
	private String[] words = new String[432];
	/** The random is to generate random words from the String[] words */
	private Random random = new Random();
	/** The w is to save the string and is used for the main frame */
	private String w = null;

	/**
	 * The class Info's constructor set the background color to white and read
	 * the words and show the specific words in the panel
	 */
	public Info() {
		// TODO Auto-generated constructor stub
		super();
		setBackground(Color.WHITE);
		File file = new File("word.txt");
		Scanner scanner;
		try {
			int i = 0;
			scanner = new Scanner(file);
			while (scanner.hasNext()) {
				words[i++] = scanner.next();
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		w = generateWords();
		JLabel label = new JLabel(w);
		add(label);

	}

	/**
	 * The generateWords method is used to generate the random words
	 * 
	 * @return string, the random generating words
	 */
	public String generateWords() {
		string = words[random.nextInt(432)];
		return string;
	}

	/**
	 * 
	 * @return w, the specific words
	 */
	public String getW() {
		return w;
	}
}
