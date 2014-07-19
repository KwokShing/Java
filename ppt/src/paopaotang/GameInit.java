/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paopaotang;

/**
 *
 * @author Administrator
 */
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.event.*;
import java.lang.String;
import java.io.*;
import java.io.IOException;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.URL;
class GameStart
{
    public GameStart()
    {
        theFrame tf = new theFrame();
        
    }
}
public class GameInit {
       Map map;
      final   MapCanvas mapcanvas;
      
    JLabel player1score;
    JLabel player2score;
    JLabel player1level;
    JLabel player2level;
    JLabel p1score;
    JLabel p2score;
    JLabel p1level;
    JLabel p2level;
    Container con;
    JButton jb;
    public void writerecord()throws IOException
    {
         DataOutputStream output=new DataOutputStream(new FileOutputStream("record.dat"));
         output.writeUTF(theFrame.player1id);
         output.writeUTF(theFrame.player1password);
         output.writeUTF(p1score.getText());
         output.writeUTF(p1level.getText());
         output.writeUTF(theFrame.player2id);
         output.writeUTF(theFrame.player2password);
         output.writeUTF(p2score.getText());
         output.writeUTF(p2level.getText());
         
    }
    public void readrecord()throws IOException
    {
        DataInputStream input=new DataInputStream(new FileInputStream("record.dat"));
        String player1name=input.readUTF();
        String player1password=input.readUTF();
        String player1scoreinf=input.readUTF();
        String player1levelinf=input.readUTF();
        String player2name=input.readUTF();
        String player2password=input.readUTF();
        String player2scoreinf=input.readUTF();
        String player2levelinf=input.readUTF();
        if(theFrame.player1id.equals(player1name)&&theFrame.player1password.equals(player1password))
        {
            mapcanvas.player1.getinf().setscore(Integer.parseInt(player1scoreinf));
            mapcanvas.player1.getinf().setlevel(Integer.parseInt(player1levelinf));
        }
         if(theFrame.player2id.equals(player2name)&&theFrame.player2password.equals(player2password))
        {
            mapcanvas.player2.getinf().setscore(Integer.parseInt(player2scoreinf));
            mapcanvas.player2.getinf().setlevel(Integer.parseInt(player2levelinf));
        }
             player1score=new JLabel("玩家"+player1name+" 得分:");
             player1level=new JLabel("玩家"+player1name+" 等级");
             player2score=new JLabel("玩家"+player2name+"  得分");
             player2level=new JLabel("玩家"+player2name+"  等级");
        
    }
       public GameInit() throws MalformedURLException
       {
         URL cb; 
        File f = new File("sound/12.wav");
        cb = f.toURL(); 
        AudioClip clip=(AudioClip) Applet.newAudioClip( cb);
        
        clip.loop();
       try{
           readrecord();
       }
       catch(Exception e)
       {
          // JOptionPane.showMessageDialog(null, e.getMessage());
       }
          GameInf gameinf=new GameInf();
          
          map=new Map();
          con=map.getContentPane();
          mapcanvas=new MapCanvas();
          
         con.setLayout(null);      
        map.add(mapcanvas);
        // con.add(rulecanvas);
        // drawrulepic();
        map.addWindowListener(new WindowAdapter(){
 public void windowClosing(WindowEvent e){
//     try{
//        writerecord();
//     }
//     catch(IOException ioe)
//     {
//         JOptionPane.showMessageDialog(null, e.paramString());
//     }
 }
});


            map.addKeyListener(new KeyAdapter()
             {
             public void keyPressed(KeyEvent e)
             {
                  switch(e.getKeyCode())
                 {
                      case KeyEvent.VK_DOWN:mapcanvas.player1.MoveDown();break;
                       case KeyEvent.VK_UP:mapcanvas.player1.MoveUp();break;
                       case KeyEvent.VK_LEFT:mapcanvas.player1.MoveLeft();break;
                       case KeyEvent.VK_RIGHT:mapcanvas.player1.MoveRight();break;
                       case KeyEvent.VK_W:mapcanvas.player2.MoveUp();break;
                       case KeyEvent.VK_A:mapcanvas.player2.MoveLeft();break;
                       case KeyEvent.VK_D:mapcanvas.player2.MoveRight();break;
                       case KeyEvent.VK_S:mapcanvas.player2.MoveDown();break;
                       case KeyEvent.VK_SPACE:mapcanvas.player2.SetBump();break;
                       case KeyEvent.VK_CONTROL:mapcanvas.player1.SetBump();break;
                  };
             //     
                 mapcanvas.repaint();
                 showPlayerInf();
             }

         });
          
            
        
             player1score=new JLabel("玩家一  得分:");
             player1level=new JLabel("玩家一  等级");
             player2score=new JLabel("玩家二  得分");
             player2level=new JLabel("玩家二  等级");
             p1score=new JLabel();
             p2score=new JLabel();
             p1level=new JLabel();
             p2level=new JLabel();
        //  p1.setBounds(550,0,100,20);
        //玩家信息显示部分   
          player1score.setBounds(0, 500, 100, 50);
          con.add(player1score);
          player1level.setBounds(0,600,100,50);
          con.add(player1level);
          player2score.setBounds(100, 500, 100, 50);
          con.add(player2score);
          player2level.setBounds(100, 600, 100, 50);
          con.add(player2level); 
          //
           p1score.setBounds(30, 530, 100, 50);
          con.add(p1score);
          p1level.setBounds(30,630,100,50);
          con.add(p1level);
          p2score.setBounds(130, 530, 100, 50);
          con.add(p2score);
          p2level.setBounds(130, 630, 100, 50);
          con.add(p2level);   
          
       }
      
