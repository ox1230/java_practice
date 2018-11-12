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
	char firstNumber;  // d의 첫번째 숫자
	
	void input() throws IOException{
		Reader r = new Reader();
		
		target = r.nextLine();
		d = r.nextLine();
	}
	
	String solve(){
		firstNumber = d.charAt(0);
		int index = target.indexOf(d,0) + d.length();  // d의 마지막 숫자의 인덱스 
		
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
					else if(temp > compa ) break bigLoop;   // index앞의 숫자가 답
					else break;   // 다음 탐색으로 넘어간다.
				}
			}
			else{
				int tempIndex = target.indexOf(d, index+1);
				if(tempIndex == -1) break;  // 마지막 나온 인덱스가 답
				else{
					index = tempIndex + d.length();
				}
			}
		}
				
		return target.substring(0, index - d.length()) + target.substring(index);
		
	}
	
}