package baekjoon;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class B01726_Robot {
	public static void main(String[] args)  {
		Robot rr = new Robot();
		rr.input();
		System.out.println(rr.solve());
	}  // end of main
} // end of Main class


class Robot{
	int M; 
	int N;

	int S[][];// �� ������ ���� ����
	int dist[][][];// P[row][col][dir]: ������������ ������������ �ɸ��� �Ÿ�  / �ʱ�ȭ: -1
	// �� ��Ȳ�� dir�̶�� ������ �Ѱ� �߰��Ǿ��� ���� 3���� �̷�ã��� ����Ѵ�.
	//  �Ÿ��� 0�� (a,b,c)�� dir, (row | col)�� �����ϸ� (col, row) �� +-3�� ������ 1���� �̵��� �� �ִ�.
	Position start;
	Position end;

	Queue<Position> q = new LinkedList<>();  // BFS�� ���� ť.

	void input() {
		Scanner sc = new Scanner(System.in);
		
		M = sc.nextInt();
		N = sc.nextInt();

		S = new int[M][N]; 
		dist = new int[M][N][5];  

		for(int i = 0 ; i<M ;i++)for(int j = 0 ; j<N ; j++){
			S[i][j] = sc.nextInt();

			// �ʱ�ȭ
			dist[i][j][1] = -1;
			dist[i][j][2] = -1;
			dist[i][j][3] = -1;
			dist[i][j][4] = -1;
		}

		int row, col, dir;

		row = sc.nextInt()-1;
		col = sc.nextInt()-1;
		dir = sc.nextInt();
		// �츮�� (0,0)���� �����ϹǷ� 1�� ���ش�.
		start = new Position(row,col,dir);


		row = sc.nextInt()-1;
		col = sc.nextInt()-1;
		dir = sc.nextInt();
		// �츮�� (0,0)���� �����ϹǷ� 1�� ���ش�.
		end = new Position(row,col,dir);

	}

	int solve(){
		// BFS. ������������ ���Ƚ���� ���� �ͺ��� �ٽ� Ž���Ѵ�.

		//init
		q.add(start);
		dist[start.row][start.col][start.dir] = 0;

		Position pre;
		int preDist; // ������ �Ÿ�.

		int rowGo[] = new int[]{0,0,0,1,-1};   // rowGo[dir]: ���� ������ dir�� �� ������ �� �� row�� �̵�����
		int colGo[] = new int[]{0,1,-1,0,0};   // rowGo[dir]: ���� ������ dir�� �� ������ �� �� col�� �̵�����		


		while(true){  // end�� ��� ������ �ݺ�

			pre = q.poll();
			if(pre.equals(end)) break;   // ���������� ���������� ����
			
			preDist = dist[pre.row][pre.col][pre.dir];

			for(int i = 1; i<=3; i++){    // ������ 1/2/3ĭ �̵�
				int nowRow = pre.row + i*rowGo[pre.dir];
				int nowCol = pre.col + i*colGo[pre.dir];
				int nowDir = pre.dir;

				if(!isValid( nowRow , nowCol , pre.dir)) break; 
				// ó�� �������� �������̷ε� �̵��Ұ���
				
				addDistAndQ(nowRow, nowCol, nowDir, preDist);

			}

			//���⺯ȯ

			switch (pre.dir){
			case 1:
			case 2:
				if(isValid( pre.row , pre.col , 3)) addDistAndQ(pre.row, pre.col, 3, preDist);
				if(isValid( pre.row , pre.col , 4)) addDistAndQ(pre.row, pre.col, 4, preDist);
				break;
			case 3:
			case 4:
				if(isValid( pre.row , pre.col , 1)) addDistAndQ(pre.row, pre.col, 1, preDist);
				if(isValid( pre.row , pre.col , 2)) addDistAndQ(pre.row, pre.col, 2, preDist);
				break;
			}  // switch end

		}
		
		return dist[ end.row ][ end.col ][ end.dir ];

	}

	private boolean isValid(int row, int col, int dir) {
		// TODO Auto-generated method stub
		// �־��� Position�� ���������ʰ�, �� �� �ִ��� Ȯ���Ѵ�.

		if(row >= M || row < 0 ) return false;
		if(col >= N || col < 0 ) return false;
		if(S[row][col] == 1) return false;
		
		return true;
	}
	
	private void addDistAndQ(int nowRow, int nowCol, int nowDir, int preDist) {
		// TODO Auto-generated method stub
		if(dist[nowRow][nowCol][nowDir] > 0 ) return; // �� ���� ����� ������ �н�

		Position tempP = new Position(nowRow, nowCol, nowDir);

		dist[nowRow][nowCol][nowDir] = preDist +1;
	
		q.add(tempP);
		return;
	}
} // end of Robot class


class Position{
	int row;
	int col;
	int dir;  // �ٶ󺸴� ����

	Position(){}
	public Position(int row, int col, int dir) {
		super();
		this.row = row;
		this.col = col;
		this.dir = dir;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + dir;
		result = prime * result + row;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (col != other.col)
			return false;
		if (dir != other.dir)
			return false;
		if (row != other.row)
			return false;
		return true;
	}
} // end of Position class
