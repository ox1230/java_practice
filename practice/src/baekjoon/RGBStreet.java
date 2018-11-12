package baekjoon;

import java.util.Scanner;

class RGBStreet{
	public static void main(String[] args){
		RGBStreetSolve st = new RGBStreetSolve();
		
		st.input();
		System.out.println(st.solve());
		
	}
}

class RGBStreetSolve {
	int N;  // 집의 수
	int[][] cost;  // 집을 칠하는 비용
	
	void input(){
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		cost = new int[N][3];
		
		for(int i=0;i<N;i++){
			cost[i][0] = sc.nextInt();
			cost[i][1] = sc.nextInt();
			cost[i][2] = sc.nextInt();
		}
		
		sc.close();
	}
	
	int solve(){
		int[][] ans;    // ans[i][j] : i번째 집을 j색으로 칠할 때의 최소비용
		
		ans = new int[N][3];
		
		for(int i=0;i<3;i++) ans[0][i] = cost[0][i];
		
		for(int i=1;i<N;i++){ 
			ans[i][0] = cost[i][0]   + (ans[i-1][1]>ans[i-1][2]? ans[i-1][2]: ans[i-1][1]);
			ans[i][1] = cost[i][1]   + (ans[i-1][0]>ans[i-1][2]? ans[i-1][2]: ans[i-1][0]);
			ans[i][2] = cost[i][2]   + (ans[i-1][1]>ans[i-1][0]? ans[i-1][0]: ans[i-1][1]);
		}
		
		
		return Math.min(ans[N-1][0], Math.min(ans[N-1][1],ans[N-1][2]));
		
	}
	
	
	
}

