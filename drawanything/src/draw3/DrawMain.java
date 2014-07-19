package draw3;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class DrawMain extends JFrame {
	private Tool tool = new Tool();
	private ToolMove tMove = new ToolMove();
	private Info info = new Info();
	private Words guess = new Words();
	private PenModel model = null;
	private penViewController viewCtrl = null;
	private StillClock clock = new StillClock();
	private String words = null;
	public static boolean end = false;
	private ShowMessage showMessage = new ShowMessage();
	private ImageIcon bg = new ImageIcon("1.png");

	public void init(int arg) {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		JLayeredPane layer = new JLayeredPane();
		JPanel infoPanel = new JPanel(new GridLayout(2, 1));
		infoPanel.add(clock);

		// setGlassPane(tMove);

		// tMove.setBounds(0, 0, 500, 500);
		// tMove.setSize(50,50);
		// JPanel eTool = new JPanel();
		// tMove.setOpaque(false);
		// viewCtrl.setModel(model);
		// viewCtrl.setToolMove(tMove);
		viewCtrl.setWrong(showMessage);
		 layer.add(viewCtrl, new Integer(0));
		 //layer.add(tMove, new Integer(1));
		 layer.add(showMessage, new Integer(1));
		clock.setCtrl(viewCtrl);
		if (arg == 1) {
			infoPanel.add(info);
			tool = new Tool(viewCtrl);
		} else {
			infoPanel.add(guess);
		}
		JLabel label = new JLabel(bg);
		label.setBounds(0, 0, 800, 50);
		JPanel bgPanel = new JPanel();
		bgPanel.add(label);
		bgPanel.setBounds(0, 0, 800, 50);
		//bgPanel.setOpaque(false);

		//viewCtrl.setOpaque(false);
		//showMessage.setOpaque(false);
		//showMessage.setBounds(0, 0, 800, 100);
		layer.add(bgPanel);
		JPanel combine = new JPanel(new GridLayout(2,1));
		combine.add(layer);
		combine.add(viewCtrl);
		//layer.add(showMessage, new Integer(1));
		frame.add(tool, BorderLayout.WEST);
		//frame.add(showMessage, BorderLayout.NORTH);
		frame.add(combine, BorderLayout.CENTER);
		//frame.add(viewCtrl, BorderLayout.CENTER);		
		frame.add(infoPanel, BorderLayout.EAST);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Äã»­ÎÒ²Â");
		frame.setSize(800, 500);
		frame.setVisible(true);
	}

	public DrawMain(Socket socket, int arg) {
		// Timer timer = new Timer();
		// timer.schedule(new Task(socket), 0, 60000);

		if (arg == 1)// turn
		{
			model = new PenModel();
			viewCtrl = new penViewController(socket, 1);
			words = info.getW();
			viewCtrl.setModel(model, words);
			init(1);
		} else {// not turn
			model = new PenModel();
			viewCtrl = new penViewController(socket, 0);
			viewCtrl.setModel(model, "hey");
			viewCtrl.setActiveControl();
			guess = new Words(viewCtrl);

			init(0);
		}
	}

	// class Task extends TimerTask{
	// private Socket socket = null;
	// public Task(Socket socket){
	// this.socket = socket;
	// }
	// @Override
	// public void run() {
	// // TODO Auto-generated method stub
	// while(true){
	// if (turn == 1)// turn
	// {
	// model = new PenModel();
	// viewCtrl = new penViewController(this.socket, true);
	// words = info.getW();
	// viewCtrl.setModel(model, words);
	// init(1);
	// } else {// not turn
	// model = new PenModel();
	// viewCtrl = new penViewController(this.socket, false);
	// viewCtrl.setModel(model, "hey");
	// guess = new Words(viewCtrl);
	//
	// init(0);
	// }
	// turn = 1 - turn;
	// try {
	// Thread.sleep(15000);
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// }
	//
	// }
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;

		try {
			// Create a server socket
			serverSocket = new ServerSocket(8001);
			System.out.println("waiting");
			socket = serverSocket.accept();
			System.out.println("accept");
		} catch (IOException ex) {
			System.err.println(ex);
		}
		new DrawMain(socket, 1);
	}
}
