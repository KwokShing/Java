
public class myThread implements Runnable{
	private int num;
	private int mutual = 10;
	public myThread(int num){
		this.num = num;
	}
	@Override
	public void run(){
		for(int i=0;i<5;i++){
			if(this.mutual>0)
				System.out.println(Thread.currentThread().getName()+" "+i+" " + this.mutual--);
		}
	}
	
	public static void main(String args[]){
		myThread thread = new myThread(10);
		new Thread(thread,"1").start();
		new Thread(thread,"2").start();
		new Thread(thread,"3").start();
		
	}
}
