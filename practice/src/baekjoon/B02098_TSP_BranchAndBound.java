package baekjoon;

import java.io.IOException;

public class B02098_TSP_BranchAndBound {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		SalesmanTour ss = new SalesmanTour();
		ss.input();
		System.out.println(ss.solve());
	}

}



class SalesmanTour{
	int W[][];   // graph
	int N;    // # of vertex
	int A[];   // current sol

	
	Minimini minis[];  // Minimini들의 집합
	int bestSol = 0x3f3f3f3f;   // currently min cost
	
	void input() throws IOException{
		Reader rd = new Reader();
		N = rd.nextInt();
		
		W = new int[N][N];
		
		for(int i=0;i<N;i++)for(int j=0;j<N;j++){
			W[i][j] = rd.nextInt();
		}
		
		
		
	}
	
	int solve(){
		minis = new Minimini[N];
		for(int i=0;i<N;i++){
			minis[i] = new Minimini(i);
		}
		
		
		return bestSol;
		
	}
	
	
	class Minimini{ // branch and bound를 위한 자료
		int v; // vertex
		int firstTo = Integer.MAX_VALUE; // vertex에서 나가는 가장 작은 edge
		int fTo;  // 가장작은 edge로 연결된 vertex
		int secondTo = Integer.MAX_VALUE; // vertex에 연결된 두번째로 작은 edge
		
		int firstFr = Integer.MAX_VALUE;  // vertex로 오는 가장 작은 edge
		int fFr;  // 가장작은 edge와 연결된 vertex
		int secondFr = Integer.MAX_VALUE;  // vertex에 연결된 두번째로 작은 edge
		
		int sum; // firstTo + firstFr
		
		Minimini(int v){
			this.v = v;
			
			for(int i= 0;i<N;i++){
				if( W[v][i] >0 && W[v][i] < secondTo){
					//TO 처리
					if(W[v][i] < firstTo){
						secondTo = firstTo;
						firstTo = W[v][i];
						fTo = i;
					}
					else{// just second
						secondTo = W[v][i];
					}
				}
				
				
				if( W[i][v] >0 && W[i][v] < secondFr){
					//from 처리
					if(W[i][v] < firstFr){
						secondFr = firstFr;
						firstFr = W[i][v];
						fFr = i;
					}
					else{// just second
						secondFr = W[i][v];
					}
				}
			}
			
		}
	}

	
}