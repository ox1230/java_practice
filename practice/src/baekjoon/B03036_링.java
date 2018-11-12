package baekjoon;

import java.util.Scanner;

public class B03036_링 {
	public static void main(String[] args)  {
		Ring r = new Ring();
		r.input();
		System.out.println(r.solve());
	} 
} // end of class

class Ring{

	int N;
	int S[];
	
	
	public void input() {
		// TODO Auto-generated method stub	
		Scanner r = new Scanner(System.in);
		N = r.nextInt();
		
		S = new int[N];
		
		for (int i = 0; i < N; i++) {
			S[i] = r.nextInt();
		}
	}

	public String solve() {
		// TODO Auto-generated method stub
		StringBuffer ret = new StringBuffer();
		int A = S[0];
		
		for (int i = 1; i < S.length; i++) {
			// A와 B의 최대공약수를 구한다.
			int t = gcd(A,S[i]);
			
			// 정답은 'A/t'/ 'B/t'형태이다.
			ret.append(A/t);
			ret.append('/');
			ret.append(S[i]/t);
			ret.append('\n');
		}
		
		
		return ret.toString();
	}

	private int gcd(int a, int i) {
		// TODO Auto-generated method stub
		if(a < i){
			int temp = i;
			i = a;
			a = temp;
		}
			
		if(i == 0) return a;
		else return gcd(i , a%i);
	}
	
	
	
} // end of class
