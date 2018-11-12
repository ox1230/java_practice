package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OneTwoThree {
	public static void main(String[] args) throws IOException{
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		Sum123 s = new Sum123();
		
		int T = Integer.parseInt(sc.readLine());
		s.ans[0] = 1;  //아무도 없는것은 아무도 없는 방법 1개로 만들 수 있다고 볼수있다 + 계산의 편의를 위해서
		s.ans[1] = 1;
		s.ans[2] = 2;
		s.ans[3] = 4;
		

		for(int i=0; i<T;i++){
			System.out.println(s.solveForOneCase(Integer.parseInt(sc.readLine())));
		}
	}
}

class Sum123{
	
	int[] ans = new int[11];

	
	int solveForOneCase(int n){
		if(n<0) return 0;
		
		else if(ans[n] != 0) return ans[n];
		else{
			int sum=0;
			sum += solveForOneCase(n/2)* solveForOneCase(n- n/2);  // 양쪽으로 정확히 가름
			sum += solveForOneCase(n/2-1) * solveForOneCase(n-n/2 -1); // 양쪽으로 가르고 사이에 2를 끼어놓음
			
			// 양쪽으로 가르고 사이에 3을 끼워 넣음
			sum += solveForOneCase(n/2-2) * solveForOneCase(n-n/2 -1);
			sum += solveForOneCase(n/2-1) * solveForOneCase(n-n/2 -2);
			
			ans[n] = sum;
			return sum;
		}
	}
}