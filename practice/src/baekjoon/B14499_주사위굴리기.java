package baekjoon;

import java.util.Scanner;

public class B14499_주사위굴리기 {
	public static void main(String[] args) {
		Dice d = new Dice();
		d.input();
		d.solve();
//		System.out.println("fin");
	} // end of main
} // end of class

class Dice{
	int N;
	int M;
	int x;
	int y;
	int k;
	
	int S[][];
	int order[];
	
	int dice[] = new int[7];  // 주사위 각 면의 숫자
	int under = 1;  // 주사위의 아랫면
	int east = 3;  // 주사위의 동쪽면
	int north = 2; // 주사위의 북쪽면
	// 참고:  주사위 각면의 반대쪽 번호와 본인의 합은 7이다.
	
	
	int di[] = {0,0,0,-1,1}; // 명령에 따라 움직이는 방향
	int dj[] = {0,1,-1,0,0}; // 명령에 따라 움직이는 방향
	
	void input(){
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		x = sc.nextInt();
		y = sc.nextInt();
		k = sc.nextInt();
		
		S = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				S[i][j] = sc.nextInt();
			}
		}
		
		order = new int[k];
		
		for (int i = 0; i < k; i++) {
			order[i] = sc.nextInt();
		}
		
	}
	
	void solve(){
		
		int ti = x;
		int tj = y;

		for (int d : order) {
			
			ti += di[d];
			tj += dj[d];
			
			if(ti <0 || tj<0 || ti>=N || tj>=M){
				ti -= di[d];
				tj -= dj[d];
				continue;
			}
			
			diceMove(d);
			
			if(S[ti][tj] != 0){
				dice[under] = S[ti][tj];
				S[ti][tj] = 0;
			}
			else{
				S[ti][tj] = dice[under];
//				dice[under] = 0;
			}
			
			System.out.println(dice[7-under]);
		}
		
	}
	
	void diceMove(int direct){
		// 현재 방향에서 direct 방향으로 움직일 시의 주사위의 밑면을 구함
		
		int temp = under;
		
		switch (direct) {
		case 1:  // 동쪽으로 이동
			under = east;
			east = 7 - temp;
			break;
		case 2:  // 서쪽으로 이동
			under = 7 - east;
			east = temp;
			break;
		case 3:  // 북쪽으로 이동	
			under = north;
			north = 7 - temp;
			break;
		case 4: // 남쪽으로 이동
			under = 7 - north;
			north = temp;
			break;
		default:
			break;
		}
	}
}

