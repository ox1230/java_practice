package baekjoon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class B1766_TextBook {
	public static void main(String[] args) throws IOException{
		TextBook tb = new TextBook();
		tb.input();
		System.out.println(tb.startSolve());
	}
}

//	jumpitup82�� �� ���� ����
class TextBook{
	int N;
	int M;
	ArrayList<Integer>[] next; // next[i] : i���Ŀ� Ǯ����ϴ� ������
	int[] cntPre; // pre[i]: i ������ Ǯ����ϴ� �������� ����
	
	void input() throws IOException{
		Reader r = new Reader();
		
		N  = r.nextInt();
		M = r.nextInt();
		
		next = new ArrayList[N+1];
		cntPre = new int[N+1];
		
		for(int i=1;i<=N;i++){
			next[i] = new ArrayList<Integer>();
		}
		
		int p,n;
		for(int i=0;i<M;i++){
			p = r.nextInt();
			n = r.nextInt();
			
			next[p].add(n);
			cntPre[n]++;
		}
	}
	
	String startSolve(){
		PriorityQueue<Integer> save = new PriorityQueue<Integer>();
		StringBuffer ret = new StringBuffer();
		
		for(int i=1;i<=N;i++){   //�ٷ� Ǯ� �Ǵ� �������� save�� ����ִ´�.
			if(cntPre[i] == 0) save.add(i);
		}
		
		int me;
		while(!save.isEmpty()){
			me = save.poll();
			
			ret.append(me);
			ret.append(" ");
			
			for(int tempNext: next[me]){  // �� �ڿ� Ǯ��� �ϴ� �������� pre���� me�� ����
				cntPre[tempNext]--;
				if(cntPre[tempNext]==0) save.add(tempNext);
			}
		}
		
		return ret.toString();
	}
}