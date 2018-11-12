package codeground;

import java.util.Scanner;
import java.util.Stack;


/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class SPPC_1_괄호2 {
	static int Answer;
	static String S;
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
		sc.nextLine();

		for(int test_case = 0; test_case < T; test_case++) {

			// Answer = 0;
			/////////////////////////////////////////////////////////////////////////////////////////////
			S = sc.nextLine();

			int N = S.length(); // 문자열의 길이
			int cnt = 0;  // 현재까지 나타난 올바른 닫는 괄호
			int max = 0; // 가장 긴 길이
			Stack<int[]> save = new Stack<>();  // 저장소
			char t;
			int[] sasave = null;

			boolean reset = false;
			for(int i = 0; i<N;i++){
				t = S.charAt(i);
				reset = false;

				if(!save.isEmpty()) sasave = save.peek();
				else sasave = null;

				switch (t){
				case '(':
				case '{':
				case '[':
					save.push(new int[]{t,cnt}); break;
				case '}':{
					if(sasave != null && sasave[0] == '{'){
						cnt += 2;
						save.pop();
					}
					else reset = true;
					break;
				}
				case ')':{
					if(sasave != null && sasave[0] == '('){
						cnt += 2;
						save.pop();
					}
					else reset = true;
					break;
				}
				case ']':{
					if(sasave != null && sasave[0] == '['){
						cnt += 2;
						save.pop();
					}
					else reset = true;
					break;
				}
				}

				if(reset){  // 정산의 시간
					int ttt = countIt(save, cnt);
					max = ttt > max ? ttt: max;

					save.empty();
					cnt = 0;
				}

			}

			int ttt = countIt(save, cnt);
			max = ttt > max ? ttt: max;

			Answer = max;
			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
	private static int countIt(Stack<int[]> save, int cnt) {
		int prevCnt = cnt;
		int max = 0;
		int t;
		while(!save.isEmpty()){
			t = save.pop()[1];
			if(prevCnt != t){
				max = max > (prevCnt - t) ? max : (prevCnt -t);
			}
			prevCnt = t;
		}

		max = max > prevCnt ? max :prevCnt;

		return max;
	}

}
