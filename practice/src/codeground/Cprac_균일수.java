package codeground;

import java.util.ArrayList;
import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class Cprac_균일수 {
	static int Answer;
	
	static ArrayList<Integer> max = new ArrayList<Integer>();  //각 b별 계산한 균일수의 최대값


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

		//b의 균일수는 t + t*b + t*(b^2) + t* (b^3)의 형태이다.
		//따라서 t로 나누면 나머지 = 0
		//그다음 1을 빼주면 b로 나누었을때 나머지 = 0  
		//또 1을 빼주면 b로 나누었을때 나머지 = 0
		// ... 무한 반복되면 N은 b의 균일수이다. 
		

		
		for(int test_case = 0; test_case < T; test_case++) {

			// Answer = 0;
			/////////////////////////////////////////////////////////////////////////////////////////////
			int N = sc.nextInt();
			int temp = N;

			//b의 균일수는 t + t*b + t*(b^2) + t* (b^3) + .... 의 형태이다.
			int min = N-1;

			
				for(int t = 1; t*t<N; t++){   // t는 rootN보다 작다. (t*t < t(1+b)  <  N
					
					temp = N;
					//따라서 t로 나누면 나머지 = 0
					if(temp % t != 0) continue;
					//그다음 1을 빼주면 b로 나누었을때 나머지 = 0
					temp /= t;
					temp--;
					
					// N = t(1+b+b^2+...)  > t*b > t^2
					for(int b = t+1;b<= temp;b++){ //b는 temp보다 작거나 같다
						int temp2 = temp;
						if(temp2 % b != 0) continue;

						// ... 무한 반복되면 N은 b의 균일수이다. 
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