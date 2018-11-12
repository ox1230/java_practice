package codeground;
/*
You should use the statndard input/output

in order to receive a score properly.

Do not use file input and output

Please be very careful. 
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class Cprac_개구리_2{
	static int Answer;
	static int N;
	static ArrayList<Integer> as;  // 각 돌들의 좌표
	static int K;

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

			// input
			N = sc.nextInt();
			as = new ArrayList<>(N);
			as.add(0);

			for(int i=0;i<N;i++){
				as.add(sc.nextInt());
			}

			K = sc.nextInt();
			
			// solve
			
			//init
			ListIterator<Integer> gogo = as.listIterator();
			int prev = gogo.next();   // 이전 돌에서 갈 수 있는 가장 먼 곳으로 이동한다.
			int lastStone = prev; // prev에서 갈수 있는 가장 먼 돌
			int curr;
			int cnt = 0;
			
			boolean jump = false;
			
			for(int i=0 ;i<N;i++){
				curr = gogo.next();
				
				if(prev + K > curr){
					lastStone = curr;
					jump = true;
				}
				else{
					if(jump){
						prev = lastStone;
						
						gogo.
						
						
						jump = false;
					}
					else{
						break; // 답이 없음
					}
				}
				
			}
			
			
			if(jump) Answer = cnt +1;
			else Answer = -1 ; 
			
			/////////////////////////////////////////////////////////////////////////////////////////////

			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}

	}
}