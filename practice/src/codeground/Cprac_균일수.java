package codeground;

import java.util.ArrayList;
import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class Cprac_���ϼ� {
	static int Answer;
	
	static ArrayList<Integer> max = new ArrayList<Integer>();  //�� b�� ����� ���ϼ��� �ִ밪


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

		//b�� ���ϼ��� t + t*b + t*(b^2) + t* (b^3)�� �����̴�.
		//���� t�� ������ ������ = 0
		//�״��� 1�� ���ָ� b�� ���������� ������ = 0  
		//�� 1�� ���ָ� b�� ���������� ������ = 0
		// ... ���� �ݺ��Ǹ� N�� b�� ���ϼ��̴�. 
		

		
		for(int test_case = 0; test_case < T; test_case++) {

			// Answer = 0;
			/////////////////////////////////////////////////////////////////////////////////////////////
			int N = sc.nextInt();
			int temp = N;

			//b�� ���ϼ��� t + t*b + t*(b^2) + t* (b^3) + .... �� �����̴�.
			int min = N-1;

			
				for(int t = 1; t*t<N; t++){   // t�� rootN���� �۴�. (t*t < t(1+b)  <  N
					
					temp = N;
					//���� t�� ������ ������ = 0
					if(temp % t != 0) continue;
					//�״��� 1�� ���ָ� b�� ���������� ������ = 0
					temp /= t;
					temp--;
					
					// N = t(1+b+b^2+...)  > t*b > t^2
					for(int b = t+1;b<= temp;b++){ //b�� temp���� �۰ų� ����
						int temp2 = temp;
						if(temp2 % b != 0) continue;

						// ... ���� �ݺ��Ǹ� N�� b�� ���ϼ��̴�. 
						while(temp2 >0){
							if(temp2%b != 0) break;

							temp2 /= b;
							temp2--;
						}

						if(temp2 ==0){
							min = min > b? b : min;
						}

					}

				}
			Answer = min;
			/////////////////////////////////////////////////////////////////////////////////////////////


			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
}