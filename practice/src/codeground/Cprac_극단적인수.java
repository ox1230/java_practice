package codeground;


import java.util.HashMap;
import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class Cprac_극단적인수 {
	static long Answer;
	
	static HashMap<Integer,Long> S = new HashMap<>();

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
		
		
		S.put(1, 4L);
		S.put(2, 7L);
		
		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {

			// Answer = 0;
			/////////////////////////////////////////////////////////////////////////////////////////////
			
			int k = sc.nextInt();
			Answer = getS(k);
			
			/////////////////////////////////////////////////////////////////////////////////////////////


			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
	
	
	static long getS(int n){
		if(S.containsKey(n)){
			return S.get(n);
		}
		else{
			int temp = n;
			int  t = 2;
			int cnt = 1;  // 자리수 세기
			int isSeven = 0; // 4인지 7인지 체크
			temp -= 2;
			
			while(temp > t){
				
				isSeven = 1;
				temp -= t;
				
				if(temp <= t) break;
				
				isSeven = 0;
				temp -= t;
				
				t *= 2;
				cnt++;
				
			}
			
			long ret = isSeven == 1? 7 : 4;
			
			ret *= Math.pow(10, cnt);
			
			//temp찾기
			int two = 2;
			for(int i=cnt-2; i>=0; i--){
				temp += two;
				two *=2;
			}
			
			ret += getS(temp);
			S.put(n, ret);
			
			return ret;
		}
	}
	
	
}