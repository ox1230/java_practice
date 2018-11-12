package baekjoon;

import java.io.IOException;

public class B09663_Nqueen {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Nqueen n = new Nqueen();
		
		n.input();
		System.out.println(n.solve());
	}

}

class Nqueen{
	int N;
	int col[]; // col[k] : k번째 row에 있는 queen의 위치
	int cnt = 0; // 경우의 수
	// row,col은 (1,1)에서 시작한다.
	void input() throws IOException{
		Reader r = new Reader();
		N = Integer.parseInt(r.nextLine());
		
		col  = new int[N+1];
		
		return;
	}
	
	long solve(){
		backtrack(0);
		
		return cnt;
	}
	
	
	void backtrack(int i){
		if(i>=N){
			cnt++;
		}
		else{
			for(int j=1; j <=N; j++){
				col[i+1] = j;
				if(promising(i+1)) backtrack(i+1);
			}
			
		}
	}

	private boolean promising(int i) {
		// TODO Auto-generated method stub
		
		boolean sw = true;
		int k = 1;
		
		while(k < i && sw){
			if(col[k] == col[i] || (Math.abs(col[i]-col[k]) == i-k))   sw  = false;
			k++;
		}

		return sw;
	}
	
}