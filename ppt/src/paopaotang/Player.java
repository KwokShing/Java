/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paopaotang;

/**
 *
 * @author Administrator
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.util.*;
import javax.swing.JLabel;
import java.io.*;
public class Player extends JPanel {
   
public BufferedImage player;
public int Cur_X=0;
public int Cur_Y=0;
public int Pre_X=0;
public int Pre_Y=0;
 
 public void getpic()throws Exception
 {
     player=ImageIO.read(new File("image/player/player0.png"));
 }
 public Player()
 {
     this.setSize(50, 50);
     try{
         getpic();
     }
     catch(Exception e)
     {
         JOptionPane.showMessageDialog(null, "fuck");
     }
     this.setVisible(true);
     repaint();
   }
   public void MoveLeft()
 {
     
     if(Cur_X>0){
     Pre_X=Cur_X;
     Pre_Y=Cur_Y;
     Cur_X-=50;
     
      repaint();
     }
 }
 public void MoveRight()
 {
     
     if(Cur_X<500){
    Pre_X=Cur_X;
    Pre_Y=Cur_Y;
     Cur_X+=50;
   
      repaint();
     }
 }
 public void MoveUp()
 {
     
     if(Cur_Y>0){
     Pre_X=Cur_X;    
     Pre_Y=Cur_Y;
     Cur_Y-=50;
     
      repaint();
     }
 }
 public void MoveDown()
 {
     
     if(Cur_Y<500){
         Pre_X=Cur_X;
      Pre_Y=Cur_Y;   
     Cur_Y+=50;   
     repaint();
     }
 }
 public void update(Graphics g)
 {
     
     paint(g);
 }
 public void paint(Graphics g)
 {
     g.clearRect(Pre_X, Pre_Y, 50, 50);
     g.drawImage(player, Cur_X, Cur_Y, 50, 50, this);
 }
 }
 

    
 

