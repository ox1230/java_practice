package codeground;

import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class SCPC_오래달리기2 {
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
			int[] resid = new int[N];

			for(int i=0;i<N;i++){
				S[i] = sc.nextInt();
				L[i] = sc.nextInt();
				D[i] = sc.nextInt();
			}

			// X가 L보다 클때

			//공통 끝나는 시간  X = (L-D)/s + L/s *t 이다.
			//정리하면 sX  = Lt + (L-D)
			// sX % L = L-D
			// (X%L * s) %L = L-D
			// 각각의 X%L을 구한다. -> resid에 저장
			boolean iszero = true;
			for(int i=0;i<N;i++){
				if(D[i] == 0){
					resid[i] =0;
				}
				else{
					iszero = false;

					int R = L[i] - D[i];

					if(S[i] % L[i] == 0) resid[i] = 0;
					else{
						int t = 1;
						for(;t<L[i];t++){
							if((t*S[i]) % L[i] == R){
								resid[i] = t;
								break;
							}
						}
					}
				}
			}

			
			if(iszero){
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
				//
				int t= -1;
				long X = 0;

				while(t < 1000000000){
					t++;
					//L(t+1) -D
					long temp = L[0]*(t) + L[0]  - D[0];

					if(temp % S[0] != 0) continue;

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
			else{
				// X = L*? + resid;
				int i =-1;
				long X = 0;
				while(i < 100000000){
					i++;
					X = L[0] *i + resid[0];
					
					boolean isAnswer = true;
					for(int j=1 ; j<N ; j++){
						if(X < resid[j] || (X-resid[j])% L[j]  != 0){
							isAnswer = false;
							break;
						}
					}

					if(isAnswer) break;
				}
				Answer = X;
			}
			/////////////////////////////////////////////////////////////////////////////////////////////

			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
	
	
	public static long lcm(long a, long b) {
	    int gcd_value = gcd((int)a, (int)b);

	    if (gcd_value == 0) return 0; // 인수가 둘다 0일 때의 에러 처리

	    return Math.abs( (a * b) / gcd_value );
	  }


	  public static int gcd(int a, int b) {
	    while (b != 0) {
	      int temp = a % b;
	      a = b;
	      b = temp;
	    }
	    return Math.abs(a);
	  }

	
}

