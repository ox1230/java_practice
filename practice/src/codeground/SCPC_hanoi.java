package codeground;

import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class SCPC_hanoi {
	static int Answer;

	static String S;
	static boolean isValid = false;

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
			int N;

			N = sc.nextInt(); sc.nextLine();
			S = sc.nextLine();

			StringBuffer ret = new StringBuffer();
			

			isValid = false;
			
			if(N >5){}
			else{
				for(int i=0;i<N;i++){
					ret.append('A');
				}

				if(S.equals(ret.toString())) isValid = true;
				move(N-1,'A',1,ret);
			}
			
			
			
			/////////////////////////////////////////////////////////////////////////////////////////////
			
			
			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			if(isValid) System.out.println("YES");
			else System.out.println("NO");
			//System.out.println(Answer);
		}
	}

	private static StringBuffer move(int n, char from, int way, StringBuffer ret) {
		// TODO Auto-generated method stub
		if(n==0){
			if(way == 1){
				switch(from){  // n-1번쨰 것을 한칸 앞으로 옮긴다.
				case 'A': 
					ret.setCharAt(n, 'B');
					break;
				case 'B':
					ret.setCharAt(n, 'C');
					break;
				case 'C':
					ret.setCharAt(n, 'A');
					break;
				}
				if(S.equals(ret.toString())) isValid = true;
			}
			else{
				switch(from){  // n-1번쨰 것을 한칸 뒤로 옮긴다.
				case 'A': 
					ret.setCharAt(n, 'B');
					if(S.equals(ret.toString())) isValid = true;
					ret.setCharAt(n, 'C');
					break;
				case 'B':
					ret.setCharAt(n, 'C');
					if(S.equals(ret.toString())) isValid = true;
					ret.setCharAt(n, 'A');
					break;
				case 'C':
					ret.setCharAt(n, 'A');
					if(S.equals(ret.toString())) isValid = true;
					ret.setCharAt(n, 'B');
					break;
				}
				if(S.equals(ret.toString())) isValid = true;
			}
		}
		else if(way == 1){  // n개를 앞으로 한칸 옮긴다.

			ret = move(n-1,from,-1,ret);

			switch(from){  // n-1번쨰 것을 한칸 앞으로 옮긴다.
			case 'A': 
				ret.setCharAt(n, 'B');
				break;
			case 'B':
				ret.setCharAt(n, 'C');
				break;
			case 'C':
				ret.setCharAt(n, 'A');
				break;
			}
			if(S.equals(ret.toString())) isValid = true;

			ret = move(n-1, (char) ((from -'A' + 2 )%3 +'A') , -1, ret);
			// 한칸 뒤에 있는 것.
		}
		else if( way == -1){ // n-1개를 뒤로 한칸 옮긴다.
			ret = move(n-1,from,-1,ret); // n-1개를 한칸 뒤로 옮긴다.

			switch(from){  // n-1번쨰 것을 한칸 앞으로 옮긴다.
			case 'A': 
				ret.setCharAt(n, 'B');
				break;
			case 'B':
				ret.setCharAt(n, 'C');
				break;
			case 'C':
				ret.setCharAt(n, 'A');
				break;
			}
			if(S.equals(ret.toString())) isValid = true;


			ret = move(n-1, (char) ((from -'A' + 2 )%3 +'A'),1,ret); // n-1개를 한칸 앞으로 옮긴다.

			switch(from){  // n-1번쨰 것을 한칸 앞으로 옮긴다.
			case 'A': 
				ret.setCharAt(n, 'C');
				break;
			case 'B':
				ret.setCharAt(n, 'A');
				break;
			case 'C':
				ret.setCharAt(n, 'B');
				break;
			}
			if(S.equals(ret.toString())) isValid = true;

			ret = move(n-1, from , -1 , ret); // n-1개를 한칸 뒤로 옮긴다.
		}
		return ret;
	}
}