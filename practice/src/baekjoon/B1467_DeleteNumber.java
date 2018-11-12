package baekjoon;

import java.io.IOException;

public class B1467_DeleteNumber {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DeleteNumber d = new DeleteNumber();
		d.input();
		System.out.println(d.solve());
	}

}

class DeleteNumber{
	String target;
	String d;
	char firstNumber;  // d�� ù��° ����
	
	void input() throws IOException{
		Reader r = new Reader();
		
		target = r.nextLine();
		d = r.nextLine();
	}
	
	String solve(){
		firstNumber = d.charAt(0);
		int index = target.indexOf(d,0) + d.length();  // d�� ������ ������ �ε��� 
		
		bigLoop:
		while( index >= d.length() && index < target.length()){
			char temp =target.charAt(index);
			
			if(temp > firstNumber){
				break;
			}
			else if(temp == firstNumber){
				
				for(int i=1;i<target.length();i++){
					temp = target.charAt(index +1 + i);
					char compa = d.charAt(i);
					if( temp== compa) continue;
					else if(temp > compa ) break bigLoop;   // index���� ���ڰ� ��
					else break;   // ���� Ž������ �Ѿ��.
				}
			}
			else{
				int tempIndex = target.indexOf(d, index+1);
				if(tempIndex == -1) break;  // ������ ���� �ε����� ��
				else{
					index = tempIndex + d.length();
				}
			}
		}
				
		return target.substring(0, index - d.length()) + target.substring(index);
		
	}
	
}