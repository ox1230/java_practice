import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * 돌맹이 하늘에서 떨어뜨리기 (여러개)
 */
public class Z05_GUI4 {
	public static void main(String[] args) {
		MyFrame3 f = new MyFrame3("돌맹이를 떨어뜨리기");
		
		
	} // end of main
} // end of class

class MyFrame3 extends Frame{
	Image imgBg; // 배경이미지
	Image imgStone;
	int x; // 돌멩이 좌표
	int y;
	ArrayList<Stone> al = new ArrayList<Stone>();
	int maxNum = 10; //돌맹이의 최대개수
	Random ran = new Random();
	
	public MyFrame3() {
		this("");
	}
	public MyFrame3(String title) {
		super(title);
		setBounds(600,100,500,400);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});
		
		imgBg = Toolkit.getDefaultToolkit().createImage("background/fight_default.png");
		imgStone = Toolkit.getDefaultToolkit().createImage("char/또가스.png");
		// 별도의 쓰레드에서 돌멩이 추가, 이동하는 작업을 반복
		Thread t2 = new Thread(new Runnable()  {
			public void run() {
				while (true) {
					//돌멩이가 부족하면 추가
					if (al.size() < maxNum) {
						Stone s = new Stone();
						s.x = ran.nextInt(getWidth());
						s.y = 0; // 위에서 시작하도록
						s.dx = 0; //안움직임
						s.dy = ran.nextInt(10)+2; // 이동량
						al.add(s);
					} 
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
					}
					
				}
			}
			
		});
		t2.start();
		
		// 스스로 조금씩 이미지를 움직이기 위해서 쓰레드를 생성
		Thread t = new Thread(new Runnable() {
			
			public void run() {
				while (true) {
					// 존재하는 돌멩이들을 이동
					for (int i = al.size()-1; i >= 0; i--) {
						Stone s = al.get(i);
						s.x += s.dx;
						s.y += s.dy;
						if(s.y > getHeight()) {
							al.remove(i);
						}
					}
					y = y+1;

					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
					}
					
					repaint();
				}
			}
		});
		t.start();
		
		setVisible(true);
	} // end of 생성자 MyFrame3
	
	public void paint(Graphics g) {
//		super.paint(arg0);
		// 배경 그리기
		g.drawImage(imgBg, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(imgStone, x, y, 40, 40, this);
		// 돌맹이 그리기
		for (int i = 0; i < al.size(); i++) {
			Stone s = al.get(i);
			g.drawImage(imgStone, s.x, s.y, 50, 50, this);
		}
		// 캐릭터 그리기
	}
	
}


class Stone {
	int x; // 돌맹이 좌표
	int y;
	int dx; // 증가량
	int dy;
}









