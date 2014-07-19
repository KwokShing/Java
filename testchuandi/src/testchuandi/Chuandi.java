package testchuandi;

//传的是引用数据类型
public class Chuandi {

	public static void change(int[] counts) {
		counts[0] = 6;
		System.out.println(counts[0]);
	}

	public static void main(String[] args) {
		int[] count = { 1, 2, 3, 4, 5 };
		change(count);
		System.out.println("and "+count[0]);
	}
}

//在方法中 传递引用数据类型int数组，实际上传递的是其引用count的拷贝，他们都指向数组对象，在方法中可以改变数组对象的内容。即:对复制的引用所调用的方法更改的是同一个对象。

