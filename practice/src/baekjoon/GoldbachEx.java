package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GoldbachEx{
	public static void main(String[] args) throws IOException{
		GoldBach g= new GoldBach();
		g.input();
		g.fillIsMulti();
		System.out.println(g.solveAndString());
	}
}

class GoldBach{
	int[] S;  //Ǯ����ϴ� ����
	int maxNum= 0;
	boolean[] isMulti; // ����� �ִ��� üũ
	
	void input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		S = new int[T];
		
		
		for(int i=0;i<T;i++){
			S[i] = Integer.parseInt(br.readLine());
			if( maxNum < S[i]) maxNum = S[i];
		}
	}
	
	void fillIsMulti(){
		isMulti = new boolean[maxNum+1];
		isMulti[1] = true;
		
		for(int i=2; i<=maxNum ; i++){
			if(isMulti[i]) continue;
			
			for(int j= i*2 ; j<=maxNum; j += i){
				isMulti[j] = true;
			}
		}
	}
	
	String solveAndString(){
		int small;
		StringBuffer ret = new StringBuffer();
		
		for(int temp: S){
			small = goldPartition(temp);
			ret.append(small +" "+ (temp-small)+ "\n");
		}
		
		return ret.toString();
		
	}
	
	private int goldPartition(int n){  // n�� ������ ��Ƽ���� ���� ���� ���( ������ ������ ���� ū���� ���)
		int ret = 0;
		
		if(isMulti[n-2] == false) ret = 2;
		
		int divide = n/2;
		for(int i=3; i<= divide ; i += 2){
			if(isMulti[i]) continue;
			
			if(isMulti[n-i] == false) ret = i;
		}
		
		return ret;
	}
}