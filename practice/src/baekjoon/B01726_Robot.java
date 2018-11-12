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

	int S[][];// 각 지역의 상태 저장
	int dist[][][];// P[row][col][dir]: 시작지점에서 도착지점까지 걸리는 거리  / 초기화: -1
	// 이 상황을 dir이라는 차원이 한개 추가되었을 때의 3차원 미로찾기로 취급한다.
	//  거리가 0인 (a,b,c)와 dir, (row | col)이 동일하면 (col, row) 가 +-3인 지역은 1만에 이동할 수 있다.
	Position start;
	Position end;

	Queue<Position> q = new LinkedList<>();  // BFS를 위한 큐.

	void input() {
		Scanner sc = new Scanner(System.in);
		
		M = sc.nextInt();
		N = sc.nextInt();

		S = new int[M][N]; 
		dist = new int[M][N][5];  

		for(int i = 0 ; i<M ;i++)for(int j = 0 ; j<N ; j++){
			S[i][j] = sc.nextInt();

			// 초기화
			dist[i][j][1] = -1;
			dist[i][j][2] = -1;
			dist[i][j][3] = -1;
			dist[i][j][4] = -1;
		}

		int row, col, dir;

		row = sc.nextInt()-1;
		col = sc.nextInt()-1;
		dir = sc.nextInt();
		// 우리는 (0,0)부터 시작하므로 1씩 빼준다.
		start = new Position(row,col,dir);


		row = sc.nextInt()-1;
		col = sc.nextInt()-1;
		dir = sc.nextInt();
		// 우리는 (0,0)부터 시작하므로 1씩 빼준다.
		end = new Position(row,col,dir);

	}

	int solve(){
		// BFS. 시작지점에서 명령횟수가 적은 것부터 다시 탐색한다.

		//init
		q.add(start);
		dist[start.row][start.col][start.dir] = 0;

		Position pre;
		int preDist; // 현재의 거리.

		int rowGo[] = new int[]{0,0,0,1,-1};   // rowGo[dir]: 현재 방향이 dir일 때 앞으로 갈 시 row의 이동방향
		int colGo[] = new int[]{0,1,-1,0,0};   // rowGo[dir]: 현재 방향이 dir일 때 앞으로 갈 시 col의 이동방향		


		while(true){  // end에 닿기 전까지 반복

			pre = q.poll();
			if(pre.equals(end)) break;   // 종료지점에 도달했으면 종료
			
			preDist = dist[pre.row][pre.col][pre.dir];

			for(int i = 1; i<=3; i++){    // 앞으로 1/2/3칸 이동
				int nowRow = pre.row + i*rowGo[pre.dir];
				int nowCol = pre.col + i*colGo[pre.dir];
				int nowDir = pre.dir;

				if(!isValid( nowRow , nowCol , pre.dir)) break; 
				// 처음 막혔으면 다음길이로도 이동불가능
				
				addDistAndQ(nowRow, nowCol, nowDir, preDist);

			}

			//방향변환

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
		// 주어진 Position이 막혀있지않고, 갈 수 있는지 확인한다.

		if(row >= M || row < 0 ) return false;
		if(col >= N || col < 0 ) return false;
		if(S[row][col] == 1) return false;
		
		return true;
	}
	
	private void addDistAndQ(int nowRow, int nowCol, int nowDir, int preDist) {
		// TODO Auto-generated method stub
		if(dist[nowRow][nowCol][nowDir] > 0 ) return; // 더 좋은 방법이 있으면 패스

		Position tempP = new Position(nowRow, nowCol, nowDir);

		dist[nowRow][nowCol][nowDir] = preDist +1;
	
		q.add(tempP);
		return;
	}
} // end of Robot class


class Position{
	int row;
	int col;
	int dir;  // 바라보는 방향

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
