package codeground;

import java.util.HashSet;
import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class Cprac_바이러스 {
	static int Answer;

	static HashSet<Integer>[] G;
	static int N,M;
	static int K;
	static int L;

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
			K = sc.nextInt();
			L = sc.nextInt();

			N = sc.nextInt();
			M = sc.nextInt();

			G = new HashSet[N+1];
			for(int i=1;i<=N;i++) G[i] = new HashSet<>();

			int t1,t2;
			for(int i=0;i<M;i++){
				t1 = sc.nextInt();
				t2 = sc.nextInt();

				G[t1].add(t2); G[t2].add(t1);
			}
			
			boolean del[] = new boolean[N];  // del이 true이면 폐기 대상
			
			for(int v=1;v<=N;v++){
				if( G[v].size() < K){
					delete(v, G, del);
				}
			}



			/////////////////////////////////////////////////////////////////////////////////////////////


			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}

	private static void delete(int v, HashSet[] GG, boolean[] del) {
		// TODO Auto-generated method stub
		del[v] = true;
		HashSet<Integer> temp = GG[v];
		
		for(int u: temp){
			
		}
	}


}