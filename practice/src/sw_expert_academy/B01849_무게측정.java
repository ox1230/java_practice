package sw_expert_academy;

import java.util.Scanner;

public class B01849_무게측정 {

	static int[][] parent;  // parent[i][0]: i보다 무거운 놈 중 가장 무거운 놈  , parent[i][1]: 둘 사이의 무게차이
	static int N;   /// 샘플 종류
	static int M;  // 일의 개수

	static void add(int a, int b, int w) {
		int[] topA = findTop(a);
		int[] topB = findTop(b);
		
		if(topA[0] == a) {
			// 원래 A가 top이었으면 
			parent[a][0] = topB[0];
			parent[a][1] = topB[1] + w;
		}
		// a가 top이 아니었고,
		else if(topA[1] > topB[1] + w) {
			//A의 top이 B의 보다 크면 (A에 모두 흡수)
			parent[topB[0]][0] = topA[0];
			parent[topB[0]][1] = topA[1] - (topB[1] + w); 
		}
		else {
			// B의 top이 A의 top보다 크면
			parent[topA[0]][0] = topB[0];
			parent[topA[0]][1] = topB[1]+ w - topA[1];
		}
		
	}
	
	static int[] findTop(int a) {
		// a의 top과, 그때의 무게차이를 구한다.
		
		if(parent[a][0] == a) {
			// a가 top이면 pass
		}
		else if( parent[parent[a][0]][0] == parent[a][0]) {
			// a가 top으로 알고 있는 것이 진짜 top이면
		}
		else {
			// a가 top으로 알고 있는 것이 top이 아니면
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



