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
class Cprac_������ {
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
		
		ArrayList<Integer> as;  // ������ ��ġ ����
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
			int prev =0;  // ������ ������ ���� ��ġ
			int curr; // ���� �븮�� ���� ��ġ
			int lastStone = 0;  // curr���� ���� ��ġ
			int cnt = 0;
			
			//solve
			while(asIter.hasNext()){
				curr = asIter.next();
				
				if(prev + K >= curr){ //curr�� ���� ����
					lastStone = curr;
				}
				else{  // curr�� jump�Ұ�  -- lastStone�� ����
					prev = lastStone;
					
					if(prev + K >= curr){  // lastStone- curr���� jump�� �������� Ȯ��
						cnt++;
						lastStone = curr;
					}
					else{  // �Ҽ� ������ break
						break;
					}
				}
			}
			
			if(lastStone == prev) Answer = -1; // ������ ����� ����
			else Answer = cnt+1; // N��° ���� �����ϴ°� �߰�
			
			
			/////////////////////////////////////////////////////////////////////////////////////////////


			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
}