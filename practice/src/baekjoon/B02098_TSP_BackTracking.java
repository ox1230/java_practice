package baekjoon;

import java.io.IOException;

public class B02098_TSP_BackTracking {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Salesman ss = new Salesman();
		ss.input();
		System.out.println(ss.solve());
	}

}


class Salesman{
	int W[][];   // graph
	int N;    // # of vertex
	int A[];   // current sol
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
		A = new int[N];
		for(int i=0 ; i<N;i++){
			A[i] = i;
		}
		backTracking(0,0);
		
		return bestSol;
		
	}
	
	void swapA(int i, int j){
		// A의 index i와 index j를 swap한다.
		int temp;
		temp = A[i];
		A[i] = A[j];
		A[j] = temp;
		
	}
	
	void backTracking(int el, int lengthSoFar){
		//el : 현재까지 들린 vertex의 숫자 ,  lengthSoFar: 현재까지 들린 cost
		if(el >= N-1){  // 마지막 도시(시작도시)를 제외한 모든 도시를 들렸으면
			if( (W[A[N-1]][0] > 0) && (lengthSoFar + W[A[N-1]][0]  < bestSol)){   // 마지막 도시와 출발도시를  연결, 해를 구함
				bestSol = lengthSoFar + W[A[N-1]][0];
			}
		}
		else{
			for(int i= el+1 ; i < N; i++){
				swapA(i , el+1);   // check every case
				if(W[A[el]][A[el+1]] > 0){
					int newLength = lengthSoFar + W[A[el]][A[el+1]];
					
					if(newLength < bestSol) backTracking(el+1, newLength);
				}
				else{ // 다음으로 넘어감
				}
				swapA(el+1, i);
			}
		}
	}
	
}