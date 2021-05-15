package khoa.object;

import java.awt.Graphics;
import java.awt.Rectangle;

import khoa.effect.Animation;

public abstract class Kameha extends ParticularObject{

	public Kameha(double posX, double posY,int damage,GameWorld gameWorld) {
		super(posX, posY, 60, 60, damage, gameWorld);
	}

	@Override
	public void Update() {
		Camera camera=getGameWorld().getCamera();
		setPosX(getPosX()+ getSpeedX());
		setPosY(getPosY()+getSpeedY());
		if(getPosX()-camera.getPosX()>=1344 || getPosX()-camera.getPosX()<0) getGameWorld().getManager().removeObj(this);
	}

	
	@Override
	public Rectangle getBoundForCollisionWithMap() {
		
		return null;
	}
	
}
