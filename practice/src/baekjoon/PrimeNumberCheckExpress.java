package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;



public class PrimeNumberCheckExpress {
	public static void main(String[] args) throws IOException{
		int M,N;
		boolean[] numbers;  // ����� �ִ��� üũ, ������ true
		StringBuffer result= new StringBuffer();
		
		BufferedReader rr = new BufferedReader(new InputStreamReader(System.in));
		
		
		M = Integer.parseInt(rr.readLine());
		N = Integer.parseInt(rr.readLine());
		
		numbers = new boolean[N+1];
		rr.close();
		
		for(int i=2;i<=N;i++){
			if(numbers[i] == false){   // i�� �Ҽ��̸�
				if(i>=M) result.append(i+"\n"); //��µ� �Ѳ����� ó��
				for(int temp=i*2;temp<=N; temp += i){
					numbers[temp] = true;	
				}
			}
		}
		
		
		System.out.print(result.toString());
		
	}
}
