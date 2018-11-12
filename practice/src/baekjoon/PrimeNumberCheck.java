package baekjoon;

import java.util.Scanner;

public class PrimeNumberCheck {
	public static void main(String[] args){
		Eratostenes   e = new Eratostenes();
		
		e.input();
		e.fillNumbers();
	}
}

class Eratostenes{ // 에라토스테네스?? 의 체
	int M,N;
	boolean[] numbers;  // 약수가 있는지 체크, 있으면 true
	
	
	void input(){
		Scanner sc = new Scanner(System.in);
		
		M = sc.nextInt();
		N = sc.nextInt();
		
		numbers = new boolean[N+1];
		
		sc.close();
	}
	
	void fillNumbers(){  
		
		for(int i=2;i<=N;i++){
			if(numbers[i] == false){   // i가 소수이면
				if(i>=M)System.out.println(i);//출력도 한꺼번에 처리
				for(int temp=i*2;temp<=N; temp += i){
					numbers[temp] = true;
				}
			}
		}
	}

}