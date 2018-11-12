package baekjoon;

import java.io.IOException;

public class WineEx {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Wine w = new Wine();
		w.input();
		System.out.println(w.startSolve());
	}

}

class Wine{
	int n;
	int[] W;
	int[][] ans;  /* ans[i][j]: i번째 와인까지 마실때의 최대값 
					   j=0 : i-1을 마심,  j=1 : i-1을 마시지 않음
				*/
	
	void input() throws IOException{
		Reader reader = new Reader();
		
		n = reader.nextInt();
		
		W = new int[n];
		
		for(int i=0;i<n;i++){
			W[i] = reader.nextInt();
		}
	}
	
	int startSolve(){
		if(n==1) return W[0];
		if(n==2) return W[0]+W[1];
		ans = new int[n][2];
		
		//ans  초기화
		ans[0][0] = W[0];
		ans[0][1] = W[0];
		
		ans[1][0] = W[0] + W[1];
		ans[1][1] = W[1];
		
		ans[2][0] = W[1] + W[2];
		ans[2][1] = W[0] + W[2];
		
		// ans 채우기
		for(int i=3;i<n;i++){
			ans[i][0] = ans[i-1][1] + W[i];
			
			int it_2 = Math.max(ans[i-2][0],ans[i-2][1]);  //두잔 전의 최대값
			int it_3 = Math.max(ans[i-3][0],ans[i-3][1]);  //세잔 전의 최대값
			
			ans[i][1] = Math.max(it_2,it_3) + W[i];
		}
		
		int itN = Math.max(ans[n-1][0],ans[n-1][1]); //n-1의 최대값 
		int itN_1 = Math.max(ans[n-2][0],ans[n-2][1]); // n-2의 최대값
		
		return Math.max(itN, itN_1) ;
	}
	
}