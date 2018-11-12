package baekjoon;

import java.util.Scanner;

public class B11052_붕어빵 {
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
	
	
	// dp를 통해 해결한다.  ( 4(i) 최적은 3개최적(j) + 1개set  / 2개최적 + 2개set / 1개최적 + 3개set  / 0개 최적(0) + 4개 set  중 하나이다.)
	
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