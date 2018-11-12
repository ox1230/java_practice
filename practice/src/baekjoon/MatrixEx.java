package baekjoon;

import java.io.IOException;

public class MatrixEx {
	public static void main(String[] args) throws IOException{
		Matrix m = new Matrix();
		
		m.input();
		System.out.println(m.solve());
	}
}

class Matrix{
	int[][] A;
	int[][] B;
	int N;
	int M;
	
	void input() throws IOException{
		Reader r = new Reader();
		
		N = r.nextInt();
		M = r.nextInt();
		
		A = new int[N][M];
		B = new int[N][M];
		
		String row;
		
		for(int i=0;i<N;i++){
			row = r.nextLine();
			for(int j=0;j<M;j++){
				A[i][j] = row.charAt(j)-'0';
			}
		}
		
		for(int i=0;i<N;i++){
			row = r.nextLine();
			for(int j=0;j<M;j++){
				B[i][j] = row.charAt(j)-'0';
			}
		}
	}
	
	boolean change(int r, int c){  /* r,c를 가장 왼쪽 끝으로하는 3*3행렬을 바꾼다
									만약 바꿀 수 없다면 false를 return
								*/
		if(r+2 <N && c+2 < M){
			for(int i=r; i<=r+2;i++)for(int j=c;j <=c+2;j++){
				A[i][j] = A[i][j] == 0? 1 : 0;
			}
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * (0,0)부터 차례대로 숫자가 B와 다르면 change를 수행. change를 수행할 수 없으면 -1 return
	 * @return
	 */
	int solve(){
		int cnt = 0;
		for(int i=0;i<N;i++)for(int j=0;j<M;j++){
			if(A[i][j] != B[i][j]){
				boolean isChanged = change(i,j);
				if(!isChanged){
					return -1;
				}
				cnt++;
			}
		}
		
		return cnt;
		
	}
	
	
}




