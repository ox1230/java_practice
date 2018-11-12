package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Fibo3Ex {
	public static void main(String[] args) throws IOException{
		Fibo3 f = new Fibo3();
		f.input();
		System.out.println(f.startSolve());
	}
}

// 행렬을 이용한 방법
class Fibo3{
	long n;
	long[][] A = new long[][]{ {1,1},
							   {1,0}};
	
	void input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
		n = Long.parseLong(br.readLine());	
	}
	
	long startSolve(){
		if(n==0) return 0;
		
		productMatrix(n);
		
		return A[0][1]; 
	}
	
	
	long[][] copyOfA(){
		long[][] ret  = new long[][]{{A[0][0], A[0][1]},
			                         {A[1][0], A[1][1]}};
			                         
	     return ret;
		
	}
	
	void productMatrix(long t){
		if(t== 1) return;
		
		productMatrix(t/2);  // t/2승을 구한다.
		ASquare();  // 구한것을 제곱한다.
		
/*		System.out.println(A[0][0] +" "+ A[0][1]);
		System.out.println(A[1][0]+ " "+ A[1][1]);
		*/
		
	    if(t % 2 == 1){
	    	long[][] temp = copyOfA();
					  
	    	A[0][0] = (temp[0][0] + temp[0][1]) % 1000000;
			A[0][1] = temp[0][0];
			A[1][0] = (temp[1][0] + temp[1][1]) % 1000000;
			A[1][1] = temp[1][0];
		}
		
	}
	
	void ASquare(){
		long[][] temp = copyOfA();
		
		A[0][0] = ((temp[0][0]*temp[0][0])%1000000 + (temp[0][1]* temp[1][0])%1000000)%1000000;
		A[0][1] = ((temp[0][0]*temp[0][1])%1000000 + (temp[0][1]* temp[1][1])%1000000)%1000000;
		A[1][0] = ((temp[1][0]*temp[0][0])%1000000 + (temp[1][1]* temp[1][0])%1000000)%1000000;
		A[1][1] = ((temp[1][0]*temp[0][1])%1000000 + (temp[1][1]* temp[1][1])%1000000)%1000000;
		
	}
	
	
}