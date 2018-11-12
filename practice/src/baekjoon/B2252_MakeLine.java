package baekjoon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class B2252_MakeLine{
	public static void main(String[] args) throws IOException{
		MakeLine m = new MakeLine();
		m.input();
		System.out.println(m.startSolve());
	}
}

class MakeLine{
	int N;
	int M;
	
	LinkedList<Integer>[] smaller;  //smaller[i] : i�տ� �־���ϴ� �͵�
	ArrayList<Integer>[] taller;  // taller[i] : i�ڿ� �־�� �ϴ� �͵�	
	
	void input() throws IOException{
		Reader r =new Reader();
		
		N = r.nextInt();
		M = r.nextInt();
		
		smaller = new LinkedList[N+1];
		taller = new ArrayList[N+1];
		
		for(int i=1;i<=N;i++){
			smaller[i] = new LinkedList<Integer>();
			taller[i] = new ArrayList<Integer>();
		}
		
		int previ , next;
		for(int i=0;i<M;i++){
			previ = r.nextInt();
			next = r.nextInt();
			
			smaller[next].add(previ);
			taller[previ].add(next);
		}
	}
	
	
	String startSolve(){
		ArrayList<Integer> order = new ArrayList<Integer>();
		Queue<Integer> save = new LinkedList<Integer>();
		
		for(int t=1;t<=N;t++){
			if(smaller[t].isEmpty()){
				order.add(t);
				save.add(t);
			}
		}
		// order�� �־��� ��� ��ġ
		
		int start;
		while(!save.isEmpty()){
			start = save.poll();
			
			for(int temp: taller[start]){
				smaller[temp].removeFirstOccurrence(start);
				
				if(smaller[temp].isEmpty() == true){
					order.add(temp);
					save.add(temp);
				}
			}
		}
		
		// ���ڿ� ��ȯ
		StringBuffer ret = new StringBuffer();
		
		for(int temp: order){
			ret.append(temp+" ");
		}
	
		return ret.toString();
	}
}