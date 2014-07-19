package draw3.copy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Info extends JPanel {
	private String string;
	private String[] words = new String[432];
	private Random random = new Random();
	private String w = null;

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

	public String generateWords() {
		string = words[random.nextInt(432)];
		return string;
	}
	
	public String getW(){
		return w;
	}
}
