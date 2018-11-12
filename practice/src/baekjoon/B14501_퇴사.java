package baekjoon;

import java.util.Scanner;



public class B14501_Επ»η {
	public static void main(String[] args)  {
		Out o = new Out();
		o.input();
		System.out.println(o.solve());
	}
}

class Out{
	int N;
	int T[];
	int P[];
	
	int max = 0;
	
	public void input() {
		// TODO Auto-generated method stub
		Scanner r = new Scanner(System.in);
		
		N = r.nextInt();
		
		T = new int[N];
		P = new int[N];
		
		for (int i = 0; i < N; i++) {
			T[i] = r.nextInt();
			P[i] = r.nextInt();
		}
		
	}
	public int solve() {
		// TODO Auto-generated method stub
		getConsultant(0,0);
		return max;
	}
	
	
	void getConsultant(int i, int sum){
		
		if(i > N){
			return;
		}
		else if(i == N){
			if(max < sum) max = sum;
		}
		else{
			
			getConsultant(i+T[i], sum+P[i]);
			getConsultant(i+1, sum);
		}
		
	}
	
}