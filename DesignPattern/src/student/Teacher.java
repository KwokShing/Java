package student;

public class Teacher implements TeacherInterface {
	public void update(String str) {
		System.out.println("老师观察到同学的状态发生改变");
		System.out.println("老师知道"+str);
	}
}
