package codeground;

import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class Cprac_하노이타워 {
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

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {

			// Answer = 0;
			/////////////////////////////////////////////////////////////////////////////////////////////
			
			int N = sc.nextInt();
			
			System.out.println("Case #"+(test_case+1));
			move(N,1,3,2);
			/////////////////////////////////////////////////////////////////////////////////////////////


			// Print the answer to standard output(screen).
		//	System.out.println("Case #"+(test_case+1));
		//	System.out.println(Answer);
		}
	}

	private static void move(int n, int from, int to, int m) {
		// TODO Auto-generated method stub
		if(n==1){
			System.out.println(from + " -> "+ to);
		}
		else{
			move(n-1,from,m,to);
			System.out.println(from + " -> "+ to);
			move(n-1,m,to,from);
		}
	}
	
	
	
}