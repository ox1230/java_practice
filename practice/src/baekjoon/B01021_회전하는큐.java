package baekjoon;

import java.util.Scanner;

public class B01021_회전하는큐 {
	public static void main(String[] args) {
		RotatingQ rq = new RotatingQ();
		
		rq.input();
		System.out.println(rq.solve());
		
	}
}


class RotatingQ{
	int N;
	int M;
	
	int orders[];
	
	public void input()  {
		// TODO Auto-generated method stub
		Scanner rd = new Scanner(System.in);
		
		N = rd.nextInt();
		M = rd.nextInt();
		
		orders = new int[M];
		
		for (int i = 0; i < M; i++) {
			orders[i] = rd.nextInt() - 1;
			// 우리의 큐는 index가 0부터 시작한다.
		}
		
	}
	public int solve() {
		// TODO Auto-generated method stub
		int cnt  = 0;     // 2,3번 명령의 수
		int head = 0; // 큐의 1번째 index가 있는  처음 위치.    큐의 index: [0 ~ N-1]
		int nowSize = N;  // 현재 큐의 길이.
		
		int disappear[] = new int[N];  
		// 빼내지 않았으면 0, 빼냈으면 1
		int target;  // 이번에 빼낼 것의 처음 위치.
		int tempHead;  // head
		int nextHead;  // tempHead의 바로 전값.
		int tempCnt = 0;
	
		for (int i = 0; i < orders.length; i++) {
			target = orders[i];
			tempCnt = 0;
			// head를 target에 도달할 때까지 왼쪽으로 이동시킨다.   => 횟수: tempCnt
			//  오른쪽으로 이동시키는 횟수는 nowSize - tempCnt
			// target을 만나기 바로 직전의 것이 다음 차례의 head가 된다. (target은 Q에서 빠져나가므로)
			
			tempHead = head;
			nextHead = tempHead;
			
			while(true){
				tempHead = (N + tempHead -1) % N ;  // 왼쪽으로 한칸이동  --   0에서 N-1로 가는 것도 고려
				
				if(tempHead == target) break;
				// 원하는 위치에 도달했으면
				
				
				if(disappear[tempHead]  == 0){  // tempHead가 아직 남아있는 것이라면
					nextHead = tempHead;
					tempCnt++;
				}
			}
			
			tempCnt++; // 마지막 이동 명령은 카운트하지 않았기 때문에 1추가한다.
			
			//
			cnt +=  tempCnt < (nowSize - tempCnt) ? tempCnt : (nowSize - tempCnt);
			
			// 빼냈으면 head변경;
			head = nextHead;
			nowSize--;
			disappear[target] = 1;
	
		}
		
		return cnt;
	}
	
	
	
	
	
	
	
}