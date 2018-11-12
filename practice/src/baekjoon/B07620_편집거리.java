package baekjoon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class B07620_편집거리 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		EditDis ee = new EditDis();
		ee.input();
		
		ee.solve();
		System.out.println(ee.findOp());
	}

}


class EditDis{
	String S; // String의 index는 0부터 시작
	String T;
	int m;
	int n;
	ArrayList<ArrayList<Integer>> E = new ArrayList<>();  // solving arrray
	//E[i][j] : S의 i번째 글자까지의 string을 T의 j번째 글자까지의  string으로 바꾸는 최소편집거리
	ArrayList<ArrayList<Character>> P = new ArrayList<>();
	// E로 온 방향을 나타냄:    'c' - 대각선    'd': delete E[i-1][j]      'i': insert E[i][j-1]
	
	//Stack<char[]> saveO = new Stack<char[]>();  
	// 편집 순서 (역순으로)를 저장
	
	void input() throws IOException{
		Reader r = new Reader();
		
		S = r.nextLine();
		m = S.length();
		
		T = r.nextLine();
		n = T.length();
		
	}
	
	int solve(){
		// E를 채운후 가장 짧은 편집거리 출력
		
		
		//initialization
		for(int i=0;i<=m;i++){
			E.add(new ArrayList<Integer>());
			P.add(new ArrayList<Character>());
			
			E.get(i).add(0);
			P.get(i).add('d');
		}
		
		for(int j=0;j<=n;j++){
			E.get(0).add(j, j);
			P.get(0).add(j,'i');
		}
		//initialization

		for(int i=1; i<=m; i++)for(int j=1; j<=n;j++){
			int a;
			if(S.charAt(i-1) == T.charAt(j-1)) a = 0;
			// string의 index는 0부터 시작
			else a = 1;
			
			if(E.get(i-1).get(j-1) +a < E.get(i-1).get(j) + 1){
				if(E.get(i-1).get(j-1) + a < E.get(i).get(j-1) + 1){   // s or c가 당첨
					E.get(i).add(j,E.get(i-1).get(j-1) + a );
					P.get(i).add('c');
				}
				else{   //i
					E.get(i).add(j,E.get(i).get(j-1) + 1 );
					P.get(i).add('i');
				}
			}
			else{
				if(E.get(i-1).get(j) < E.get(i).get(j-1) ){
					E.get(i).add(j,E.get(i-1).get(j) + 1 );
					P.get(i).add('d');
				}
				else{
					E.get(i).add(j,E.get(i).get(j-1) + 1 );
					P.get(i).add('i');
				}
			}
			
		}
		
		return E.get(m).get(n);
	}
	
	String findOp(){
		StringBuffer ret = new StringBuffer();
		
		char temp;
		int i = m;
		int j = n;
		while(i>0 || j>0){
			temp = P.get(i).get(j);
			
			if(temp == 'c'){
				if( S.charAt(i-1) == T.charAt(j-1)) ret.insert(0,"c "+S.charAt(i-1)+"\n");
				else ret.insert(0,"m "+T.charAt(j-1)+'\n');
				i--; j--;
			}
			else if(temp == 'i'){
				ret.insert(0, "a "+ T.charAt(j-1) +'\n');
				j--;
			}
			else{  //temp == 'd'
				ret.insert(0, "d "+ S.charAt(i-1)+'\n');
				i--;
			}
		}
		
		return ret.toString();
	}
}