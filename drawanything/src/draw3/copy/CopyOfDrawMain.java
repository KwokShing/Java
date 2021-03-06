package draw3.copy;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;


public class CopyOfDrawMain extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tool tool = new Tool();
	private Words guess = new Words();
	private ToolMove tMove = new ToolMove();
	private Info info = new Info();
	private PenModel model = null;
	private penViewController viewCtrl = null/* = new penViewController() */;
	private Copy_2_of_penViewController copyviewCtrl = null;
	private StillClock clock = new StillClock();
	private String words = null;

	public void init(int arg) {
		JFrame frame = new JFrame();
		frame.setLayout(new BorderLayout());
		JLayeredPane layer = new JLayeredPane();
		JPanel infoPanel = new JPanel(new GridLayout(2, 1));
		infoPanel.add(clock);
		//infoPanel.add(guess);
		// info.generateWords();
		//infoPanel.add(info);

		// tMove.setBounds(0, 0, 500, 500);
		// tMove.setSize(50,50);
		// JPanel eTool = new JPanel();
		// tMove.setOpaque(false);

		// viewCtrl.setToolMovw(tMove);
		// layer.add(viewCtrl, new Integer(0));
		// layer.add(tMove, new Integer(1));

		// tool = new Tool(copyviewCtrl);

		//frame.add(tool, BorderLayout.WEST);
		//frame.add(copyviewCtrl, BorderLayout.CENTER);
		// frame.add(layer, BorderLayout.CENTER);
		
		if (arg == 1) {
			infoPanel.add(info);
			tool = new Tool(viewCtrl);
			frame.add(tool, BorderLayout.WEST);
			frame.add(viewCtrl, BorderLayout.CENTER);
		} else{
			infoPanel.add(guess);
			frame.add(viewCtrl, BorderLayout.CENTER);
		}
		frame.add(infoPanel, BorderLayout.EAST);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("�㻭�Ҳ�copy");
		frame.setSize(800, 500);
		frame.setVisible(true);
	}

	public CopyOfDrawMain(Socket socket, int arg) {
		if (arg == 1)// turn
		{
			//tool = new Tool(viewCtrl);	
			model = new PenModel();
			viewCtrl = new penViewController(socket, true);
			words = info.getW();
			viewCtrl.setModel(model,words);
			//viewCtrl.setTurn();
			
			init(1);
		} else {// not turn
			model = new PenModel();
			viewCtrl = new penViewController(socket, false);
			viewCtrl.setModel(model,"hey");
			guess = new Words(copyviewCtrl);
				
			init(0);
		}
	}

	public static void main(String[] args) {
		Socket socket = null;
		try {
			socket = new Socket("localhost", 8001);
		} catch (IOException ex) {
			System.err.println(ex);
		}
		new CopyOfDrawMain(socket, 1);
	}
}
