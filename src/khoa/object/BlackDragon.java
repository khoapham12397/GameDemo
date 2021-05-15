package khoa.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import khoa.effect.Animation;

public class BlackDragon  extends Human{

	private Animation forwardFlyingAnim,backFlyingAnim;
	private Animation forwardFlyingFireAnim,backFlyingFireAnim;
	private Animation forwardBeHurtAnim,backBeHurtAnim;
	private Animation fireDeathAnim;
	
	private int count=0;
	private long timeForAttack=700*1000000;
	private long lastTimeBeHurt;
	public BlackDragon(double posX, double posY, int width, int height, int damage1, int blood, GameWorld gameWorld) {
		super(posX, posY, width, height, damage1, blood, gameWorld);
		
		forwardFlyingAnim=getGameWorld().getCacheDataLoader().getBlackDragonAnim().get("flying");
		backFlyingAnim=new Animation(getGameWorld().getCacheDataLoader().getBlackDragonAnim().get("flying"));
		backFlyingAnim.flipAllImage();
		forwardFlyingAnim.resetAnim();
		backFlyingAnim.resetAnim();
		
		forwardFlyingFireAnim=getGameWorld().getCacheDataLoader().getBlackDragonAnim().get("flyingfire");
		backFlyingFireAnim=new Animation(getGameWorld().getCacheDataLoader().getBlackDragonAnim().get("flyingfire"));
		backFlyingFireAnim.flipAllImage();
		forwardFlyingFireAnim.resetAnim();
		backFlyingFireAnim.resetAnim();
		
		forwardBeHurtAnim=getGameWorld().getCacheDataLoader().getBlackDragonAnim().get("behurt");
		backBeHurtAnim=new Animation(getGameWorld().getCacheDataLoader().getBlackDragonAnim().get("behurt"));
		backBeHurtAnim.flipAllImage();
		forwardBeHurtAnim.resetAnim();
		backBeHurtAnim.resetAnim();
		
		setAttacking(false);
		timeForBeHurt=500*1000000;
		fireDeathAnim=getGameWorld().getCacheDataLoader().getFireAnimations().get("fire1");
		timeForDeathAnim=600*1000000;
		
		setName("blackDragon");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack(int keyCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jump() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stopRun() {
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

	
	public void run1() {
		if(getDirect()==LEFT_DIR) {
			setSpeedX(-5);
		}else {
			setSpeedX(5);
		}
	}
	public void stopRunAuto() {
		setSpeedX(0);
		forwardFlyingAnim.resetAnim();
		backFlyingAnim.resetAnim();
	}
	public void UpdateAuto() {
		Human mainCharacter=getGameWorld().getMainCharater();
		double kcx;
		double kcy;
		count++;
		if(count==10) count=0;
		int t=count%20;
		if(t>=0 && t<=4) setSpeedY(-1);
		else setSpeedY(1);
		
		setPosY(getPosY()+getSpeedY());
		setPosX(getPosX()+getSpeedX());
		kcx=getPosX()-mainCharacter.getPosX();
		kcy=Math.abs(getPosY()-mainCharacter.getPosY());
		if(getState()==ALIVE) {
			
			if(kcx>=0) {
			if(Math.abs(kcx)>=10 && Math.abs(kcx)<=80 && kcy<=30 && mainCharacter.isAttacking() && !mainCharacter.isKame() && mainCharacter.getDirect()==RIGHT_DIR) {
				beHurt(mainCharacter.getDamage());
				
			}
			}else {
				if(Math.abs(kcx)>=10 && Math.abs(kcx)<=80 && kcy<=30 && mainCharacter.isAttacking() && !mainCharacter.isKame()&& mainCharacter.getDirect()==LEFT_DIR) {
					beHurt(mainCharacter.getDamage());
				}
			}
		}
		
	
		switch(getState()) {
		case ALIVE:
			if(isAttacking()) attackAuto();
			else {
				if(kcx>=800) run1();
				else {
					Random m=new Random();
					int n= m.nextInt(3);
					switch(n) {
					case 0:
						stopRunAuto();
						break;
					case 1:
						attack1();
						break;
					case 2:
						run1();
						break;
					}
				}	
			}
			
			break;
		case BEHURT:
			if(System.nanoTime()-lastTimeBeHurt>=timeForBeHurt) {
				if(getBlood()<=0) 
					{
						setState(BEFORE_DEATH);
						stopRunAuto();
						timeBeforeDeath=System.nanoTime();
					}
					
				else {
					setState(ALIVE);
					backBeHurtAnim.resetAnim();
					forwardBeHurtAnim.resetAnim();
				}
			}
			break;
		case BEFORE_DEATH:
			if(System.nanoTime()-timeBeforeDeath>=timeForDeathAnim) {
				mainCharacter.setCountKill(mainCharacter.getCountKill()+1);
				setState(DEATH);
			}
			break;
		case DEATH:
			setState(ALIVE);
			//getGameWorld().getMainCharater().setCountKill(getGameWorld().getMainCharater().getCountKill()+1);
			if(getPosX()<20000)
			setPosX(getPosX()+1000);
			Random rd=new Random();
			int m= rd.nextInt(3);
			switch(m) {
			case 0:
				setPosY(50);
				break;
			case 1:
				setPosY(75);
				break;
			case 2:
				setPosY(100);
				break;
			}
			
			setAttacking(false);
			setBlood(10);
			backFlyingFireAnim.resetAnim();
			forwardFlyingAnim.resetAnim();
			backFlyingAnim.resetAnim();
			forwardFlyingAnim.resetAnim();
			backBeHurtAnim.resetAnim();
			forwardBeHurtAnim.resetAnim();
			setDirect(LEFT_DIR);
			setSpeedX(0); setSpeedY(0);
			break;
		}
	}
	public void attackAuto() {
		if(System.nanoTime()-lastTimeAttack>=timeForAttack) {
			setAttacking(false);
			forwardFlyingFireAnim.resetAnim();
			backFlyingFireAnim.resetAnim();
		}
	}
	public void attack1() {
		setAttacking(true);
		lastTimeAttack=System.nanoTime();
	}
	public void beHurt(int damage) {
		setBlood(getBlood()-damage);
		setState(BEHURT);
	}
	
	@Override
	public void Update() {
		UpdateAuto();
	}
	@Override
	public void draw(Graphics g) {
		Camera camera=getGameWorld().getCamera();
		switch(getState()) {
		
		case ALIVE:
			if(isAttacking()) {
				if(getDirect()==LEFT_DIR) {
					backFlyingFireAnim.Update();
					backFlyingFireAnim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
				}else {
					forwardFlyingFireAnim.Update();
					forwardFlyingFireAnim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
				}
			}else {
				if(getDirect()==LEFT_DIR) {
					backFlyingAnim.Update();
					backFlyingAnim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
				}else {
					forwardFlyingAnim.Update();
					forwardFlyingAnim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
				}
			}
			break;
		case BEHURT:
			if(getDirect()==LEFT_DIR) {
				backBeHurtAnim.Update();
				backBeHurtAnim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
			}else {
				forwardBeHurtAnim.Update();
				forwardBeHurtAnim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
			}
			break;
			
		case BEFORE_DEATH:
			if(System.nanoTime()-timeBeforeDeath<timeForDeathAnim) {
				fireDeathAnim.Update();
				fireDeathAnim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
			}
			break;
		case DEATH:
			
			break;
		
		}
		
	}
}
