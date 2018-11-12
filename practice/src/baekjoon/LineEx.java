package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;



public class LineEx {
	public static void main(String[] args) throws IOException{
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		
		ArrayList<Integer> save = new ArrayList<>();
		
		
		int n = Integer.parseInt(sc.readLine());
		
		
		StringTokenizer st = new StringTokenizer(sc.readLine());
		
		for(int i=0;i<n;i++){
			save.add(Integer.parseInt(st.nextToken()));
		}
		
		
		Collections.sort(save);
		
		int temp=0;
		int sum = 0;
		
		for(int time: save){
			temp += time;
			sum += temp;
		}
		
		System.out.println(sum);
		sc.close();
	}
}
