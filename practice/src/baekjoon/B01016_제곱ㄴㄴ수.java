package baekjoon;

import java.util.HashSet;
import java.util.Scanner;

public class B01016_제곱ㄴㄴ수 {
	public static void main(String[] args) {
		NoJegob nj = new NoJegob();
		nj.input();
		nj.makeS();
		System.out.println(nj.solve());
	} 
}

class NoJegob{
	long min;
	long max;
	
	HashSet<Long> S = new HashSet<Long>(); 
	// min부터 max까지의 숫자를 저장하는 곳
	
	public void input() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		min = sc.nextLong();
		max = sc.nextLong();
	}
	public void makeS() {
		// TODO Auto-generated method stub
		for (long i = min; i <= max; i++) {
			S.add(i);
		}
		
	}
	public int solve() {
		// TODO Auto-generated method stub
		
		
		// 4부터 각 제곱수의 배수를 S에서 제거한다.
		long i = 2;
		long jegob = i*i;
		
		while(true){
			jegob = i*i; 
			
			if(jegob > max){
				break; // loop종료
			}
			long j = min/jegob;
			long end = max/jegob +1;
			// 제곱수 jegob의 배수들을 set에서 제거
			for (; j < end; j++) {
				S.remove(jegob * j);
			}
			
			i++;
		}
		
		return S.size();
	}
	
}