package baekjoon;

import java.io.IOException;

public class B01309_동물원 {

	static int N;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Reader r = new Reader();
		N = r.nextInt();
		System.out.println(solve());
	}
	
	static int solve(){
		long ret = 0;
		
		long zoo[][] = new long[N][3]; 
		// 0: 둘다 없음
		// 1: 오른쪽에만 있음
		// 2: 왼쪽에만 있음

		zoo[0][0] = 1L;
		zoo[0][1] = 1L;
		zoo[0][2] = 1L;
		
		for(int i=1; i<N; i++){
			
			zoo[i][0] = zoo[i-1][0]%9901 + zoo[i-1][1]%9901 + zoo[i-1][2]%9901;
			zoo[i][1] = zoo[i-1][0]%9901 + zoo[i-1][2]%9901;
			zoo[i][2] = zoo[i-1][0]%9901 + zoo[i-1][1]%9901;
		}
		
		ret = zoo[N-1][0] + zoo[N-1][1] + zoo[N-1][2];
		return  (int)(ret % 9901);
	}
}
