package codeground;


import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class Cprac_rectengle2 {
	static int Answer;

	static int K;
	static int N;
	static int M;

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


		// �����ϴ� �簢���� �� ū ������ ���� ��, ���� �� �����ϴ� , �����ϴ� ������ ã�´�. 

		PriorityQueue<Integer> ldx = new PriorityQueue<>(new Ldx());   // ���� �Ʒ����� x��ǥ�� �������� �簢�� ���� 
		LinkedList<Integer> ldy  ;   // ���� �Ʒ����� y��ǥ�� �������� �簢�� ����
		LinkedList<Integer> rux;   // ������ �� x
		LinkedList<Integer> ruy;   // ������ �� y
		
		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {

			// Answer = 0;
			/////////////////////////////////////////////////////////////////////////////////////////////
			//���ʹر��� ��������   , // ������ �� ���� �������� ������ �ǽ�
			// �� list�� LCS�� ���̸� ���Ѵ�.
			
			N = sc.nextInt();
			M = sc.nextInt();
			K = sc.nextInt();
			
			//init
		

			/////////////////////////////////////////////////////////////////////////////////////////////


			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
}

class Ldx implements Comparator<Integer>{
	@Override
	public int compare(Integer arg0, Integer arg1) {
		// TODO Auto-generated method stub
		if()
		return 0;
	}
}

