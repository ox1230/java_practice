package baekjoon;

import java.util.HashSet;
import java.util.Scanner;

public class B01016_���������� {
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
	// min���� max������ ���ڸ� �����ϴ� ��
	
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
		
		
		// 4���� �� �������� ����� S���� �����Ѵ�.
		long i = 2;
		long jegob = i*i;
		
		while(true){
			jegob = i*i; 
			
			if(jegob > max){
				break; // loop����
			}
			long j = min/jegob;
			long end = max/jegob +1;
			// ������ jegob�� ������� set���� ����
			for (; j < end; j++) {
				S.remove(jegob * j);
			}
			
			i++;
		}
		
		return S.size();
	}
	
}