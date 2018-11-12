package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PrintStar10Ex {
	public static void main(String[] args) throws IOException{
		PrintStar10 a = new PrintStar10();
		
		a.input();
		System.out.println(a.solveAndPrint());
	}
}


class PrintStar10{
	boolean S[][];  // S[i][j]가 true이면 *출력
	int n;
	
	void input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		n = Integer.parseInt(br.readLine());
		
		S = new boolean[n][n];
	}
	
	String solveAndPrint(){
		int t= n;

		fillS(0,0,t);
		
		StringBuffer ret = new StringBuffer(); 
		
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(S[i][j]) ret.append('*');
				else ret.append(' ');
			}
			ret.append('\n');
		}
		
		return ret.toString();
	}
	
	/**
	 * @param r 시작 row
	 * @param c 시작 column
	 * @param t 그리는 단계
	 */
	void fillS(int r,int c,int t){
		int temp = t/3;
		
		if(temp == 0){
			S[r][c] = true;
		}
		else{
			for(int i=0;i<3;i++)for(int j=0;j<3;j++){
				if(i==1 && j==1) continue;
				fillS(r+ i*temp , c + j*temp, temp);
			}
		}
	}
	
}