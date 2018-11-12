package codeground;

import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class SCPC_hanoi2 {
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

			int from = 0;
			int way = 1;

			isValid = true;
			for(int n=N-1;n>0;n--){
				int test = S.charAt(n) - 'A';

				if(way == 1){// 앞으로 옮길때는 마지막것은 뒤로 가지 않는다.

					if(test == from){  //아직 가기 전
						way = -1;  // from에 있는 것을 뒤로 보내는 중
					}
					else if(test == (from+1)%3){ // 한칸 앞에 있음
						from = (from+2) % 3;
						way = -1;   // 뒤에 있던 것을 앞으로 보낸다.
					}
					else{  // 실패
						isValid = false;
						break;
					}
				}
				else if(way == -1){  // 뒤로 옮길 때
					if(test == from){  //아직 가기 전
						way = -1;  // from에 있는 것을 뒤로 보내는 중
					}
					else if(test == (from+1)%3){ // 한칸 앞에 있음
						from = (from+2) % 3;
						way = 1;   // 뒤에 있던 것을 from으로 복귀 시킴
					}
					else{  //뒤로 왔음
						way = -1;  // from에 있던 것을 다시 뒤로 보냄
					}
				}
			}
			// 1개만 옮길때
			if(isValid && way ==1){ // 뒤로 가지 않는다.
				if( (int)(S.charAt(0) - 'A') == (from+2)%3) isValid = false;
				else isValid = true;
			}
			else{ 

			}
			/////////////////////////////////////////////////////////////////////////////////////////////


			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			if(isValid) System.out.println("YES");
			else System.out.println("NO");
			//System.out.println(Answer);
		}
	}

}