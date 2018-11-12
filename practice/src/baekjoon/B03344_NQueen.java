package baekjoon;

import java.io.IOException;
import java.util.ArrayList;


public class B03344_NQueen {
	public static void main(String[] args) throws IOException{
		Queens q = new Queens();
		q.input();
		
		System.out.println(q.solve());
		
	}
}

class Queens{
	StringBuffer S = new StringBuffer();  // 정답저장 & 출력 -- col과 같음
	ArrayList<Boolean> slash = new ArrayList<>();   // /대각선
	ArrayList<Boolean> bslash  = new ArrayList<>();  // \대각선
	ArrayList<Boolean> col = new ArrayList<>(); // 세로
	// 바로 출력한다. (각 숫자 사이에 \n을 넣어준다. 
	int N;
	
	void input() throws IOException{
		Reader r = new Reader();
		
		
	
		
		N = r.nextInt();
	}
	
	
	String solve(){
		// 초기화
		for(int n=0; n<N;n++){
			S.append('0');
			S.append('\n');
			
			slash.add(false);
			bslash.add(false);
			slash.add(false);
			bslash.add(false);
			
			col.add(false);
		}
		
		nQueen(-2);  // nQueen이 n+2이므로 -2부터 시작해야 index0을 채울수 있다.
		
		return S.toString();
	}
	
	boolean nQueen(int n){
		//경우의 수가 1가지 나오면 즉시 종료한다. 
		if(n == 2*(N-1)){
			return true;
		}
		else{
			boolean temp = false;
			
			for(int k=0 ; k<N ; k++){
				
				if(promising(n+2, k) == false) continue; // 해가 될 수 없으면 pass
				
				//해가 될 수 있으면
				S.setCharAt(n+2, (char)('0'+k+1));   //출력 행번호는 1부터 시작...
				int trow = (n+2)/2;
				col.set(k, true);
				slash.set(trow + k, true);
				bslash.set(trow - k + (N-1), true);
				
				temp = nQueen(n+2); // enter는 건너뛰고 그다음 인덱스를 확인해야 한다.
				if(temp == true) break; // 경우의 수를 하나 찾았으면 종료
				
				// 아니면 원상복귀
				col.set(k , false);
				slash.set(trow + k, false);
				bslash.set(trow - k + (N-1), false);
			}
			
			return temp;  // 한번이라도 true가 있었으면 true, 아니면 false
		}
	}
	
	boolean promising(int n, int c){
		// n+2에 c를 넣는 것이 해가 될 수 있는 지 확인
		
		if(col.get(c)) return false;
		if(slash.get((n/2) + c)) return false;
		if(bslash.get((n/2) - c + (N-1) )) return false;
		
		return true;
		
	}
	
	
}

