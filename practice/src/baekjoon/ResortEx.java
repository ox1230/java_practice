package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ResortEx {
	public static void main(String[] args) throws IOException{
		Resort r = new Resort();
		
		r.input();
		System.out.println(r.startSolve()*1000);
		
	}
}

class Resort{
	int N;
	int M;
	
	int min;
	
	boolean isCanceled[];
	int ans[][];  // ans[i][j] : i일부터 j일까지의 cost의 최소값
	
	
	void input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		isCanceled = new boolean[N+1];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<M;i++){
			isCanceled[Integer.parseInt(st.nextToken())] = true;
		}
	}
	
	int startSolve(){
		min = 37*N;
		
		ans = new int[N+1][N+1];
		Arrays.fill(ans, -1);
		
	
		solve(1,0,0);
		
		return min;
	}
	
	private void solve(int day, int cost, int coupon){
	
	}
	
}
