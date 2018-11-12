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


		// 포함하는 사각형을 더 큰 것으로 했을 때, 가장 긴 증가하는 , 감소하는 수열을 찾는다. 

		PriorityQueue<Integer> ldx = new PriorityQueue<>(new Ldx());   // 왼쪽 아래점의 x좌표를 기준으로 사각형 정렬 
		LinkedList<Integer> ldy  ;   // 왼쪽 아래점의 y좌표를 기준으로 사각형 정렬
		LinkedList<Integer> rux;   // 오른쪽 위 x
		LinkedList<Integer> ruy;   // 오른쪽 위 y
		
		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {

			// Answer = 0;
			/////////////////////////////////////////////////////////////////////////////////////////////
			//왼쪽밑기준 오름차순   , // 오른쪽 위 기준 내림차순 정렬을 실시
			// 두 list의 LCS의 길이를 구한다.
			
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

