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
		/*f,v1,v2,t기둥에서 f의 i-2개를 v1으로 이동 ( ans[i-2]번)
			f의 1개를 v2로 이동 (1번)
			f의 마지막 1개를 t로 이동(1번)
			v2의 1개를 t로 이동(1번)
			v1의 i-2개를 t로 이동 (ans[i-2]번)
			ans[i] = ans[i-2] +3 + ans[i-2]
		*/
		for(int i=2;i<=N;i++){
			ans[i] = ( (2*ans[i-2])%9901 +3) %9901;
		}
		
	}
	
}