package khoa.object;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Human extends ParticularObject{

	public final static int autoAction=1;
	public final static int controlAction=2;
	private int actionType;
	
	private int blood;
	private double mass;
	
	private boolean kame;
	
	private int countKill;
	
	public int getCountKill() {
		return countKill;
	}



	public void setCountKill(int countKill) {
		this.countKill = countKill;
	}
	protected boolean jumping;
	private boolean landing;
	private boolean dicking;
	private boolean attacking;
	
	protected long lastTimeAttack;
	protected long timeBeforeDeath;
	protected long timeForDeathAnim;
	protected long timeForBeHurt;

	public boolean isKame() {
		return kame;
	}



	public void setKame(boolean kame) {
		this.kame = kame;
	}
	public int getActionType() {
		return actionType;
	}



	public void setActionType(int actionType) {
		this.actionType = actionType;
	}
	public boolean isAttacking() {
		return attacking;
	}
	
	
	
	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public boolean isDicking() {
		return dicking;
	}

	public void setDicking(boolean dicking) {
		this.dicking = dicking;
	}

	public boolean isLanding() {
		return landing;
	}

	public void setLanding(boolean landing) {
		this.landing = landing;
	}

	public double getMass() {
		return mass;
	}

	public void setMass(double mass) {
		this.mass = mass;
	}

	public Human(double posX, double posY, int width, int height,int damage1,int blood,GameWorld gameWorld) {
		super(posX,posY,width,height,damage1,gameWorld);
		this.blood=blood;
		
	}
	
	public int getBlood() {
		return blood;
	}

	public void setBlood(int blood) {
		this.blood = blood;
	}
	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}

	
	@Override
	public void Update() {
		switch(getState()) {
		case ALIVE:
			Rectangle rect,r,r1;
			setPosX(getPosX()+getSpeedX());
			r=getBoundForCollisionWithMap();
			if(getDirect()==LEFT_DIR) {
				rect=getGameWorld().getPhysMap().haveCollisionWithLeftWall(r);
				if(rect!=null) {
					setPosX(rect.x+rect.width+2);
				}
			}else {
				rect=getGameWorld().getPhysMap().haveCollisionWithRightWall(r);
				if(rect!=null) {
					setPosX(rect.x-r.width-1);
				}
			}
			
			r1=getBoundForCollisionWithMap();
			if(getSpeedY()>0) {
				r1.y+=getSpeedY();
			}else {
			if(getSpeedY()==0)
				r1.y+=2;
			}
			
			if(!isLanding()) {
			
			rect=getGameWorld().getPhysMap().haveCollisionWithTop(r);
			if(rect!=null) {
				setPosY(rect.y+rect.height+2);
			}else {
				
				if(getSpeedY()>0) {
					r.y+=getSpeedY();
				}else {
				
					r.y+=2;
				}
				rect=getGameWorld().getPhysMap().haveCollisionWithLand(r);
				if(rect!=null) {
					setJumping(false);
					setLanding(true);
					setDicking(false);
					setPosY(rect.y-r.height-5);
				}
				else {
					setSpeedY(getSpeedY()+getMass());
					setPosY(getPosY()+getSpeedY());
				}
			}
			}
			
			break;
		case BEHURT:
			break;
		case DEATH:
			break;
		}
		
	}
	public abstract void run();
	public abstract void attack(int keyCode);
	public abstract void jump();
	public abstract void stopRun();
	public abstract void dick();
	public abstract void standUp();
}
