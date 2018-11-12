package baekjoon;

import java.util.Scanner;

public class FiboCountEx {
	static int[][] S;


	public static void main(String[] args){
		S = new int[41][2];  // 40까지 숫자 (1,0이나온 횟수)

		S[0][0] = 1;
		S[1][1] = 1;
		
		
		Scanner sc = new Scanner(System.in);
		
		int C = sc.nextInt();
		
		for(int i=0;i<C;i++){
			int[] temp = count(sc.nextInt());
			System.out.println(temp[0]+" "+ temp[1]);
		}
		

	}

	static int[] count(int n){
		if(S[n][0]==0 && S[n][1] == 0){
			int[] temp1 = count(n-1);
			int[] temp2 = count(n-2);
			
			S[n][0] = temp1[0] +temp2[0];
			S[n][1] = temp1[1] +temp2[1];
		}
		else{  // 이미 존재하면
		}
		
		return S[n];
	}

}
