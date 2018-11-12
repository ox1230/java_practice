package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
//import java.util.StringTokenizer;

public class BertrangEx {
	public static void main(String[] args) throws IOException{
		Prime p = new Prime();
		p.input();
		System.out.println(p.solve());
	}
}

class Prime{
	ArrayList<Integer> save = new ArrayList<Integer>();
	int maxNum = 0;
	boolean isMulti[];  // 약수가 있는지 체크 

	void input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int temp;
		
		
		while(true){
			temp = Integer.parseInt(br.readLine());
			
			if(temp == 0) break;
			if(maxNum < temp*2) maxNum = temp*2;
			
			save.add(temp);
		}
	}

	String solve(){
		// 소수 구하기
		if(save.isEmpty()) return "";
		isMulti = new boolean[maxNum+1];
		isMulti[0] = true;
		isMulti[1] = true;

		for(int i=2;i<=maxNum;i++){
			if(isMulti[i]==true) continue;

			for(int j= i*2 ;j<=maxNum ;j += i){
				isMulti[j] = true;
			}
		}
		
		StringBuffer ret = new StringBuffer();
		
		for(int temp: save){
			int cnt = 0;
			for(int i=temp+1; i<=temp*2 ; i++ ){
				if(isMulti[i] == false) cnt++;
			}
			ret.append(cnt + "\n"); 
		}
		
		return ret.toString();
	}

}