package baekjoon;

import java.io.IOException;

public class B11049_��İ��� {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		MatrixMulti mm = new MatrixMulti(); 
		mm.input();
		System.out.println(mm.solve());
	}

}

class MatrixMulti{
	
	int N;  // ����� 1~N���� N��
	int D[];   // D[k] = ��� Sk�� ���� ���� ( D[0] =S1�� ���� ����)
	int C[][]; //C[i][j]: ��� i���� j���� ���ϴ� �� �ʿ��� �ּ� ������ ��
	
	
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
	
	int solve(){
		C = new int[N+1][N+1];
		
		int j;
		for(int l=1; l<= N-1;l++){
			for(int i=1; i<=N-l; i++){
				j = i+l;
				C[i][j] = Integer.MAX_VALUE;
				
				for(int k=i; k<j;k++){
					int t = C[i][k] + C[k+1][j] + D[i-1]*D[k]*D[j];
					          //Si ~Sk���� ���Ѱ� + Sk+1���� Sj���� ���Ѱ� +  �� �ΰ��� ���Ѵ�(Si�� ��*Sk�� ��* Sj�� ��)
					if(t < C[i][j])   C[i][j] = t;
					
				}	
			}
		}
		
		
		return C[1][N];
	}
	
	
	
	
}