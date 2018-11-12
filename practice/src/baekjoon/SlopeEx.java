package baekjoon;

import java.io.IOException;

public class SlopeEx {
	public static void main(String[] args) throws IOException{
		Slope s = new Slope();
		s.input();
		System.out.println(s.startSolve());
		//System.out.println();
	}
}

class Slope{
	int M;
	int N;
	int S[][];  // 지도
	int ans[][];   // ans[i][j] : 0,0에서 i,j까지 가는 길의 경우의 수
	final int UNKNOWN = -1; // 경우의 수를 아직 알수 없음
	
	void input() throws IOException{
		Reader reader = new Reader();
		
		M = reader.nextInt();
		N = reader.nextInt();
		
		S = new int[M][N];
		ans = new int[M][N];
		
		
		for(int i=0;i<M;i++) for(int j=0;j<N;j++){
			S[i][j]  = reader.nextInt();
			ans[i][j] = UNKNOWN;
		}
	}
	
	int startSolve(){
		ans[0][0] = 1;
		
		return solve(M-1,N-1);
	}
	
	private int solve(int r,int c){
		if(ans[r][c] != UNKNOWN) return ans[r][c];
		
		else{
			int cnt = 0;
			
			
			if(r+1 < M  && S[r+1][c] > S[r][c]) cnt += solve(r+1,c);
			if(r-1 >= 0 && S[r-1][c] > S[r][c]) cnt += solve(r-1,c);
			if(c+1 < N  && S[r][c+1] > S[r][c]) cnt += solve(r,c+1);
			if(c-1 >= 0 && S[r][c-1] > S[r][c]) cnt += solve(r,c-1);
			
			ans[r][c] = cnt;
			
			return cnt;
			
			
		}
		
	}
}