package sw_expert_academy;

import java.util.ArrayList;
import java.util.Scanner;

public class P05550_croak {
	public static void main(String[] args) {
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
		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		 */
		sc.nextLine(); //
		for(int test_case = 1; test_case <= T; test_case++)
		{
			//Frog가 들어있는 arrayList를 만든다. input을 받고, 각 글씨별로 가능한 Frog를 확인한다. 없으면 -1, 출력

			// 글자를 받는다.
			String target = sc.nextLine();

			ArrayList<Frog> pond = new ArrayList<>();  // 개구리를 저장한다.

			//첫번째 글자 처리
			char c = target.charAt(0);
			int cnt = -1;
			if (c != 'c') {
				//아무것도 안함
			}
			else {
				// 있으면
				pond.add(new Frog());
				cnt = 1;
				bigLoop:
					for(int i =1; i < target.length(); i++) {
						//가능한게 있는지 확인
						c = target.charAt(i);
						int flag = 0;  // 반응한 것이 있으면 1증가
						for(Frog temp: pond) {
							if(temp.isOK(c)) {
								temp.goNextStep();
								flag++;
								break;
							}
							else {
								//그냥 넘어감
							}
						}

						// 전혀 없으면 불가능하므로 종료
						if(flag == 0 && c != 'c') {
							cnt = -1;
							break bigLoop;
						}
						else if(flag == 0 && c == 'c') {
							pond.add(new Frog());
							cnt++;
						}
						else {
							// 가능한 것이 있음, 다음으로 넘어감
						}
					}
			}

			// 모든 개구리가 croak으로 제대로 끝냈는지 확인한다.
			for (Frog frog : pond) {
				if(!frog.isOK('c')){  // k로 끝나지 않으면
					cnt = -1;
				}
			}
			
			System.out.println("#"+ test_case+ " "+cnt);

		}
	}
}

class Frog{
	char stat = 'c';

	boolean isOK(char c) {
		//c가 다음 위치인지 확인한다. (즉, 현재 stat이 c인데, r이 들어왔으면 true, 아니면 False
		boolean ret = false;
		switch (stat) {
		case 'c': 
			ret = c == 'r'? true: false;
			break;
		case 'r': 
			ret = c == 'o'? true: false;
			break;
		case 'o': 
			ret = c == 'a'? true: false;
			break;
		case 'a': 
			ret = c == 'k'? true: false;
			break;
		case 'k': 
			ret = c == 'c'? true: false;
			break;
		default:
			break;
		}

		return ret;
	}

	void goNextStep() {
		// stat을 다음 단계로 이동시킨다. 
		switch (stat) {
		case 'c': 
			stat = 'r';
			break;
		case 'r': 
			stat = 'o';
			break;
		case 'o': 
			stat = 'a';
			break;
		case 'a': 
			stat = 'k';
			break;
		case 'k': 
			stat = 'c';
			break;
		default:
			break;
		}
	}
}

