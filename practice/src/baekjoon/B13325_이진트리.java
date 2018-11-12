package baekjoon;

import java.io.IOException;
import java.util.ArrayList;

public class B13325_이진트리 {
	public static void main(String[] args){
		
	}
}

class BiTree{
	int k;
	ArrayList<Integer> S = new ArrayList<>();
	
	void input() throws IOException{
		Reader r = new Reader();
		
		k = r.nextInt();
		
		for(int i=1; i< Math.pow(2,k+1);i++){
			S.add(r.nextInt());
		}
		
	}
	
	void solve(){
		
	}
	
}
