package codeground;
/*
You should use the statndard input/output

in order to receive a score properly.

Do not use file input and output

Please be very careful. 
 */

import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class Cprac_������ {
	static int Answer;
	static int[][] S2;  // 2�� ����
	static int[][] S3;  // 3�� ����

	/* i,j������ upper bound�� ����Ѵ�.
	// ���̾� i���� ��� j���� ���� ������.
	    ���� 2,3�� ������  (���� ���� 2,3�� �ִ�ġ* ���� ��) + �� �ະ max������ Ŭ�� ����. 
	 */
	static int[] maxR2;  // 0~i���� ���ະ 2,3�� ���� ���� ������ �� 
	static int[] maxR3;
	
	static int[] maxC2;   // 0~j���� ���ະ 2,3�� ���� ���� ������ ��
	static int[] maxC3;

	static int N;
	static int max;

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

			if(N > 50){
				Answer = 0;
			}
			else{
				S2 = new int[N][N];
				S3 = new int[N][N];
				maxR2 = new int[N];
				maxR3 = new int[N];
				maxC2 = new int[N];
				maxC3 = new int[N];
				
				int t;
				int m2,m3; // �ະ �ִ�ġ
				int MM2 =  0, MM3 = 0; // ������� �ִ�ġ
				for(int i=0;i<N;i++){
					m2 = 0;
					m3 = 0;
					for(int j=0;j<N;j++){

						t = sc.nextInt();

						S2[i][j] = multi(2,t);
						S3[i][j] = multi(3,t);

						m2 = S2[i][j] > m2 ? S2[i][j]: m2;
						m3 = S3[i][j] > m3 ? S3[i][j]: m3;
					}

					if(i >0){
						maxR2[i] = maxR2[i-1] + m2;
						maxR3[i] = maxR3[i-1] + m3;
					}
					else{ // i =0
						maxR2[i] = m2;
						maxR3[i] = m3;
					}
				}
				
				//maxCä���
				m2 = S2[0][0];
				m3 = S3[0][0];
				
				for(int i=1;i<N;i++){
					m2 = S2[i][0] > m2? S2[i][0]: m2;
					m3 = S3[i][0] > m3? S3[i][0]: m3;
				}		
				maxC2[0] = m2;
				maxC3[0] = m3;
		
				for(int j=1;j<N;j++){
					m2 = S2[0][j];
					m3 = S3[0][j];
					
					for(int i=1;i<N;i++){
						m2 = S2[i][j] > m2? S2[i][j]: m2;
						m3 = S3[i][j] > m3? S3[i][j]: m3;
					}
					
					maxC2[j] = maxC2[j-1] +m2;
					maxC3[j] = maxC3[j-1] +m3;
				}
				
				
				/////////////////////////////////////////////////////////////////////////////////////////////
				max = 0;
				solve(N-1,N-1,0,0);
				Answer = max;
			}	
			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);

		}
	}	


	static int multi(int n , int t){
		//t�� 2,3�� � ����°�?
		int ret = 0;

		while(t%n == 0){
			ret++;
			t /= n;
		}

		return ret;
	}

	static int upperBound(int i, int j, int n2, int n3){
		n2 += maxR2[i];
		n3 += maxR3[i];
		
		n2 += maxC2[j];
		n3 += maxC3[j];
		
	//	System.out.println(n2+","+n3);
		return Math.min(n2, n3);
	}

	static void solve(int i, int j, int n2, int n3){
		// ��ü Ž�� - ����, ���� �̵��Ѵ�.
		if(i ==0 && j == 0){
			n2 += S2[0][0];
			n3 += S3[0][0];
			if(max < Math.min(n2, n3)) max = Math.min(n2, n3);
		}
		else if(upperBound(i,j,n2,n3) > max){
			if(i > 0){
				solve(i-1,j,n2+ S2[i][j], n3 + S3[i][j]);
			}

			if(j >0){
				solve(i,j-1,n2+ S2[i][j], n3 + S3[i][j]);
			}
		}
	}
}