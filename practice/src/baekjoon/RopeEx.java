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

		int cnt = 0; // 현재 사용하는 로프의 수
		int maxW = 0; // 로프로 들수 있는 최대 무게
		int tempW = 0; // 현재 로프들로 들 수 있는 무게

		for (int minRope : rope) {
			cnt++;
			tempW = minRope * cnt;

			if (maxW < tempW)
				maxW = tempW;
		}

		return maxW;

	}

}