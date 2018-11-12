package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1607_MonkeyTower {
	public static void main(String[] args) throws IOException{
		MonkeyTower m = new MonkeyTower();
		m.input();
		System.out.println(m.startSolve());
	}
}


class MonkeyTower{
	int N;
	int ans[];
	void input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
	
	}
	
	int startSolve(){
		ans = new int[N+1];
		ans[1] =1;
		fillAns();
		return ans[N];
	}
	
	void fillAns(){
		if(N<=1) return;
		/*f,v1,v2,t��տ��� f�� i-2���� v1���� �̵� ( ans[i-2]��)
			f�� 1���� v2�� �̵� (1��)
			f�� ������ 1���� t�� �̵�(1��)
			v2�� 1���� t�� �̵�(1��)
			v1�� i-2���� t�� �̵� (ans[i-2]��)
			ans[i] = ans[i-2] +3 + ans[i-2]
		*/
		for(int i=2;i<=N;i++){
			ans[i] = ( (2*ans[i-2])%9901 +3) %9901;
		}
		
	}
	
}