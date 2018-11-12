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
	// n=5일때, 5보다 큰 수가 1개, 4보다 큰 수가 2개 ... 있어야 한다.
	// 각 숫자가 몇 있는지 개수를 세놓고, 그 수 보다 큰수가 몇개있는지 센다
	//  모든 i에 대해, i보다 큰 수가  n-i +1 개 있으면 TAK,  아니면 NIE이다.
	// i보다 큰 수가 n-i +1개 있지 않으면 그 수 i는 wrong에서 체크한다.
	
	// 숫자가 i에서 j로 바뀌면 i와 j사이 숫자의 sumofNumbers를 바꾼다.
	// 바뀐 sumOfNumbers가 n-i근처라면 wrong을 바꾼다. 
	
	int[] S;  // 처음 숫자 배열 저장
	int n; 
	int m;  
	int[] numbers;     //S에서 각 숫자의 수 체크
	int[] sumOfNumbers; // n보다 큰 수의 개수
	int wrong = 0 ;    // 숫자 i의 sumOfNumbers가 n-i+1보다 작은 숫자의 수

	void input(Scanner sc){

		n = sc.nextInt();
		S = new int[n+1];
		numbers = new int[n+1];
		sumOfNumbers = new int[n+1];
		int number;

		for(int i=1;i<=n;i++){
			number = sc.nextInt();

			S[i] = number;

			if(number < n){   // n보다 작으면 각 number 1씩 증가
				numbers[number]++;
			}
			else{   // n보다  크면 n 1증가
				numbers[n]++;
			}
		}
	}
	void firstCheck(){   // 첫번째 체크 + sum of numbers를 채움
		int sum=0;  // 숫자들개수의 합  -- 숫자 n보다 작거나 같은 숫자들의 개수   // 처음 시작
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
				if(sumOfNumbers[checkNum] == n-checkNum) wrong++;   // wrong에 추가
			}
		}
		else{
			;
		}
		
	}


	void solve(Scanner sc){
		m = sc.nextInt();

		int changeIndex;  // 이번에 바뀔 곳의 위치
		int changeNumber;   // 바뀌는 숫자
		int prevNumber;   // 바뀌기 이전 숫자
		
		if(wrong==0) System.out.println("TAK");   // wrong이 비어있으면 TAK
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
