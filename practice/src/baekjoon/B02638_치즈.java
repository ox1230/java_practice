package baekjoon;

import java.io.IOException;

public class B02638_치즈 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Cheese2 C = new Cheese2();
		C.input();
		System.out.println(C.solve());
	}

}

class Cheese2{
	int N;
	int M;
	int A[][];
	
	void input() throws IOException{
		Reader r = new Reader();
		N = r.nextInt();
		M = r.nextInt();
		
		A = new int[N][M];
		
		for(int i=0;i<N;i++){
			for(int j=0;j<M;j++){
				A[i][j] = r.nextInt();
			}
		}
		
	}
	
	int solve(){
		int cnt = 2;  //일반 치즈와의 구분을 위해 2부터 시작(cnt는 한번 체크했다는 표시를 겸한다)
		boolean isFinish = false;
		
		while(!isFinish){
			isFinish= true;
			isFinish = dfs(0,0,cnt,isFinish);
			cnt++;
		}
		cnt-=3;
		return cnt;
		
	}
	
	boolean dfs(int i, int j,int cnt, boolean ret){
		

		if(A[i][j] > 0){// 치즈임
			if(A[i][j] == cnt){  //없어지는 치즈 
				A[i][j] = cnt * -1;
			}
			else{ //아직 공기와 1면만 접촉한 치즈
				A[i][j] = cnt;// 이번 턴에 접촉했다는  체크
			}
			return false; 
			
		}
		else { // 공기임
			A[i][j] = cnt * -1;
			
			//상하좌우 체크
			if(i+1 <N && A[i+1][j]> cnt*-1) ret = dfs(i+1,j,cnt,ret);
			if(i-1 >= 0 && A[i-1][j]> cnt*-1) ret = dfs(i-1,j,cnt,ret);
			if(j+1 <M && A[i][j+1]> cnt*-1) ret = dfs(i,j+1,cnt,ret);
			if(j-1 >= 0 && A[i][j-1]> cnt*-1) ret = dfs(i,j-1,cnt,ret);
			return ret;
		}
	}
	
	
	
}
