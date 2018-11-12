import java.util.ArrayList;

public class Z01_GUI {
	public static void main(String[] args) {
		Boy brr[] = new Boy[3];
		brr[0]=new Boy(10, "홍길동");
		brr[1]=new Boy(20, "수지");
		brr[2]=new Boy(30, "오승환");
		
//		brr[0].age = 10;
//		brr[0].name = "홍길동";

		for (int i = 0; i < brr.length; i++) {
			Boy b = brr[i];
			System.out.println(b.name + ", " +b.age + "살");
		}
		
		// ArrayList : 동적 배열
		ArrayList<Boy> al = new ArrayList<Boy>();
		al.add(new Boy(4, "지은4"));
		al.add(new Boy(5, "지은5"));
		al.add(new Boy(6, "지은6"));
		al.add(new Boy(7, "지은7"));
		Boy b = new Boy();
		b.age = 20;
		b.name = "수지";
		al.add(b); // 마지막에 원소 삭제
		
		al.remove(2); // 해당 index의 원소 삭제
		System.out.println("어레이리스트의 크기"+al.size()); // 크기
		for (int i = 0; i < al.size(); i++) {
			Boy d = al.get(i); 
			System.out.println(d.name+","+d.age+"살");
		}
	} // end of main
} // end of class

class Boy {
	int age;
	String name = "";
	public Boy() {
		
	}
	public Boy(int age, String name) {
		this.age = age;
		this.name = name; 
	}
 }
