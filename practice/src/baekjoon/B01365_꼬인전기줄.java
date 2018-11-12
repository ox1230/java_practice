package baekjoon;

import java.util.Scanner;


public class B01365_꼬인전기줄 {
	public static void main(String[] args) {
		Wire w = new Wire();
		w.input();
		System.out.println(w.solve());
	}
}

class Wire {
	int N;
	int[] S;

	// 수열을 오름차순으로 만들기 위해 최소한으로 빼야하는 숫자의 개수문제와 같다.
		// 최장 증가하는 수열을 구한다.
	int[] dp; // dp[i];  0~i까지의 숫자중 최장증가하는 수열의 길이.			  
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
		
		// 자기 이전의 dp[i]를 모두 탐색하면서 가능한 가장긴 수열을 만들수 있는 것을 찾는다.
		for (int i = 1; i < N; i++) {
			int tMax = 0;
			
			for (int j = 0; j < i; j++) {
				if(S[i] > S[j] && dp[j] > tMax){  // 추가 가능
					tMax = dp[j];
				}
			}
			dp[i] = tMax +1;   // i까지중 가장 많이 남길 수 있는 전깃줄의 개수
		}
	
		int max = 0;
		
		for (int i = 0; i < dp.length; i++) {
			if(dp[i] > max) max = dp[i];
		}
		
		return N - max; // 제거한 전깃줄의 개수 출력
	}

}