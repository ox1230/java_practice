package codeground;
/*
You should use the statndard input/output

in order to receive a score properly.

Do not use file input and output

Please be very careful. 
 */


import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class Cprac_대피소_2 {
	static int Answer;

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
		
		//다익스트라 알고리즘  + a - 각 정점을 시작점으로 하여 대피소에 도달할 때 까지 반복
		// 구한 최적 값으로 그래프를 업데이트 한다. 
		
		HashMap<Integer, Integer>[] G;  // graph 
		int N;
		int M;
		int K;
		HashSet<Integer> S;  // shelter들
		
		
		int[] dist;  // 시작 정점에서의 거리 
		boolean[] finish;  // 최단거리를 찾았는지 여부
		PriorityQueue<int[]> P;  // {최근 업데이트 거리, 노드}
		ForPP ff = new ForPP();
		
		int a1;   //답1
		int a2;    // 답2
		
		
		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {
			N = sc.nextInt();
			M = sc.nextInt();
			K = sc.nextInt();
			
			G = new HashMap[N+1];
			S = new HashSet<Integer>();
			
			for(int i=1;i<=N;i++){
				G[i] = new HashMap<Integer,Integer>();
				G[i].put(i, 0);
			}
			
			// input
			int f,t,c;
			for(int i=0;i<M;i++){
				f = sc.nextInt();
				t = sc.nextInt();
				c = sc.nextInt();
				
				G[f].put(t, c);
				G[t].put(f, c);
			}
			
			for(int i=0;i<K;i++){
				S.add(sc.nextInt());
			}
			
			// input 종료
			
			if(N > 300){  // pass
				a1 = 0;
				a2 = 0;
			}
			else{
			
				
				a1 = 0 ;
				a2 = 0 ;
				
				
				// 각vertex의  대피소를 구한다.
				for(int s=1 ; s<=N ; s++){
					P = new PriorityQueue<>(ff);
			
					P.add(new int[]{0,s});
					dist = new int[N+1];
					finish = new boolean[N+1];
					
					Arrays.fill(dist, 0x7FFFFFFF);
					dist[s] = 0;
					
					int to;
					int toDis; // s에서 to까지의 거리
					// s dijstra 실시
					di:
					while(!P.isEmpty()){
						to = P.poll()[1];
						toDis = dist[to];
						
						
						if(finish[to]) continue; //이미 했으면 pass
						finish[to] = true;
						if(G[to].containsKey(s)){
							G[to].replace(s, dist[to]);
							G[s].replace(to, dist[to]);
						}
						else{
							G[to].put(s, dist[to]);
							G[s].put(to, dist[to]);
						}
						
						
						if(S.contains(to)){  // 대피소를 찾으면 break.
							a1 += toDis;
							a2 += to;
							break di;
						}
						
						// s에서 to를 거쳐 dest로 가는 dist update
						for(int dest= 1; dest <=N; dest++){
							if(finish[dest]) continue;
							
							if(!G[to].containsKey(dest)) continue; // to에서 dest로의 길이 없으면
							
							if(dist[to] + G[to].get(dest) < dist[dest]){
								dist[dest] = dist[to] + G[to].get(dest);
								P.add(new int[]{dist[dest],dest});				
							}
						}
					}
				}
			}
			
			
			
		// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(a1);
			System.out.println(a2);
		}

	}
}


class ForPP implements Comparator<int[]>{

	@Override
	public int compare(int[] arg0, int[] arg1) {
		// TODO Auto-generated method stub
		
		if(arg0[0] > arg1[0]) return 1;
		else if(arg0[0] < arg1[0]) return -1;
		else{
			return arg0[1] - arg1[1];
		}
	}
	
}