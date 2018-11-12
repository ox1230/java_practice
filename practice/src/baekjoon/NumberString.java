package baekjoon;

import java.util.Scanner;

public class NumberString{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		SolveString s = new SolveString();

		s.input(sc);
		s.firstCheck();
		s.solve(sc);
	}
}

class SolveString{
	// n=5�϶�, 5���� ū ���� 1��, 4���� ū ���� 2�� ... �־�� �Ѵ�.
	// �� ���ڰ� �� �ִ��� ������ ������, �� �� ���� ū���� ��ִ��� ����
	//  ��� i�� ����, i���� ū ����  n-i +1 �� ������ TAK,  �ƴϸ� NIE�̴�.
	// i���� ū ���� n-i +1�� ���� ������ �� �� i�� wrong���� üũ�Ѵ�.
	
	// ���ڰ� i���� j�� �ٲ�� i�� j���� ������ sumofNumbers�� �ٲ۴�.
	// �ٲ� sumOfNumbers�� n-i��ó��� wrong�� �ٲ۴�. 
	
	int[] S;  // ó�� ���� �迭 ����
	int n; 
	int m;  
	int[] numbers;     //S���� �� ������ �� üũ
	int[] sumOfNumbers; // n���� ū ���� ����
	int wrong = 0 ;    // ���� i�� sumOfNumbers�� n-i+1���� ���� ������ ��

	void input(Scanner sc){

		n = sc.nextInt();
		S = new int[n+1];
		numbers = new int[n+1];
		sumOfNumbers = new int[n+1];
		int number;

		for(int i=1;i<=n;i++){
			number = sc.nextInt();

			S[i] = number;

			if(number < n){   // n���� ������ �� number 1�� ����
				numbers[number]++;
			}
			else{   // n����  ũ�� n 1����
				numbers[n]++;
			}
		}
	}
	void firstCheck(){   // ù��° üũ + sum of numbers�� ä��
		int sum=0;  // ���ڵ鰳���� ��  -- ���� n���� �۰ų� ���� ���ڵ��� ����   // ó�� ����
		int cnt=1;   
		
		
		for(int checkNumber= n ; checkNumber>0 ;checkNumber--){  
			sum += numbers[checkNumber];
			sumOfNumbers[checkNumber] = sum;
			if(sum < cnt){
				wrong++;
			}
			else if(sum == 0){
				wrong++;
			}
			
			cnt++;
		}
	}
	
	
	
	void changeSumOfNumbers(int prevNum, int changeNum){ 
		if(changeNum > n) changeNum = n;
		
		if(prevNum < changeNum){
			for(int checkNum = prevNum+1; checkNum <= changeNum; checkNum++){
				sumOfNumbers[checkNum]++;
				if(sumOfNumbers[checkNum] == n-checkNum + 1)wrong--;
				
			}
		}
		else if(prevNum > changeNum){
			for(int checkNum = changeNum+1; checkNum <= prevNum; checkNum++){
				sumOfNumbers[checkNum]--;
				if(sumOfNumbers[checkNum] == n-checkNum) wrong++;   // wrong�� �߰�
			}
		}
		else{
			;
		}
		
	}


	void solve(Scanner sc){
		m = sc.nextInt();

		int changeIndex;  // �̹��� �ٲ� ���� ��ġ
		int changeNumber;   // �ٲ�� ����
		int prevNumber;   // �ٲ�� ���� ����
		
		if(wrong==0) System.out.println("TAK");   // wrong�� ��������� TAK
		else System.out.println("NIE");

		for(int i=0;i<m;i++){
			changeIndex = sc.nextInt();
			changeNumber = sc.nextInt();


			prevNumber = S[changeIndex];
			S[changeIndex] = changeNumber;
			
			changeSumOfNumbers(prevNumber,changeNumber);

			if(wrong==0) System.out.println("TAK");
			else System.out.println("NIE");

		}
	}
	
	
}
