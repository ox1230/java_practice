package codeground;

import java.util.Arrays;
import java.util.Scanner;


public class SW_Test_1767_���μ��������ϱ� {
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
		// TODO Auto-generated method stub // (i,j) , notConn: ������� ���� Connector�� ����
		// cost: ������ ������ ��
		if(j==N) {  // ���� ����
			if(i<N-1)	dfsStart(i+1,0,notConn,cost);
			else {
				// dfs����
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
			if(S[i][j] != 1) { // processor�� �ƴϸ�
				dfsStart(i,j+1,notConn, cost); //�������� �̵�
			}
			// processor�ε� ���� ���� - �Ű� x 
			else if(i==0 || j==0 || i==N-1 || j== N-1) {
				dfsStart(i,j+1,notConn, cost);
			}
			else {
				//�� , �Ʒ�, ��, �� ������ �����غ���.
				int thisCost = 0; //�̹��� cost
				boolean getConnected = false;
				for(int d = 0; d<4 ;d++) {  //��,�Ʒ�,��,��
					thisCost = connectIt(i,j,d); 
					if(thisCost > 0) { // ���ᰡ���ϸ�
						getConnected = true;
						fillIt(i,j,d,-1); 
						dfsStart(i,j+1,notConn, cost + thisCost);
						fillIt(i,j,d,0);
					}
				}	
				if(!getConnected) { // �ϳ��� ������� ������
					dfsStart(i,j+1,notConn+1,cost);
				}
			}
		}
	}

	private void fillIt(int i, int j, int to, int k) {
		// (i,j)���� to �������� k�� ä���.
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
		//  (i,j)�� k(0:��, �Ʒ�,��, ��)�������� �����غ���. 
		// ���� processor�� ������ ������ �Ұ����ϴ�.
		int[] p = {i,j};
		
		int[] d = choice_d[k];
		
		int cost=0; //������ ����.
		p[0] += d[0];
		p[1] += d[1];

		while(p[0]>=0 && p[0]<=N-1 && p[1]>=0 && p[1]<=N-1) {
			if(S[p[0]][p[1]] != 0) { // ���� ����
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