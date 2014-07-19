package student;

public class Student implements StudentInterface {
	// 学生是否上交作业
	private boolean isHW = false;
	// 学生是否需要答疑
	private boolean isConfused = false;
	private Teacher teacher = new Teacher();

	public boolean isHW() {
		return isHW;
	}

	public void setHW(boolean isHaveBreakfast) {

		this.isHW = isHaveBreakfast;
	}

	public boolean isConfused() {
		return isConfused;
	}

	public void setConfused(boolean isHaveFun) {
		this.isConfused = isHaveFun;
	}

	// 学生交作业
	public void handHW() {
		this.isHW = true;
		System.out.println("学生上交作业");
		this.teacher.update("学生上交作业");
	}

	// 学生发现有疑惑
	public void haveConfusion() {
		this.isConfused = true;
		System.out.println("学生有疑惑");
		this.teacher.update("学生有疑惑");
	}
}
