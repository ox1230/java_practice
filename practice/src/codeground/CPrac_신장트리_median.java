package codeground;

import java.util.PriorityQueue;
import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class CPrac_신장트리_median {
	static int Answer;
	static int P[];

	public static void main(String args[]) throws Exception	{
		/*
		   The method below means that the program will read from input.txt, instead of standard(keyboard) input.
		   To test your program, you may save input data in input.txt file,
		   and call below method to read from the file when using nextInt() method.
		   You may remove the comment symbols(//) in the below statement and use it.
		   But before submission, you must remove the freopen function or rewrite comment symbols(//).
		 */		

		/*
		   Make new scanner from standard input System.in, and read data.
		 */
		Scanner sc = new Scanner(System.in);
		//Scanner sc = new Scanner(new FileInputStream("input.txt"));

		int T = sc.nextInt();
		
		int N,M;
		PriorityQueue<Edge> G = new PriorityQueue<>();  // 원래 그래프

		int[] cpP = P;
		
		for(int test_case = 0; test_case < T; test_case++) {
			
			N = sc.nextInt();
			M = sc.nextInt();
			G.clear();
			
			int s,t,c;
			
			for(int i=0;i<M;i++){
				s = sc.nextInt();
				t = sc.nextInt();
				c = sc.nextInt();
				
				G.offer(new Edge(s,t,c));
			}
			
			P = new int[N+1]; //각 vertex가 연결되었는지 확인
			for(int i=1;i<=N;i++){
				P[i]--;  //부모가 없으면 -1
			}
			
			int cnt= 0;
			Edge last = G.peek();
			
			while(cnt < N/2){
				last = G.poll();
				
				if(getRoot(last.u) == getRoot(last.v)){
					continue;  // 이미 연결되어 있음
				}
				else{
					union(last.u,last.v);
					cnt++;
				}
				
			}
			
			Answer = last.c;
			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}
	
	static class Edge implements Comparable<Edge>{
		int u;
		int v;
		int c;
		
		public Edge(int u, int v, int c) {
			super();
			this.u = u < v? u: v;
			this.v = u > v? u: v;
			this.c = c;
		}
		
		@Override
		public int compareTo(Edge arg0) {
			// TODO Auto-generated method stub
			if(c < arg0.c) return -1;
			else if(c > arg0.c) return 1;
 			return 0;
		}
	}
	
	
	static int getRoot(int v){
		if(P[v] == -1){
			return v;
		}
		else{
			P[v] = getRoot(P[v]);  // 부모를 root로 옮긴다.
			return P[v];
		}
	}
	
	static void union(int u, int v){  // u와 v의 집합을 하나로 합친다.
		int uR = getRoot(u);
		int vR = getRoot(v);
	
		P[uR] = vR;
	}
}