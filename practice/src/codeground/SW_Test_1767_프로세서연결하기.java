package codeground;

import java.util.Arrays;
import java.util.Scanner;


public class SW_Test_1767_프로세서연결하기 {
	public static void main(String[] args) {
		Processor p = new Processor();
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int i = 0; i < T; i++) {
			p.input(sc);
			System.out.println("#"+(i+1)+" "+p.solve());		
		}
	}
}

class Processor{
	int N;
	int S[][];
	int numOfP = 0;
	int[][] choice_d = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	int minOfNotConn;
	int minCost;
	
	void input(Scanner sc) {		
		N = sc.nextInt();
		S = new int[N][N];
	
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				S[i][j] = sc.nextInt();
				if (S[i][j] ==1) numOfP += 1;
			}
		}
	}
	
	int solve() {
		minOfNotConn = numOfP;
		minCost = N*N;
		dfsStart(0,0, 0, 0);
		return minCost;
	}

	private void dfsStart(int i, int j, int notConn, int cost) {
		// TODO Auto-generated method stub // (i,j) , notConn: 연결되지 않은 Connector의 개수
		// cost: 연결한 전선의 수
		if(j==N) {  // 한행 종료
			if(i<N-1)	dfsStart(i+1,0,notConn,cost);
			else {
				// dfs종료
				if (notConn < minOfNotConn) {
					minOfNotConn = notConn;
					minCost = cost;
//					System.out.println(notConn + " " + minCost);
				}
				else if(notConn == minOfNotConn && cost < minCost) {
					minCost = cost;
//					System.out.println(notConn + " " + minCost);
//					for (int k = 0; k < S.length; k++) {
//						System.out.println(Arrays.toString(S[k]));
//					}
				}
			}
		}
		else {
			if(S[i][j] != 1) { // processor가 아니면
				dfsStart(i,j+1,notConn, cost); //다음으로 이동
			}
			// processor인데 끝에 있음 - 신경 x 
			else if(i==0 || j==0 || i==N-1 || j== N-1) {
				dfsStart(i,j+1,notConn, cost);
			}
			else {
				//위 , 아래, 좌, 우 순서로 연결해본다.
				int thisCost = 0; //이번기 cost
				boolean getConnected = false;
				for(int d = 0; d<4 ;d++) {  //위,아래,좌,우
					thisCost = connectIt(i,j,d); 
					if(thisCost > 0) { // 연결가능하면
						getConnected = true;
						fillIt(i,j,d,-1); 
						dfsStart(i,j+1,notConn, cost + thisCost);
						fillIt(i,j,d,0);
					}
				}	
				if(!getConnected) { // 하나도 연결되지 않으면
					dfsStart(i,j+1,notConn+1,cost);
				}
			}
		}
	}

	private void fillIt(int i, int j, int to, int k) {
		// (i,j)부터 to 방향으로 k를 채운다.
		int[] p = {i,j};
		int[] d = choice_d[to];
		
		p[0] += d[0];
		p[1] += d[1];

		while(p[0]>=0 && p[0]<=N-1 && p[1]>=0 && p[1]<=N-1) {
			S[p[0]][p[1]] =k;
			
			p[0] += d[0];
			p[1] += d[1];	
		}
	}

	private int connectIt(int i, int j, int k) {
		//  (i,j)를 k(0:위, 아래,좌, 우)방향으로 연결해본다. 
		// 만약 processor나 전선이 있으면 불가능하다.
		int[] p = {i,j};
		
		int[] d = choice_d[k];
		
		int cost=0; //전선의 길이.
		p[0] += d[0];
		p[1] += d[1];

		while(p[0]>=0 && p[0]<=N-1 && p[1]>=0 && p[1]<=N-1) {
			if(S[p[0]][p[1]] != 0) { // 연결 실패
				cost = -1;
				break;
			}
			else {
				cost ++;				
			}
			
			p[0] += d[0];
			p[1] += d[1];
		}
	
		return cost;
	}
}