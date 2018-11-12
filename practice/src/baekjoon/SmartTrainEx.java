package baekjoon;

import java.io.IOException;

public class SmartTrainEx {
	public static void main(String[] args) throws IOException{
		int sum= 0;
		int max = 0;
		
		int x;
		int y;
		
		Reader reader = new Reader();
		
		for(int i=0;i<4;i++){
			x = reader.nextInt();
			y = reader.nextInt();
			
			sum -= x;
			sum += y;
			
			if(max < sum) max = sum;		
		}
		
		System.out.println(max);
		
	}
}


	
	
