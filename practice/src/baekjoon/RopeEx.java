package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class RopeEx {

	public static void main(String[] args) throws IOException {
		Rope r = new Rope();
		
		
		r.input();
		System.out.println(r.solve());
	}
}

class Rope {
	ArrayList<Integer> rope = new ArrayList<>();

	void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			rope.add(Integer.parseInt(br.readLine()));
		}

	}

	int solve() {
		Collections.sort(rope, Collections.reverseOrder());

		int cnt = 0; // ���� ����ϴ� ������ ��
		int maxW = 0; // ������ ��� �ִ� �ִ� ����
		int tempW = 0; // ���� ������� �� �� �ִ� ����

		for (int minRope : rope) {
			cnt++;
			tempW = minRope * cnt;

			if (maxW < tempW)
				maxW = tempW;
		}

		return maxW;

	}

}