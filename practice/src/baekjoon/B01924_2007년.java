package baekjoon;

import java.util.Scanner;

public class B01924_2007�� {
	public static void main(String[] args) {
		Scanner sc  = new Scanner(System.in);
		
		int x = sc.nextInt();
		int y = sc.nextInt();

		String[] yoil = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT" };
		
		int[] rest = {0,0,3,3,6,8,11,13,16,19,21,24,26};
		// 1�� 1�ϰ� i�� 1���� ���� ����
		// ������ ��¥��%7�� ���� ��
		
		System.out.println(yoil[(y+rest[x])%7]);
	}
}
