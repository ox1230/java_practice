package baekjoon;

import java.util.Scanner;



public class B13458_시험감독 {
	public static void main(String[] args) {
		TestD td = new TestD();
		td.input();
		System.out.println(td.solve());
	}
}

class TestD{
	int N;
	int A[];
	int B;
	int C;
	public void input()  {
		// TODO Auto-generated method stub
		Scanner r = new Scanner(System.in);

		N = r.nextInt();

		A = new int[N];

		for (int i = 0; i < A.length; i++) {
			A[i] = r.nextInt();
		}

		B= r.nextInt();
		C =r.nextInt();

	}
	public long solve() {
		// TODO Auto-generated method stub
		long cnt = 0;

		for (int i = 0; i < N; i++) {
			int t = A[i];

			cnt++;
			t -= B;

			if(t> 0){
				cnt += (t/C);

				if(t%C > 0) cnt++;
			}
		}


		return cnt;
	}

}