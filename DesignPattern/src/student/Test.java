package student;

public class Test {
	public static void main(String[] args) throws InterruptedException {
		Student student = new Student();

		Runnable hwThread = new HwThread(student, 1);
		Runnable confuseThread = new HwThread(student, 0);
		//�۲���ҵ�߳�
		Thread thread1 = new Thread(hwThread);
		//�۲��ɻ��߳�
		Thread thread2 = new Thread(confuseThread);
		thread1.start();
		thread2.start();

	}
}

class HwThread implements Runnable {
	Student student;
	int type;
	int i = 0;

	public HwThread(Student student, int type) {
		this.student = student;
		this.type = type;
	}

	@Override
	public void run() {
		while (i++ < 10) {
			if (type == 1)
				student.handHW();
			else
				student.haveConfusion();
		}
	}
}
