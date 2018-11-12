package baekjoon;

import java.io.IOException;
import java.util.ArrayList;

public class B10986_RSum {
	public static void main(String[] args){
		
	}
}


class RSum{
	int N;
	int M;
	ArrayList<Integer> S = new ArrayList<>();  // 각 숫자의 나머지 저장
	
	void input() throws IOException{
		Reader r = new Reader();
		
		N = r.nextInt();
		M = r.nextInt();
		
		for(int i=0;i<N;i++){
			S.add(r.nextInt() % M);
		}
	}
	
	int startSolve(){
		
		
	}
}

class Span implements Comparable<Span>{
	int i;
	int j;
	int sumR;
	
	Span(){}
	Span(int i,int j){
		this.i = i;
		this.j = j;
	}
	@Override
	public int compareTo(Span arg0) {
		// TODO Auto-generated method stub
		if(this.i > arg0.i) return 1;
		else if(this.i < arg0.i) return -1;
		else return this.j - arg0.j;
	}
}
