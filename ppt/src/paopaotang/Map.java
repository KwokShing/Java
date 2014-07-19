/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package paopaotang;

/**
 *
 * @author Administrator
 */
//import java.awt.Toolkit;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.util.*;
import java.util.Timer;
import javax.swing.JLabel;
import java.io.*;
import java.awt.event.*;
import java.lang.Integer;
import javax.swing.border.*;
public class Map extends JFrame {
     
  
    public Map()
    {
      ;
       this.setBounds(0, 0, 800, 700);  
        this.setVisible(true);
        this.setTitle("泡泡堂by（李炜，华杰，张初群）");
         this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
   
          
  
    }
   
}
//产生50整数倍的坐标
class PointMath extends Point{
   
public Point random()
{
    int x=(int)(Math.random()*500);
    int y=(int)(Math.random()*500);
   
    x=((int)(x/50))*50;
    y=((int)(y/50))*50;
    Point point=new Point(x,y);
    return point;
}
}


class MapCanvas extends Canvas{
   Image[]img=new Image[7];
   BufferedImage []imgmap=new BufferedImage[7];
   BufferedImage []imgbox=new BufferedImage[3];
   BufferedImage[]imgbump=new BufferedImage[10];
   BufferedImage imgplayer[]=new BufferedImage[2];
  BufferedImage []imgfood=new BufferedImage[5];
  BufferedImage imgdead;
  BufferedImage imgrule;
    HashMap<String,Point> hashmap=new HashMap<String,Point>();
   Set<Point> roadset=new HashSet<Point>();
   Set<Point> treeset=new HashSet<Point>();
   Set<Point> houseset=new HashSet<Point>();
   Set<Point> boxset=new HashSet<Point>();
   Set<Point>bumpset=new HashSet<Point>();
   Set<Point>foodset=new HashSet<Point>();
    Set<Point>playerset=new HashSet<Point>(); 
  PlayerAction player1;
  PlayerAction player2;
    public void readpic()throws Exception
    {
        
        imgmap[1]=ImageIO.read(new File("image/map/redhouse.png"));
        imgmap[2]=ImageIO.read(new File("image/map/yellowhouse.png"));
        imgmap[3]=ImageIO.read(new File("image/map/bluehouse.png"));
        imgmap[4]=ImageIO.read(new File("image/map/tree.png"));
        imgmap[5]=ImageIO.read(new File("image/map/road.png"));
        imgmap[6]=ImageIO.read(new File("image/map/road2.png"));
        imgmap[0]=ImageIO.read(new File("image/map/grass.png"));
        imgbox[0]=ImageIO.read(new File("image/box/box1.png"));
        imgbox[1]=ImageIO.read(new File("image/box/box2.png"));
        imgbox[2]=ImageIO.read(new File("image/box/box3.png"));
        imgplayer[0]=ImageIO.read(new File("image/player/player0.png"));
        imgplayer[1]=ImageIO.read(new File("image/player/player1/player1.png"));
        imgbump[0]=ImageIO.read(new File("image/bump/bump1.png"));
        imgbump[1]=ImageIO.read(new File("image/bump/bump2.png"));
        imgbump[2]=ImageIO.read(new File("image/bump/bump3.png"));
        imgbump[3]=ImageIO.read(new File("image/bump/bump4.png"));
        imgbump[4]=ImageIO.read(new File("image/bump/bump5.png"));
        imgbump[5]=ImageIO.read(new File("image/bump/bump6.png"));
        imgbump[6]=ImageIO.read(new File("image/bump/bump7.png"));
        imgbump[7]=ImageIO.read(new File("image/bump/bump8.png"));
        imgbump[8]=ImageIO.read(new File("image/bump/bump9.png"));
        imgbump[9]=ImageIO.read(new File("image/bump/bump10.png"));
        imgfood[0]=ImageIO.read(new File("image/food/food1.png"));   
        imgdead=ImageIO.read(new File("image/die/paopao1.png"));
        imgrule=ImageIO.read(new File("image/rule/rule.png"));
    }
    public void picsrc()
    {
        img[1]=Toolkit.getDefaultToolkit().getImage("image/map/redhouse.png");
        img[2]=Toolkit.getDefaultToolkit().getImage("image/map/yellowhouse.png");
        img[3]=Toolkit.getDefaultToolkit().getImage("image/bluehouse.png");
       img[4]=Toolkit.getDefaultToolkit().getImage("image/map/tree.png");
       img[5]=Toolkit.getDefaultToolkit().getImage("image/map/road.png");
       img[0]=Toolkit.getDefaultToolkit().getImage("image/map/grass.png");
       
      //  b1=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
    }
    public MapCanvas()
    {
        this.setSize(800,500);
        // picsrc();
        
       // hashmap.put("player1",new Point(0,0) );
        setMapPoint();
        try{
        readpic();
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "shit");
            
        }
     
