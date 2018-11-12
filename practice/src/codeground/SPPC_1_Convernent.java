package codeground;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class SPPC_1_Convernent {
	static long Answer;

	static int N;
	static int M;

	static HashMap<Integer,Integer>[] tree;
	static HashMap<Integer,Boolean> diameter;  // 지름선상에 속한 노드들의 집합   + 다른 n들이 그 노드와 연결되는지의 여부체크
	static long diaDis = 0; // 지름의 길이

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
		for(int test_case = 0; test_case < T; test_case++) {

			// Answer = 0;
			/////////////////////////////////////////////////////////////////////////////////////////////

			N = sc.nextInt();
			M = sc.nextInt();

			int fr, to;
			int c;

			tree = new HashMap[M+1];

			for(int i=1;i<=M;i++){
				tree[i] = new HashMap<>();
			}

			for(int i=0;i< M-1;i++){
				fr =sc.nextInt();
				to =sc.nextInt();
				c = sc.nextInt();

				tree[fr].put(to, c);
				tree[to].put(fr, c);
			}
			if(N <= 10){
				// 1.트리의 지름을 구한다  (n1, n2선정)

				//노드 1에서 길이가 가장 긴 n1을 잦는다.
				Queue<long[]> save = new LinkedList<>();  //{노드, 길이}
				long max = 0; 
				int n1 = 1; // 트리의 지름의 양끝
				int n2;
				long[] t;
				save.add(new long[]{1,0});


				while(!save.isEmpty()){
					t = save.poll();

					Iterator<Entry<Integer,Integer>> iter = tree[(int)t[0]].entrySet().iterator();
					Entry<Integer,Integer> temp;

					while(iter.hasNext()){
						temp = iter.next();						
						int next = temp.getKey(); 
						long dis = temp.getValue() + t[1];

						if(next <= 2*N){  // leaf도착
							if(dis > max){
								max = dis;
								n1 = next;
							}
						}
						else{ // 중간 노드
							save.add(new long[]{next,dis});
						}
					}
				}
				// n1에서 거리가 가장 긴 반대쪽 노드를 구한다.
				HashMap<Integer,Boolean> tt;
				tt = new HashMap<Integer,Boolean>();
				tt.put(n1,false);
				boolean isKid  = n1 >N ? true: false;

				n2 = findN2(n1, tt, 0 ,0 , n1, isKid);

				//각 n에서 지름까지의 길이를 구한다
				long kid;
				long parent;
				long sum = 0;

				for(int n=1; n<=N;n++){

					kid = findToDia(N+n);
					parent = findToDia(n);
					if(kid == 0 || parent == 0) continue;
					sum += kid > parent ? kid : parent;
				}


				Answer = sum + diaDis;
				if(n1 == n2){  // 둘중 하나를 버려야 한다.
					// 시간 되면 ㄱ

				}

			}
			else{
				Answer = 0;
			}
			/////////////////////////////////////////////////////////////////////////////////////////////


			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}


	private static long findToDia(int n) {
		// 지름까지의 길이를 찾는다.
		if(diameter.containsKey(n)) return 0;

		long ret = 0;
		Queue<long[]> save = new LinkedList<>();  //{노드, 길이}
		long[] t;
		save.add(new long[]{n,0});


		bigLoop:
			while(!save.isEmpty()){
				t = save.poll();

				Iterator<Entry<Integer,Integer>> iter = tree[(int)t[0]].entrySet().iterator();
				Entry<Integer,Integer> temp;

				while(iter.hasNext()){
					temp = iter.next();						
					int next = temp.getKey(); 
					long dis = temp.getValue() + t[1];

					if(diameter.containsKey(next)){  // 지름 도착
						ret = dis;
						break bigLoop;
					}
					else{ // 중간 노드
						save.add(new long[]{next,dis});
					}
				}
			}

		return ret;
	}


	private static int findN2(int now, HashMap<Integer,Boolean> tt ,long dis, long max, int n2 , boolean isKid) {
		// TODO Auto-generated method stub
		Iterator<Entry<Integer,Integer>> iter = tree[now].entrySet().iterator();
		Entry<Integer,Integer> temp;


		while(iter.hasNext()){
			temp = iter.next();

			if(tt.containsKey(temp.getKey())) continue; // 이미 지나온 길

			if(isKid && temp.getKey() <= 2*N){
				// leaf도착
				if(dis + temp.getValue() > max){  // 새로운 n2탄생
					n2 = temp.getKey();
					max = dis + temp.getValue();
					diaDis = max;
					tt.put(n2,false);
					diameter = new HashMap<Integer,Boolean>(tt);

				}
				// 아니면 pass
			}
			else{  // 중간 노드라면
				tt.put(temp.getKey(), false);
				n2  = findN2(temp.getKey(), tt , dis+ temp.getValue(), max, n2 , isKid);
				tt.remove(temp.getKey());
			}
		}
		return n2;
	}

	private static long forVictim(int n) {
		// 지름까지의 길이를 찾는다.
		if(diameter.containsKey(n)) return 0;

		long ret = 0;
		Queue<long[]> save = new LinkedList<>();  //{노드, 길이}
		long[] t;
		save.add(new long[]{n,0});


		bigLoop:
			while(!save.isEmpty()){
				t = save.poll();

				Iterator<Entry<Integer,Integer>> iter = tree[(int)t[0]].entrySet().iterator();
				Entry<Integer,Integer> temp;

				while(iter.hasNext()){
					temp = iter.next();						
					int next = temp.getKey(); 
					long dis = temp.getValue() + t[1];

					if(diameter.containsKey(next)){  // 지름 도착
						ret = dis;
						break bigLoop;
					}
					else{ // 중간 노드
						save.add(new long[]{next,dis});
					}
				}
			}

		return ret;
	}



}