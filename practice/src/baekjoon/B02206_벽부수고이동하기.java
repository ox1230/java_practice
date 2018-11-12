package baekjoon;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;


public class B02206_벽부수고이동하기 {
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

	// from A to B에서 사용한다.
	int[][] tempMaze;  // 거리를 저장하기 위한 미로
	Queue<int[]> q ; // 미로찾기를 위한 Q


	public void input() throws IOException {
		// TODO Auto-generated method stub
		Reader rd = new Reader();

		N = rd.nextInt();
		M = rd.nextInt();

		maze = new int[N][M];

		//rd.nextLine(); // 버퍼 제거
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
		// 미로의 각지점까지의(벽포함) 출발지에서의 길이를 구한다.
		// 도착지까지의 길이를 구한다.
		// 두 합이 가장 작은 길을 찾는다.
		
		int[][] fromStart = fromA(0,0);

		int[][] toEnd = fromA(N-1,M-1);

		// fromStart + toEnd -1이 가장작은 지점을 찾는다.
		// 0이면 갈 수 없는 곳이므로 pass
		int min = Integer.MAX_VALUE;
		
		for (int i = 0; i < N; i++) for (int j = 0; j < M; j++) {
			if(fromStart[i][j] == 0 || toEnd[i][j] == 0){   // 가는 길이 없음
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
		//A에서 각 지역까지의 거리를 측정해 리턴
		//벽은 도착지로만 기능할 수 있다.

		Queue<int[]> q = new LinkedList<>(); // 미로찾기를 위한 Q
		tempMaze = new int[N][M];  // 거리를 저장하기 위한 미로
		q = new LinkedList<>(); // 미로찾기를 위한 Q

		int[] moveI = {1,-1,0,0};
		int[] moveJ = {0, 0,-1,1}; 		


		//초기화
		tempMaze[ai][aj] = 1;
		q.add(new int[]{ai,aj});

		int[] now; // 지금 위치

		while(!q.isEmpty()){
			now = q.poll();
			for (int d = 0; d < 4; d++) {
				
				int nextI = now[0] + moveI[d]; // i의 이동
				int nextJ = now[1] + moveJ[d]; // j의 이동

				//미로 밖을 벗어날때
				if(nextI >= N || nextI < 0) continue;
				if(nextJ >= M || nextJ < 0) continue; 

				// 이미 간곳일때
				if(tempMaze[nextI][nextJ] >0) continue; 

				tempMaze[nextI][nextJ] = tempMaze[now[0]][now[1]] +1;
				if(maze[nextI][nextJ] == 0)	q.add(new int[]{nextI, nextJ});
				// 길이면 계속진행
			} 
		}

		return tempMaze;
	}


}// end of WM