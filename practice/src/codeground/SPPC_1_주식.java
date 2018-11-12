package codeground;

import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class SPPC_1_주식 {
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
			int s;  // 주식을 사는 시점
			int high;  // 주식을 파는 시점
			int low;  // 주식을 다시 사는 시점 == s
			int cnt = 0;  // 사고 파는 횟수
			// s< high이고, high>low이어야 한다.
			int N;  // 거래 일수
			int now;  // 현재 가격
			
			N  = sc.nextInt();
			
			s = sc.nextInt(); // init
			high = s;
			low = s;
			boolean have =false ; // 주식을 현재 갖고 있는지 체크
			// have = false이면 s - high만 신경쓴다.
			// have = true이면  high - low만 신경쓴다.
			for(int i=1;i<N;i++){
				now = sc.nextInt();
				
				if(have){  // 주식 소유중  low만 신경쓴다.
					if(now >= high) high  = now;
					else{  // 주식을 팔수 있다! 						
						 cnt ++;
						 s = now;
						 have = false;
					}
				}
				else{
					if(s >= now) s = now; // 더 낮은 값으로 교체
					else{ // 주식을 살 수 있다.
						high = now;
						have = true;
					}
				}
				
				
				
			}
			
			if(have && s<high) cnt++;  // 주식을 가지고 있고, 마지막날 가격이 산날보다 높다면
			Answer = 2 * cnt;
			/////////////////////////////////////////////////////////////////////////////////////////////


			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
}