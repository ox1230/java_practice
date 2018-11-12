package baekjoon;

import java.util.Scanner;

public class MaxHeapEx {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		int C = sc.nextInt();
		MaxHeap H = new MaxHeap(C);
		
		long getter=0;
		
		for(int i=0;i<C;i++){
			getter = sc.nextLong();
			
			if(getter==0){
				System.out.println(H.remove());
			}
			else{
				H.add(getter);
			}
		}
		sc.close();
		
	}
}

class MaxHeap{
	long[] S;
	int LENGTH = 0;
	
	MaxHeap(int maxSize){
		S = new long[maxSize +1];
	}
	
	
	void add(long n){
		LENGTH++;
		S[LENGTH] = n;
		
		int parent = LENGTH/2;
		int child = LENGTH;
		
		while(parent >0){
			if(S[child] > S[parent]){  // child가 더 크면 바꿈
				long temp = S[child];
				S[child] = S[parent];
				S[parent] = temp;
			}
			else break;
			
			child = parent ;
			parent /= 2;
		}
		
	}
	
	long remove(){
		if(LENGTH == 0 ) return 0;
		else{
			long ret = S[1];
			
			S[1] = S[LENGTH];
			LENGTH--;
			
			int parent = 1;
			int child = 2;
			
			while(child<=LENGTH){
				if(child < LENGTH && S[child+1] > S[child]) child++;  //자기 형제노드와의 비교
				
				if(S[child] > S[parent]){ // 자식이 크면 바꾸고 계속 진행
					long temp = S[child];
					S[child] = S[parent];
					S[parent] = temp;
				}
				else break; // 크지 않으면 loop종료
				
				parent = child;
				child *= 2;
			}
			
			return ret;
		}
		
	}
	
	
}
