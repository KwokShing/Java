public class SynchronizedThread {

	public static void main(String args[]) {
		Print thread = new Print();
		new Thread() {
			public void run() {
				thread.print("zhangsan");

			}
		}.start();
		new Thread() {
			public void run() {
				thread.print("lisi");
			}
		}.start();
	}

}

class Print {
	// synchronized ensure the function is mutual exclusive
	//	two ways
	public synchronized void print(String name) {

		for (int i = 0; i < name.length(); i++) {
			System.out.print(name.charAt(i));
			try {
				Thread.sleep(1);
			} catch (Exception e) {
			}
		}

	}

	// public void print(String name) {
	// synchronized (this) {
	// for (int i = 0; i < name.length(); i++) {
	// System.out.print(name.charAt(i));
	// try {
	// Thread.sleep(1);
	// } catch (Exception e) {
	// }
	// }
	// }
	// }
}