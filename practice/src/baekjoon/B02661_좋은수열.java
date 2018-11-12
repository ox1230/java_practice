package baekjoon;

import java.util.Scanner;
import java.util.Stack;

public class B02661_�������� {
	public static void main(String[] args) {
		GoodSerial gs = new GoodSerial();
		gs.input();
		gs.solve();
		System.out.println(gs.makeString());
	}  // end of main
} // end of class


class GoodSerial{
	int N;
	int[] S; // ������ ��Ƶ� �迭
	Stack<int[]> stack = new Stack<>();
	// �ٽ� ���ư��� ���� ���� (i��°, 2�� �־���.)

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
		//0���� index������ ������ �������� üũ�Ѵ�.
		int diff;

		for (int i = index-1; i >=0; i--) {
			diff = index - i;
			for (int j = 0;j < diff; j++) {
				if(i-j<0 || S[i-j] != S[index - j]) break;

				if(j == diff-1) { // �Ȱ��� ���� �߰�!!
					return false;
				}
			}
		}
		return true;
	}
}

