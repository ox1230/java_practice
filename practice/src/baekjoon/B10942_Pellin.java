package baekjoon;

import java.io.IOException;

public class B10942_Pellin {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Pellin p = new Pellin();
		Reader r = new Reader();
		
		p.input(r);
		System.out.println(p.startSolve(r));
		
	}	

}

class Pellin{
	int N;
	int M;
	int num[];
	
	void input(Reader r) throws IOException{
		N = r.nextInt();
		
		num = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			num[i] = r.nextInt();
		}
		
		M = r.nextInt();
	}
	
	String startSolve(Reader r) throws IOException{
		StringBuffer ret = new StringBuffer();
		
		for(int i=0;i<M;i++){
			ret.append(solve(r));
			ret.append("\n");
		}
		
		return ret.toString();
	}

	private int solve(Reader r) throws IOException {
		int S = r.nextInt();
		int E = r.nextInt();
		
		int M  = (E-S)/2;  // 중간까지의 개수
				
		for(int i = 0;i<=M;i++){
			if(num[S+i] != num[E-i]) return 0;
		}
		
		return 1;
	}
	
	
	
}
