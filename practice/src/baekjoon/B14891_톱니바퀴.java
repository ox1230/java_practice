package baekjoon;

import java.util.Scanner;
import java.util.Arrays;

public class B14891_톱니바퀴 {
	public static void main(String args[]) {
		Topni tt = new Topni();
		tt.input();
		tt.solve();
		System.out.println(tt.getScore());
	}
}

class Topni{
	int[][] t = new int[5][8];
	Scanner sc = new Scanner(System.in);
	int ways[];	// 각 톱니를 어떤방향으로 돌릴지 결정한다.
	void input() {
		for(int i = 1; i<5; i++) {
			String temp = sc.next();
			
			for(int j=0; j<8; j++) {
				t[i][j] = temp.charAt(j) - '0';
			}
		}
	}
	
	void solve() {
		int n = sc.nextInt();
		
		for(int k=0; k<n;k++) {
			int tar = sc.nextInt();
			int way = sc.nextInt();
//			System.out.println(tar+"--"+way);
			
			ways = new int[5];
			makeWays(tar, way);
//			System.out.println(Arrays.toString(ways));
			for(int i=1;i<5;i++) {
				moveTo(i,ways[i]);
			}
		}
	}
	
	private void makeWays(int tar, int way) {
		// TODO Auto-generated method stub
		// tar를 way로 결정, 양옆의 way  결정
		ways[tar] = way;
		if((tar<4 && ways[tar+1]== 0) && isdiffSN(tar,tar+1)) {
			//오른쪽 결정
			makeWays(tar+1,-way);
		}
		
		if((tar>1 &&ways[tar-1] ==0) && isdiffSN(tar-1,tar)) {
			// 왼쪽 결정
			makeWays(tar-1,-way);
		}
		
		
	}

	boolean isdiffSN(int first, int second) {
		if (t[first][2] + t[second][6] == 1) {
			// 두극이 서로 다르다.
			return true;  
		}
		else {
			return false;
		}
	}
	
	void moveTo(int target, int way) {
	
		if(way == 1) {
			// 시계방향으로 이동
			int temp = t[target][7];
			for(int j=7;j>=1;j--) {
				t[target][j] = t[target][j-1];
			}
			
			t[target][0]= temp;
		}
		else if(way == -1) {
			// 반시계방향으로 이동
			int temp = t[target][0];
			for(int j=1;j<=7;j++) {
				t[target][j-1] = t[target][j];
			}
			
			t[target][7]= temp;
			
		}
		
		else if(way == 0) {
			//이동하지 않음
		}	
//		System.out.println(target);
//		System.out.println(Arrays.toString(t[target]));
	}
	
	int getScore() {
		int ret = 0;
		if (t[1][0] == 1) ret+= 1;
		if (t[2][0] == 1) ret+= 2;
		if (t[3][0] == 1) ret+= 4;
		if (t[4][0] == 1) ret+= 8;
		
		return ret;
	}
}