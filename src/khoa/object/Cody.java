package khoa.object;

import java.awt.Graphics;
import java.awt.Rectangle;

import khoa.effect.Animation;

public class Cody extends Human{
	
	Animation forwardIdleAnim,backIdleAnim;
	Animation forwardRunAnim,backRunAim;
	Animation forwardJumpAnim,backJumpAnim;
	Animation forwardPunchAnim,backPunchAnim;
	Animation forwardKickAnim,backKickAnim;
    Animation forwarrdAttackAnim,backAttackAnim;
	private GameWorld gameWorld;
	
	public Cody(double posX, double posY,GameWorld gameWorld) {
		super(posX, posY, 100, 150, 100, 100,gameWorld);
		
	}

	@Override
	public void run() {
		
		
	}

	

	@Override
	public void jump() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopRun() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack(int keyCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void standUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Rectangle getBoundForCollisionWithMap() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
