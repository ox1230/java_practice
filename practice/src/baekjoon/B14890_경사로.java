package baekjoon;

import java.util.Scanner;

public class B14890_���� {
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

		// �ະ üũ
		for (int i = 0; i < N; i++) {
			boolean[] tempTilt = new boolean[N];
			// ���ΰ� ������ true
			for (int j = 0; j < N-1; j++) {
				if(S[i][j] == S[i][j+1]){
				}
				else if(S[i][j] - S[i][j+1] == 1){
					if(!makeTilt(i,j+1,1,tempTilt)){ // ������ 1��ũ�� �����ʹ������� ���θ� �����.
						// ������ 1�� ũ���� ���θ� ����� ������
						break;
					}
				}
				else if(S[i][j] - S[i][j+1] == -1){
					if(!makeTilt(i,j,3,tempTilt)){  // �������� 1��ũ�� ���ʹ������� ���θ� �����.
						break;
					}
				}
				else{  // �� ������ �������̰� 2�̻��̸�
					break;
				}

				if(j == N-2){
					
					cnt++;  // ������ �� ���������
//					System.out.println(i+"!!");
				}
			}
		}

		// ���� üũ
		for (int j = 0; j < N; j++) {
			boolean[] tempTilt = new boolean[N];
			// ���ΰ� ������ true
			for (int i = 0; i < N-1; i++) {
				if(S[i][j] == S[i+1][j]){
				}
				else if(S[i][j] - S[i+1][j] == 1){
					if(!makeTilt(i+1,j,2,tempTilt)){ // ������ 1��ũ�� �Ʒ��ʹ������� ���θ� �����.
						// ������ 1�� ũ���� ���θ� ����� ������
						break;
					}
				}
				else if(S[i][j] - S[i+1][j] == -1){
					if(!makeTilt(i,j,0,tempTilt)){  // �Ʒ����� 1��ũ�� ���ʹ������� ���θ� �����.
						break;
					}
				}
				else{  // �� ������ �������̰� 2�̻��̸�
					break;
				}

				if(i == N-2){
					cnt++;  // ������ �� ���������
//					System.out.println(j+"??");
				}
			}
		}
		
		return cnt;
	}

	private boolean makeTilt(int i, int j, int d, boolean[] tempTilt) {
		// i,j���� �����ϴ� ���θ� �����.
		// d�� ������ �ǹ� 0:����, 1:������, 2:�Ʒ���, 3:����
		int startHeight = S[i][j];

		int[] di = {-1,0,1,0};
		int[] dj = {0,1,0,-1};

		for (int l = 0; l < L; l++) { // L���� ����
			if(i+l*di[d] < 0 || i+l*di[d] >= N ||
					j+ l*dj[d] <0 || j+l*dj[d] >= N){  // ������ �����
				return false;
			}

			if(S[i+ l*di[d]][j+ l*dj[d]] != startHeight){  //���̰� �ٸ���
				return false;
			}

			if((d==0||d==2)){  // ����Ž��
				if(tempTilt[i+l*di[d]]){  // �̹� ���θ� ��Ҵٸ� 
					return false;
				}

				tempTilt[i + l*di[d]] = true;
			}
			else{  // �ະŽ��
				if(tempTilt[j+l*dj[d]]){   //�̹� ���θ� ��Ҵٸ�
					return false;
				}

				tempTilt[j + l*dj[d]] = true;
			}
		}

		return true;
	}











}
