package baekjoon;

import java.io.IOException;

public class B01912_연속합 {
	public static void main(String[] args) throws IOException {
		SerialSum ss = new SerialSum();
		ss.input();
		System.out.println(ss.solve());
		
	}// end of main
} // end of class

class SerialSum{

	int N;
	int[] S;
		
	public void input() throws IOException {
		// TODO Auto-generated method stub
		Reader r = new Reader();
		
		N = r.nextInt();
		
		S = new int[N];
		
		for (int i = 0; i < N; i++) {
			S[i] = r.nextInt();
		}
	}

	public int solve() {
		// TODO Auto-generated method stub
		
		int max = Integer.MIN_VALUE;
		// maxi[i]:  i에서 시작하는 수열의 합중 최대값인 합
		
		for (int i = 0; i < N; i++) {
			int tempMax = S[i];
			int tempSum = S[i];
			for (int j = i+1; j < N; j++) {
				tempSum += S[j];
				
				if(tempSum > tempMax) tempMax = tempSum;
			}
			
			if(tempMax > max) max = tempMax;
		}
		
		return max;
	}
	
	
	
	
	
}