        repaint();
         player1=new PlayerAction(0,0);
         player2=new PlayerAction(450,450);
         hashmap.put("player1", new Point(0,0));
         hashmap.put("player2", new Point(450,450));
         
        
    }
  
   
    public void setMapPoint()
    {
        Point point;
        //设置公路的坐标
        for(int i=0;i<20;i++)
        {
            if(i<10){
           
            point=new Point(250,i*50);
            }
            else
            {     
               point=new Point(300,(i-10)*50);
            
            }
         hashmap.put("road"+i,point );
         roadset.add(point);
        }
        //设置箱子的坐标
        for(int i=0;i<20;i++)
        {
           
           while(hashmap.containsValue(point=new PointMath().random())||(point.x<100&&point.y<100)||(point.x>350&&point.y>350));
          
         //  JOptionPane.showMessageDialog(null,point.x+" "+point.y);
                hashmap.put("box"+i,point);
              boxset.add(point);
                
               
        }
        //设置树的坐标
         for(int i=0;i<10;i++)
        {
           
           while(hashmap.containsValue(point=new PointMath().random())||(point.x<100&&point.y<100)||(point.x>350&&point.y>350));
          
         //  JOptionPane.showMessageDialog(null,point.x+" "+point.y);
                hashmap.put("tree"+i,point);
               treeset.add(point);
        }
         //设置房屋的坐标
         for(int i=0;i<10;i++)
         {
             while(hashmap.containsValue(point=new PointMath().random())||(point.x<100&&point.y<100)||(point.x>350&&point.y>350));
             hashmap.put("house"+i, point);
             houseset.add(point);
         }
         //设置食物坐标
         createFood();
        
    }
    
    public void drawmap(Graphics g)
    {
        //整张地图用背景填充
         for(int i=0;i<500;i+=50)
            for(int j=0;j<500;j+=50)
            {
              //  if(!hashmap.containsValue(new Point(i,j)))
                {
                    g.drawImage(imgmap[0], i, j, 50, 50, null);
                }
            }
        //画路
      
       Iterator<Point>it1 =roadset.iterator();
        while(it1.hasNext())
       {
            Point temp=it1.next();
             g.drawImage(imgmap[5], temp.x, temp.y, 50, 50, this);
        }
        //画树
       
       Iterator<Point>it2 =treeset.iterator();
        while(it2.hasNext())
       {
            Point temp=it2.next();
             g.drawImage(imgmap[4], temp.x, temp.y, 50, 50, null);
        }
        //画食物
        Iterator <Point> it0=foodset.iterator();
        while(it0.hasNext())
        {
            Point temp=it0.next();
            g.drawImage(imgfood[0], temp.x, temp.y, 50,50,null);
        }
                
        //画箱子
       
       Iterator<Point>it3 =boxset.iterator();
        while(it3.hasNext())
       {
            Point temp=it3.next();
             g.drawImage(imgbox[0], temp.x, temp.y, 50, 50, null);
        }
        //画房子
        Iterator<Point>it4=houseset.iterator();
        while(it4.hasNext())
        {
            Point temp=it4.next();
             g.drawImage(imgmap[1], temp.x, temp.y, 50, 50, null);
        }
        //画炸弹
        if(!bumpset.isEmpty())
        {
        Iterator<Point>it5=bumpset.iterator();
        while(it5.hasNext())
        {
            Point temp=it5.next();
             g.drawImage(imgbump[0], temp.x, temp.y, 50, 50, null);
        }
        }
        //画玩家
        hashmap.remove("player1");
        hashmap.remove("player2");
        hashmap.put("player1",new Point(player1.Cur_X,player1.Cur_Y));
        hashmap.put("player2",new Point(player2.Cur_X,player2.Cur_Y));
       g.drawImage(imgplayer[0], hashmap.get("player1").x,  hashmap.get("player1").y, 50, 50, null);
       g.drawImage(imgplayer[1], hashmap.get("player2").x,  hashmap.get("player2").y, 50, 50, null);
      
       

    }
 
   
    
    public void paint(Graphics g)
    {
     
         drawmap(g);
         g.drawImage(imgrule,500,0, 250, 500, null);

    }
    public void update(Graphics g)
    {
        Image img;
        img=this.createImage(500,500);
        Graphics tempgraphics=img.getGraphics();
        paint(tempgraphics);
        
        g.drawImage(img,0,0,500,500,null);
    }
