package codeground;

import java.util.ArrayList;
import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class Cprac_rectengle3 {
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

		ArrayList<Rec> S = new ArrayList<>();
		int[] dpMax ; // dpMax[i]: i까지의 사각형으로 만들수 있는 가장 긴 증가하는 수열의 길이
		int[] dpMin ; //dpMin[i]: i까지의 사각형으로 만들수 있는 가장 긴 감소하는 수열의 길이

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
			S.clear();
			dpMax  = new int[K];
			dpMin = new int[K];
			
			dpMax[0] = 1;
			dpMin[0] = 1;
			
			int lx,ly,rx,ry;
			lx = sc.nextInt();
			ly = sc.nextInt();
			rx = sc.nextInt();
			ry = sc.nextInt();

			S.add(new Rec(lx,ly,rx,ry));

			
			for(int i=1; i<K;i++){
				lx = sc.nextInt();
				ly = sc.nextInt();
				rx = sc.nextInt();
				ry = sc.nextInt();

				Rec temp = new Rec(lx,ly,rx,ry);

				S.add(temp);
				dpMax[i] = 1;
				dpMin[i] = 1;
				
				for(int j=i-1;j>=0;j--){
					if(temp.compareTo(S.get(j)) == -1){
						if(dpMin[i] < dpMin[j] +1){
							dpMin[i] = dpMin[j] +1;
						}
					}
					
					else if(temp.compareTo(S.get(j)) == 1){
						if(dpMax[i] < dpMax[j] +1){
							dpMax[i] = dpMax[j] +1;
						}
					}
				}
			
			}

			int max = 0;
			for(int i=0;i<K;i++){
				max = dpMax[i] > max ? dpMax[i] : max;
				max = dpMin[i] > max ? dpMin[i] : max;
			}

			Answer = max;
			/////////////////////////////////////////////////////////////////////////////////////////////


			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
}

class Rec implements Comparable<Rec>{
	int ldx;   // left down x
	int ldy;
	int rux;    // right up
	int ruy;

	public Rec(int ldx, int ldy, int rux, int ruy) {
		super();
		this.ldx = ldx;
		this.ldy = ldy;
		this.rux = rux;
		this.ruy = ruy;
	}

	@Override
	public int compareTo(Rec other) {
		// TODO Auto-generated method stub
		// 포함하면 1 포함되면 -1 아무것도 아니면 0
		if((ldx < other.ldx && ldy < other.ldy) && (rux > other.rux && ruy > other.ruy)){
			return 1;
		}
		else if((ldx > other.ldx && ldy > other.ldy) && (rux < other.rux && ruy < other.ruy)){
			return -1;
		}
		else return 0;		

	}

	@Override
	public String toString() {
		return "R[ldx=" + ldx + ", ldy=" + ldy + ", rux=" + rux + ", ruy=" + ruy + "]";
	}
	
	
}
