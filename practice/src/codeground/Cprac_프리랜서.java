package codeground;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class Cprac_�������� {
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
		int N;
		ArrayList<Integer> P = new ArrayList<Integer>();
		ArrayList<Integer> Q = new ArrayList<Integer>();
		
		ArrayList<Integer> dpW = new ArrayList<Integer>();  //dpW[i]: i���� ���Ҷ��� �ִ� ����
		ArrayList<Integer> dpNot = new ArrayList<Integer>(); // dpNot[i]: i���� ������ �������� �ִ����(i-1�ϱ�����)
		                                                       // i+1�Ͽ� Q�� �� ��������
		
		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {

			// Answer = 0;
			/////////////////////////////////////////////////////////////////////////////////////////////
			P.clear(); Q.clear();
			//input
			N = sc.nextInt();
			for(int i=0;i<N;i++){
				P.add(sc.nextInt());
			}
			for(int i=0;i<N;i++){
				Q.add(sc.nextInt());
			}
			
			//init
			dpW.clear();
			dpNot.clear();
			Iterator<Integer> Pi = P.iterator();
			Iterator<Integer> Qi = Q.iterator();
			
			dpW.add(Math.max(Pi.next(), Qi.next()));
			dpNot.add(0);
			
			int p,q;
			for(int i=1;i<N;i++){
				int temp = Math.max(dpW.get(i-1), dpNot.get(i-1)); 
				int temp2;
				
				dpNot.add(temp);  // ������ 2���� ������ �� ū�� ����
				
				temp += Pi.next();   // P�� �� ����
 				temp2 = dpNot.get(i-1) + Qi.next(); //Q�� �� ����
				
				dpW.add(Math.max(temp, temp2));
			}
			/////////////////////////////////////////////////////////////////////////////////////////////

			Answer = Math.max(dpW.get(N-1), dpNot.get(N-1));
			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
}