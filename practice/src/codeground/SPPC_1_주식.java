package codeground;

import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class SPPC_1_�ֽ� {
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
			int s;  // �ֽ��� ��� ����
			int high;  // �ֽ��� �Ĵ� ����
			int low;  // �ֽ��� �ٽ� ��� ���� == s
			int cnt = 0;  // ��� �Ĵ� Ƚ��
			// s< high�̰�, high>low�̾�� �Ѵ�.
			int N;  // �ŷ� �ϼ�
			int now;  // ���� ����
			
			N  = sc.nextInt();
			
			s = sc.nextInt(); // init
			high = s;
			low = s;
			boolean have =false ; // �ֽ��� ���� ���� �ִ��� üũ
			// have = false�̸� s - high�� �Ű澴��.
			// have = true�̸�  high - low�� �Ű澴��.
			for(int i=1;i<N;i++){
				now = sc.nextInt();
				
				if(have){  // �ֽ� ������  low�� �Ű澴��.
					if(now >= high) high  = now;
					else{  // �ֽ��� �ȼ� �ִ�! 						
						 cnt ++;
						 s = now;
						 have = false;
					}
				}
				else{
					if(s >= now) s = now; // �� ���� ������ ��ü
					else{ // �ֽ��� �� �� �ִ�.
						high = now;
						have = true;
					}
				}
				
				
				
			}
			
			if(have && s<high) cnt++;  // �ֽ��� ������ �ְ�, �������� ������ �곯���� ���ٸ�
			Answer = 2 * cnt;
			/////////////////////////////////////////////////////////////////////////////////////////////


			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
}