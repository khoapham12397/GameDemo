package khoa.userInterface;

import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import khoa.object.FighterGuy;
import khoa.object.GameWorld;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener,Runnable{
	
	private GameWorld gameWorld;
	private Thread gameThread ;
	private boolean gameRun=true;
	private AudioClip bgmusic;
	private BufferedImage image1;
	private InputManager inputManager;
	public GamePanel() throws IOException {
		super();
		gameWorld=new GameWorld();
		inputManager=new InputManager(gameWorld);
		bgmusic=gameWorld.getCacheDataLoader().getSounds().get("bgmusic");
		gameThread=new Thread(this);
		//demo();
	}
	@Override
	public void paint(Graphics g) {
		g.clearRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
		g.drawImage(gameWorld.getBufferImage(), 0, 0,this);
		
	}
	
	public void Start() {
		gameThread.start();
	}
	
	@Override
	public void run() {
		
		long cycle=23*1000000;
		bgmusic.loop();
		while(true) {
			long startTime=System.nanoTime();
			
			gameWorld.Update();
			gameWorld.Render();
			//guy.Update();
			repaint();
			long currentTime=System.nanoTime();
			if(currentTime-startTime<cycle) {
				try {
					Thread.sleep((cycle-currentTime+startTime)/1000000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				try {
					Thread.sleep(cycle/2000000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		/*	try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
	}
	public void demo() {
		try {
			BufferedImage image=ImageIO.read(new File("Data/Image/Ryu.png"));
			
			image1=image.getSubimage(6, 16, 44, 85);
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void keyPressed(KeyEvent e) {
		inputManager.keyPressed(e.getKeyCode());
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		inputManager.keyReleased(e.getKeyCode());
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
