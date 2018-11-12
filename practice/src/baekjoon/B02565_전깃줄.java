package baekjoon;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class B02565_������ {
	public static void main(String[] args) throws IOException {
		Wire2 w = new Wire2();
		w.input();
		System.out.println(w.solve());
	}
}


class Wire2{
	int N;
	int[][] S; // ������ ����
	
	int dp[];  //  dp[i]: i��°���� �������� ���� ���� ���� �� �ִ� �������� ��
	
	public void input() throws IOException {
		// TODO Auto-generated method stub
		Reader r = new Reader();
		
		N = r.nextInt();
		
		S = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			S[i][0] = r.nextInt();
			S[i][1] = r.nextInt();
		}
		
	}

	public int solve() {
		// TODO Auto-generated method stub
		// ù��° �����븦 �������� ����
		Arrays.sort(S, new Comp());
		
//		for (int i = 0; i < S.length; i++) {
//			System.out.println(Arrays.toString(S[i]));
//		}
		
		dp = new int[N];
		dp[0] = 1;
		
		for (int i = 1; i < N ; i++) {
			int tMax = 0;
			for (int j = 0; j < i; j++) {
				if(S[i][1] > S[j][1] && dp[j] > tMax){ // ������ �߰� ���� && ���� ŭ
					tMax = dp[j];
				}
			}
			
			dp[i] = tMax +1;
		}
		
		int max = 0;
		for (int i = 0; i < N ; i++) {
			if(max < dp[i]) max = dp[i];
		}
		
		return N - max;
	}
	
}

class Comp  implements Comparator<int[]>{

	@Override
	public int compare(int[] o1, int[] o2) {
		// TODO Auto-generated method stub
		if(o1[0] < o2[0]) return -1;
		else if(o1[0] > o2[0]) return 1;
		else return 0;
	}
	
}


