package baekjoon;

import java.util.Scanner;

public class NumberTriangleEx {
	
	public static void main(String args[]){
		NumberTriangle a = new NumberTriangle();
		
		a.input();
		System.out.println(a.solve());
	}
}


class NumberTriangle{
	int n; // 삼각형의 크기
	int[][] triangle;
	
	void input(){
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		triangle = new int[n][];
		
		for(int i=0;i<n;i++){
			triangle[i] = new int[i+1];
			
			for(int j=0;j<i+1;j++){
				triangle[i][j] = sc.nextInt();
			}
		}
		sc.close();
	}
	
	int solve(){
		int ans[][] = new int[n][];  //ans[i][j] : i층의 j번째 숫자에 도달하기 위한 최대값
		
		ans[0] = new int[1];
		ans[0][0] = triangle[0][0];
		
		for(int i=1;i<n;i++){
			ans[i] = new int[i+1];
			//가장 왼쪽
			ans[i][0] = triangle[i][0] + ans[i-1][0];
			
			//중간
			for(int j=1;j<i;j++){
				ans[i][j] = triangle[i][j] + (ans[i-1][j-1]<ans[i-1][j]? ans[i-1][j]: ans[i-1][j-1]);
			}
			//가장 오른쪽
			ans[i][i] = triangle[i][i]  + ans[i-1][i-1];
		}
		
		int max= ans[n-1][n-1];
		for(int i=1;i<=n-1;i++){
			if(ans[n-1][i] >max) max = ans[n-1][i];
		}
		
		return max;
	}
	
	
}