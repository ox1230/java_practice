package baekjoon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

public class Writer {
	
	public static void main(String[] args) throws IOException{
		File f = new File("test.txt");
		try {
			OutputStreamWriter ow = new OutputStreamWriter(new PrintStream(f));
			
			ow.write("5000000 1\n");
		
			StringBuffer sb = new StringBuffer();
			for(int i=0;i<2500000;i++){
				int num  = (int)(Math.random()*2000000001 - 1000000000);
				sb.append(num);
				sb.append(" ");
			}
			ow.write(sb.toString());
			
			sb = new StringBuffer();
			for(int i=0;i<2500000;i++){
				int num  = (int)(Math.random()*2000000001 - 1000000000);
				sb.append(num);
				sb.append(" ");
			}
			ow.write(sb.toString());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
}
