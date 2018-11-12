package codeground;
/*
You should use the statndard input/output

in order to receive a score properly.

Do not use file input and output

Please be very careful. 
 */

import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class Cprac_작은방2 {
	static int Answer;
	static int[][] S2;  // 2의 개수
	static int[][] S3;  // 3의 개수
	
	
	static int[][][] dp;   //dp[i][j][k]: i,j로 오늘길에 3이 k개 있을 때 최대 2의 개수 

	static int N;

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

			if(N > 50){
				Answer = 0;
			}
			else{
				S2 = new int[N][N];
				S3 = new int[N][N];
				
				int t;
				m3 = 0;
				for(int i=0;i<N;i++){
					for(int j=0;j<N;j++){

						t = sc.nextInt();

						S2[i][j] = multi(2,t);
						S3[i][j] = multi(3,t);
						
						m3 = m3 > S3[i][j] ? m3: S3[i][j];
					}
				}

				dp = new int[N][N][m3+1];
				/////////////////////////////////////////////////////////////////////////////////////////////
				Answer = solve();
				
			}	
			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);

		}
	}	


	static int multi(int n , int t){
		//t에 2,3이 몇개 들었는가?
		int ret = 0;

		while(t%n == 0){
			ret++;
			t /= n;
		}

		return ret;
	}


	static int solve(){
		// n-1,n-1부터 dp를 거꾸로 채워나간다.
		
		for(int i=N-1;i>=0;i--)for(int j=N-1;j>=0;j--){
			for(int k=0 ; k <= m3 - S3[i][j] ; k++){
				
			}
		}
		
		
	}
}
