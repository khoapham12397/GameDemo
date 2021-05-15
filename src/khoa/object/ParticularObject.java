package khoa.object;

import java.awt.Graphics;
import java.awt.Rectangle;

import khoa.userInterface.GameFrame;

public abstract class ParticularObject extends Object{
	private double speedX;
	private double speedY;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private int damage;
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	private int direct;
	public static final int LEAGUE_TEAM=1;
	public static final int ENEMY_TEAM=2;
	public int teamType;
	public int getTeamType() {
		return teamType;
	}
	public void setTeamType(int teamType) {
		this.teamType = teamType;
	}
	public int getDirect() {
		return direct;
	}
	public void setDirect(int direct) {
		this.direct = direct;
	}
	public static final int LEFT_DIR=1;
	public static final int RIGHT_DIR=2;
	public static final int ALIVE=1;
	public static final int BEHURT=2;
	public static final int BEFORE_DEATH=4;
	public static final int DEATH=3;
	private int state;
	public double getSpeedX() {
		return speedX;
	}
	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}
	public double getSpeedY() {
		return speedY;
	}
	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}
	
	public ParticularObject(double posX,double posY,int width, int height,int damage,GameWorld gameWorld) {
		
		super(posX,posY,width,height,gameWorld);
		
		this.damage=damage;
		
		setState(1);
		
	}
	public boolean outOfCamera() {
		Camera camera=getGameWorld().getCamera();
		double kcx=getPosX()-camera.getPosX();
		double kcy=getPosY()-camera.getPosY();
		if(kcx>=-130 && kcx<=GameFrame.SCREEN_WIDTH+130 && kcy >=-130 && kcy<= GameFrame.SCREEN_HEIGHT+130)
		return false;
		else return true;
	}
	public abstract Rectangle getBoundForCollisionWithMap() ;
		
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public  abstract void draw(Graphics g);
	
	
}