public void drawplayer(Graphics g)
{
    
}
public class PlayerAction{
    Set<Point> curbumpset=new HashSet<Point>();
        int Cur_X=0;
       int Cur_Y=0;
       int Pre_X=0;
       int Pre_Y=0;
      public  PlayerInf inf;
       public PlayerAction(int x,int y)
       {
           Cur_X=x;
           Cur_Y=y;
           Pre_X=x;
           Pre_Y=y;
           inf =new PlayerInf();
       }
       public PlayerInf getinf()
       {
           return inf;
       }
    public void MoveLeft()
 {
     if(!obstacleexist(new Point(Cur_X-50,Cur_Y))){
     if(Cur_X>=50){
     Pre_X=Cur_X;
     Pre_Y=Cur_Y;
     Cur_X-=50;
     if(foodset.contains(new Point(Cur_X,Cur_Y)))
     {
         inf.score++;
         foodset.remove(new Point(Cur_X,Cur_Y));
         
     }
   
     }
     }
 }
 public void MoveRight()
 {
      if(!obstacleexist(new Point(Cur_X+50,Cur_Y))){
     if(Cur_X<=400){
    Pre_X=Cur_X;
    Pre_Y=Cur_Y;
     Cur_X+=50;
    if(foodset.contains(new Point(Cur_X,Cur_Y)))
     {
          inf.score++;
         foodset.remove(new Point(Cur_X,Cur_Y));
         
     }
     
     }
      }
 }
 public void MoveUp()
 {
      if(!obstacleexist(new Point(Cur_X,Cur_Y-50))){
     if(Cur_Y>=50){
     Pre_X=Cur_X;    
     Pre_Y=Cur_Y;
     Cur_Y-=50;
      if(foodset.contains(new Point(Cur_X,Cur_Y)))
     {
          inf.score++;
         foodset.remove(new Point(Cur_X,Cur_Y));
         
     }
 
     }
      }
 }
 public void MoveDown()
 {
      if(!obstacleexist(new Point(Cur_X,Cur_Y+50))){
     if(Cur_Y<=400){
         Pre_X=Cur_X;
      Pre_Y=Cur_Y;   
     Cur_Y+=50;  
      if(foodset.contains(new Point(Cur_X,Cur_Y)))
     {
          inf.score++;
         foodset.remove(new Point(Cur_X,Cur_Y));
         
     }
   
     }
      }
 }
 public void SetBump()
 {
     if(curbumpset.isEmpty()){
     if(!bumpset.contains(new Point(Cur_X,Cur_Y))){
          curbumpset.add(new Point(Cur_X,Cur_Y));
     bumpset.add(new Point(Cur_X,Cur_Y));
     hashmap.put("bump"+bumpset.size()+1, new Point(Cur_X,Cur_Y));
     
   //  BombBump bomb=new BombBump();
    //Thread threadbump= new Thread(bomb);
   // threadbump.start();
    
  new Thread(new BombBump()).start();
     }
     }

   // Graphics2D graphics=(Graphics2D)getGraphics();
   // graphics.drawImage(imgbump[0], Cur_X, Cur_Y, 50, 50, null);
 }
  private class BombBump implements Runnable
         {
      Graphics2D graphics=(Graphics2D)getGraphics();
         Integer []bombpic=new Integer[10];
         
