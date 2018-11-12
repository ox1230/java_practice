package codemonster;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Code {
	
	public static void main(String[] agrs){
		File f = new File("input.txt");
		Scanner sc;
		FileWriter fw;
		
		try{
			sc = new Scanner(f);
			try {
				fw = new FileWriter("result.txt");
				PrintWriter pw = new PrintWriter(fw);
				
				int C= Integer.parseInt(sc.nextLine()); 
				System.out.println(C);
				
				for(int i=0;i<C;i++){
					Decode com = new Decode();
					com.getString(sc);
					int index = com.getCodeNo();
					
					System.out.println(index);
					pw.println(index);
					
				}
			pw.close();	
			sc.close();
				}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		catch (FileNotFoundException e){
			System.out.println("bb");
		}
	}
	
	
}

class Decode{
	String a1;
	String a2;
	int MAX_INDEX;
	
	void getString(Scanner sc){
		MAX_INDEX = Integer.parseInt(sc.nextLine());
		System.out.println(MAX_INDEX);
		
		a1 = sc.nextLine();
		a2 = sc.nextLine();
		//System.out.println(a1);
		//System.out.println(a2);
	}
	
	int getCodeNo(){	
		char first=a1.charAt(0);
		int pivot=0;
		int i=0;
		
		while(i<MAX_INDEX){
			if(a2.charAt(i) == first){ 
				int k = isCorrectCode(pivot, i);
				if(k==-1) return i;
				else pivot = k;
			}
			i++;
		}
		return -1;
	}
	
	int isCorrectCode(int pivot, int i){   //맞으면 -1 안맞으면 i
		int k1=pivot;
		int k2=i+pivot;
		
		if(k2>=MAX_INDEX) k2 -= MAX_INDEX;
		
		do{	
			if(a1.charAt(k1) != a2.charAt(k2)) return k1;
			
			k1++; k2++;
			if(k1>=MAX_INDEX) k1=0;
			if(k2>= MAX_INDEX) k2=0;
			
		}while(k1 !=pivot);
		
		return -1;
	}
	
}