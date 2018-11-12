package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Reader {
	BufferedReader br;
	StringTokenizer st;
	
	Reader(){
		br = new BufferedReader(new InputStreamReader(System.in));
	}
	

	
	String nextLine() throws IOException{
		return br.readLine();
	}
	
	int nextInt() throws IOException{
		if(st == null || !st.hasMoreTokens()){
			st = new StringTokenizer(br.readLine());
		}
		
		return Integer.parseInt(st.nextToken());
	}
}
