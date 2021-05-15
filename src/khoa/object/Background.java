package khoa.object;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Background extends Object{

	private BufferedImage image;
	private int widthBG=1024;

	private BufferedImage[] arrImage;
	public Background(double posX, double posY, int width, int height, GameWorld gameWorld) {
		super(posX, posY, width, height, gameWorld);
		image=getGameWorld().getCacheDataLoader().getBk();
		arrImage=new BufferedImage[100];
		for(int i=0;i<100;i++) {
			arrImage[i]=init(image);
		}
	}
	public BufferedImage init(BufferedImage im) {
		
		BufferedImage image=new BufferedImage(im.getWidth(),im.getHeight(),im.getType());
		Graphics g=image.getGraphics();
		g.drawImage(im, 0, 0, null);
		
		return im;
	}

	@Override
	public void Update() {
	
	}
	public void draw(Graphics g) {
		Camera camera =getGameWorld().getCamera();
		int m=(int)camera.getPosX()/widthBG;
		g.drawImage(image, (int) ((m-1)*widthBG-camera.getPosX()),0,null);
		g.drawImage(arrImage[m],(int) (m*widthBG-camera.getPosX()),0,null );
		g.drawImage(arrImage[m+1], (int) ((m+1)*widthBG-camera.getPosX()), 0,null);
	}

	
}
