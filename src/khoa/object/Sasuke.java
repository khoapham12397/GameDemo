package khoa.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import khoa.effect.Animation;

public class Sasuke extends Human{

	private Animation forwardRunAnim,backRunAnim;
	private Animation forwardSlade1Anim,backSlade1Anim;
	private Animation forwardKick1Anim,backKick1Anim;
	private Animation forwardIdleAnim,backIdleAnim;
	private Animation forwardSlade2Anim,backSlade2Anim;
	private boolean attacking;
	private boolean slading1;
	private boolean slading2;
	private boolean kicking1;
	private boolean jumping;
	private long timeSlade1=200*1000000;
	private long timeSlade2=400*1000000;
	private long timeKick1=300*1000000;
	
	
	private long lastTimeAttack;
	
	
	
	
	public Sasuke(double posX, double posY, GameWorld gameWorld) {
		super(posX, posY, 80, 80, 100, 100, gameWorld);
		setMass(1);
		
		forwardRunAnim=getGameWorld().getCacheDataLoader().getSasukeAnimations().get("run");
		backRunAnim=new Animation(getGameWorld().getCacheDataLoader().getSasukeAnimations().get("run"));
		backRunAnim.flipAllImage();
		forwardRunAnim.resetAnim();
		backRunAnim.resetAnim();
		
		forwardSlade1Anim=getGameWorld().getCacheDataLoader().getSasukeAnimations().get("sladet1");
		backSlade1Anim=new Animation(getGameWorld().getCacheDataLoader().getSasukeAnimations().get("sladet1"));
		backSlade1Anim.flipAllImage();
		
		forwardSlade2Anim=getGameWorld().getCacheDataLoader().getSasukeAnimations().get("sladet2");
		backSlade2Anim=new Animation(getGameWorld().getCacheDataLoader().getSasukeAnimations().get("sladet2"));
		backSlade2Anim.flipAllImage();
		
		forwardKick1Anim=getGameWorld().getCacheDataLoader().getSasukeAnimations().get("kickt1");
		backKick1Anim=new Animation(getGameWorld().getCacheDataLoader().getSasukeAnimations().get("kickt1"));
		backKick1Anim.flipAllImage();
		
		forwardIdleAnim=getGameWorld().getCacheDataLoader().getSasukeAnimations().get("idle");
		backIdleAnim=new Animation(getGameWorld().getCacheDataLoader().getSasukeAnimations().get("idle"));
		backIdleAnim.flipAllImage();
		
		forwardRunAnim.setRepeat(true);
		backRunAnim.setRepeat(true);
	}

	@Override
	public void run() {
		
	}

	@Override
	public void attack(int keyCode) {
		
		if(!isAttacking()) {
			switch(keyCode) {
			case KeyEvent.VK_A:
				setAttacking(true);
				setSlading1(true);
				lastTimeAttack=System.nanoTime();
				forwardRunAnim.resetAnim();
				backRunAnim.resetAnim();
				
				break;
				
			case KeyEvent.VK_Z:
				setAttacking(true);
				setSlading2(true);
				lastTimeAttack=System.nanoTime();
				forwardRunAnim.resetAnim();
				backRunAnim.resetAnim();
				break;
			case KeyEvent.VK_X:
				setAttacking(true);
				setKicking1(true);
				lastTimeAttack=System.nanoTime();
				forwardRunAnim.resetAnim();
				backRunAnim.resetAnim();
				break;
			}
		}
	}

	@Override
	public void jump() {
		if(!isJumping()) {
		setSpeedY(-10);
		setJumping(true);
		setSpeedY(getSpeedY()+getMass());
		setPosY(getPosY()+getSpeedY());
		}
	}

	@Override
	public void stopRun() {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void Update() {
		switch(getState()) {
		case ALIVE:
			setSpeedY(getSpeedY()+getMass());
			setPosY(getPosY()+getSpeedY());
			if(isAttacking()) {
				if(isSlading1()) {
					if(System.nanoTime()-lastTimeAttack>=timeSlade1) {
						setAttacking(false);
						setSlading1(false);
						forwardSlade1Anim.resetAnim();
						backSlade1Anim.resetAnim();
					}
				}else {
					if(isSlading2()) {
						if(System.nanoTime()-lastTimeAttack>=timeSlade2) {
							setAttacking(false);
							setSlading2(false);
							forwardSlade2Anim.resetAnim();
							backSlade2Anim.resetAnim();
					}
					}
					else {
						if(isKicking1()) {
							if(System.nanoTime()-lastTimeAttack>=timeKick1) {
								setAttacking(false);
								setKicking1(false);
								forwardKick1Anim.resetAnim();
								backKick1Anim.resetAnim();
						}
					}
				}
			}
			
		}
			if(isJumping()) {
				
					setJumping(false);
				
			}
			if(getPosY()>=250) {
				
				setPosY(250);
			}
			
				
			break;
				
		case BEHURT:
			
			break;
		case DEATH:
			
			break;
		}
	}

	public boolean isAttacking() {
		return attacking;
	}

	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}

	public boolean isSlading1() {
		return slading1;
	}

	public void setSlading1(boolean slading1) {
		this.slading1 = slading1;
	}

	public boolean isSlading2() {
		return slading2;
	}

	public void setSlading2(boolean slading2) {
		this.slading2 = slading2;
	}

	public boolean isKicking1() {
		return kicking1;
	}

	public void setKicking1(boolean kicking1) {
		this.kicking1 = kicking1;
	}

	@Override
	public void draw(Graphics g) {
		if(isAttacking()) {
			if(isSlading1()) {
				if(getDirect()==LEFT_DIR) {
					backSlade1Anim.Update();
					backSlade1Anim.draw(g, getPosX(), getPosY());
				}else {
					forwardSlade1Anim.Update();
					forwardSlade1Anim.draw(g, getPosX(), getPosY());
				}
			}
			if(isSlading2()) {
				if(getDirect()==LEFT_DIR) {
					backSlade2Anim.Update();
					backSlade2Anim.draw(g, getPosX(), getPosY());
				}else {
					forwardSlade2Anim.Update();
					forwardSlade2Anim.draw(g, getPosX(), getPosY());
				}
			}
			if(isKicking1()) {
				if(getDirect()==LEFT_DIR) {
					backKick1Anim.Update();
					backKick1Anim.draw(g, getPosX(), getPosY());
				}else {
					forwardKick1Anim.Update();
					forwardKick1Anim.draw(g, getPosX(), getPosY());
				}
			}
		}
		else {
			if(getDirect()==LEFT_DIR) {
				backRunAnim.Update();
				backRunAnim.draw(g, getPosX(), getPosY());
			}else {
				forwardRunAnim.Update();
				forwardRunAnim.draw(g, getPosX(), getPosY());
			}
		}
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
