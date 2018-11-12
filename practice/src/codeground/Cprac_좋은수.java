package codeground;

import java.util.HashSet;
import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class Cprac_좋은수 {
	static int Answer;

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
		
		int[] S;
		int N;
		HashSet<Integer> two = new HashSet<>(); 
		// 1-i까지의 숫자 2개를 더해 만들 수 있는 수
		
		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {

			// Answer = 0;
			/////////////////////////////////////////////////////////////////////////////////////////////
			N = sc.nextInt();
			S = new int[N];
			two.clear();
			//init
			
			for(int i=0;i<N;i++){
				S[i]  = sc.nextInt();
			}
			
			two.add(S[0]*2);
			
			int cnt = 0;
			for(int i=1;i<N;i++){
				for(int j=i-1; j>=0; j--){	
					if(two.contains(S[i] - S[j])){
						//S[i] = S[j] + 다른 두수의 합이면
						cnt++;
						break;
					}
				}
				for(int j=i;j>=0;j--){ // two에 S[i]추가
					two.add(S[i]+S[j]);
				}
			}
			
			Answer = cnt;
			/////////////////////////////////////////////////////////////////////////////////////////////


			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
}