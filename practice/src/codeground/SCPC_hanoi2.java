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

				if(way == 1){// ������ �ű涧�� ���������� �ڷ� ���� �ʴ´�.

					if(test == from){  //���� ���� ��
						way = -1;  // from�� �ִ� ���� �ڷ� ������ ��
					}
					else if(test == (from+1)%3){ // ��ĭ �տ� ����
						from = (from+2) % 3;
						way = -1;   // �ڿ� �ִ� ���� ������ ������.
					}
					else{  // ����
						isValid = false;
						break;
					}
				}
				else if(way == -1){  // �ڷ� �ű� ��
					if(test == from){  //���� ���� ��
						way = -1;  // from�� �ִ� ���� �ڷ� ������ ��
					}
					else if(test == (from+1)%3){ // ��ĭ �տ� ����
						from = (from+2) % 3;
						way = 1;   // �ڿ� �ִ� ���� from���� ���� ��Ŵ
					}
					else{  //�ڷ� ����
						way = -1;  // from�� �ִ� ���� �ٽ� �ڷ� ����
					}
				}
			}
			// 1���� �ű涧
			if(isValid && way ==1){ // �ڷ� ���� �ʴ´�.
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