          Iterator<Point> it=curbumpset.iterator();
         public BombBump(){
         for(int i=0;i<10;i++)
         {
             bombpic[i]=0;
         }
         
         int i=0;
         while(it.hasNext())
         {
          Point point=it.next();
        Timer timer=new Timer();
         timer.schedule(new Bombed(point.x,point.y), 3000);
         }
         }
       public void changeshape()
       {
            
             int j=0;
             Iterator<Point>it1=curbumpset.iterator();
            for(int i=0;i<curbumpset.size();i++)
        {
           
            Point temp=it1.next();
            graphics.drawImage(imgmap[0], temp.x, temp.y, 50, 50, null);
             graphics.drawImage(imgbump[(bombpic[j]++)%3], temp.x, temp.y, 50, 50, null);
              j++;
        }
       }
     public void run()
     {
         while(true)
         {
             try{
             Thread.sleep(500);
             }
             catch(InterruptedException e){
             e.printStackTrace();
             }
            // while(flag){
            changeshape();
           //  }
           }
         }
}
   public class Bombed extends TimerTask
 {
     int pointx;
     int pointy;
     public Bombed(int x,int y)
     {
         pointx=x;
         pointy=y;
     }
     int  ifdead(int x,int y)
     {
         if(hashmap.get("player1").x==x)
         {
             if(hashmap.get("player1").y==y||hashmap.get("player1").y==y+50||hashmap.get("player1").y==y-50)
            // return true;
                 return 1;
         }
         if(hashmap.get("player1").y==y)
         {
             if(hashmap.get("player1").x==x+50||hashmap.get("player1").x==x-50)
               //  return true;
                 return 1;
         }
         if(hashmap.get("player2").x==x)
         {
             if(hashmap.get("player2").y==y||hashmap.get("player2").y==y+50||hashmap.get("player2").y==y-50)
            // return true;
                 return 2;
         }
         if(hashmap.get("player2").y==y)
         {
             if(hashmap.get("player2").x==x+50||hashmap.get("player2").x==x-50)
               //  return true;
                 return 2;
         }
         return 0;
       //  return false;
     }
     public void run()
     {
          Graphics2D graphics=(Graphics2D)getGraphics();
       //   flag=false;
           curbumpset.remove(new Point(pointx,pointy));
          bumpset.remove(new Point(pointx,pointy));
          graphics.drawImage(imgbump[3], pointx, pointy, 50, 50, null);
          if(!bumpobstacleexist(new Point(pointx,pointy-50))){
              graphics.drawImage(imgbump[4], pointx, pointy-50, 50, 50, null);
               if(boxset.contains(new Point(pointx,pointy-50)))
           {
               boxset.remove(new Point(pointx,pointy-50));
           }
          
          }
         
          if(!bumpobstacleexist(new Point(pointx,pointy+50))){
          graphics.drawImage(imgbump[5], pointx, pointy+50, 50, 50, null);
              if(boxset.contains(new Point(pointx,pointy+50)))
           {
               boxset.remove(new Point(pointx,pointy+50));
           }
          }
          if(!bumpobstacleexist(new Point(pointx-50,pointy))){
          graphics.drawImage(imgbump[6], pointx-50, pointy, 50, 50, null);
          if(boxset.contains(new Point(pointx-50,pointy)))
           {
               boxset.remove(new Point(pointx-50,pointy));
           }
          }
          if(!bumpobstacleexist(new Point(pointx+50,pointy))&&(pointx<450)){
           graphics.drawImage(imgbump[7], pointx+50, pointy, 50, 50, null);
           if(boxset.contains(new Point(pointx+50,pointy)))
           {
               boxset.remove(new Point(pointx+50,pointy));
           }
          }
           if(ifdead(pointx,pointy)==1)
           {
               graphics.drawImage(imgdead,hashmap.get("player1").x, hashmap.get("player1").y, 50, 50, null);
               JOptionPane.showMessageDialog(null, "Player2 Win");
               
           }
           if(ifdead(pointx,pointy)==2)
           {
               graphics.drawImage(imgdead, hashmap.get("player2").x, hashmap.get("player2").y, 50, 50, null);
               JOptionPane.showMessageDialog(null, "Player1 Win");
               
           }
//          if(!bumpobstacleexist(new Point(pointx,pointy-100))){
//           graphics.drawImage(imgbump[8], pointx, pointy-100, 50, 50, null);
//          }
//          if(!bumpobstacleexist(new Point(pointx,pointy+100))){
//           graphics.drawImage(imgbump[8], pointx, pointy+100, 50, 50, null);
//          }
//          if(!bumpobstacleexist(new Point(pointx-100,pointy))){
//           graphics.drawImage(imgbump[9], pointx-100, pointy, 50, 50, null);
//          }
//          if(!bumpobstacleexist(new Point(pointx+100,pointy))){
//           graphics.drawImage(imgbump[9], pointx+100, pointy, 50, 50, null);
//          }
           try
           {
           Thread.sleep(1000);
           }
           catch(Exception e)
           {
               this.cancel();
           }
          
           repaint();
     }
 }
     }

 public boolean obstacleexist(Point point)
 {
  if(treeset.contains(point)||boxset.contains(point)||houseset.contains(point)||bumpset.contains(point))
        return true;
  return false;
 }
 public boolean bumpobstacleexist(Point point)
 {
     if(treeset.contains(point)||houseset.contains(point)||bumpset.contains(point))
        return true;
  return false;
 }
 
 public void createFood(){
   Iterator<Point> it=boxset.iterator();
  for(int i=0;i<8;i++)
   {
       
       foodset.add(it.next());
   }
 
 }
 

    
 }

