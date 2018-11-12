package baekjoon;

import java.util.Scanner;

public class Coin {
	public static void main(String[] args){
		Counter c = new Counter();
		
		c.input();
		System.out.println(c.solve());
	}
}

class Counter{
	int N;
	int K;
	int[] coins;

	
	
	void input(){
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		
		coins = new int[N];
		
		
		for(int i=0; i<N;i++){
			coins[i] = sc.nextInt();
		}
		
		
		sc.close();
	}
	
	int solve(){
		
		int temp = K;
		int cnt=0;
		
		for(int i=N-1;i>=0;i--){
			if(coins[i] > temp) continue;
		
			cnt += temp/coins[i];
			temp = temp%coins[i];
			
			if(temp==0) break;
		}
		
		return cnt;
	}
	
}
