package codeground;

import java.util.Arrays;
import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */

class SPPC_1_전광판2 {
	static String Answer;
	static int[][] G;// 전광판
	static int[][] Grow; // 행스위치 번호
	static int[][] Gcol;  //열 스위치 번호

	static int[][] rowSwit;  // 행 스위치 on off   [n][m]   - -1:작동x, 0: 꺼짐, 1:켜짐
	static int[][] colSwit;  // 열 스위치 on off   [m][n]

	static int[][] anRow;  //정답
	static int[][] anCol;




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
		/*
		 * 	0행, 0열 부터 모든 경우의 수를 따져 본다.
		 */
		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {

			// Answer = 0;
			/////////////////////////////////////////////////////////////////////////////////////////////
			N = sc.nextInt();
			M = sc.nextInt();
			G = new int[N][M];
			Grow = new int[N][M];
			Gcol = new int[N][M];

			int on;
			int row;
			int col;

			boolean pass = false;//일단 case2까지만
			boolean case2 = true;
			for(int i=0;i<N;i++)for(int j=0;j<M;j++){
				on = sc.nextInt();
				row = sc.nextInt();
				col = sc.nextInt();

				if(row >= 1 || col >= 1) pass = true;

				G[i][j] = on;
				Grow[i][j] = row;
				Gcol[i][j] = col;
			}


			/////////////////////////////////////////////////////////////////////////////////////////////

			//if(N > 50 || M > 50) case2 = false;

			//일단 case1만 
			if(pass == false){
				int[] C1rowSwit = new int[N];
				int[] C1colSwit = new int[M];
				//case1
				C1rowSwit[0] = 0;

				//각 colSwit을 맞춰준다.
				for(int j=0;j<M;j++){
					C1colSwit[j] = G[0][j] == 0 ? 1 : 0;
					//전구, row스위치 꺼졌으면
				}

				boolean canDo = false;
				for(int i=1;i<N;i++){
					canDo = testOneRow(i,C1rowSwit,C1colSwit);
					if(canDo == false) break;
				}

				if(canDo){ // 정답 당첨
					Answer = createAnswer(C1rowSwit,C1colSwit);
				}
				else{ // 정답 아님
					//case2 
					C1rowSwit = new int[N];
					C1colSwit = new int[M];
					C1rowSwit[0] = 1;


					//각 colSwit을 맞춰준다.
					for(int j=0;j<M;j++){
						C1colSwit[j] = (1 + G[0][j])%2 == 0 ? 1 : 0;
						//전구, row스위치가 둘다 켜졌으면
					}

					canDo = false;
					for(int i=1;i<N;i++){
						canDo = testOneRow(i,C1rowSwit,C1colSwit);
						if(canDo == false) break;
					}

				}
				if(canDo == false) Answer = "Impossible";
				else Answer = createAnswer(C1rowSwit,C1colSwit);
			}
			else if (case2 == true){ // case2
				boolean canDo = false;

				// init
				rowSwit = new int[N][];
				for(int i=0;i<N;i++){
					rowSwit[i] = new int[M];
					Arrays.fill(rowSwit[i], -1);
				}
				colSwit = new int[M][];
				for(int j=0;j<M;j++){
					colSwit[j] = new int[N];
					Arrays.fill(colSwit[j], -1);
				}


				canDo = dps(0,0);


				if(canDo){}// 이미 정답이 완성되어 있음
				else{
					Answer = "Impossible";
				}
			}
			//			int[][] GGrow = Grow; // 행스위치 번호
			//			int[][] GGcol = Gcol;  //열 스위치 번호
			//
			//			int[][] rrowSwit = rowSwit;  // 행 스위치 on off   [n][m]   - -1:작동x, 0: 꺼짐, 1:켜짐
			//			int[][] ccolSwit = colSwit;  // 열 스위치 on off   [m][n]



			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
	static String createAnswer(){
		StringBuffer ret = new StringBuffer();

		for(int j=0;j<M;j++){
			for(int k=0; k<N ; k++)
				if(colSwit[j][k] == 1){
					ret.append('C');
					if(j<10) ret.append('0');
					ret.append(j);
					if(k<10) ret.append('0');
					ret.append(k);
					ret.append(' ');
				}
		}

		for(int i=0;i<N;i++){
			for(int k=0; k<M ; k++)
				if(rowSwit[i][k] == 1){
					ret.append('R');
					if(i<10) ret.append('0');
					ret.append(i);
					if(k<10) ret.append('0');
					ret.append(k);
					ret.append(' ');
				}
		}

		ret.deleteCharAt(ret.length()-1);
		return ret.toString();



	}
	private static boolean dps(int i, int j) {
		//		int[][] GGrow = Grow; // 행스위치 번호
		//		int[][] GGcol = Gcol;  //열 스위치 번호
		//
		//		int[][] rrowSwit = rowSwit;  // 행 스위치 on off   [n][m]   - -1:작동x, 0: 꺼짐, 1:켜짐
		//		int[][] ccolSwit = colSwit;  // 열 스위치 on off   [m][n]
		//	System.out.println(i + " " + j);
		if(i >= N){
			//success
			Answer = createAnswer();
			return true;
		}
		else{
			boolean ret = true;

			int rowOn = rowSwit[i][Grow[i][j]];
			int colOn = colSwit[j][Gcol[i][j]];

			if(rowOn == -1){  // 조작한 적 없음
				if(colOn == -1){  // 조작한적 없음
					// row off
					rowSwit[i][Grow[i][j]] = 0;
					colSwit[j][Gcol[i][j]] = (0 + G[i][j]) % 2 == 0 ? 1 : 0;

					if(j == M-1)ret = dps(i+1,0);
					else ret= dps(i, j+1);

					if(ret) return true;  // 더이상 탐색할 필요 없음


					// row on
					rowSwit[i][Grow[i][j]] = 1;
					colSwit[j][Gcol[i][j]] = (1 + G[i][j]) % 2 == 0 ? 1 : 0;

					if(j == M-1) ret = dps(i+1,0);
					else ret= dps(i, j+1);

					rowSwit[i][Grow[i][j]] = -1;
					colSwit[j][Gcol[i][j]] = -1;
				}
				else{  // col조작 되어 있음
					rowSwit[i][Grow[i][j]] = (colOn + G[i][j]) % 2 == 0 ? 1 : 0;					
					if(j == M-1) ret = dps(i+1,0);
					else ret= dps(i, j+1);
					rowSwit[i][Grow[i][j]] = -1;
				}
			}
			else{  // 이미 조작되어 있음
				if(colOn == -1){  // 조작한적 없음
					colSwit[j][Gcol[i][j]] = (rowOn + G[i][j]) % 2 == 0 ? 1 : 0;					
					if(j == M-1)ret = dps(i+1,0);
					else ret= dps(i, j+1);
					colSwit[j][Gcol[i][j]] = -1;
				}
				else{  // 이미 조작되어 있음
					if((rowOn + colOn + G[i][j])% 2 == 0) ret = false;  // 잘못된 길
					else{
						if(j == M-1) ret = dps(i+1,0);
						else ret= dps(i, j+1);
					}
				}
			}
			return ret;
		}
	}

	private static String createAnswer(int[] rowSwit, int[] colSwit) {
		StringBuffer ret = new StringBuffer();

		for(int j=0;j<M;j++){
			if(colSwit[j] == 1){
				ret.append('C');
				if(j<10) ret.append('0');
				ret.append(j+"00 ");
			}
		}

		for(int i=0;i<N;i++){
			if(rowSwit[i] == 1){
				ret.append('R');
				if(i<10) ret.append('0');
				ret.append(i+"00 ");
			}
		}

		ret.deleteCharAt(ret.length()-1);
		return ret.toString();
	}

	private static boolean testOneRow(int i, int[] rowSwit, int[] colSwit) {
		//case 1 
		boolean ret  = true;
		rowSwit[i] = 0;

		for(int j=0;j<M;j++){
			if((0 + colSwit[j] + G[i][j]) %2 == 1);   // 최종 전구: 켜짐
			else{
				ret = false;
				break;
			}
		}


		if(ret == true){} // 
		else{ //case2
			rowSwit[i] = 1;
			ret = true;
			for(int j=0;j<M;j++){
				if((1 + colSwit[j] + G[i][j]) %2 == 1);   // 최종 전구: 켜짐
				else{
					ret = false;
					break;
				}
			}
		}

		return ret;
	}
}