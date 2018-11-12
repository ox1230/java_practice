package baekjoon;

import java.util.Scanner;

public class B14890_경사로 {
	public static void main(String[] args) {
		Tilt t = new Tilt();
		t.input();
		System.out.println(t.solve());

	} // end of main
} // end of class

class Tilt{
	int N;
	int L;
	int S[][];

	int cnt= 0;


	public void input() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		L = sc.nextInt();

		S = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				S[i][j] = sc.nextInt();
			}
		}
	}

	public int solve() {
		// TODO Auto-generated method stub

		// 행별 체크
		for (int i = 0; i < N; i++) {
			boolean[] tempTilt = new boolean[N];
			// 경사로가 있으면 true
			for (int j = 0; j < N-1; j++) {
				if(S[i][j] == S[i][j+1]){
				}
				else if(S[i][j] - S[i][j+1] == 1){
					if(!makeTilt(i,j+1,1,tempTilt)){ // 왼쪽이 1더크면 오른쪽방향으로 경사로를 만든다.
						// 왼쪽이 1더 크지만 경사로를 만들수 없으면
						break;
					}
				}
				else if(S[i][j] - S[i][j+1] == -1){
					if(!makeTilt(i,j,3,tempTilt)){  // 오른쪽이 1더크면 왼쪽방향으로 경사로를 만든다.
						break;
					}
				}
				else{  // 두 지역의 높이차이가 2이상이면
					break;
				}

				if(j == N-2){
					
					cnt++;  // 끝까지 잘 통과했으면
//					System.out.println(i+"!!");
				}
			}
		}

		// 열별 체크
		for (int j = 0; j < N; j++) {
			boolean[] tempTilt = new boolean[N];
			// 경사로가 있으면 true
			for (int i = 0; i < N-1; i++) {
				if(S[i][j] == S[i+1][j]){
				}
				else if(S[i][j] - S[i+1][j] == 1){
					if(!makeTilt(i+1,j,2,tempTilt)){ // 위쪽이 1더크면 아래쪽방향으로 경사로를 만든다.
						// 왼쪽이 1더 크지만 경사로를 만들수 없으면
						break;
					}
				}
				else if(S[i][j] - S[i+1][j] == -1){
					if(!makeTilt(i,j,0,tempTilt)){  // 아래쪽이 1더크면 위쪽방향으로 경사로를 만든다.
						break;
					}
				}
				else{  // 두 지역의 높이차이가 2이상이면
					break;
				}

				if(i == N-2){
					cnt++;  // 끝까지 잘 통과했으면
//					System.out.println(j+"??");
				}
			}
		}
		
		return cnt;
	}

	private boolean makeTilt(int i, int j, int d, boolean[] tempTilt) {
		// i,j부터 시작하는 경사로를 만든다.
		// d는 방향을 의미 0:위쪽, 1:오른쪽, 2:아래쪽, 3:왼쪽
		int startHeight = S[i][j];

		int[] di = {-1,0,1,0};
		int[] dj = {0,1,0,-1};

		for (int l = 0; l < L; l++) { // L개의 경사로
			if(i+l*di[d] < 0 || i+l*di[d] >= N ||
					j+ l*dj[d] <0 || j+l*dj[d] >= N){  // 범위를 벗어나면
				return false;
			}

			if(S[i+ l*di[d]][j+ l*dj[d]] != startHeight){  //높이가 다르면
				return false;
			}

			if((d==0||d==2)){  // 열별탐색
				if(tempTilt[i+l*di[d]]){  // 이미 경사로를 깔았다면 
					return false;
				}

				tempTilt[i + l*di[d]] = true;
			}
			else{  // 행별탐색
				if(tempTilt[j+l*dj[d]]){   //이미 경사로를 깔았다면
					return false;
				}

				tempTilt[j + l*dj[d]] = true;
			}
		}

		return true;
	}











}
