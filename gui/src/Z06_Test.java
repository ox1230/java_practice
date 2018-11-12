import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Z06_Test {
	static Clip clip;
	public static void main(String[] args) {
		SukJae a1 = new SukJae("숙제");
		a1.setBounds(100, 100, 700, 600); 
		Graphics g = a1.getGraphics();


		System.out.println(a1.getGraphics());
				
				File file = new File("C:\\dev\\python\\workspace\\Day17\\sound\\하울의 움직이는성.wav");
				System.out.println(file.exists()); // 파일이 존재하니?
				try {
					AudioInputStream stream = AudioSystem.getAudioInputStream(file);
					clip = AudioSystem.getClip();
					clip.open(stream);
					clip.start();
					
				} catch (Exception e1) {
					System.out.println("에러발생");
				}
				

			
		a1.setVisible(true);
	} // end of main
} // end of class 


class SukJae extends Frame{
	Image bg;
	Image me;
	Image stone;
	
	
	int x=300;
	int y=500;
	final int WME = 50;
	final int HME = 50;
	
	final int WSTONE = 50;
	final int HSTONE = 50;
	
	ArrayList<homework> al = new ArrayList<homework>();
	int maxNum = 10; //돌맹이의 최대개수
	Random ran = new Random();


	public SukJae() {
		this("");
	}
	public SukJae(String title) {
		super(title);
		setBounds(100, 100, 600, 800);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) { // 창 닫기버튼 누르면
				System.exit(0);
			}
		});
		addKeyListener(new KeyListener() {

			public void keyPressed(KeyEvent e) { // 글자 입력시
				int keyCode = e.getKeyCode(); // 누른키의 정보
				switch(keyCode) {
				case KeyEvent.VK_LEFT:
					x-=10;
					break;
				case KeyEvent.VK_RIGHT :
					x +=10;
					break;
				case KeyEvent.VK_UP :
					y -= 10;
					break;
				case KeyEvent.VK_DOWN :
					y+= 10;
					break;

				}

				repaint(); // 변경된 사항을 다시 그리기
			}

			public void keyReleased(KeyEvent e) { // 손을 뗏을 때 

			}

			public void keyTyped(KeyEvent e) { // 손을 눌렀을 때
			}

		});
		setResizable(false); // 창의 크기변경 못하게 막기
		setVisible(true);

		bg = Toolkit.getDefaultToolkit().getImage("background/startScene.png");
		me = Toolkit.getDefaultToolkit().getImage("char/피카츄.png");
		stone = Toolkit.getDefaultToolkit().getImage("char/또가스.png");

		// 별도의 쓰레드에서 돌멩이 추가, 이동하는 작업을 반복
		Thread t2 = new Thread(new Runnable()  {
			public void run() {
				while (true) {
					//돌멩이가 부족하면 추가
					if (al.size() < maxNum) {
						homework s = new homework();
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

		Thread t = new Thread(new Runnable() {
			public void run() {
				while (true) {
					// 존재하는 돌멩이들을 이동
					for (int i = al.size()-1; i >= 0; i--) {
						homework s = al.get(i);
						s.x += s.dx;
						s.y += s.dy;
						if(s.y > getHeight()) {
							al.remove(i);
						}
						if((y-HSTONE <s.y && s.y < y+HME) && (x-WSTONE <s.x && s.x < x+WME)){
							System.out.println("game over!!");
							System.exit(0);
							break;
						}

					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
					}
					repaint();
				}
			}
		});
		t.start();
		
	} // 생성자


	public void paint(Graphics g) {
		//		super.paint(arg0);
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(me, x, y, WME,HME, this);


		for (int i = 0; i < al.size(); i++) {
			homework s = al.get(i);
			g.drawImage(stone, s.x, s.y, WSTONE, HSTONE, this);
		}
		
		
//		g.setColor(Color.red);
//		g.setFont(new Font("Forte", Font.BOLD, 50));
//		g.drawString("SukJae ", 100, 100);

	}
} // end of SukJae


class homework {
	int x;
	int y;
	int dx;
	int dy;
}