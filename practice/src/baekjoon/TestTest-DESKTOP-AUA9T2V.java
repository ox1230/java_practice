package baekjoon;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestTest {
	public static void main(String[] args) throws FileNotFoundException{
		File f = new File("test.txt");
		Scanner sc = new Scanner(f);
		
		sc.nextInt();
		sc.nextInt();
		
		int cnt = 0;
		while(! sc.hasNextInt()){
			System.out.println(sc.nextInt());
			
			cnt++;
		}
		
		System.out.println(cnt);
	}
}
