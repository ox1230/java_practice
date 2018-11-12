package baekjoon;

import java.util.Scanner;

public class B14503_로봇청소기 {
	public static void main(String[] args) {
		RobotCleaner rc = new RobotCleaner();

		rc.input();
		System.out.println(rc.solve());

	} 
}

class RobotCleaner{
	int N;
	int M;

	int S[][];

	int r;
	int c;
	int d;

	// d에따른 이동 방향
	int dr[] = {-1,0,1,0}; 
	int dc[] = {0,1,0,-1};


	int cnt = 0; // 청소한 횟수

	public void input() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		r = sc.nextInt();
		c = sc.nextInt();
		d = sc.nextInt();

		S = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				S[i][j] = sc.nextInt();
			}
		}

	}

	public int solve() {
		// TODO Auto-generated method stub

		while(true){
//			System.out.println(""+r +" "+ c);
			if(S[r][c] == 0){
				S[r][c] = 2;  // 청소한 지역
				cnt++;
			}
			int tempR = r;
			int tempC = c;

			for (int i = 1; i <= 4; i++) {
				tempR = r + dr[(4+d-i)%4];
				tempC = c + dc[(4+d-i)%4];

				if(tempR<0 || tempC<0 || 
						tempR>= N || tempC >= M || S[tempR][tempC] >0){					
				}
				else{  // 다음 청소 지역으로 이동
					r = tempR;
					c = tempC;
					d = (4+d-i)%4;
					break;
				}


				if(i == 4){ // 청소할 곳이 없으면 후진
					r -= dr[d];
					c -= dc[d];
				}
			}


			if(r<0 || c<0 || 
					r>= N || c >= M || S[r][c] == 1){		// 후진했는데 벽을 만남			
				break;
			}

		}


		return cnt;
	}

}
