package baekjoon;

import java.io.IOException;
import java.util.Stack;

public class B01874_���ü��� {
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
	//������ ������� ����

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
		// �־��� ������ �����ϸ鼭 top < ��������̸� ���ÿ� ��������(next)�� ����ؼ� �״´�.
		// top == ��������̸� pop�Ѵ�. 
		
		for (int idx = 0; idx < n ; idx++) {
			
			while(next <= ipOrder[idx]){
				ret.append('+');
				ret.append('\n');
				stack.add(next);
				
				next++;
			}
			
			// ���Ƽ� or �־��� ���ڰ� �� �۾Ƽ� Ż���
			//top�� �־��������̸� pop�Ѵ�.
			if(stack.peek() == ipOrder[idx]){
				ret.append('-');
				ret.append('\n');
				stack.pop();
			}
		}

		// �۾��� �ٳ����µ��� stack�� ���� �ʾ����� ������ �ִ�.
		if(!stack.isEmpty())  return "NO";
		else                 return ret.toString();
	}

}// end of class