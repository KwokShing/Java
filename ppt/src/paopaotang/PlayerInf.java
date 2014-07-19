/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paopaotang;

/**
 *
 * @author Administrator
 */
public class PlayerInf {
      int score=0;
     public int level=0;
      public int speed=5;
     public int getspeed()
     {
         return speed;
     }
     public void setspeed()
     {
         speed=speed+2*level;
     }
     public int getscore()
     {
         return score;
     }
     public int getlevel()
     {
         return level;
     }
    public void setscore(int sc)
    {
        score=sc;
    }
    public void setlevel(int lv)
    {
        level=lv;
    }
    
}
