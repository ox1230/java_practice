package baekjoon;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class B02824_최대공약수 {
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

	HashSet<Integer> prime ; // 지금까지 발견한 소수를 저장함
	int maxPrime = 1;  // 지금까지 발견한 가장 큰 소수.


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
			primeFacto(r.nextInt(), A);  // 읽은 숫자의 소인수 분해 실시
		}

		M = r.nextInt();

		for (int i = 0; i < M; i++) {
			primeFacto(r.nextInt(), B);  // 읽은 숫자의 소인수 분해 실시
		}

	}


	private void primeFacto(int nextInt, HashMap<Integer, Integer> a2) { 
		// TODO Auto-generated method stub
		// 소인수 분해

		// 잘못된 코드
		
		
//		int temp = nextInt;
//		// prime에 있는 소수들로 최대한 나눈다.
//		for (int p : prime) {
//			while(temp%p == 0){
//				a2.put(p,a2.getOrDefault(p, 0)+1); 
//				temp/= p;
//			}
//		}
//
//		while(temp > 1){  // 새로운 소수 발견
//			for (int i = maxPrime+1; i <= temp; i++) {  // 더큰 소수를 찾는다.  (이미 위에서 기존의 소수로 모두 나눠주었으므로
//				//여기서 나누어떨어지면 새로운 소수이다.
//				if(temp % i == 0){
//					while(temp%i == 0){
//						a2.put(i,a2.getOrDefault(i, 0)+1); 
//						temp/= i;
//					}
//
//					prime.add(i);  // 새로운 소수 저장
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
		//숫자들을 곱해나간다.
		// 숫자가 EDGE보다 크면 나머지 연산을 실시한다. 
		boolean isResid = false;  // 나머지 연산을 실시했는지 여부

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