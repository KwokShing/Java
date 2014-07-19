package draw3;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 * The class CopyOfDrawMain is the main frame of the drawing game.
 * 
 * @author Kross
 * 
 */
public class CopyOfDrawMain extends JFrame {
	private static final long serialVersionUID = 1L;
	/***/
	private Tool tool = new Tool();
	private Words guess = new Words();
	// private ToolMove tMove = new ToolMove();
	private Info info = new Info();
	private PenModel model = null;
	private penViewController viewCtrl = null;
	private StillClock clock = new StillClock();
	private String words = null;
	public static boolean end = false;
	private ShowMessage showMessage = new ShowMessage();

	public void init(int arg) {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		JLayeredPane layer = new JLayeredPane();
		JPanel infoPanel = new JPanel(new GridLayout(2, 1));
		
		infoPanel.setPreferredSize(new Dimension(200,100));
		infoPanel.add(clock);

		// setGlassPane(tMove);

		// tMove.setBounds(0, 0, 500, 500);
		// tMove.setSize(50,50);
		// JPanel eTool = new JPanel();
		// tMove.setOpaque(false);
		// viewCtrl.setModel(model);
		// viewCtrl.setToolMove(tMove);
		viewCtrl.setWrong(showMessage);
		// layer.add(viewCtrl, new Integer(0));
		// layer.add(tMove, new Integer(1));
		// layer.add(showMessage, new Integer(1));
		clock.setCtrl(viewCtrl);
		if (arg == 1) {
			infoPanel.add(info);
			tool = new Tool(viewCtrl);
			//frame.add(tool, BorderLayout.WEST);
			//frame.add(showMessage, BorderLayout.NORTH);
		} else {
			infoPanel.add(guess);
			//frame.add(tool, BorderLayout.WEST);
			//frame.add(showMessage, BorderLayout.NORTH);
		}
		frame.add(tool, BorderLayout.WEST);
		frame.add(showMessage, BorderLayout.NORTH);
		frame.add(viewCtrl, BorderLayout.CENTER);
		// frame.add(layer, BorderLayout.CENTER);
		frame.add(infoPanel, BorderLayout.EAST);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Äã»­ÎÒ²Âcopy");
		frame.setSize(800, 500);
		frame.setVisible(true);
	}

	public CopyOfDrawMain(Socket socket, int arg) {
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
		Socket socket = null;
		try {
			socket = new Socket("localhost", 8001);
		} catch (IOException ex) {
			System.err.println(ex);
		}
		new CopyOfDrawMain(socket, 0);
	}
}
