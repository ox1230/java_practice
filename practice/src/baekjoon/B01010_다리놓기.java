package baekjoon;

import java.util.Scanner;

public class B01010_�ٸ����� {
	public static void main(String[] args)  {
		Scanner r = new Scanner(System.in);
		
		int T  = r.nextInt();
		
		int N;
		int M;
		
		for (int i = 0; i < T; i++) {
			
			N = r.nextInt();
			M = r.nextInt();
			
			
			// M�� �߿� M-N���� ���� ����� ��
			
			long ret = 1;
			int t = M;
			for (int j = 1; j <= M-N; j++) {
				
				// M���� M-N���� ���ڸ� ���ϴ� �κ�
				ret *= t;
				t--;
				
				// M-N!�� �����ִ� �κ�
				ret/= j;
				
			}
			
			
			System.out.println(ret);
			
		}
	}
}
