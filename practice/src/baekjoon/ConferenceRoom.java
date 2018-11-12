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

class Conference implements Comparable<Conference>{  //������ ȸ�� �ϳ��ϳ���
	long start;  // ���۽ð�
	long finish; // ������ �ð�
	
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
	int n; // ȸ�� ����
	ArrayList<Conference> confer = new ArrayList<>(); // ȸ�ǵ� 
	
	
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
			confer.add(newy);  // ȸ�ǵ��� ���Ľ�Ų��.
		}
		
		Collections.sort(confer);
		
		sc.close();
	}
	
	
	int solve(){
		/* 
		 * ������ �ð��� ���� ª�� conference��  �����ϰ�, �� �ڿ��� ���ð����� �� �߿��� ������ �ð��� ����ª�� conference�� ã�´�.
		 * ���� ã�� ���� ª�� finish���� ũ�ų� ���� start���� ���� conference���� ���ο� Ž���� �����Ѵ�.
		 */
		int cnt = 1; // ó�� conference�ϳ��� �����ϰ� �����ϹǷ�.
		
		long minFinish = confer.get(0).finish;
		Conference temp;
		
		for(int i=1;i<n;i++){
			temp = confer.get(i);
			
			if(temp.start >= minFinish){   // ���ο� ȸ�� ��÷
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