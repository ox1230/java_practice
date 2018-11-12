package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SevenTo100 {
	static int[] S = new int[9];
	static boolean[] chk = new boolean[9];
	
	public static void main(String[] args) throws IOException{
		input();
		System.out.println(startSolve());
		System.out.println();
	}
	
	static void input() throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0;i<9;i++){
			S[i]  = Integer.parseInt(reader.readLine());
		}
		
		Arrays.sort(S);
	}
	
	static String startSolve(){
		solve(0,0,0);
		
		StringBuffer ret = new StringBuffer();
		
		for(int i=0;i<9;i++){
			if(chk[i]==true) ret.append(S[i]+ "\n");
		}
		
		return ret.toString();
	}
	
	static boolean solve(int t, int sum, int checked){
		if(sum > 100){
			return false;
		}
		else if(checked == 7){
			if(sum == 100) return true;
			else return false;
		}
		else if(t == 9){
			return false;
		}
		else{
			chk[t] = true;
			
			if(solve(t+1,sum+ S[t],checked+1)== true){    // 이미 찾음
				return true;
			}
			else{   // 못찾음
				chk[t] = false;
				return solve(t+1,sum, checked); 
			}
		}	
	}
	
}
