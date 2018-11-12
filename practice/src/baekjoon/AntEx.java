package baekjoon;

import java.io.IOException;

public class AntEx {

}

class Ant{
	int L;
	int n;
	int ant[];
	
	void input() throws IOException{
		Reader reader = new Reader();
		
		L = reader.nextInt();
		n = reader.nextInt();
		
		ant = new int[n];
		
		for(int i=0;i<n;i++){
			ant[i] =  reader.nextInt();
		}
	}

}