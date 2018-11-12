package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OneTwoThree {
	public static void main(String[] args) throws IOException{
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		Sum123 s = new Sum123();
		
		int T = Integer.parseInt(sc.readLine());
		s.ans[0] = 1;  //�ƹ��� ���°��� �ƹ��� ���� ��� 1���� ���� �� �ִٰ� �����ִ� + ����� ���Ǹ� ���ؼ�
		s.ans[1] = 1;
		s.ans[2] = 2;
		s.ans[3] = 4;
		

		for(int i=0; i<T;i++){
			System.out.println(s.solveForOneCase(Integer.parseInt(sc.readLine())));
		}
	}
}

class Sum123{
	
	int[] ans = new int[11];

	
	int solveForOneCase(int n){
		if(n<0) return 0;
		
		else if(ans[n] != 0) return ans[n];
		else{
			int sum=0;
			sum += solveForOneCase(n/2)* solveForOneCase(n- n/2);  // �������� ��Ȯ�� ����
			sum += solveForOneCase(n/2-1) * solveForOneCase(n-n/2 -1); // �������� ������ ���̿� 2�� �������
			
			// �������� ������ ���̿� 3�� ���� ����
			sum += solveForOneCase(n/2-2) * solveForOneCase(n-n/2 -1);
			sum += solveForOneCase(n/2-1) * solveForOneCase(n-n/2 -2);
			
			ans[n] = sum;
			return sum;
		}
	}
}