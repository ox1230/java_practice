package codeground;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/*
   As the name of the class should be Solution , using Solution.java as the filename is recommended.
   In any case, you can execute your program by running 'java Solution' command.
 */
class SCPC_Divisor {
	static int Answer;
	static HashMap<Integer , HashMap<Integer,Integer>> S = new HashMap<>();
	// {n : {a:b}}  = n을 소인수 분해하면 a가 b개 나온다.  
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
		
		HashMap cpS = S;
		int T = sc.nextInt();
		for(int test_case = 0; test_case < T; test_case++) {

			// Answer = 0;
			/////////////////////////////////////////////////////////////////////////////////////////////
			int N = sc.nextInt();		
			int M = sc.nextInt();
			ArrayList<Integer> As = new ArrayList<>();

			for(int i=0;i<N;i++){
				As.add(sc.nextInt());
				if(N <1000) getInsu(As.get(i));
			}

			int sum = 0;
			int b;
			int l,r;
			for(int i=0;i<M;i++){
				b = sc.nextInt();
				l =sc.nextInt();
				r =sc.nextInt();
				
				
				if(N> 1000){
					sum = +0;
				}
				else{
					int cnt = 1;
					HashMap<Integer,Integer> BB = getInsu(b);
					// BB의 전체 약수의수 -1
					HashSet<Integer> BBs = new HashSet<>();
					getAllDiv(BB,BBs);
					
					//
					
					// 
					for(int j=l;j<=l;j++){
						int a  = As.get(j);
						int Acnt = 1;
						HashMap <Integer,Integer> AA  = getInsu(a);
						
						for(int bb: BB.keySet()){
							if(AA.containsKey(bb)) Acnt *= AA.get(bb);
						}
						cnt -= (Acnt -1);
					}
					
					
					
					
					sum += cnt;
				}
			}
			Answer = sum;
			/////////////////////////////////////////////////////////////////////////////////////////////


			// Print the answer to standard output(screen).
			System.out.println("Case #"+(test_case+1));
			System.out.println(Answer);
		}
	}

	private static void getAllDiv(HashMap<Integer, Integer> bB, HashSet<Integer> bBs) {
		// TODO Auto-generated method stub
		int t =1;
		
		for(int i: bB.keySet()){
			int temp = t;
			
			for(int j=0; j<bB.get(i) ; j++){
				temp *= i;
				bBs.add(temp);
			}
		}
		
		
		
		
		
	}

	static HashMap<Integer,Integer> getInsu(int n){
		HashMap cpS = S;
		if(S.containsKey(n)) return S.get(n);
		
		HashMap<Integer, Integer> ret = new HashMap<>();
		
		int sqrtNum;        // 제곱근	
		sqrtNum = (int)Math.sqrt(n);
		
		int i= 2;
		for(;i <= sqrtNum; i++){
			if(n%i == 0){
				
				HashMap<Integer,Integer> temp = getInsu(n/i);
				
				ret.putAll(temp);
				ret.compute(i, (k, v) -> (v==null)? 1 : v+1);
				break;
			}
		}
		if(ret.isEmpty()){ // n은 소수
			ret.put(n,1);
		}
		
		S.put(n, ret);
		return ret;
	}
}