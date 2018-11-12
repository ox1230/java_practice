package sw_expert_academy;

import java.util.Scanner;

public class B01849_�������� {

	static int[][] parent;  // parent[i][0]: i���� ���ſ� �� �� ���� ���ſ� ��  , parent[i][1]: �� ������ ��������
	static int N;   /// ���� ����
	static int M;  // ���� ����

	static void add(int a, int b, int w) {
		int[] topA = findTop(a);
		int[] topB = findTop(b);
		
		if(topA[0] == a) {
			// ���� A�� top�̾����� 
			parent[a][0] = topB[0];
			parent[a][1] = topB[1] + w;
		}
		// a�� top�� �ƴϾ���,
		else if(topA[1] > topB[1] + w) {
			//A�� top�� B�� ���� ũ�� (A�� ��� ���)
			parent[topB[0]][0] = topA[0];
			parent[topB[0]][1] = topA[1] - (topB[1] + w); 
		}
		else {
			// B�� top�� A�� top���� ũ��
			parent[topA[0]][0] = topB[0];
			parent[topA[0]][1] = topB[1]+ w - topA[1];
		}
		
	}
	
	static int[] findTop(int a) {
		// a�� top��, �׶��� �������̸� ���Ѵ�.
		
		if(parent[a][0] == a) {
			// a�� top�̸� pass
		}
		else if( parent[parent[a][0]][0] == parent[a][0]) {
			// a�� top���� �˰� �ִ� ���� ��¥ top�̸�
		}
		else {
			// a�� top���� �˰� �ִ� ���� top�� �ƴϸ�
			int[] temp = findTop(parent[a][0]);
			
			parent[a][0] = temp[0];
			parent[a][1] += temp[1];
		}
		
		return parent[a];
	}
	

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int  T  = sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++) {

			N = sc.nextInt();
			M = sc.nextInt();

			System.out.print("#"+test_case);

			parent = new int[N+1][2];
			int[][] p = parent;
			
			for (int i = 1; i <= N; i++) {
				parent[i][0] = i;
			}
			
			for (int i = 0; i < M; i++) {
				String next = sc.next(); 

				if(next.equals("!")) {
					int a = sc.nextInt();
					int b = sc.nextInt();
					int w = sc.nextInt();
					
					add(a,b,w);
				}
				else if (next.equals("?")) {
					int a = sc.nextInt();
					int b = sc.nextInt();
					
					int[] topA = findTop(a);
					int[] topB = findTop(b);
					
					if(topA[0] != topB[0]) {
						System.out.print(" UNKNOWN");
					}
					else {
						System.out.print(" "+ (topA[1] - topB[1]));
					}
				}
			}
			System.out.println();
		}
	}
}



