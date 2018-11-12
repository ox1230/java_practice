package baekjoon;

import java.io.IOException;

public class B02780_비밀번호 {
	public static void main(String[] args) throws IOException{
		int T;
		int N;
		Reader r =new Reader();
		
		T = r.nextInt();
		for(int i=0;i<T;i++){
			N = r.nextInt();
			Pass p = new Pass();
			p.N = N;
			System.out.println(p.solve());
		}
		
		
	}
}

class Pass{
	int N;
	int S[][]; //S[n][i]: n번째 숫자로 i를 선택하는 경우의 수
	
	
	int solve(){
		S = new int[N][10];
		
		for(int i=0;i<10;i++) S[0][i] = 1;
		
		for(int n=1; n<N;n++){
			S[n][1] = S[n-1][2] + S[n-1][4];
			S[n][2] = S[n-1][1] + S[n-1][3]+ S[n-1][5];
			S[n][3] = S[n-1][2] + S[n-1][6];
			S[n][4] = S[n-1][1] + S[n-1][5]+ S[n-1][7];
			S[n][5] = S[n-1][2] + S[n-1][4]+ S[n-1][6] + S[n-1][8] ;
			S[n][6] = S[n-1][3] + S[n-1][5]+ S[n-1][9];
			S[n][7] = S[n-1][4] + S[n-1][8]+ S[n-1][0];
			S[n][8] = S[n-1][5] + S[n-1][7]+ S[n-1][9];
			S[n][9] = S[n-1][6] + S[n-1][8];
			S[n][0] = S[n-1][7];
			
			for(int i=0;i<10;i++) S[n][i] %= 1234567;
		}
		
		int sum = 0;
		for(int i=0;i<10;i++) sum += S[N-1][i];
		
		return sum %1234567;
		
	}
	
	
}
