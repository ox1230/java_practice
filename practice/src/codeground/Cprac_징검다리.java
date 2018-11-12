package codeground;
/*
You should use the statndard input/output

in order to receive a score properly.

Do not use file input and output

Please be very careful. 
*/

import java.util.HashSet;
import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class Cprac_징검다리 {
	static int Answer;
	
	static int N;
	static int K;
	static int L;
	
	static HashSet<Integer> bomb; //bomb의 집합 
	
	public static void main(String args[]) throws Exception	{
		/*
		   The method below means that the program will read from input.txt, instead of standard(keyboard) input.
		   To test your program, you may save input data in input.txt file,
		   and call below method to read from the file when using nextInt() method.
		   You may remove the comment symbols(//) in the below statement and use it.
		   But before submission, you must remove the freopen function or rewrite comment symbols(//).
		 */		

		/*
		   Make new scanner from standard input System.in, and read data.
		 */
		Scanner sc = new Scanner(System.in);
		//Scanner sc = new Scanner(new FileInputStream("input.txt"));
		
		int dp[][];  // dp[i][k]: i번째 돌에 오기까지의 경우의수 (바로 직전, k개의 돌을 건너뛰었다.)
		// dp[i][0]에는 i번째 돌에 오는 모든 경우의 수를 저장

		
		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			
			// Answer = 0;
			/////////////////////////////////////////////////////////////////////////////////////////////
			N = sc.nextInt();
			K = sc.nextInt();
			L = sc.nextInt();
			
			bomb = new HashSet<>();
			for(int i=0;i<L;i++){
				bomb.add(sc.nextInt());
			}
			
			dp = new int[N+1][];
			
			//initialization
			dp[0] = new int[K+1];
			
			// 풀이 시작
			for(int i=1; i<=N;i++){
				dp[i] = new int[K+1];
				if(bomb.contains(i)) continue;
				// 지뢰가 있으면 pass
				
				int sum = 0;
				for(int m=1 ; m<= K;m++){
					if(i <m) break;  // 끝
					else if (i==m) dp[i][m] = 1;
					else dp[i][m] = (dp[i-m][0] - dp[i-m][m] + 1000000009) % 1000000009;  
					
					sum = (sum + dp[i][m]) % 1000000009;
				}
				
				dp[i][0] = sum; 
			}
			
			Answer = (int)(dp[N][0] % 1000000009);
			/////////////////////////////////////////////////////////////////////////////////////////////


			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
}