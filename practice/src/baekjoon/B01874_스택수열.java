package baekjoon;

import java.io.IOException;
import java.util.Stack;

public class B01874_스택수열 {
	public static void main(String[] args) throws IOException {
		StackNumber sn = new StackNumber();
		sn.input();
		System.out.println(sn.solve());
	}
}

class StackNumber {
	int n;
	int[] ipOrder;

	Stack<Integer> stack = new Stack<Integer>();
	//실제로 집어넣을 스택

	public void input() throws IOException {
		// TODO Auto-generated method stub
		Reader rd = new Reader();

		n = rd.nextInt();

		ipOrder = new int[n];

		for (int i = 0; i < ipOrder.length; i++) {
			ipOrder[i] = rd.nextInt();
		}
	}

	public String solve() {
		// TODO Auto-generated method stub
		StringBuffer ret = new StringBuffer();
		
		int next = 1;
		// 주어진 수열을 진행하면서 top < 현재숫자이면 스택에 다음숫자(next)를 계속해서 쌓는다.
		// top == 현재숫자이면 pop한다. 
		
		for (int idx = 0; idx < n ; idx++) {
			
			while(next <= ipOrder[idx]){
				ret.append('+');
				ret.append('\n');
				stack.add(next);
				
				next++;
			}
			
			// 같아서 or 주어진 숫자가 더 작아서 탈출시
			//top이 주어진숫자이면 pop한다.
			if(stack.peek() == ipOrder[idx]){
				ret.append('-');
				ret.append('\n');
				stack.pop();
			}
		}

		// 작업이 다끝났는데도 stack이 비지 않았으면 문제가 있다.
		if(!stack.isEmpty())  return "NO";
		else                 return ret.toString();
	}

}// end of class