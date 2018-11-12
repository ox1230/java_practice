package baekjoon;

import java.io.IOException;

public class Coin1Ex {
	public static void main(String[] args) throws IOException{
		Coin1 c1 = new Coin1();
		
		c1.input();

		System.out.println(c1.startSolve());
		System.out.println();
	}
}
class Coin1{
	int n;
	int k;
	int[] coins;  // ������ �׼�
	int[][] ans;  //ans[i][t]: i���������� ����� ��, t���� ���� �� �ִ� ����� ��	
	void input() throws IOException{
		Reader reader = new Reader();
		
		n = reader.nextInt();
		k = reader.nextInt();
		
		coins = new int[n];
		
		for(int i=0;i<n;i++){
			coins[i] = reader.nextInt();
		}
		
		
	}
	
	int startSolve(){
		ans = new int[n][k+1];
	
		for(int i=0;i<n;i++){
			ans[i][0]=1;
		}
		
		for(int i=1;i<=k;i++){
			int coin = coins[0];
			if(i%coin == 0) ans[0][i] = 1;
		}
		
		
		return solve(n-1,k);
	}
	
	int solve(int index, int target){ // index �������� ����� ��, target���� ����� �ִ� ����� ���� ���Ѵ�.
		                              
		if(target<0){
			return 0;
		}
		else if(index == 0 || ans[index][target] != 0){
			return ans[index][target];
		}
		else{
			int cnt=0;
			
			int tempSum = 0;
			for(;tempSum <= target ; tempSum += coins[index]){
				cnt += solve(index-1, target - tempSum);
			}
			
			ans[index][target] = cnt;
			return cnt;
		}
	
	}
}