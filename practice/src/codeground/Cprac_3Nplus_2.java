package codeground;
/*
You should use the statndard input/output

in order to receive a score properly.

Do not use file input and output

Please be very careful. 
 */

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class Cprac_3Nplus_2 {
	static int Answer;

	static HashMap<Long,Integer> S = new HashMap<>(); //S<i,k> : i는 k번만에 1에 도달한다.
	static HashMap<Integer,Long> Smin = new HashMap<>(); // <k,min>: k에 도달가능한 수 중 가장 작은 수는  min                  
	
	
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

		long sMax = 1;  // S를 위해 탐색한 가장 큰값
			
		HashMap a = S;
		HashMap b = Smin;
		
		// initialization
		S.put(sMax, 0);
		Smin.put(0,sMax);
		
		
		for(int test_case = 0; test_case < T; test_case++) {

			// Answer = 0;
			/////////////////////////////////////////////////////////////////////////////////////////////
			long m = Long.MAX_VALUE;
			BigInteger M = new BigInteger("1");
			int K = sc.nextInt();
			
			if(Smin.containsKey(K)){ // 할것 없음 
			}
			else{
				long i = sMax +1;
				int tk;
			
				while(true){
					tk = getK((long)i);
					
					if(Smin.containsKey(tk)){
						// 할것 없음
					}
					else{
						Smin.put(tk, i);
						if(tk == K){
							sMax = i;
							break;
						}
					}
		
					i++;
				}
			}
			
			m = Smin.get(K);
			if(K >= 1){
				M = new BigInteger("2");
				M = M.pow(K);
			}
			
			
			/////////////////////////////////////////////////////////////////////////////////////////////


			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(m + " " + M);}
	}	
	
	static int getK(long t){
		int cnt = 0;
		
		if(S.containsKey(t)){
			cnt = S.get(t);
		}
		else{
			if(t%2 == 0){
				cnt = 1 + getK(t/2);
			}
			else{  // 3N +1
				cnt = 1 + getK(3*t + 1);
			}
			S.put(t,cnt);
		}
		
		return cnt;
	}
	
}