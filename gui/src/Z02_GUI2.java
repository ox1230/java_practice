import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * 그래픽 처리
 */
public class Z02_GUI2 {
	public static void main(String[] args) {
		JFrame f = new JFrame("그래픽");
		f.setBounds(100, 100, 400, 300); 
		System.out.println(f.getGraphics()); // null 값
		f.setVisible(true); // 화면에 나타난 이후에 그래픽 객체를 얻을 수 있음
		
		// 그래픽 객체를 얻어와서 그림작업을 한다
		
		// 1. 각 컴포넌트의 그래픽 객체에 얻어와서 작업
//		 2. paint 메서드를 오버라이딩을 해서 그래픽 작업
		System.out.println(f.getGraphics()); // 객체를 얻어올 수 있음 
//
//		Graphics g = f.getGraphics();
//		g.fillRect(100, 100, 200, 100);
//		
	
		
		MyFrame mf = new MyFrame("그래픽 paint() 오버라이딩");
		mf.setBounds(500, 100, 400, 300);
		mf.setVisible(true);
		
		
	} // end of main
} // end of class
// repaint() : update() -> paint()


class MyFrame extends Frame {
	public MyFrame() {
		
	}
	public MyFrame(String title) {
		super(title);
	}
	@Override
	public void paint(Graphics g) {
//		super.paint(g);
		g.drawRect(100, 100, 200, 100); // 사각형 : 좌상단 좌표, 폭, 높이
		g.setColor(Color.red);
		// g.setColor(new Color(255,0,0);
		g.fillRect(150, 150, 200, 100); // 색깔 사각형
		
		g.drawOval(0, 40, 100, 100); // 원 : 좌상단 좌표, 폭, 높이
		g.setFont(new Font("Serif", Font.BOLD, 30));
		g.drawString("안녕하세요", 30, 40);
		
		
//		int arr[] = new int[3];
//		int brr[] = {1,2,3};
//		int crr[] = new int[] {1,2,3} // 익명배열
		g.setColor(Color.BLUE);
		g.drawPolygon(new int[] {80, 150, 150, 80}, // x 좌표들의 배열
					new int[] {50,50,200,200}, // y 좌표들의 배열
					4); //몇각형
		
		
		Image img = Toolkit.getDefaultToolkit().getImage("image/emerald.jpg");
		g.drawImage(img, 0, 0, getWidth(), getHeight(),this); // 이미지 객체, 좌상단 좌표, this
		
		
		
		
	}
	
	
}