package codemonster;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class BinaryChange {
	public static void main(String[] args){
		Binary com = new  Binary();
		File f = new File("input.txt");
		try {
			Scanner sc = new Scanner(f);
			FileWriter fw;
			try {
				fw = new FileWriter("result.txt");
				PrintWriter pw = new PrintWriter(fw);
				com.ans.put(1L, 1L);
				
				long C = sc.nextInt();
				for(long i=0;i<C;i++){
					com.input(sc);
					long ans = com.solve();
					
					pw.println(ans);
				}
				pw.close();
				fw.close();
				sc.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

class Binary{
	long first;
	long last;
	HashMap<Long,Long> ans = new HashMap<Long,Long>(); //0에서 a까지 가는 방법의 수
	
	
	void input(Scanner sc){
		first = sc.nextLong();
		last = sc.nextLong();
	
	}
	
	long zeroTo(long to){
		long temp = to;
		if(!ans.isEmpty() && ans.containsKey(to)){
			return ans.get(to);
		}
		else{
			long ret=1L;
			long i=2L;
			while(temp/i >0){
				ret += i;
				i*=2;	
			}
			if(to% (i/2) != 0) ret  += zeroTo(to%(i/2));
			ans.put(to,ret);
			return ret;	
		}			
	}
	
	long solve(){
		return zeroTo(last)- zeroTo(first);
 	}
	
}