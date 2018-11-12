package baekjoon;


import java.util.Scanner;

public class B02609_최대공약수최소공배수 {
	public static void main(String[] args)  {
		Scanner r = new Scanner(System.in);
		
		int a = r.nextInt();
		int b = r.nextInt();
		
		int num = gcd(a,b);
		System.out.println(num);
		
		int a1 = a/num;
		int a2 = b/num;
		
		System.out.println(a1 * a2 * num);
		
	}// end of main
	
	
	static int gcd(int a , int b){
		
		if(b>a){
			int temp = a;
			a = b;
			b = temp;
		}
		
		if(b == 0) return a;
		else 	return gcd(b, a%b);
	}
	
	
	
}// end of class


