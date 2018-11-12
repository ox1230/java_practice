package baekjoon;

import java.util.Scanner;

public class B01152_단어의개수 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String S = sc.nextLine();
		
		int cnt = 0;
		int now = 1;
		int pre = 0;
		
		if(S.charAt(0) != ' ') cnt++; // 맨 앞 단어
		
		for(;now< S.length();now++){
			if(S.charAt(pre) == ' ' && S.charAt(now) != ' ') cnt++;
			pre = now;
		}
		
		System.out.println(cnt);
	}

}
