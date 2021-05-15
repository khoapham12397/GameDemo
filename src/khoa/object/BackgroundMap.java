package khoa.object;

import java.awt.Color;
import java.awt.Graphics;

import khoa.userInterface.GameFrame;

public class BackgroundMap extends Object{

	public int[][] map;
    private int tileSize;
    
	public BackgroundMap(double posX, double posY, int width, int height, GameWorld gameWorld) {
		super(posX, posY, width, height, gameWorld);
		map = getGameWorld().getCacheDataLoader().getBackground_map();
        tileSize = 30;
	}
	
	 @Override
	    public void Update() {}
	    
	    public void draw(Graphics g){
	        Camera camera=getGameWorld().getCamera();
	        g.setColor(Color.RED);
	        for(int i = 0;i< map.length;i++)
	            for(int j = 0;j<map[0].length;j++)
	                if(map[i][j]!=0 && j*tileSize > -30 && j*tileSize < GameFrame.SCREEN_WIDTH
	                        && i*tileSize > -30 && i*tileSize < GameFrame.SCREEN_HEIGHT){ 
	                    g.drawImage(getGameWorld().getCacheDataLoader().getFrameImage("tiled"+map[i][j]).getImage(), (int) getPosX() + j*tileSize-(int)camera.getPosX() , 
	                        (int) getPosY() + i*tileSize-(int)camera.getPosY() , null);
	                }
	        
	    }

}
