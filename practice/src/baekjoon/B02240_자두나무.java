package baekjoon;

import java.io.IOException;

public class B02240_�ڵγ��� {
	public static void main(String[] args) throws IOException{
		Tree t = new Tree();
		
		t.input();
		System.out.println(t.solve());
		
	}
}


class Tree{
	int T;
	int W;
	int[] fall;
	int[][] leftJadu;
	//leftJadu[t][w][i]--  t�ʿ� w�� �̵����� �� ���ʿ� ���� �� �ִ� �ڵμ�.
	int[][] rightJadu;
	public void input() throws IOException {
		// TODO Auto-generated method stub
		Reader r = new Reader();
		T = r.nextInt();
		W = r.nextInt();
		
		fall = new int[T];
		
		for(int i=0;i<T;i++){
			fall[i] = r.nextInt();
		}
		
	}
	
	int solve(){
		leftJadu = new int[T][W+1];
		rightJadu = new int[T][W+1];
		
		//init
		if(fall[0] == 1) leftJadu[0][0] =1;
		else rightJadu[0][1] = 1;
		//
		
		for(int t=1; t<T; t++){
			leftJadu[t][0] = leftJadu[t-1][0];
			rightJadu[t][0] = rightJadu[t-1][0];
			if(fall[t] == 1) leftJadu[t][0]++;
			else rightJadu[t][0]++;
			
			for(int w=1; w<=W; w++){
				if(w > t+1) continue;
				
				// left 
				if(leftJadu[t-1][w] > rightJadu[t-1][w-1]) leftJadu[t][w] = leftJadu[t-1][w];
				else leftJadu[t][w] = rightJadu[t-1][w-1];
				
				// right
				if(rightJadu[t-1][w] > leftJadu[t-1][w-1]) rightJadu[t][w] = rightJadu[t-1][w];
				else rightJadu[t][w] = leftJadu[t-1][w-1];
		
				if(fall[t] == 1) leftJadu[t][w]++;
				else rightJadu[t][w]++;
			
			}
		}
		
		int ret = 0;
		for(int w=0;w<=W;w++){
			if(ret < leftJadu[T-1][w]) ret = leftJadu[T-1][w];

			if(ret < rightJadu[T-1][w]) ret = rightJadu[T-1][w];

		}
		
		
		return ret;
	}
	
	
}