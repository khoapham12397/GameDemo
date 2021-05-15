package khoa.userInterface;

import java.io.IOException;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	
	public static final int SCREEN_WIDTH=1024;
	public static final int SCREEN_HEIGHT=512;
	private GamePanel gamePanel;
	public GameFrame() throws IOException {
		setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
		setLocation(100,100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamePanel=new GamePanel();
		add(gamePanel);
		//addKeyListener(gamePanel);
		
	}
	
	public static void main(String[] args) throws IOException {
		GameFrame gameFrame=new GameFrame() ;
		gameFrame.setVisible(true);
		gameFrame.gamePanel.Start();
		
	}
}
