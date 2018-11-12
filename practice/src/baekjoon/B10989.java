package baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B10989{
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter
				(new OutputStreamWriter(System.out));
		
		
		int N = Integer.parseInt(br.readLine());
		
		int cnt[] = new int[10001];
		for (int i = 0; i < N; i++) {
			cnt[Integer.parseInt(br.readLine())]++;
		}
		
		for (int i = 0; i < cnt.length; i++) {
			for (int j = 0; j < cnt[i]; j++) {
				bw.write(i+'\n');
			}
		}
		
		bw.close();
		br.close();
		
	}
}
