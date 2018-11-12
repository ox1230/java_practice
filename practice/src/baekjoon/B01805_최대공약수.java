package baekjoon;

import java.util.Scanner;

public class B01805_최대공약수 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		long A = sc.nextLong();
		long B = sc.nextLong();
		
		long num = gcd(A,B);
		
		StringBuffer ans  = new StringBuffer();
		
		for (int i = 0; i < num; i++) {
			ans.append(1);
		}
		
		System.out.println(ans);
		
		
	} // end of main
	
	static long gcd(long a, long b){
		if(b> a ){
			long temp = b;
			b = a;
			a = temp;
		}
		
		if(b==0) return a;
		else return gcd(b, a%b);
	}
	
	
} // end of class
