package baekjoon;


import java.util.Scanner;
import java.util.Stack;

public class B01094_Stick {
	public static void main(String[] args){
		Stick64 s6 = new Stick64();
		s6.input();
		System.out.println(s6.solve());
	}
}


class Stick64{
	Stack<Integer> st = new Stack<Integer>();   //�׻� ���߿� �����°� �۴�.
	int X;
	
	void input(){
		Scanner sc = new Scanner(System.in);
		X = sc.nextInt();
	}
	
	
	int solve(){
		if(X == 64) return 1;
		st.add(64);
		
		int small;
		int n = 64;  // ������  ������ ��
		int cnt = 1; // ������� ����
		
		while(X < n){
			small = st.pop();
			
			small /=2;
			cnt++;
			
			if(n-small >= X){
				st.add(small);
				n -= small;
				cnt--;
			}
			else{
				st.add(small);
				st.add(small);
			}
		}
		
		return cnt;
	}
	
	
}