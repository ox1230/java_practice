package baekjoon;

import java.util.Scanner;

public class UpStairUpgrade {
	public static void main(String[] args){
		UpStair u = new UpStair();
		u.input();
		
		System.out.println(u.solve());
	}
}

class UpStair{
	int[] stairs;
	int n;
	int[][] ANS;   // ANS[i][k] : i번째 계단까지의 점수(k=0: 바로 밑의 계단을 밟음
	                                          //  k=1: 밑밑에 있는 계단을 밟음
	
	
	void input(){
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		stairs = new int[n];
		
		
		for(int i=0;i<n;i++){
			stairs[i] = sc.nextInt();
		}
		
		sc.close();
	}
	
	int solve(){
		ANS = new int[n][2];
		
		ANS[0][0] = stairs[0];
		ANS[0][1] = stairs[0];
		
		ANS[1][0] = stairs[1] + ANS[0][1];
		ANS[1][1] = stairs[1];
		
		
		for(int stair=2;stair<n;stair++){
			ANS[stair][0] = stairs[stair] + ANS[stair-1][1];
			ANS[stair][1] = stairs[stair] + Math.max(ANS[stair-2][0],ANS[stair-2][1]); 
		}
		
		return Math.max(ANS[n-1][0], ANS[n-1][1]);
	}
	
}