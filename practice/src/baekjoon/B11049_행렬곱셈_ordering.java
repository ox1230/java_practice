package baekjoon;

import java.io.IOException;

public class B11049_��İ���_ordering {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		MatrixMultiO mm = new MatrixMultiO(); 
		mm.input();
		System.out.println(mm.solveO());
		System.out.println(mm.printing());
	}

}

class MatrixMultiO{
	
	int N;  // ����� 1~N���� N��
	int D[];   // D[k] = ��� Sk�� ���� ���� ( D[0] =S1�� ���� ����)
	int C[][]; //C[i][j]: ��� i���� j���� ���ϴ� �� �ʿ��� �ּ� ������ ��
	int P[][];  // P[i][j]�� i~k,  k+1~j�� ���ϴ� ���� �ּҰ�
	
	void input() throws IOException{
		Reader r = new Reader();
		
		N = r.nextInt();
		
		D = new int[N+1];
		
		D[0] = r.nextInt();
		D[1] = r.nextInt();
		
		for(int i=2; i<=N;i++){
			r.nextInt();  // ������ ���� �����̹Ƿ� ������.
			D[i] = r.nextInt();
		}
		
		return;
	}
	
	int solveO(){
		C = new int[N+1][N+1];
		P = new int[N+1][N+1];
		
		int j;
		for(int l=1; l<= N-1;l++){
			for(int i=1; i<=N-l; i++){
				j = i+l;
				C[i][j] = Integer.MAX_VALUE;
				
				for(int k=i; k<j;k++){
					int t = C[i][k] + C[k+1][j] + D[i-1]*D[k]*D[j];
					          //Si ~Sk���� ���Ѱ� + Sk+1���� Sj���� ���Ѱ� +  �� �ΰ��� ���Ѵ�(Si�� ��*Sk�� ��* Sj�� ��)
					if(t < C[i][j]){
						C[i][j] = t;
						P[i][j] = k;
					}
					
				}	
			}
		}
		
		
		return C[1][N];
	}
	
	String printing(){
		StringBuffer ret = new StringBuffer();
		
		ordering(1,N,ret);
		
		return ret.toString();
	}
	
	void ordering(int i,int j,StringBuffer ret){
		if(i == j){
			ret.append(" A");
			ret.append(i);
			ret.append(" ");
		}
		else{
			ret.append("(");
			ordering(i,P[i][j], ret);
			ordering(P[i][j]+1, j, ret);
			ret.append(")");	
		}
		
		return;
	}
	
}