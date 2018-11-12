package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ACMcraftEx {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int i=0;i<T;i++){
			ACM acm = new ACM();
			acm.input(br);
			System.out.println(acm.startSolve());
		}
	}
}


class ACM{
	int N;
	int K;
	int W;

	int[] cost;	
	ArrayList<Integer>[] toBuild; // tobuild[Y] : Y를 짓기 위해 필요한 건물
	int[] totalCost;


	void input(BufferedReader br) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		cost = new int[N+1];

		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++){
			cost[i] = Integer.parseInt(st.nextToken());
		}

		toBuild = new ArrayList[N+1];

		for(int i=1;i<=N;i++){
			toBuild[i] = new ArrayList<>();
		}

		int X, Y;
		for(int i=0;i<K;i++){
			st = new StringTokenizer(br.readLine());

			X = Integer.parseInt(st.nextToken());
			Y = Integer.parseInt(st.nextToken());

			toBuild[Y].add(X);
		}

		W = Integer.parseInt(br.readLine());
	}

	int startSolve(){
		totalCost = new int[N+1];
		Arrays.fill(totalCost,-1);

		return solve(W);
	}


	private int solve(int t){
		if(totalCost[t] >=0) return totalCost[t];
		else if(toBuild[t].isEmpty()){
			totalCost[t] = cost[t];
			return cost[t];
		}
		else {
			int max = 0;
			int temp;
			for(int prev: toBuild[t]){
				temp = solve(prev);
				if(max < temp) max = temp;
			}	
			
			totalCost[t] = max + cost[t];
			
			return totalCost[t];
		}


	}

}