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
	StringBuffer S = new StringBuffer();  // �������� & ��� -- col�� ����
	ArrayList<Boolean> slash = new ArrayList<>();   // /�밢��
	ArrayList<Boolean> bslash  = new ArrayList<>();  // \�밢��
	ArrayList<Boolean> col = new ArrayList<>(); // ����
	// �ٷ� ����Ѵ�. (�� ���� ���̿� \n�� �־��ش�. 
	int N;
	
	void input() throws IOException{
		Reader r = new Reader();
		
		
	
		
		N = r.nextInt();
	}
	
	
	String solve(){
		// �ʱ�ȭ
		for(int n=0; n<N;n++){
			S.append('0');
			S.append('\n');
			
			slash.add(false);
			bslash.add(false);
			slash.add(false);
			bslash.add(false);
			
			col.add(false);
		}
		
		nQueen(-2);  // nQueen�� n+2�̹Ƿ� -2���� �����ؾ� index0�� ä��� �ִ�.
		
		return S.toString();
	}
	
	boolean nQueen(int n){
		//����� ���� 1���� ������ ��� �����Ѵ�. 
		if(n == 2*(N-1)){
			return true;
		}
		else{
			boolean temp = false;
			
			for(int k=0 ; k<N ; k++){
				
				if(promising(n+2, k) == false) continue; // �ذ� �� �� ������ pass
				
				//�ذ� �� �� ������
				S.setCharAt(n+2, (char)('0'+k+1));   //��� ���ȣ�� 1���� ����...
				int trow = (n+2)/2;
				col.set(k, true);
				slash.set(trow + k, true);
				bslash.set(trow - k + (N-1), true);
				
				temp = nQueen(n+2); // enter�� �ǳʶٰ� �״��� �ε����� Ȯ���ؾ� �Ѵ�.
				if(temp == true) break; // ����� ���� �ϳ� ã������ ����
				
				// �ƴϸ� ���󺹱�
				col.set(k , false);
				slash.set(trow + k, false);
				bslash.set(trow - k + (N-1), false);
			}
			
			return temp;  // �ѹ��̶� true�� �־����� true, �ƴϸ� false
		}
	}
	
	boolean promising(int n, int c){
		// n+2�� c�� �ִ� ���� �ذ� �� �� �ִ� �� Ȯ��
		
		if(col.get(c)) return false;
		if(slash.get((n/2) + c)) return false;
		if(bslash.get((n/2) - c + (N-1) )) return false;
		
		return true;
		
	}
	
	
}

