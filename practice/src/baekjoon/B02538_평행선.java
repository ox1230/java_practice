package baekjoon;
import java.util.HashMap;
import java.util.Scanner;

public class B02538_평행선{
	public static void main(String[] args) {
		Parallal p = new Parallal();
		p.input();
		System.out.println(p.solve());
	}
}

// 동일한 X좌표, 동일한 Y좌표를 가진 점들의 개수를 저장한다.   ---각 좌표의 점이 2개 이상이면 평행선이 1개 생긴다.
class Parallal{
	int n;
	
	HashMap<Integer, Integer> Xs = new HashMap<>();   // Xs< x좌표값, 점의 개수>
	HashMap<Integer, Integer> Ys = new HashMap<>();   // Ys< y좌표값, 점의 개수>
	
	void input(){
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		
		int tempX;
		int tempY;
		
		
		for(int i=0; i<n; i++){
			tempX = sc.nextInt();
			tempY = sc.nextInt();
			
			// 기존에 없는 X,Y값이면 값을 1로 추가한다.   있었으면 1을 증가시킨다.
			if( Xs.putIfAbsent(tempX,1) != null){
				Xs.put(tempX, Xs.get(tempX) +1);
			}
			if( Ys.putIfAbsent(tempY,1) != null){
				Ys.put(tempY, Ys.get(tempY) +1);
			}
		}
		
		sc.close();
	}
	
	
	int solve(){
		int cnt = 0;
		
		for(int v: Xs.values()){
			if(v != 1) cnt++;
		}
		for(int v: Ys.values()){
			if(v != 1) cnt++;
		}
		
		return cnt;
	}
}