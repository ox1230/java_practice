package baekjoon;

import java.io.IOException;
import java.util.HashMap;

public class B01620_pocketmonMaster {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Reader r = new Reader();
		
		Poke p = new Poke();
		p.input(r);
		
		System.out.println(p.solve(r));
		
	}

}

class Poke{
	int N;
	int M;
	HashMap<String,Integer> StoI = new HashMap<>();
	HashMap<Integer,String> ItoS = new HashMap<>();
	
	void input(Reader r) throws IOException{
		 N = r.nextInt();
		 M = r.nextInt();
		
		 String temp = "";
		 for(int i=1;i<=N;i++){
			 temp = r.nextLine();
			 StoI.put(temp, i);
			 ItoS.put(i, temp);
		 }
		 
	}
	
	String solve(Reader r) throws IOException{
		StringBuffer ret = new StringBuffer("");
		
		String temp;
		for(int i=0;i<M;i++){
			temp = r.nextLine();
			
			if(StoI.containsKey(temp)){
				ret.append(StoI.get(temp)+"\n");
			}
			else{
				int t = Integer.parseInt(temp);
				
				ret.append(ItoS.get(t));
				ret.append("\n");
			}
		}
		
		return ret.toString();
	}
	
	
}

