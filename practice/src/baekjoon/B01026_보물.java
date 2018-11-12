package baekjoon;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class B01026_보물 {
	public static void main(String[] args) {
		Precious p = new Precious();
		p.input();
		System.out.println(p.solve());
	}
}


class Precious{
	int N;
	
	int[] A;
	int[] B;
	
	public void input() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		A = new int[N];
		B = new int[N];
		
		for (int i = 0; i < A.length; i++) {
			A[i] = sc.nextInt();
		}
		
		for (int i = 0; i < A.length; i++) {
			B[i] = sc.nextInt();
		}
	}
	public int solve() {
		// TODO Auto-generated method stub
		//A는 내림차순, B는 오름차순으로 가져옴
		
		Arrays.sort(A);  
		Arrays.sort(B);  
		
		int sum = 0; 
		for (int i = 0; i < N; i++) {
			sum +=  A[i] * B[N-1-i];
		}
		
		return sum;
	}
}