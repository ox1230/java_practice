package codeground;

import java.util.HashMap;
import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class Cprac_균일수2 {

	static int Answer;
	static HashMap<Integer, HashMap<Integer,Integer>> E = new HashMap<>(); // E[N] = <b,t>  : N은 t로 이루어진 b의 균일수
	
	
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
		
		HashMap cp = E;
		
		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {

			// Answer = 0;
			/////////////////////////////////////////////////////////////////////////////////////////////
			int N = sc.nextInt();
			int temp = N;
			
			int b= 2 ;
			for(;b<N;b++){
				temp = N;
				
				int t = temp % b; 
				if(t == 0) continue;
				temp /= b;
				
				if(temp <=2){
					if(temp == t) break;
				}
				else{
					if(!E.containsKey(temp)) getEqual(temp);
					if(E.get(temp).containsKey(b) && t == E.get(temp).get(b)) break;
				}
				
				
			}
			
			Answer = b;
			/////////////////////////////////////////////////////////////////////////////////////////////


			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}


	private static void getEqual(int T) {
		// TODO Auto-generated method stub
		// temp의 b, t를 찾는다.
		HashMap cp = E;
		E.put(T, new HashMap<>());
		
		int b = 2;
		int temp;
		HashMap<Integer,Integer> now = E.get(T);
		
		for(;b<T;b++){
			temp = T;
			
			int t = temp % b; 
			if(t == 0) continue;
			temp /= b;
			
			if(temp <=2){
				if(temp == t) now.put(b,t);
			}
			else{
				if(!E.containsKey(temp)) getEqual(temp);
				
				if(E.get(temp).containsKey(b) && t == E.get(temp).get(b))
					now.put(b,t);
				
			}
		}
	}
}