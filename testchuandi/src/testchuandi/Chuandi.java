package testchuandi;

//������������������
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

//�ڷ����� ����������������int���飬ʵ���ϴ��ݵ���������count�Ŀ��������Ƕ�ָ����������ڷ����п��Ըı������������ݡ���:�Ը��Ƶ����������õķ������ĵ���ͬһ������

