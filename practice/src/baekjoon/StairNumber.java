package baekjoon;

import java.util.Scanner;

public class StairNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stair s =  new Stair();
		s.input();
		System.out.println(s.solve());
	}
}

class Stair{
	int N;
	long ans[][];  // ans[n][t] = n자리의 t로 끝나는 계단수의 수
	
	void input(){
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
	}
	
	long solve(){
		ans = new long[N+1][10];
		
		
		for(int i=1;i<10;i++){
			ans[1][i] = 1;
		}
		
		//문제 풀이 시작
		for(int i=2;i<=N;i++){
			ans[i][1] += ans[i-1][0];
			ans[i][8] += ans[i-1][9];
			
			for(int j=1;j<=8;j++){
				ans[i][j-1] += ans[i-1][j];
			
				ans[i][j+1] += ans[i-1][j];
			}
			
			for(int j=0;j<=9;j++){
				ans[i][j] %= 1000000000;
			}
			
			
			
		}
		
		long sum=0;
		for(long temp: ans[N]){
			sum += temp%1000000000;
		}
		
		return sum%1000000000;
		
		
	}
	
}
