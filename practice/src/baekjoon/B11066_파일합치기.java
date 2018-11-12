package baekjoon;

import java.util.Scanner;

public class B11066_파일합치기 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		SumOfFile sf = new SumOfFile(); 

		for (int i = 0; i < T; i++) {
			sf.input(sc);
			System.out.println(sf.solve());
		}
	}
}

class SumOfFile{
	int K;
	int S[]; // 파일들을 입력받을 배열

	int DD[][];   // DD[s][e]: S~E까지 합치는데 드는 비용의 최솟값.

	public void input(Scanner sc) {
		// TODO Auto-generated method stub

		K = sc.nextInt();

		S = new int[K];

		for (int i = 0; i < S.length; i++) {
			S[i] = sc.nextInt();
		}
	}
	public int solve() {
		// TODO Auto-generated method stub
		DD = new int[K][K];

		return dp(0,K-1);
	}
	private int dp(int s, int e) {
		// TODO Auto-generated method stub
		// s~ e까지의 파일을 합치는 방법중 최소방법을 찾는다.
		if(DD[s][e] > 0) {}
		else if(s == e){
			return 0;
		}
		else if(s+1 == e){
			DD[s][e] = S[s] + S[e]; 
		}
		else{

			int min = Integer.MAX_VALUE;
			// s~e의 파일들을 합쳐 두파일로 만드는데 드는 최솟값
			for (int i = s; i < e; i++) {
				int temp = dp(s,i) + dp(i+1,e);
				if(min > temp) min = temp;
			}

			// 위에서 구한 두파일을 합치는데 드는 비용( 전체 파일들의 크기의 합)
			for (int i = s; i <= e; i++) {
				min += S[i];
			}

			DD[s][e] = min;
		}

		return DD[s][e];
	}
}