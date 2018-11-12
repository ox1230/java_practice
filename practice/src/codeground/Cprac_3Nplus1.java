package codeground;
/*
You should use the statndard input/output

in order to receive a score properly.

Do not use file input and output

Please be very careful. 
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class Cprac_3Nplus1 {
	static int Answer;

	static HashMap<Integer,ArrayList<Long>> S = new HashMap<>(); // <k, {}>  : k���� 1�� �����ϴ� �͵��� ����
	static HashSet<Long> already = new HashSet<>();// ���� n�� �̹� �Ѱ����� üũ                      
	static int kMax = 0;


	// N�� k��° ���� �����ϸ� 2N , (N-1)/3�� k+1���� ���� �����ϴٴ� ���� �̿�
	// k=0���� k�� ������Ű�鼭 ������ NȮ��	

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

		// initialization
		ArrayList<Long> t = new ArrayList<>();
		t.add(1L);
		S.put(0, t);
		already.add(1L);


		for(int test_case = 0; test_case < T; test_case++) {

			// Answer = 0;
			/////////////////////////////////////////////////////////////////////////////////////////////
			long m = Long.MAX_VALUE;
			long M = 1;

			int K = sc.nextInt();

			if( K > 40){ // case ����
				m = 0;
				M = 0;
			}
			else{
				if(K > kMax){  // S�� �� ä���� �Ѵ�.
					//Sä���
					ArrayList<Long> t1; // g-1�� ������ ����
					ArrayList<Long> t2; // g�� ������ ����

					for(int g= kMax+1 ; g <= K ; g++){
						t1 = S.get((g-1));
						t2 = new ArrayList<>();

						for(long h: t1){
							// *2
							long temp = h*2;
							if(already.contains(temp)) continue;
							else{
								t2.add(temp);
								already.add(temp);
							}

							//3N+1
							temp = h-1;
							if(temp%3 != 0) continue;
							else{
								temp /= 3;
								if(already.contains(temp)) continue;

								t2.add(temp);
								already.add(temp);
							}
						}

						S.put(g, t2);
					}
					kMax = K;
				}

				// K�� min�� Max�� ���Ѵ�.
				ArrayList<Long> tt = S.get(K);

				m = Long.MAX_VALUE;
				M = 1;
				for(int i=0; i<K;i++){
					M *= 2;
				}


				long i;
				for(int j= 0 ;j < tt.size(); j++){
					i = tt.get(j);
					m = m > i? i: m;
				}
			}
			/////////////////////////////////////////////////////////////////////////////////////////////


			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(m + " " + M);}
	}	
}