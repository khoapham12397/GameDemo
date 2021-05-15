package khoa.object;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import khoa.effect.CacheDataLoader;
import khoa.userInterface.GameFrame;
public class GameWorld {
	public final int INTRO_GAME=0;
	public final int GAME_PLAY=1;
	
	public BufferedImage getBufferImage() {
		return bufferImage;
	}

	private int stateGame;
	
	private AudioClip bgmusic;
	private Human mainCharater;
	private Human mainBoss;
	private BufferedImage bufferImage;
	private CacheDataLoader cacheDataLoader;
	private FighterGuy guy;
	private Trunks trunks;
	private ParticularObjectManager manager;
	private Sasuke sasuke;
	private BufferedImage bk;
	private BackgroundMap bgm;
	private PhysicalMap physMap;
	private Camera camera;
	private Background bg;
	private Piccolo piccolo;
	private KamehaManager kameManager;
	private  BlackDragon blackDragon;
	
	public GameWorld() throws IOException {
		bufferImage=new BufferedImage(GameFrame.SCREEN_WIDTH,GameFrame.SCREEN_HEIGHT,BufferedImage.TYPE_INT_ARGB);
		cacheDataLoader=new CacheDataLoader();
		manager=new ParticularObjectManager(this);
		
		trunks=new Trunks(200,200,this);
		
		trunks.setDirect(ParticularObject.RIGHT_DIR);
		trunks.setJumping(true);
		trunks.setTeamType(ParticularObject.LEAGUE_TEAM);
		
		sasuke=new Sasuke(100,250,this);
		sasuke.setDirect(ParticularObject.RIGHT_DIR);
		blackDragon=new BlackDragon(1300, 50,140, 140, 10, 10, this);
		blackDragon.setTeamType(ParticularObject.ENEMY_TEAM);
		blackDragon.setDirect(ParticularObject.LEFT_DIR);
		bgm=new BackgroundMap(0,0,GameFrame.SCREEN_WIDTH,GameFrame.SCREEN_HEIGHT, this);
		
		setMainCharater(trunks);
		
		piccolo=new Piccolo(6000,300,this);
		
		piccolo.setDirect(ParticularObject.LEFT_DIR);
		piccolo.setPosLand(300);
		piccolo.setTypeAction(1);
		piccolo.setTeamType(ParticularObject.ENEMY_TEAM);
		
		setMainBoss(piccolo);
		
		manager.addObject(mainCharater);
		manager.addObject(mainBoss);
		manager.addObject(blackDragon);
		bgmusic=cacheDataLoader.getSounds().get("bgmusic");
		
		bk=cacheDataLoader.getBk();
		physMap=new PhysicalMap(0,0, 1000, 1000, this);
		
		bg=new Background(0,0, 1000, 1000, this);
		kameManager=new KamehaManager(this);
		camera=new Camera(0,0,1000,1000,this);
	}
	
	
	public BlackDragon getBlackDragon() {
		return blackDragon;
	}


	public Human getMainBoss() {
		return mainBoss;
	}


	public void setMainBoss(Human mainBoss) {
		this.mainBoss = mainBoss;
	}


	public KamehaManager getKameManager() {
		return kameManager;
	}


	public ParticularObjectManager getManager() {
		return manager;
	}


	public Sasuke getSasuke() {
		return sasuke;
	}


	public void drawIntro() {
		Graphics g=bufferImage.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, GameFrame.SCREEN_WIDTH, GameFrame.SCREEN_HEIGHT);
		g.setColor(Color.red);
		try {
			BufferedImage image1=ImageIO.read(new File("D:/HINH DO HOA/tilte.png"));
			BufferedImage image2=ImageIO.read(new File("D:/HINH DO HOA/tilte1.png"));
			
			g.drawImage(image1,140,100 , null);
			g.drawImage(image2,140,200 , null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	


	public Piccolo getPiccolo() {
		return piccolo;
	}


	public Human getMainCharater() {
		return mainCharater;
	}



	

	public void setMainCharater(Human mainCharater) {
		this.mainCharater = mainCharater;
	}





	public Trunks getTrunks() {
		return trunks;
	}


	

	public CacheDataLoader getCacheDataLoader() {
		return cacheDataLoader;
	}


	public FighterGuy getGuy() {
		return guy;
	}

	public void Update() {
		
		if(mainBoss.getPosX()-mainCharater.getPosX()<=600) camera.locked=true;
		//bg.Update();
		//Graphics g=bufferImage.getGraphics();
		//g.setColor(Color.black);
		//g.fillRect(camera.getPosX(), camera.getPosY(), GameFrame.WIDTH, GameFrame.HEIGHT);
		
		manager.Update();
		camera.Update();
		kameManager.Update();
	}
	
	public void drawBlood(Graphics g) {
		
		
		
		g.setColor(Color.blue);
		
		g.fillRect(30,30, 200, 10);
		
		g.setColor(Color.red);
		g.fillRect(30,30, mainCharater.getBlood()*2, 10);
		
		if(!mainBoss.outOfCamera()) {
			g.setColor(Color.blue);
			
			g.fillRect(800,30, 200, 10);
			
			g.setColor(Color.red);
			g.fillRect(800,30,mainBoss.getBlood()*2, 10);
		}
		g.setColor(Color.black);
		g.drawString(String.valueOf(mainCharater.getCountKill()), 10, 10);
	
		
		
		
	}
	public void Render() {
		Graphics g=bufferImage.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, bufferImage.getWidth(), bufferImage.getHeight());
//		drawIntro();
	
		//bg.draw(g);
		
	
		drawBlood(g);
		manager.draw(g);
		kameManager.draw(g);
	}


	public Camera getCamera() {
		return camera;
	}


	public PhysicalMap getPhysMap() {
		return physMap;
	}
}
