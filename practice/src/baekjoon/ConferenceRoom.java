package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;



public class ConferenceRoom {
	public static void main(String[] args) throws IOException{
		Assignment a = new Assignment();
		a.input();
		
		System.out.println(a.solve());
	}
}

class Conference implements Comparable<Conference>{  //각각의 회의 하나하나들
	long start;  // 시작시간
	long finish; // 끝나는 시간
	
	@Override
	public int compareTo(Conference o) {
		// TODO Auto-generated method stub
		if(this.start > o.start) return 1;
		else if (this.start < o.start) return -1;
		else if (this.finish > o.finish) return 1;
		else if (this.finish < o.finish) return -1;
		return 0;
	}

	@Override
	public String toString() {
		return "[start=" + start + ", finish=" + finish + "]";
	}
	
	
	
}

class Assignment{
	int n; // 회의 개수
	ArrayList<Conference> confer = new ArrayList<>(); // 회의들 
	
	
	void input() throws IOException{
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(sc.readLine());
		
		String temp[];
		Conference newy;
		
		for(int i=0;i<n;i++){
			temp = sc.readLine().split(" ");
			newy = new Conference();
			
			newy.start = Long.parseLong(temp[0]);
			newy.finish = Long.parseLong(temp[1]);
			confer.add(newy);  // 회의들을 정렬시킨다.
		}
		
		Collections.sort(confer);
		
		sc.close();
	}
	
	
	int solve(){
		/* 
		 * 끝나는 시간이 가장 짧은 conference를  선택하고, 그 뒤에도 선택가능한 것 중에서 끝나는 시간이 가장짧은 conference를 찾는다.
		 * 현재 찾은 가장 짧은 finish값과 크거나 같은 start값을 가진 conference부터 새로운 탐색을 시작한다.
		 */
		int cnt = 1; // 처음 conference하나는 선택하고 시작하므로.
		
		long minFinish = confer.get(0).finish;
		Conference temp;
		
		for(int i=1;i<n;i++){
			temp = confer.get(i);
			
			if(temp.start >= minFinish){   // 새로운 회의 당첨
				minFinish = temp.finish;
				cnt++;
			}
			else if(temp.finish < minFinish){
				minFinish = temp.finish;
			}
		}
		
		return cnt;
		
	}
	
}