package baekjoon;

import java.util.Scanner;

public class B14499_�ֻ��������� {
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
	
	int dice[] = new int[7];  // �ֻ��� �� ���� ����
	int under = 1;  // �ֻ����� �Ʒ���
	int east = 3;  // �ֻ����� ���ʸ�
	int north = 2; // �ֻ����� ���ʸ�
	// ����:  �ֻ��� ������ �ݴ��� ��ȣ�� ������ ���� 7�̴�.
	
	
	int di[] = {0,0,0,-1,1}; // ��ɿ� ���� �����̴� ����
	int dj[] = {0,1,-1,0,0}; // ��ɿ� ���� �����̴� ����
	
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
		// ���� ���⿡�� direct �������� ������ ���� �ֻ����� �ظ��� ����
		
		int temp = under;
		
		switch (direct) {
		case 1:  // �������� �̵�
			under = east;
			east = 7 - temp;
			break;
		case 2:  // �������� �̵�
			under = 7 - east;
			east = temp;
			break;
		case 3:  // �������� �̵�	
			under = north;
			north = 7 - temp;
			break;
		case 4: // �������� �̵�
			under = 7 - north;
			north = temp;
			break;
		default:
			break;
		}
	}
}

