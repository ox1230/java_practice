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

	
	Minimini minis[];  // Minimini���� ����
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
	
	
	class Minimini{ // branch and bound�� ���� �ڷ�
		int v; // vertex
		int firstTo = Integer.MAX_VALUE; // vertex���� ������ ���� ���� edge
		int fTo;  // �������� edge�� ����� vertex
		int secondTo = Integer.MAX_VALUE; // vertex�� ����� �ι�°�� ���� edge
		
		int firstFr = Integer.MAX_VALUE;  // vertex�� ���� ���� ���� edge
		int fFr;  // �������� edge�� ����� vertex
		int secondFr = Integer.MAX_VALUE;  // vertex�� ����� �ι�°�� ���� edge
		
		int sum; // firstTo + firstFr
		
		Minimini(int v){
			this.v = v;
			
			for(int i= 0;i<N;i++){
				if( W[v][i] >0 && W[v][i] < secondTo){
					//TO ó��
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
					//from ó��
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