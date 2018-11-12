package baekjoon;

import java.io.IOException;

public class B11049_행렬곱셈 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		MatrixMulti mm = new MatrixMulti(); 
		mm.input();
		System.out.println(mm.solve());
	}

}

class MatrixMulti{
	
	int N;  // 행렬은 1~N까지 N개
	int D[];   // D[k] = 행렬 Sk의 열의 숫자 ( D[0] =S1의 행의 숫자)
	int C[][]; //C[i][j]: 행렬 i부터 j까지 곱하는 데 필요한 최소 연산의 수
	
	
	void input() throws IOException{
		Reader r = new Reader();
		
		N = r.nextInt();
		
		D = new int[N+1];
		
		D[0] = r.nextInt();
		D[1] = r.nextInt();
		
		for(int i=2; i<=N;i++){
			r.nextInt();  // 다음은 같은 숫자이므로 버린다.
			D[i] = r.nextInt();
		}
		
		return;
	}
	
	int solve(){
		C = new int[N+1][N+1];
		
		int j;
		for(int l=1; l<= N-1;l++){
			for(int i=1; i<=N-l; i++){
				j = i+l;
				C[i][j] = Integer.MAX_VALUE;
				
				for(int k=i; k<j;k++){
					int t = C[i][k] + C[k+1][j] + D[i-1]*D[k]*D[j];
					          //Si ~Sk까지 곱한것 + Sk+1부터 Sj까지 곱한것 +  그 두개를 곱한다(Si의 행*Sk의 열* Sj의 열)
					if(t < C[i][j])   C[i][j] = t;
					
				}	
			}
		}
		
		
		return C[1][N];
	}
	
	
	
	
}