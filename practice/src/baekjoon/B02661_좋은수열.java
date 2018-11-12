package baekjoon;

import java.util.Scanner;
import java.util.Stack;

public class B02661_좋은수열 {
	public static void main(String[] args) {
		GoodSerial gs = new GoodSerial();
		gs.input();
		gs.solve();
		System.out.println(gs.makeString());
	}  // end of main
} // end of class


class GoodSerial{
	int N;
	int[] S; // 수열을 담아둘 배열
	Stack<int[]> stack = new Stack<>();
	// 다시 돌아가기 위한 스택 (i번째, 2를 넣었다.)

	public void input() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
	}

	public String makeString() {
		// TODO Auto-generated method stub
		StringBuffer ret = new StringBuffer();
		
		for (int i = 0; i < N; i++) {
			ret.append(S[i]);
		}
		
		return ret.toString();
	}

	public void solve() {
		// TODO Auto-generated method stub
		//init
		S = new int[N];
		S[0] = 1;
		stack.add(new int[]{0,1});

		int index = 1;
		int num = 2;
		int[] temp;

		bigLoop:
		while(!stack.isEmpty()){
			while(index< N && num<= 3){
				S[index] = num;
				
				if(isValid(index)){
					stack.add(new int[]{index,num});
					index++;
					num = 1;
					
					if(index == N) break bigLoop;
				}
				else{
					num++;					
				}
			}

			temp = stack.pop();

			index = temp[0];
			num = temp[1];

			switch (num) {
			case 1:
			case 2:
				num++;
				break;
			case 3:
				index++;
				num = 1;
				break;
			default:
				break;
			}
		}

		return;
	}

	boolean isValid(int index) {
		//0부터 index까지의 수열이 적절한지 체크한다.
		int diff;

		for (int i = index-1; i >=0; i--) {
			diff = index - i;
			for (int j = 0;j < diff; j++) {
				if(i-j<0 || S[i-j] != S[index - j]) break;

				if(j == diff-1) { // 똑같은 수열 발견!!
					return false;
				}
			}
		}
		return true;
	}
}

