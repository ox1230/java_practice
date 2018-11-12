import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Z03_GUI3 {
	public static void main(String[] args) {
	
		MyFrame2 mf = new MyFrame2("그림게임");
		mf.setBounds(100, 100, 700, 600); 
		Graphics g = mf.getGraphics();
		
	
	} // end of main
} // end of class 


class MyFrame2 extends Frame{
	int x = 100;
	int y = 100;
	int cnt= 0;
	public MyFrame2() {
		this("");
	}
	public MyFrame2(String title) {
		super(title);
		setBounds(100, 100, 300, 400);
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
	} // 생성자
	
	
	@Override
	public void paint(Graphics g) {
//		super.paint(arg0);
		Image bg = Toolkit.getDefaultToolkit().getImage("background/startScene.jpg");
		g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
		
		Image stone = Toolkit.getDefaultToolkit().getImage("char/피카츄.png");
		g.drawImage(stone, x, y, 50, 50, this);
		
	
		g.setColor(Color.red);
		g.setFont(new Font("Serif", Font.BOLD, 50));
		g.drawString("에메랄드 다 가지렴 ^^", 100, 100);
		
	}
} //end of class MyFrame2