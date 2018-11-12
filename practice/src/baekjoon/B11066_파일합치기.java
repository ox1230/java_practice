package baekjoon;

import java.util.Scanner;

public class B11066_������ġ�� {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		SumOfFile sf = new SumOfFile(); 

		for (int i = 0; i < T; i++) {
			sf.input(sc);
			System.out.println(sf.solve());
		}
	}
}

class SumOfFile{
	int K;
	int S[]; // ���ϵ��� �Է¹��� �迭

	int DD[][];   // DD[s][e]: S~E���� ��ġ�µ� ��� ����� �ּڰ�.

	public void input(Scanner sc) {
		// TODO Auto-generated method stub

		K = sc.nextInt();

		S = new int[K];

		for (int i = 0; i < S.length; i++) {
			S[i] = sc.nextInt();
		}
	}
	public int solve() {
		// TODO Auto-generated method stub
		DD = new int[K][K];

		return dp(0,K-1);
	}
	private int dp(int s, int e) {
		// TODO Auto-generated method stub
		// s~ e������ ������ ��ġ�� ����� �ּҹ���� ã�´�.
		if(DD[s][e] > 0) {}
		else if(s == e){
			return 0;
		}
		else if(s+1 == e){
			DD[s][e] = S[s] + S[e]; 
		}
		else{

			int min = Integer.MAX_VALUE;
			// s~e�� ���ϵ��� ���� �����Ϸ� ����µ� ��� �ּڰ�
			for (int i = s; i < e; i++) {
				int temp = dp(s,i) + dp(i+1,e);
				if(min > temp) min = temp;
			}

			// ������ ���� �������� ��ġ�µ� ��� ���( ��ü ���ϵ��� ũ���� ��)
			for (int i = s; i <= e; i++) {
				min += S[i];
			}

			DD[s][e] = min;
		}

		return DD[s][e];
	}
}