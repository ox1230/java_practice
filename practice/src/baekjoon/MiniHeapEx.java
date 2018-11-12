package baekjoon;

import java.util.Scanner;

public class MiniHeapEx {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		int C = sc.nextInt();
		MiniHeap H = new MiniHeap(C);
		
		long getter;
		
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

class MiniHeap{
	long[] S;
	int LENGTH = 0;
	
	MiniHeap(int maxSize){
		S = new long[maxSize +1];
	}
	
	
	void add(long n){
		LENGTH++;
		S[LENGTH] = n;
		
		for(int i=LENGTH; i>0; i /=2){
			if(S[i] < S[i/2]){
				long temp = S[i];
				S[i] = S[i/2];
				S[i/2] = temp;
			}
			else{
				break;
			}
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
				if(child < LENGTH && S[child+1] < S[child]) child++;  //자기 형제노드와의 비교
				
				if(S[child] < S[parent]){ // 자식이 작으면 바꾸고 계속 진행
					long temp = S[child];
					S[child] = S[parent];
					S[parent] = temp;
				}
				else break; // 작지 않으면 loop종료
				
				parent = child;
				child *= 2;
			}
			
			return ret;
		}
		
	}
	
	
}
