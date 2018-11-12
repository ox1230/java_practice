package codeground;


import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class Cprac_수강신청 {
	static int Answer;
	static int N;
	static int M;
	static int[] cre;
	
	static int dp[][];  // dp[N][M]  - > [i][j]:   i번째 수업까지,  제한이 j일때의 최대 수강 학점
	
	
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

		int T = sc.nextInt();
		
		
		for(int test_case = 0; test_case < T; test_case++) {

			// Answer = 0;
			/////////////////////////////////////////////////////////////////////////////////////////////
			N = sc.nextInt();
			M = sc.nextInt();
			
			//init
			int[] cre = new int[N+1];
			int dp[][] = new int[N+1][];  // dp[N][M]  - > [i][j]:   i번째 수업까지,  제한이 j일때의 최대 수강 학점
			
			
			dp[0] = new int[M+1];
			for(int i=1;i<=N;i++){
				cre[i] = sc.nextInt();
				dp[i] = new int[M+1];
			}

			
			for(int i=1;i<=N;i++){
				int m = cre[i];
				for(int j=m;j <= M; j++){
					dp[i][j] = dp[i-1][j] > dp[i-1][j-m] + m ? dp[i-1][j] : dp[i-1][j-m] + m;
				}
			}
			
			Answer = dp[N][M];
			/////////////////////////////////////////////////////////////////////////////////////////////


			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
}