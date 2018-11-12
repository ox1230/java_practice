package baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class Algo_동전의개수 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = 1000 - sc.nextInt();
		
		int coin[] = {1,5,10,50,100,500};
		int dp[][] = new int[coin.length][N+1];
		
		
		//1원 동전이 무조건 있어야 한다.
		for (int i = 1; i <= N; i++) {
			dp[0][i] = i; 
		}
		
		for (int j = 1; j < coin.length; j++) {
			for (int i = 1; i <= N ; i++) {
				if(coin[j] > i) {
					dp[j][i] = dp[j-1][i];
					continue;
				}
				
				dp[j][i] = dp[j-1][i] > dp[j][i-coin[j]]+1 ?  dp[j][i-coin[j]]+1 : dp[j-1][i];
			}
		}

//		for (int i = 0; i < dp.length; i++) {
//			System.out.println(Arrays.toString(dp[i]));
//		}
//		
		
		System.out.println(dp[coin.length-1][N]);
		
	}
}
