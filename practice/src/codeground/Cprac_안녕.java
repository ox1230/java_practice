package codeground;

import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class Cprac_안녕 {
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
		
		String S;
		
		int T = sc.nextInt();
		sc.nextLine();  // 버퍼 제거
		
		for(int test_case = 0; test_case < T; test_case++) {
			
			// Answer = 0;
			/////////////////////////////////////////////////////////////////////////////////////////////
			S = sc.nextLine();
			
			int cntH = 0;
			int cntE = 0;
			int cntL = 0;
			int cntO = 0;
			
			for(char t: S.toCharArray()){
				switch (t){
					case 'h': cntH++;break;
					case 'e': cntE++;break;
					case 'l': cntL++;break;
					case 'o': cntO++;break; 
				}
			}
			
			cntL /= 2;  // hello한개당 l은 2개 필요하다.
			
			// 숫자가 가장 작은 문자에 맞출 수 밖에 없다.
			int min  = cntH > cntE ? cntE : cntH;
			min = min > cntL ? cntL : min;
			min = min > cntO ? cntO : min;
			
			Answer = min;
			/////////////////////////////////////////////////////////////////////////////////////////////


			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
}