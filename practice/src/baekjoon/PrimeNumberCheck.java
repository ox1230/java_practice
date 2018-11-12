package baekjoon;

import java.util.Scanner;

public class PrimeNumberCheck {
	public static void main(String[] args){
		Eratostenes   e = new Eratostenes();
		
		e.input();
		e.fillNumbers();
	}
}

class Eratostenes{ // �����佺�׳׽�?? �� ü
	int M,N;
	boolean[] numbers;  // ����� �ִ��� üũ, ������ true
	
	
	void input(){
		Scanner sc = new Scanner(System.in);
		
		M = sc.nextInt();
		N = sc.nextInt();
		
		numbers = new boolean[N+1];
		
		sc.close();
	}
	
	void fillNumbers(){  
		
		for(int i=2;i<=N;i++){
			if(numbers[i] == false){   // i�� �Ҽ��̸�
				if(i>=M)System.out.println(i);//��µ� �Ѳ����� ó��
				for(int temp=i*2;temp<=N; temp += i){
					numbers[temp] = true;
				}
			}
		}
	}

}