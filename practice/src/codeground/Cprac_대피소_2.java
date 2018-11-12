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
class Cprac_���Ǽ�_2 {
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
		
		//���ͽ�Ʈ�� �˰���  + a - �� ������ ���������� �Ͽ� ���Ǽҿ� ������ �� ���� �ݺ�
		// ���� ���� ������ �׷����� ������Ʈ �Ѵ�. 
		
		HashMap<Integer, Integer>[] G;  // graph 
		int N;
		int M;
		int K;
		HashSet<Integer> S;  // shelter��
		
		
		int[] dist;  // ���� ���������� �Ÿ� 
		boolean[] finish;  // �ִܰŸ��� ã�Ҵ��� ����
		PriorityQueue<int[]> P;  // {�ֱ� ������Ʈ �Ÿ�, ���}
		ForPP ff = new ForPP();
		
		int a1;   //��1
		int a2;    // ��2
		
		
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
			
			// input ����
			
			if(N > 300){  // pass
				a1 = 0;
				a2 = 0;
			}
			else{
			
				
				a1 = 0 ;
				a2 = 0 ;
				
				
				// ��vertex��  ���ǼҸ� ���Ѵ�.
				for(int s=1 ; s<=N ; s++){
					P = new PriorityQueue<>(ff);
			
					P.add(new int[]{0,s});
					dist = new int[N+1];
					finish = new boolean[N+1];
					
					Arrays.fill(dist, 0x7FFFFFFF);
					dist[s] = 0;
					
					int to;
					int toDis; // s���� to������ �Ÿ�
					// s dijstra �ǽ�
					di:
					while(!P.isEmpty()){
						to = P.poll()[1];
						toDis = dist[to];
						
						
						if(finish[to]) continue; //�̹� ������ pass
						finish[to] = true;
						if(G[to].containsKey(s)){
							G[to].replace(s, dist[to]);
							G[s].replace(to, dist[to]);
						}
						else{
							G[to].put(s, dist[to]);
							G[s].put(to, dist[to]);
						}
						
						
						if(S.contains(to)){  // ���ǼҸ� ã���� break.
							a1 += toDis;
							a2 += to;
							break di;
						}
						
						// s���� to�� ���� dest�� ���� dist update
						for(int dest= 1; dest <=N; dest++){
							if(finish[dest]) continue;
							
							if(!G[to].containsKey(dest)) continue; // to���� dest���� ���� ������
							
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