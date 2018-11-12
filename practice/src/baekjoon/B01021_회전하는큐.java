package baekjoon;

import java.util.Scanner;

public class B01021_ȸ���ϴ�ť {
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
			// �츮�� ť�� index�� 0���� �����Ѵ�.
		}
		
	}
	public int solve() {
		// TODO Auto-generated method stub
		int cnt  = 0;     // 2,3�� ����� ��
		int head = 0; // ť�� 1��° index�� �ִ�  ó�� ��ġ.    ť�� index: [0 ~ N-1]
		int nowSize = N;  // ���� ť�� ����.
		
		int disappear[] = new int[N];  
		// ������ �ʾ����� 0, �������� 1
		int target;  // �̹��� ���� ���� ó�� ��ġ.
		int tempHead;  // head
		int nextHead;  // tempHead�� �ٷ� ����.
		int tempCnt = 0;
	
		for (int i = 0; i < orders.length; i++) {
			target = orders[i];
			tempCnt = 0;
			// head�� target�� ������ ������ �������� �̵���Ų��.   => Ƚ��: tempCnt
			//  ���������� �̵���Ű�� Ƚ���� nowSize - tempCnt
			// target�� ������ �ٷ� ������ ���� ���� ������ head�� �ȴ�. (target�� Q���� ���������Ƿ�)
			
			tempHead = head;
			nextHead = tempHead;
			
			while(true){
				tempHead = (N + tempHead -1) % N ;  // �������� ��ĭ�̵�  --   0���� N-1�� ���� �͵� ���
				
				if(tempHead == target) break;
				// ���ϴ� ��ġ�� ����������
				
				
				if(disappear[tempHead]  == 0){  // tempHead�� ���� �����ִ� ���̶��
					nextHead = tempHead;
					tempCnt++;
				}
			}
			
			tempCnt++; // ������ �̵� ����� ī��Ʈ���� �ʾұ� ������ 1�߰��Ѵ�.
			
			//
			cnt +=  tempCnt < (nowSize - tempCnt) ? tempCnt : (nowSize - tempCnt);
			
			// �������� head����;
			head = nextHead;
			nowSize--;
			disappear[target] = 1;
	
		}
		
		return cnt;
	}
	
	
	
	
	
	
	
}