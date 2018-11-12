import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * java SE - wav 만 지원
 */
public class Z04_Sound {
	static Clip clip;
	public static void main(String[] args) {
		JFrame f = new JFrame("사운드재생");
		f.setBounds(100, 100, 200, 300);
		f.setLayout(new GridLayout(3,1));
		
		JButton bStart = new JButton("시작");
		JButton bReStart = new JButton("재시작");
		JButton bStop = new JButton("정지");
		f.add(bStart);
		f.add(bReStart);
		f.add(bStop);
		
		File file = new File("bgm/lose.mp3");
		System.out.println(file.exists()); // 파일이 존재하니?
		try {
			AudioInputStream stream = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(stream);
//			clip.start();
//			clip.stop();
			
		} catch (Exception e1) {
			System.out.println("에러발생");
		}
		
		
		bStart.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				clip.setFramePosition(0); // 재생위치를 처음으로 이동
				clip.loop(0); // 0입력시 한번만 하고 끝남 , 반복회수 지정
				clip.loop(-1); // -1입력시 무한재생
				clip.start(); // 
			}
		});
		bReStart.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				clip.loop(3); // 반복회수 지정
				clip.start(); // 이전 재생위치부터 시작
			}
		});
		bStop.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				clip.stop(); // 정지
			}
		});
		
		
		
		f.setVisible(true);
	} // end of main 
} // end of class
