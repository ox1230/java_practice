package codeground;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Cprac_���� {

	static int Answer;
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		//Scanner sc = new Scanner(new FileInputStream("input.txt"));

		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			
			int A = sc.nextInt();
			int B = sc.nextInt();
			int D = sc.nextInt();
			
			
			Answer =  (int) Math.ceil((D-A) /(double)(A-B));
			
			
			Answer++;
			
		
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}

}
