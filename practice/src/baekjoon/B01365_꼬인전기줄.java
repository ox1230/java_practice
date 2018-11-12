package baekjoon;

import java.util.Scanner;


public class B01365_���������� {
	public static void main(String[] args) {
		Wire w = new Wire();
		w.input();
		System.out.println(w.solve());
	}
}

class Wire {
	int N;
	int[] S;

	// ������ ������������ ����� ���� �ּ������� �����ϴ� ������ ���������� ����.
		// ���� �����ϴ� ������ ���Ѵ�.
	int[] dp; // dp[i];  0~i������ ������ ���������ϴ� ������ ����.			  
	// 
	public void input()  {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();

		S = new int[N];

		for (int i = 0; i < N; i++) {
			S[i] = sc.nextInt();
		}
	}

	public int solve() {
		// TODO Auto-generated method stub
		
		dp = new int[N];
		
		//init
		dp[0] = 1;
		
		// �ڱ� ������ dp[i]�� ��� Ž���ϸ鼭 ������ ����� ������ ����� �ִ� ���� ã�´�.
		for (int i = 1; i < N; i++) {
			int tMax = 0;
			
			for (int j = 0; j < i; j++) {
				if(S[i] > S[j] && dp[j] > tMax){  // �߰� ����
					tMax = dp[j];
				}
			}
			dp[i] = tMax +1;   // i������ ���� ���� ���� �� �ִ� �������� ����
		}
	
		int max = 0;
		
		for (int i = 0; i < dp.length; i++) {
			if(dp[i] > max) max = dp[i];
		}
		
		return N - max; // ������ �������� ���� ���
	}

}