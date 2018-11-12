package baekjoon;

import java.util.Scanner;

public class B14500_테트로미노 {
	public static void main(String[] args) {
		Tet t = new Tet();
		t.input();
		System.out.println(t.solve());
	}
	
}

class Tet{
	int N;
	int M;
	int S[][];
	int mv = 0;  // 값중 가장 큰값 
	
	int max;
	
	
	void input(){
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		S = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				S[i][j] = sc.nextInt();
				if(mv < S[i][j]){
					mv = S[i][j];
				}
			}
		}
	}

	public int solve() {		
		// TODO Auto-generated method stub
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				checkTet(i,j,1,0);  
			}
		}
		
		return max;
	}

	private void checkTet(int i, int j,int c,int sum, int direct) {
		// (i,j)   ,c: 현재까지 고른 블럭의 수
		// c: 고른 블럭의 개수.   // sum: 점수합
		// direct:온 방향  0:위 1:우 2:밑 3:좌
		switch (c) {
		case 1:    //첫번째에는 위/아래로만 간다.
			if(i < N-1) checkTet(i+1,j,2,sum+S[i+1][j], 0);
			if(j < M-1) checkTet(i,j+1,2,sum+S[i][j+1], 3);
			break;
		case 2:
			//2칸 이동
			switch (direct) {
			case 0:
				// 좌 밑
				break;

			default:
				break;
			}
			// 2일때도 1칸이동은 한다.
		case 3:
			//1칸이동
			if(i < N-1 && direct != 2) checkTet(i+1,j  ,c+1,sum+S[i+1][j  ], 0);
			if(j < M-1 && direct != 1) checkTet(i  ,j+1,c+1,sum+S[i  ][j+1], 3);
			if(i > 0 && direct != 0)   checkTet(i-1,j  ,c+1,sum+S[i-1][j  ], 2);
			if(j > 0 && direct != 3)   checkTet(i  ,j-1,c+1,sum+S[i  ][j-1], 1);
			
			break;
		
		case 4:
			if(sum > max) max = sum;
			break;
		default:
			break;
		}
		
		
				
	}
}