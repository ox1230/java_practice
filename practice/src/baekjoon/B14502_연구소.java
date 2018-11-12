package baekjoon;


import java.util.Arrays;
import java.util.Scanner;

public class B14502_������ {
	public static void main(String[] args)  {
		Lab l = new Lab();
		l.input();
		System.out.println(l.solve());
	} // end of main
} // end of class

class Lab{
	int N;
	int M;
	int S[][];
	
	int max = 0;
	
	public void input()  {
		// TODO Auto-generated method stub
		Scanner r = new Scanner(System.in);
		
		N = r.nextInt();
		M = r.nextInt();

		S = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				S[i][j] = r.nextInt();
			}
		}
	}
	public int solve() {
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(S[i][j] == 0) createWallAndCheck(i,j,0);
			}
		}
		
		
		return max;
	}
	private void createWallAndCheck(int ai, int aj, int k) {
		// TODO Auto-generated method stub
		// i,j: ��ġ,  k: ������� ���� ���� ����
		
		if(k == 2){  // ���̻� ���� ������ �ʴ´�.
			S[ai][aj] =1;
			int temp[][] = copyOfS();
			
//			for (int i = 0; i < temp.length; i++) {
//				System.out.println(Arrays.toString(temp[i]));
//			}
//			System.out.println();
//			
			
			// ���̷����� Ȯ��
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(temp[i][j] == 2) spreadVirus(i,j,temp);
				}
			}
			
			//���������� ���� ����
			int cnt = 0;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(temp[i][j] == 0) cnt++;
				}
			}
			
			if(max < cnt){
				max = cnt;
//				for (int i = 0; i < temp.length; i++) {
//					System.out.println(Arrays.toString(temp[i]));
//				}
//				System.out.println();
			}

			S[ai][aj] = 0;
		} //end of if(k == 2)
		else{  // ���� �����.
			
			S[ai][aj] = 1;
			
			for (int j = aj+1; j < M; j++) {
				if(S[ai][j] == 0) createWallAndCheck(ai, j, k+1);
			}
			
			for (int i = ai+1; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(S[i][j] == 0) createWallAndCheck(i, j, k+1);
				}
			}

			S[ai][aj] = 0;
		}
		
	}
	
	private void spreadVirus(int i, int j, int[][] temp) {
		// TODO Auto-generated method stub
		
		if(i<0 || j<0 || i>=N || j>=M){
			return; // ����
		}
		else if(temp[i][j] == 2 || temp[i][j] == 0){  // ���̷����̰ų� �������ɱ����̸�
			temp[i][j] = 3;
			spreadVirus(i+1,j  ,temp);
			spreadVirus(i-1,j  ,temp);
			spreadVirus(i  ,j+1,temp);
			spreadVirus(i  ,j-1,temp);

		}
	}
	int[][] copyOfS(){
		int[][] temp = new int[N][M];
		for (int i = 0; i < temp.length; i++) {
			System.arraycopy(S[i], 0, temp[i], 0, M);
		}
		
		return temp;
	}
	
	
	
}
