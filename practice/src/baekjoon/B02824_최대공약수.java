package baekjoon;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class B02824_�ִ����� {
	public static void main(String[] args) throws IOException {
		GCDGCD gg = new GCDGCD();
		gg.input();
		System.out.println(gg.makeString());
	} // end of main
} // end of class


class GCDGCD{
	int N;
	int M;
	final int EDGE = 1000000000;



	HashMap<Integer,Integer> A ;
	HashMap<Integer,Integer> B ;

	HashSet<Integer> prime ; // ���ݱ��� �߰��� �Ҽ��� ������
	int maxPrime = 1;  // ���ݱ��� �߰��� ���� ū �Ҽ�.


	public void input() throws IOException {
		// TODO Auto-generated method stub
		A = new HashMap<>();
		B = new HashMap<>();
		prime = new HashSet<Integer>();
		prime.add(2);
		maxPrime = 2;

		Reader r = new Reader();

		N = r.nextInt();

		for (int i = 0; i < N; i++) {
			primeFacto(r.nextInt(), A);  // ���� ������ ���μ� ���� �ǽ�
		}

		M = r.nextInt();

		for (int i = 0; i < M; i++) {
			primeFacto(r.nextInt(), B);  // ���� ������ ���μ� ���� �ǽ�
		}

	}


	private void primeFacto(int nextInt, HashMap<Integer, Integer> a2) { 
		// TODO Auto-generated method stub
		// ���μ� ����

		// �߸��� �ڵ�
		
		
//		int temp = nextInt;
//		// prime�� �ִ� �Ҽ���� �ִ��� ������.
//		for (int p : prime) {
//			while(temp%p == 0){
//				a2.put(p,a2.getOrDefault(p, 0)+1); 
//				temp/= p;
//			}
//		}
//
//		while(temp > 1){  // ���ο� �Ҽ� �߰�
//			for (int i = maxPrime+1; i <= temp; i++) {  // ��ū �Ҽ��� ã�´�.  (�̹� ������ ������ �Ҽ��� ��� �����־����Ƿ�
//				//���⼭ ����������� ���ο� �Ҽ��̴�.
//				if(temp % i == 0){
//					while(temp%i == 0){
//						a2.put(i,a2.getOrDefault(i, 0)+1); 
//						temp/= i;
//					}
//
//					prime.add(i);  // ���ο� �Ҽ� ����
//					maxPrime = i;
//
//					if(temp == 1) break;
//				}
//			}
//		}

	}

	public String makeString() {
		// TODO Auto-generated method stub

		long num = 1;
		//���ڵ��� ���س�����.
		// ���ڰ� EDGE���� ũ�� ������ ������ �ǽ��Ѵ�. 
		boolean isResid = false;  // ������ ������ �ǽ��ߴ��� ����

		for (int p : A.keySet()) {
			if(B.containsKey(p)){
				int min = A.get(p) > B.get(p) ? B.get(p): A.get(p);

				for (int j = 1; j <= min; j++) {
					num *= p;
					if(num >= EDGE){
						num %= EDGE;
						isResid = true;
					}
				}
			}
		}


		if(isResid){
			return String.format("%09d", num);
		}
		else {
			return String.valueOf(num);
		}
	}



}