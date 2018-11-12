package codemonster;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;



public class CableEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		File f = new File("input.txt");
		try {
			Scanner sc = new Scanner(f);
			FileWriter fw;
			try {
				fw = new FileWriter("result.txt");
				PrintWriter pw = new PrintWriter(fw);
				
				long C = sc.nextInt();
				for(long i=0;i<C;i++){
					Cable com = new  Cable();
					com.input(sc);
					pw.println(com.solve(sc));
				}
				pw.close();
				fw.close();
				sc.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

class Way{
	int from;
	int to;
	int v = 100000000;
	List<int[]> middle = new LinkedList<int[]>();
	List<Integer> already = new ArrayList<Integer>();
	
	Way(){};
	Way(int f,int t,int v){
		from = f;
		to = t;
		this.v = v;
		already.add(f);
		if(f!=t) already.add(t);
	}
	
	
	void setV(int v){
		if(this.v >v) this.v = v;
	}
	
	Way copy(){
		Way ret = new Way(from,to,v);
		//for(int i=0;i<middle.size();i++){
		//	ret.middle.add(middle.get(i).clone());
		//}
		for(int i=0;i<already.size();i++){
			ret.already.add(already.get(i));
		}
		
		return ret;
		
	}
	void toGo(int b, int v){
		to = b;
		
		setV(v);
		//already.add(b);
		//middle.add(new int[]{b,v});
	}
	
	void getVforMiddle(int m){}
	
}



class Cable{
	List<int[]>[] graph ;
	int N,M;
	//List<Way> saveAnsSet = new ArrayList<Way>();  //정답 Way를 저장한다.
	
	int min(int a,int b){return a>b ?b:a;}
	int max(int a,int b){return a<b ?b:a;}
	
	void input(Scanner sc){
		N = sc.nextInt();
		M = sc.nextInt();
		
		graph = new List[N+1];
		
		for(int i=1;i<N+1;i++){
			graph[i] = new ArrayList<int[]>();
		}
		
		for(int i=0;i<M;i++){
			int a = sc.nextInt();
			int b = sc.nextInt();
			int v = sc.nextInt();
			
			graph[a].add(new int[]{b,v});
			graph[b].add(new int[]{a,v});
		}
		
	}
	
	Way greedyWay(int a,int b){
		Stack<Way> save = new Stack<Way>();
		int v= 100000000;
		
		Way now = new Way(a,a,v);
		
		save.add(now);
		
		while(!save.isEmpty()){
			now = save.pop();
			//System.out.println("help me!");
			while(true){  //전진 할 수 있을 때까지 전진한다.
				int tempMaxTo = 0;
				int tempMaxV = 0;
				for(int[] temp: graph[now.to]){
					if(now.already.contains(temp[0])) continue; // now가 다음 갈 곳에 이미 갔었으면 pass
					
					if(tempMaxV < temp[1]){
						tempMaxV = temp[1];
						tempMaxTo = temp[0];
					}
				}
				
				if(tempMaxTo==0) break;
				else{
					now.already.add(tempMaxTo);
					save.push(now.copy());
					now.toGo(tempMaxTo, tempMaxV);
				//	System.out.println("Nope!");
					if(now.to== b) return now;
					
				}
				
			}
		}
		
		
		return null;
	}
	
	Way FMway(int a,int b){
		Stack<Way> save = new Stack<Way>();
		int maxV= greedyWay(a,b).v;
		System.out.println("good!");
		
		Way start = new Way(a,a,maxV);
		Way now;
		save.add(start);
		
		while(!save.isEmpty()){
			now = save.pop();
			
			while(true){  //전진 할 수 있을 때까지 전진한다.
				boolean isExist = false;
				for(int i=0;i < graph[now.to].size();i++){
					int[] temp =  graph[now.to].get(i);
					if(now.already.contains(temp[0])) continue; // now가 다음 갈 곳에 이미 갔었으면 pass
					
					if(maxV > temp[1]) continue;   //대역폭이 max보다 작으면 pass
					now.already.add(temp[0]);
					save.push(now.copy());
					now.toGo(temp[0], temp[1]);
				//	System.out.println("Nope!");
					isExist = true;
					break;  //조건을 통과했으므로 빠져나옴
					
				}
				
				if(!isExist) break; //갈곳이 없으면 뒤로 되돌아감
				
				if(now.to==b) return now;
			}
		}
		
		return start; //greedy가 가장좋음
	}


	int solve(Scanner sc){
		int Q = sc.nextInt();
		int sum=0;
		
		for(int i=0;i<Q;i++){
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			Way tempAnsSet = FMway(a,b);
			//System.out.println(tempAnsSet.v);
			sum += tempAnsSet.v;
		}
		
		return sum;
	}
}