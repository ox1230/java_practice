package codeground;

import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class SCPC_오래달리기 {
	static long Answer;

	static int N;

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

			N = sc.nextInt();
			int[] S = new int[N]; 
			int[] L = new int[N]; 
			int[] D = new int[N];

			boolean iszero = true;
			for(int i=0;i<N;i++){
				S[i] = sc.nextInt();
				L[i] = sc.nextInt();
				D[i] = sc.nextInt();

			}

			//공통 끝나는 시간  X = (L-D)/s + L/s *t 이다.
			//정리하면 sX + D = L(t+1)
			// 1번의 각 t별 X를 구한다.
			// 각 X별 sX + D 를 구해서 L로 나누어 떨어지면 가능
			if(N >3){
				Answer = 0;
			}
			else{
				//L/S가 가장 큰 주자를 기준으로 한다.
				int p = 0;
				for(int i=0;i<N;i++){
					if(L[p]/S[p] <L[i]/S[i]) p = i;
				}

				// 0과 p의 위치를 바꾼다.
				if(p> 0){
					int forChange;
					forChange = L[0];
					L[0] = L[p];
					L[p] = forChange;

					forChange = S[0];
					S[0] = S[p];
					S[p] = forChange;

					forChange = D[0];
					D[0] = D[p];
					D[p] = forChange;


				}
				//sX  =  Lt + (L-D)
				// 따라서 Lt%s + (L-D) %s = s or 0
				// (L-D)%s = r이라하면 L*t = s*? + r
				// L = (s*? + L%s) t = (s*? + t%s)    Lt %s = (L%s * t%s) %s = r    => t%s도출 가능
				// t = s*b + t%s
				int r = (L[0] - D[0]) % S[0];
				int k = r*S[0] - (L[0] %S[0]);
				
				int ii = -1;
				int t = 0;
				long X = 0;

				while(t < 1000000000){
					ii++;
					t = S[0]*ii + k; 
					//L(t+1) -D
					long temp = L[0]*(t) + L[0]  - D[0];

				//	if(temp % S[0] != 0) continue;

					X = temp / S[0];

					long tt;
					boolean isAnswer = true;
					for(int i=1;i <N;i++){
						tt = S[i]*X -L[i] + D[i];

						if(tt % L[i] != 0){
							isAnswer = false;
							break;
						}
					}

					if(isAnswer){
						break;
					}
				}


				Answer = X;
			}
			/////////////////////////////////////////////////////////////////////////////////////////////

			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
}