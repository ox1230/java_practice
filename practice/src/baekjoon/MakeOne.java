package baekjoon;

import java.util.Scanner;

public class MakeOne {
	public static void main(String[] args){
		MakeOneProcess m = new MakeOneProcess();
		m.input();
		System.out.println(m.startSolve());
	}
}


class MakeOneProcess{
	int X;
	int[] ans;
	
	
	void input(){
		Scanner sc = new Scanner(System.in);
		
		X = sc.nextInt();
		
		sc.close();
		
	}
	
	int startSolve(){
		ans = new int[X+4];
		
		ans[2] = 1;
		ans[3] = 1;
		
		return solve(X);
	}
	
	
	int solve(int x){
		if(x<=1) return 0;
		if(ans[x] != 0) return ans[x];
		else{
			ans[x] = Math.min(solve(x/2) + x%2 + 1,solve(x/3) + x%3 + 1);
			
			return ans[x];
		}
	}
	
	 
}