public class sharedVariable {
	private int num = 0;

	public void count() {
//		int num=0;
		for (int i = 1; i <10; i++) {
			num += i;
		}

		System.out.println(Thread.currentThread().getName() + " " + num);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runnable run = new Runnable() {
			

			public void run() {
				sharedVariable s = new sharedVariable();
				s.count();
			}
		};
		for (int j = 0; j < 10; j++) {
			new Thread(run).start();
		}

	}

}
