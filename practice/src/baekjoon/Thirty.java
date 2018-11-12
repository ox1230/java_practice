package baekjoon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Thirty {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		MakeThirty mt = new MakeThirty();
		
		mt.n = sc.next();
		System.out.println(mt.solve());
		
	}
}

class MakeThirty{
	String n;
	ArrayList<Integer> save = new ArrayList<>(); 
	
	boolean isVaild(){
		
		if(n.indexOf("0") == -1) return false;
		else{
			long sum = 0;
			for(int i=0;i<n.length();i++){
				int temp = Integer.parseInt(n.substring(i,i+1));
				sum += temp;
				save.add(temp);
			}
			
			if(sum%3 == 0) return true;
			else return false;
		}
	}
	
	String solve(){
		if(isVaild() == false) return "-1";
		else{
			StringBuffer ret = new StringBuffer();
			
			Collections.sort(save,Collections.reverseOrder());
			
			for(int a:save){
				ret.append(String.valueOf(a));
			}
			
			return ret.toString();
			
		}	
	}
	
}