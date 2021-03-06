package baekjoon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;



public class B02230_수고르기 {
	public static void main(String[] args) throws IOException{
		NuM n = new NuM();
		
		n.input();
		System.out.println(n.solve());
	}
}


class NuM{
	int N;
	int M;
	LinkedList<Integer> A = new LinkedList<>();
	
	void input() throws IOException{
		Reader r = new Reader();
		
		N = r.nextInt();
		M = r.nextInt();
		
		for(int i=0;i<N;i++){
			A.add(r.nextInt());   
		}
	}
	
	int solve(){
		Collections.sort(A);
		
		ArrayList<Integer> B = new ArrayList<>();
		//B:  B[i] = A[i+1] - A[i]:  A의 각 원소별 차이만 뽑아와서 쉽게 비교
		
		Iterator<Integer> iter1 = A.iterator();
		Iterator<Integer> iter2 = A.iterator();
		
		iter1.next();
		
		while(iter1.hasNext()){
			B.add(iter1.next() - iter2.next());
		}
		
		
		// B의 원소를 조합해 M보다 크지만 가장 작은 수를 찾는다.
		
		int min = Integer.MAX_VALUE;
		
		int j = 0;
		int sum =0;
		
		loop:
		for(int i=0; i<N-1;i++){  // i부터 하나씩 최적 min을 찾는다. 
			
			if(j < i) j = i;
			while(sum < M && j < N-1){
				sum += B.get(j);
				 j++;
			}
			
			if(sum >= M && sum < min) min = sum;
			sum -= B.get(i);
		}
		
		return min;
		
	}
	
	
	
}