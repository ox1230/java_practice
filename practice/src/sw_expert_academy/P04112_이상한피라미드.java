package sw_expert_academy;

import java.util.Scanner;

public class P04112_�̻����Ƕ�̵� {
	public static void main(String args[]) throws Exception
	{
		/*
		   �Ʒ��� �޼ҵ� ȣ���� ������ ǥ�� �Է�(Ű����) ��� input.txt ���Ϸκ��� �о���ڴٴ� �ǹ��� �ڵ��Դϴ�.
		   �������� �ۼ��� �ڵ带 �׽�Ʈ �� ��, ���Ǹ� ���ؼ� input.txt�� �Է��� ������ ��,
		   �� �ڵ带 ���α׷��� ó�� �κп� �߰��ϸ� ���� �Է��� ������ �� ǥ�� �Է� ��� ���Ϸκ��� �Է��� �޾ƿ� �� �ֽ��ϴ�.
		   ���� �׽�Ʈ�� ������ ������ �Ʒ� �ּ��� ����� �� �޼ҵ带 ����ϼŵ� �����ϴ�.
		   ��, ä���� ���� �ڵ带 �����Ͻ� ������ �ݵ�� �� �޼ҵ带 ����ų� �ּ� ó�� �ϼž� �մϴ�.
		 */
		//System.setIn(new FileInputStream("res/input.txt"));

		/*
		   ǥ���Է� System.in ���κ��� ��ĳ�ʸ� ����� �����͸� �о�ɴϴ�.
		 */
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		int stairFinish[] = new int[150];
		
		stairFinish[1] = 1;
		
		for(int i = 2; i < 150; i++) {
			// ���� ���� 1��, �� ���� ���� ������ ��ȣ�� ǥ�� (10000�� 141���� ��ġ�Ѵ�)
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
			
			int aS = 0; // a�� ����
			int bS = 0; // b�� ����
			
			for (int i = 0; i < stairFinish.length; i++) {
				// a�� b�� ������ ���Ѵ�.
				
				if(aS == 0 && a <= stairFinish[i]) {
					aS = i;
				}
				if(bS == 0 && b <= stairFinish[i]) {
					bS = i;
					break;
				}
			}
			
			//a,b�� ���ʿ��� �󸶳� �������ִ��� Ȯ��(���� ������ 1)
			int aLeft = a - stairFinish[aS-1];
			int bLeft = b - stairFinish[bS-1];
			
			int dist = 0;
			
			// b�� a�� �������������ϴ� �ﰢ�� �ȿ� ��ġ�ϴ��� Ȯ���Ѵ�.
			if(bLeft < aLeft ){
				// b�� a���� �������� �ٱ��� ��ġ
				dist = (bS - aS) + (aLeft - bLeft);
			}
			else if(bLeft > aLeft + (bS-aS)  ) {
				// b�� a���� ���������� �ٱ��� ��ġ
				dist = bLeft - aLeft;
				//dist = (bS -aS)  + (bLeft - aLeft - (bS - aS));
			}
			else {
				//�ﰢ���ȿ� ��ġ
				dist = bS - aS;
			}
			
			System.out.println("#"+test_case + " "+ dist);
		}
	}
}
