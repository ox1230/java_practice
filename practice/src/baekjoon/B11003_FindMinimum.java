package baekjoon;

import java.io.IOException;


public class B11003_FindMinimum {
	public static void main(String[] args) throws IOException{
		FindMinimum f = new FindMinimum();
		f.input();
		System.out.println(f.startSolve());
		
	}
}

//heap�� ���
class FindMinimum{
	final int MAX_NUM = 1000000000 +1;
	int N;
	int L;
	int[] S;
	
	int[] sol;
		
	int atom; // �Ѵ���
	int half; // �߰� 
	int time; // �Ѵ����� ��� �ؾ� �ϴ���
	
	
	// http://pgr21.com/pb/pb.php?id=freedom&no=43185 ������ ���
	void input() throws IOException{
		Reader r = new Reader();
		
		N = r.nextInt();
		L = r.nextInt();
		
		atom = 2*L -1; 
		half = atom/2; 
		time = N/L +1; 
		
		S = new int[atom *(N/atom +2) +1];  // 1���� ����
		
		int i=1;
		
		int firstNum  = r.nextInt();
		for(;i<=L;i++){
			S[i] = firstNum;
		}
		
		//�̸� ù��° ���ڸ� L-1�� �� ����ִ´�.
		for( ; i < N+L;i++){
			S[i] = r.nextInt();
		}
		
	
	}
		
	String startSolve(){
		StringBuffer ret = new StringBuffer();
		
		for(int i=N+L+1; i< S.length; i++){
			S[i] = MAX_NUM;
		}
		

		sol = new int[atom];  //0���� atom-1���� 
		
		int index = 1;
		bigLoop:
		for(int t=0;t<time;t++){
			
			int min = MAX_NUM;
			
			for(int i=half;i>=0;i--){
				if(S[index+i] <min) min = S[index+i]; 
				
				sol[i] = min;
			}
			
			min = sol[half];
			for(int i=half+1; i<atom;i++){
				if(S[index+i] <min) min = S[index+i]; 
				
				sol[i] = min;
			}
			
			for(int i=0;i<L;i++){
				ret.append(Math.min(sol[i],sol[i + L-1]));
				ret.append(" ");
				if(index + i == N ) break bigLoop;
			}
			
			
			index += L;
		}
		
		
		
		return ret.toString();
	}
}
