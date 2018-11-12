package baekjoon;
import java.util.HashMap;
import java.util.Scanner;

public class B02538_���༱{
	public static void main(String[] args) {
		Parallal p = new Parallal();
		p.input();
		System.out.println(p.solve());
	}
}

// ������ X��ǥ, ������ Y��ǥ�� ���� ������ ������ �����Ѵ�.   ---�� ��ǥ�� ���� 2�� �̻��̸� ���༱�� 1�� �����.
class Parallal{
	int n;
	
	HashMap<Integer, Integer> Xs = new HashMap<>();   // Xs< x��ǥ��, ���� ����>
	HashMap<Integer, Integer> Ys = new HashMap<>();   // Ys< y��ǥ��, ���� ����>
	
	void input(){
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		
		int tempX;
		int tempY;
		
		
		for(int i=0; i<n; i++){
			tempX = sc.nextInt();
			tempY = sc.nextInt();
			
			// ������ ���� X,Y���̸� ���� 1�� �߰��Ѵ�.   �־����� 1�� ������Ų��.
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