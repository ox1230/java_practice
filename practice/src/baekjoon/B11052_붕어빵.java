package baekjoon;

import java.util.Scanner;

public class B11052_�ؾ {
	public static void main(String[] args)  {
		FishBread fb = new FishBread();
		fb.input();
		System.out.println(fb.solve());
	}
}


class FishBread{
	int N;
	int[] P;
	public void input() {
		// TODO Auto-generated method stub
		Scanner rr = new Scanner(System.in);
		
		N = rr.nextInt();
		
		P = new int[N+1];
		
		for(int i=1; i <=N ; i++){
			P[i] = rr.nextInt();
		}
		
	}
	
	
	// dp�� ���� �ذ��Ѵ�.  ( 4(i) ������ 3������(j) + 1��set  / 2������ + 2��set / 1������ + 3��set  / 0�� ����(0) + 4�� set  �� �ϳ��̴�.)
	
	public int solve() {
		// TODO Auto-generated method stub
		int dp[] = new int[N+1];
		
		dp[0] = 0;
		
		int max;
		for(int i=1; i <= N; i++){	
			max = 0;
			
			for( int j = 0; j < i; j++){
				if(dp[j] + P[i-j] > max) max = dp[j] + P[i-j]; 
			}
			
			dp[i] = max;
		
		}
		
		
		return dp[N];
	}
	
}