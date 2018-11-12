package codeground;
/*
You should use the statndard input/output

in order to receive a score properly.

Do not use file input and output

Please be very careful. 
*/

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class Cprac_개구리 {
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
		
		ArrayList<Integer> as;  // 돌들의 위치 저장
		int N;
		int K;
		
		for(int test_case = 0; test_case < T; test_case++) {
			
			// Answer = 0;
			/////////////////////////////////////////////////////////////////////////////////////////////
			//Input
			N = sc.nextInt();
			
			as = new ArrayList<>(N);
			for(int i=0;i<N;i++){
				as.add(sc.nextInt());
			}
			K = sc.nextInt();
			
			//init
			Iterator<Integer> asIter = as.iterator();
			int prev =0;  // 이전에 착지한 돌의 위치
			int curr; // 현재 노리는 돌의 위치
			int lastStone = 0;  // curr직전 돌의 위치
			int cnt = 0;
			
			//solve
			while(asIter.hasNext()){
				curr = asIter.next();
				
				if(prev + K >= curr){ //curr에 점프 가능
					lastStone = curr;
				}
				else{  // curr에 jump불가  -- lastStone에 점프
					prev = lastStone;
					
					if(prev + K >= curr){  // lastStone- curr까지 jump가 가능한지 확인
						cnt++;
						lastStone = curr;
					}
					else{  // 할수 없으면 break
						break;
					}
				}
			}
			
			if(lastStone == prev) Answer = -1; // 움직일 방법이 없음
			else Answer = cnt+1; // N번째 돌로 점프하는것 추가
			
			
			/////////////////////////////////////////////////////////////////////////////////////////////


			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
}