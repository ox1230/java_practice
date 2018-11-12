package codeground;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Scanner;


/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class SPPC_1_Convernent2 {
	static long Answer;

	static int N;
	static int M;

	static HashMap<Integer,Integer>[] tree;   //tree[i].get(j) = i에서 j로의 cost
	static boolean[] checked;

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
				checked = new boolean[M+1];
				Iterator<Integer> iter;
				
				HashMap<Integer,Integer>[] Ttree = tree;   //tree[i].get(j) = i에서 j로의 cost
				 boolean[] Cchecked = checked;

				
				
				iter = tree[1].keySet().iterator();
			
				int t= iter.next();
				checked[t] = true;
				Answer = eachN(2, tree[1].get(t));
				checked[t] = false;

				//1번은 배아를 데려감
				iter = tree[N+1].keySet().iterator();
				t= iter.next();
				checked[t] = true;

				long temp = eachN(2, tree[N+1].get(t));
			
				Answer = Answer > temp ? Answer : temp;
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

	private static long eachN(int i, long div) {
		// TODO Auto-generated method stub
		HashMap<Integer,Integer>[] Ttree = tree;   //tree[i].get(j) = i에서 j로의 cost
		 boolean[] Cchecked = checked;
		// 1 ~N까지, 어른과 배아를 각각 데려가는 div를 구해 더 높은 div를 선택한다.
		if(i  > N){
			return div;
		}
		else{
			
			// 1번은 어른을 데려감	
			boolean[] visited = new boolean[M+1];
			LinkedList<Integer> forChecked1 = new LinkedList<>();
			
			long toto = toSub(i, forChecked1 ,visited, 0);
			
			for(int v: forChecked1){
				checked[v] = true;
			}
			
			long temp1 = eachN(i+1, div + toto );

			for(int v: forChecked1){
				checked[v] = false;
			}
			
			//1번은 배아를 데려감
			visited = new boolean[M+1];
			LinkedList<Integer> forChecked2 = new LinkedList<>();
			
			toto = toSub(N+i, forChecked2 ,visited, 0);
		
			for(int v: forChecked2){
				checked[v] = true;
			}
		
			long temp2 = eachN(i+1, div + toto ); 
			
			for(int v: forChecked2){
				checked[v] = false;
			}
			
			return temp1 > temp2 ? temp1 : temp2 ;
		}

	}

	private static long toSub(int i, LinkedList<Integer> forChecked,  boolean[] visited, int div) {
		// TODO Auto-generated method stub
		HashMap<Integer,Integer>[] Ttree = tree;   //tree[i].get(j) = i에서 j로의 cost
		 boolean[] Cchecked = checked;
		 
		if(checked[i]){
			if(!forChecked.isEmpty()) forChecked.removeLast();  // i는 원래 checked이므로 제거한다. 
			return div;
		}
		else{
			long ret = 0;
			visited[i] = true;
			for(Entry<Integer,Integer> ee : tree[i].entrySet()){
				if(visited[ee.getKey()]) continue;
				
				forChecked.add(ee.getKey());
				ret = toSub(ee.getKey(), forChecked, visited, div + ee.getValue());
				
				if(ret > 0) break;
				forChecked.removeLastOccurrence(ee.getKey());  
			}
			return ret;
		}
		

	}

	
}
