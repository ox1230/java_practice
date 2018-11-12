package sw_expert_academy;

import java.util.Scanner;

public class P04112_이상한피라미드 {
	public static void main(String args[]) throws Exception
	{
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
		//System.setIn(new FileInputStream("res/input.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		int stairFinish[] = new int[150];
		
		stairFinish[1] = 1;
		
		for(int i = 2; i < 150; i++) {
			// 가장 위가 1층, 각 층의 가장 마지막 번호를 표시 (10000은 141층에 위치한다)
			stairFinish[i] = stairFinish[i-1] + i;	
		}
		
		
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			if(a>b) {
				int temp = a;
				a = b;
				b = temp;
			}
			
			int aS = 0; // a의 층수
			int bS = 0; // b의 층수
			
			for (int i = 0; i < stairFinish.length; i++) {
				// a와 b의 층수를 구한다.
				
				if(aS == 0 && a <= stairFinish[i]) {
					aS = i;
				}
				if(bS == 0 && b <= stairFinish[i]) {
					bS = i;
					break;
				}
			}
			
			//a,b가 왼쪽에서 얼마나 떨어져있는지 확인(가장 왼쪽이 1)
			int aLeft = a - stairFinish[aS-1];
			int bLeft = b - stairFinish[bS-1];
			
			int dist = 0;
			
			// b가 a를 위꼭지점으로하는 삼각형 안에 위치하는지 확인한다.
			if(bLeft < aLeft ){
				// b가 a보다 왼쪽으로 바깥에 위치
				dist = (bS - aS) + (aLeft - bLeft);
			}
			else if(bLeft > aLeft + (bS-aS)  ) {
				// b가 a보다 오른쪽으로 바깥에 위치
				dist = bLeft - aLeft;
				//dist = (bS -aS)  + (bLeft - aLeft - (bS - aS));
			}
			else {
				//삼각형안에 위치
				dist = bS - aS;
			}
			
			System.out.println("#"+test_case + " "+ dist);
		}
	}
}
