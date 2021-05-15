package khoa.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PhysicalMap extends Object{

	private int[][] physMap;
	private int tileSize=30;
	public PhysicalMap(double posX, double posY, int width, int height, GameWorld gameWorld) {
		super(posX, posY, width, height, gameWorld);
		physMap=getGameWorld().getCacheDataLoader().getMap();
				
	}
	
	public Rectangle haveCollisionWithTop(Rectangle rect){

        int posX1 = rect.x/tileSize;
        posX1 -= 2;
        int posX2 = (rect.x + rect.width)/tileSize;
        posX2 += 2;

        //int posY = (rect.y + rect.height)/tileSize;
        int posY = rect.y/tileSize;

        if(posX1 < 0) posX1 = 0;
        
        if(posX2 >= physMap[0].length) posX2 = physMap[0].length - 1;
        
        for(int y = posY; y >= 0; y--){
            for(int x = posX1; x <= posX2; x++){
                
                if(physMap[y][x] == 1){
                    Rectangle r = new Rectangle((int) getPosX() + x * tileSize, (int) getPosY() + y * tileSize, tileSize, tileSize);
                    if(rect.intersects(r))
                        return r;
                }
            }
        }
        return null;
    }
    
    
    public Rectangle haveCollisionWithLand(Rectangle rect){

        int posX1 = rect.x/tileSize;
        posX1 -= 2;
        int posX2 = (rect.x + rect.width)/tileSize;
        posX2 += 2;

        int posY = (rect.y + rect.height)/tileSize;

        if(posX1 < 0) posX1 = 0;
        
        if(posX2 >= physMap[0].length) posX2 = physMap[0].length - 1;
        for(int y = posY; y < physMap.length;y++){
            for(int x = posX1; x <= posX2; x++){
                
                if(physMap[y][x] == 1){
                    Rectangle r = new Rectangle((int) getPosX() + x * tileSize, (int) getPosY() + y * tileSize, tileSize, tileSize);
                    if(rect.intersects(r))
                        return r;
                }
            }
        }
        return null;
    }
    
    public Rectangle haveCollisionWithRightWall(Rectangle rect){
   
        
        int posY1 = rect.y/tileSize;
        posY1-=2;
        int posY2 = (rect.y + rect.height)/tileSize;
        posY2+=2;
        
        int posX1 = (rect.x + rect.width)/tileSize;
        int posX2 = posX1 + 3;
        if(posX2 >= physMap[0].length) posX2 = physMap[0].length - 1;
        
        if(posY1 < 0) posY1 = 0;
        if(posY2 >= physMap.length) posY2 = physMap.length - 1;
        
        
        for(int x = posX1; x <= posX2; x++){
            for(int y = posY1; y <= posY2;y++){
                if(physMap[y][x] == 1){
                    Rectangle r = new Rectangle((int) getPosX() + x * tileSize, (int) getPosY() + y * tileSize, tileSize, tileSize);
                    if(r.y < rect.y + rect.height - 1 && rect.intersects(r))
                        return r;
                }
            }
        }
        return null;
        
    }
    
    public Rectangle haveCollisionWithLeftWall(Rectangle rect){
        
   
        
        int posY1 = rect.y/tileSize;
        posY1-=2;
        int posY2 = (rect.y + rect.height)/tileSize;
        posY2+=2;
        
        int posX1 = (rect.x + rect.width)/tileSize;
        int posX2 = posX1 - 3;
        if(posX2 < 0) posX2 = 0;
        
        if(posY1 < 0) posY1 = 0;
        if(posY2 >= physMap.length) posY2 = physMap.length - 1;
        
        
        for(int x = posX1; x >= posX2; x--){
            for(int y = posY1; y <= posY2;y++){
                if(physMap[y][x] == 1){
                    Rectangle r = new Rectangle((int) getPosX() + x * tileSize, (int) getPosY() + y * tileSize, tileSize, tileSize);
                    if(r.y < rect.y + rect.height - 1 && rect.intersects(r))
                        return r;
                }
            }
        }
        return null;
        
    }
	
	
	@Override
	public void Update() {
		
	}
	 public void draw(Graphics g){
	        Camera camera=getGameWorld().getCamera();
	       
	        
	        g.setColor(Color.GRAY);
	        for(int i = 0;i< physMap.length;i++)
	            for(int j = 0;j<physMap[0].length;j++)
	                if(physMap[i][j]!=0) g.fillRect( j*tileSize -(int)camera.getPosX(), 
	                        i*tileSize-(int)camera.getPosY(), tileSize, tileSize);
	        
	    }
	    

}
