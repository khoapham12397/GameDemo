package khoa.object;

import java.awt.Graphics;

public abstract class Object {
	private double posX;
	private double posY;
	private int width;
	private int height;
	private GameWorld gameWorld;
	public Object(double posX,double posY,int width, int height,GameWorld gameWorld) {
		this.posX=posX;
		this.posY=posY;
		this.width=width;
		this.height=height;
		this.gameWorld=gameWorld;
	}
	public GameWorld getGameWorld() {
		return gameWorld;
	}
	public double getPosX() {
		return posX;
	}
	public void setPosX(double posX) {
		this.posX = posX;
	}
	public double getPosY() {
		return posY;
	}
	public void setPosY(double posY) {
		this.posY = posY;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	public abstract void Update() ;
	
	
}
