class yThread implements Runnable{
 
    private int ticket = 10;  //5��Ʊ
 
    public void run() {
        for (int i=0; i<=5; i++) {
            if (this.ticket > 0) {
                System.out.println(Thread.currentThread().getName()+ "������Ʊ"+this.ticket--);
            }
        }
    }
}
public class lzwCode {
     
    public static void main(String [] args) {
        yThread my = new yThread();
        new Thread(my, "1�Ŵ���").start();
        new Thread(my, "2�Ŵ���").start();
        new Thread(my, "3�Ŵ���").start();
    }
}