       public void showPlayerInf()
       {
            
           p1score.setText(mapcanvas.player1.getinf().getscore()+"");
           p2score.setText(mapcanvas.player2.getinf().getscore()+"");
       }
     


} 

 class MouseMonitor1 implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent me) {
            System.out.println("clicked  "+me.getButton());
            theFrame.jf.dispose();
        try {
            GameInit game=new GameInit();
           // throw new UnsupportedOperationException("Not supported yet.");
        } catch (Exception ex) {
          //  Logger.getLogger(MouseMonitor1.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

        @Override
        public void mousePressed(MouseEvent me) {
          //  throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mouseReleased(MouseEvent me) {
           // throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mouseEntered(MouseEvent me) {
           // throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mouseExited(MouseEvent me) {
           // throw new UnsupportedOperationException("Not supported yet.");
        }
    
}
class MouseMonitor2 implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent me) {
            System.out.println("clicked  "+me.getButton());
            System.exit(1);
           // throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mousePressed(MouseEvent me) {
          //  throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mouseReleased(MouseEvent me) {
           // throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mouseEntered(MouseEvent me) {
           // throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void mouseExited(MouseEvent me) {
           // throw new UnsupportedOperationException("Not supported yet.");
        }
    
}
 class Monitor1 implements ActionListener{

    
    @Override
    public void actionPerformed(ActionEvent ae){
        
        String line = ae.getActionCommand();
        System.out.println(line);
        theFrame.player1id= line;
       // throw new UnsupportedOperationException("Not supported yet.");
    }

}
 class Monitor2 implements ActionListener{

    
    @Override
    public void actionPerformed(ActionEvent ae){
        
        String line = ae.getActionCommand();
        System.out.println(line);
        theFrame.player2id= line;
       // throw new UnsupportedOperationException("Not supported yet.");
    }

}
 class Monitor3 implements ActionListener{

    
    @Override
    public void actionPerformed(ActionEvent ae){
        
        String line = ae.getActionCommand();
        System.out.println(line);
        theFrame.player1password = line;
       // throw new UnsupportedOperationException("Not supported yet.");
    }

}
 class Monitor4 implements ActionListener{

    
    @Override
    public void actionPerformed(ActionEvent ae){
        
        String line = ae.getActionCommand();
        System.out.println(line);
        theFrame.player2password = line;
       // throw new UnsupportedOperationException("Not supported yet.");
    }

}
 class theFrame extends JFrame{
        public static JFrame  jf = new JFrame("this");
        public static String player1id;
        public static String player2id;
        public static String player1password;
        public static String player2password;
         theFrame(){
              jf.setSize(800, 700);
              jf.setTitle("泡泡大战");
              draw1();
        jf.setLayout(null);
         }
         public void draw1(){
             JPanel jp1 = new JPanel();
             jp1.setLayout(null);
             jp1.setBounds(0, 0, 800, 700);
             JLabel  jlb = new JLabel();
             jlb.setBounds(0, 0, 800, 700);
             jlb.setIcon(new ImageIcon("image/title.png"));
             jp1.add(jlb);
         
             ImageIcon image1 = new ImageIcon("image/确定.jpg");    
             ImageIcon image2 = new ImageIcon("image/退出.jpg");
             JButton  button1 = new JButton("",image1);
        
             JButton  button2 = new JButton("",image2);
             button1.setBounds(270, 616, 100, 30);
             button2.setBounds(437, 616, 100, 30);
             MouseMonitor1 mouse1 = new MouseMonitor1();
             MouseMonitor2 mouse2 = new MouseMonitor2();
             button1.addMouseListener(mouse1);
             button2.addMouseListener(mouse2);
         
             JTextField  jtf1 = new JTextField("");
             jtf1.setBounds(283, 534, 94, 24);
             JTextField  jtf2 = new JTextField("");
             jtf2.setBounds(485, 534, 94, 24);
             JTextField  jtf3 = new JTextField("");
             jtf3.setBounds(283, 559, 94, 24);
             JTextField  jtf4 = new JTextField("");
             jtf4.setBounds(485, 559, 94, 24);
             Monitor1 mm1 = new Monitor1();
             Monitor2 mm2 = new Monitor2();
             Monitor3 mm3 = new Monitor3();
             Monitor4 mm4 = new Monitor4();
             jtf1.addActionListener(mm1);
             jtf2.addActionListener(mm2);
             jtf3.addActionListener(mm3);
             jtf4.addActionListener(mm4);
         
             jp1.add(button1);
             jp1.add(button2);
             jp1.add(jtf1);
             jp1.add(jtf2);
             jp1.add(jtf3);
             jp1.add(jtf4);
             jf.add(jp1);
            jf.setVisible(true);
         }
 
}
