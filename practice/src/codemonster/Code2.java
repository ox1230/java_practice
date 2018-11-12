package codemonster;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Code2 {
	
	public static void main(String[] args){
		File f = new File("input.txt");
		FileWriter fw;
		PrintWriter pw;
		
		try {
			Scanner sc = new Scanner(f);
			
			try {
				fw = new FileWriter("result.txt");
				pw = new PrintWriter(fw);
				
				int C = Integer.parseInt(sc.nextLine());
				
				for(int i=0;i<C;i++){
					Decode2 com = new Decode2();
					com.getInput(sc);
					
					int ans = com.getAnswer();
					
					pw.println(ans);
					
				}
				
				
				sc.close();
				pw.close();
				
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

class Decode2{
	int MAX_INDEX;
	String a1;
	String a2;
	
	void getInput(Scanner sc){
		MAX_INDEX = Integer.parseInt(sc.nextLine());
		
		a1 = sc.nextLine();
		a2 = sc.nextLine();
	}
	
	int isSameString(int i, int pivot){
		int k1, k2;
		
		k2 = pivot;
		
		if (i <= pivot) k1 = pivot -i;
		else k1 = MAX_INDEX - i + pivot;
			
		int start = k1;
		
		do{
			if(a1.charAt(k1) != a2.charAt(k2)) return k2;	
			
			k1++; 
			if(k1 >= MAX_INDEX) k1=0;
			
			k2++;
			if(k2>= MAX_INDEX) k2=0;
		}while(k1 != start);
		
		return -1;
	}
	
	int getAnswer(){
		char first = a1.charAt(0);
		int pivot=0;
		int k=0;
		
		for(int i=0;i<MAX_INDEX;i++){
			//System.out.println(k+" "+" "+pivot+" "+ i);
			if(first == a2.charAt(i)){
				k = isSameString(i,pivot);
				
				if(k == -1) return i;
				else pivot = k;
			}
		}
		
		return -1;
	}
}