package baekjoon;

import java.util.Scanner;

public class PerfectNumber {
	public static void main(String[] args){
		Scanner  sc = new Scanner(System.in);
		
		int N=sc.nextInt();
		
		for(int i=2;i<=N;i++){
			if(isPerfect(i)) System.out.println(i);
		}
		
	}
	
	static boolean isPerfect(int n){
		int sum= 0;
		for(int i=1; i*i <=n ;i++){
			if(n%i == 0){
				sum += i;
				if(i!=1 && i != n/i) sum += n/i;
				if(sum > n) return false;
			}
		}
		
		if(sum == n) return true;
		else return false;
	}
}
