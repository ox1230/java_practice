package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SumOf2DArray {
	public static void main(String[] args) throws IOException{
		TwoDiArray td = new TwoDiArray();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		td.inputAndFillSumto(br);
		System.out.println(td.solve(br));
	}
}

class TwoDiArray{
	int[][] S;
	int N,M;
	
	int[][] sumto;  // sumto[i][j] = i,1���� i,j������ ���ڵ��� ��
	
	void inputAndFillSumto(BufferedReader br) throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		S = new int[N+1][M+1];
		sumto = new int[N+1][M+1];
		
		int temp;
		int sum;
		
		for(int i=1;i<=N;i++){
			st = new StringTokenizer(br.readLine());
			sum = 0;
			for(int j=1;j<=M;j++){
				temp = Integer.parseInt(st.nextToken());
				S[i][j] = temp;
				sum += temp;
				sumto[i][j] = sum;
			}
		}
		
	}
	
	String solve(BufferedReader br) throws IOException{
		StringBuffer ret = new StringBuffer();
		
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		int i,j,x,y;
		
		for(int t=0;t<K;t++){
			st = new StringTokenizer(br.readLine());
			i = Integer.parseInt(st.nextToken());
			j = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			
			int sum=0;
			//i����� x�����  ���࿡�� j������ y�������� ���� ���Ѵ�. 
			for(int row=i;row<=x;row++){
				sum += sumto[row][y] - sumto[row][j-1];  // j���� 1�� ��쿣 0���� ����
			}
			
			ret.append(sum+"\n");
			
		}
		return ret.toString();
	}
	
	
	
}