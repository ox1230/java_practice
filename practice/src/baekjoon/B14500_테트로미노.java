package baekjoon;

import java.util.Scanner;

public class B14500_��Ʈ�ι̳� {
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
	int mv = 0;  // ���� ���� ū�� 
	
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
		// (i,j)   ,c: ������� �� ���� ��
		// c: �� ���� ����.   // sum: ������
		// direct:�� ����  0:�� 1:�� 2:�� 3:��
		switch (c) {
		case 1:    //ù��°���� ��/�Ʒ��θ� ����.
			if(i < N-1) checkTet(i+1,j,2,sum+S[i+1][j], 0);
			if(j < M-1) checkTet(i,j+1,2,sum+S[i][j+1], 3);
			break;
		case 2:
			//2ĭ �̵�
			switch (direct) {
			case 0:
				// �� ��
				break;

			default:
				break;
			}
			// 2�϶��� 1ĭ�̵��� �Ѵ�.
		case 3:
			//1ĭ�̵�
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