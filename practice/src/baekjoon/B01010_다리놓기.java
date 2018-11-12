package baekjoon;

import java.util.Scanner;

public class B01010_다리놓기 {
	public static void main(String[] args)  {
		Scanner r = new Scanner(System.in);
		
		int T  = r.nextInt();
		
		int N;
		int M;
		
		for (int i = 0; i < T; i++) {
			
			N = r.nextInt();
			M = r.nextInt();
			
			
			// M개 중에 M-N개를 고르는 경우의 수
			
			long ret = 1;
			int t = M;
			for (int j = 1; j <= M-N; j++) {
				
				// M부터 M-N개의 숫자를 곱하는 부분
				ret *= t;
				t--;
				
				// M-N!로 나눠주는 부분
				ret/= j;
				
			}
			
			
			System.out.println(ret);
			
		}
	}
}
