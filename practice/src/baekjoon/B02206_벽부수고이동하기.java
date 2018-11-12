package baekjoon;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;


public class B02206_���μ����̵��ϱ� {
	public static void main(String[] args) throws IOException {
		WallMaze wm = new WallMaze();
		wm.input();
		System.out.println(wm.solve());
	}
}


class WallMaze{

	int N;
	int M;

	int[][] maze;

	// from A to B���� ����Ѵ�.
	int[][] tempMaze;  // �Ÿ��� �����ϱ� ���� �̷�
	Queue<int[]> q ; // �̷�ã�⸦ ���� Q


	public void input() throws IOException {
		// TODO Auto-generated method stub
		Reader rd = new Reader();

		N = rd.nextInt();
		M = rd.nextInt();

		maze = new int[N][M];

		//rd.nextLine(); // ���� ����
		String temp ;
		for (int i = 0; i < N; i++) {
			temp = rd.nextLine();

			for (int j = 0; j < M; j++) {
				maze[i][j] = temp.charAt(j) - '0';
			}
		}

	}

	public int solve() {
		// TODO Auto-generated method stub
		// �̷��� ������������(������) ����������� ���̸� ���Ѵ�.
		// ������������ ���̸� ���Ѵ�.
		// �� ���� ���� ���� ���� ã�´�.
		
		int[][] fromStart = fromA(0,0);

		int[][] toEnd = fromA(N-1,M-1);

		// fromStart + toEnd -1�� �������� ������ ã�´�.
		// 0�̸� �� �� ���� ���̹Ƿ� pass
		int min = Integer.MAX_VALUE;
		
		for (int i = 0; i < N; i++) for (int j = 0; j < M; j++) {
			if(fromStart[i][j] == 0 || toEnd[i][j] == 0){   // ���� ���� ����
				continue;
			}
			else{
				int temp = fromStart[i][j] + toEnd[i][j] -1;
				
				min = min > temp ? temp : min;
				
			}
			
		}
		
		if(min == Integer.MAX_VALUE) return -1;
		else return min;
	}

	int[][] fromA(int ai, int aj){
		//A���� �� ���������� �Ÿ��� ������ ����
		//���� �������θ� ����� �� �ִ�.

		Queue<int[]> q = new LinkedList<>(); // �̷�ã�⸦ ���� Q
		tempMaze = new int[N][M];  // �Ÿ��� �����ϱ� ���� �̷�
		q = new LinkedList<>(); // �̷�ã�⸦ ���� Q

		int[] moveI = {1,-1,0,0};
		int[] moveJ = {0, 0,-1,1}; 		


		//�ʱ�ȭ
		tempMaze[ai][aj] = 1;
		q.add(new int[]{ai,aj});

		int[] now; // ���� ��ġ

		while(!q.isEmpty()){
			now = q.poll();
			for (int d = 0; d < 4; d++) {
				
				int nextI = now[0] + moveI[d]; // i�� �̵�
				int nextJ = now[1] + moveJ[d]; // j�� �̵�

				//�̷� ���� �����
				if(nextI >= N || nextI < 0) continue;
				if(nextJ >= M || nextJ < 0) continue; 

				// �̹� �����϶�
				if(tempMaze[nextI][nextJ] >0) continue; 

				tempMaze[nextI][nextJ] = tempMaze[now[0]][now[1]] +1;
				if(maze[nextI][nextJ] == 0)	q.add(new int[]{nextI, nextJ});
				// ���̸� �������
			} 
		}

		return tempMaze;
	}


}// end of WM