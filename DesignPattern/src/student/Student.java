package student;

public class Student implements StudentInterface {
	// ѧ���Ƿ��Ͻ���ҵ
	private boolean isHW = false;
	// ѧ���Ƿ���Ҫ����
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

	// ѧ������ҵ
	public void handHW() {
		this.isHW = true;
		System.out.println("ѧ���Ͻ���ҵ");
		this.teacher.update("ѧ���Ͻ���ҵ");
	}

	// ѧ���������ɻ�
	public void haveConfusion() {
		this.isConfused = true;
		System.out.println("ѧ�����ɻ�");
		this.teacher.update("ѧ�����ɻ�");
	}
}
