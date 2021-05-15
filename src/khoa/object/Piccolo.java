package khoa.object;

import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import khoa.effect.Animation;

public class Piccolo extends Human{


	public final int BeHurtStand=1;
	public final int BeHurtSerious=2;
	private Animation forwardIdleAnim,backIdleAnim;
	private Animation forwardJumpAnim,backJumpAnim;
	private Animation forwardFlyAnim,backFlyAnim;
	private Animation forwardFlyDownAnim,backFlyDownAnim;
	private Animation forwardKick1Anim,backKick1Anim;
	private Animation forwardKick2Anim,backKick2Anim;
	private Animation forwardKameDownAnim,backKameDownAnim;
	private Animation  forwardKameUpAnim,backKameUpAnim;
	private Animation  forwardKame2Anim,backKame2Anim;
	private Animation forwardHand1Anim,backHand1Anim;
	private Animation forwardBeHurtStandAnim,backBeHurtStandAnim;
	private Animation forwardBeHurtSEAnim,backBeHurtSEAnim;
	private Animation beforeDeath;
	private AudioClip kamesmall;
	private AudioClip screamDeath;
	private AudioClip kamedown;
	private AudioClip screamGoku;
	private boolean KamingUp;
	
	private boolean KamingDown;
	private boolean Kaming2;
	private boolean Kicking1;
	private boolean Kicking2;
	private boolean handing1;
	
	private int kamed2;
	public boolean isHanding1() {
		return handing1;
	}
	public void setHanding1(boolean handing1) {
		this.handing1 = handing1;
	}
	public boolean isKicking2() {
		return Kicking2;
	}
	public void setKicking2(boolean kicking2) {
		Kicking2 = kicking2;
	}

	private boolean jumping;
	private boolean flying;
	private boolean flyingdown;
	
	private long timeForKamingUp=800*1000000;
	private long timeForKamingDown=500*1000000;
	private long timeForKaming2=750*1000000;
	private long timeForHanding1=400*1000000;
	private long timeForKicking1=350*1000000;
	private long timeForKicking2=400*1000000;
	private long separateTimeJump=2000*1000000;
	private long lastTimeJumping;
	
	private long timeToStartKame=600*1000000;
	private long timeStartBehurt;
	private int posLand;
	private int typeAction;
	private int typeBeHurt;
	
	public int getTypeBeHurt() {
		return typeBeHurt;
	}
	public void setTypeBeHurt(int typeBeHurt) {
		this.typeBeHurt = typeBeHurt;
	}
	public int getPosLand() {
		return posLand;
	}
	public void setPosLand(int posLand) {
		this.posLand = posLand;
	}
	public int getTypeAction() {
		return typeAction;
	}
	public void setTypeAction(int typeAction) {
		this.typeAction = typeAction;
	}
	public boolean isFlying() {
		return flying;
	}
	public void setFlying(boolean flying) {
		this.flying = flying;
	}
	public boolean isFlyingdown() {
		return flyingdown;
	}
	public void setFlyingdown(boolean flyingdown) {
		this.flyingdown = flyingdown;
	}

	private int countJump;
	
	public Piccolo(double posX, double posY, GameWorld gameWorld) {
		super(posX, posY, 130, 130, 10, 100, gameWorld);
		
		setMass(1);
		setAttacking(false);
		setJumping(false);
		setName("goku");
		
		
		backIdleAnim=getGameWorld().getCacheDataLoader().getPiccoloAnimations().get("idle");
		forwardIdleAnim=new Animation(getGameWorld().getCacheDataLoader().getPiccoloAnimations().get("idle"));
		forwardIdleAnim.flipAllImage();
		backIdleAnim.resetAnim();
		forwardIdleAnim.resetAnim();
		
		backFlyAnim=getGameWorld().getCacheDataLoader().getPiccoloAnimations().get("flyt1");
		forwardFlyAnim=new Animation(getGameWorld().getCacheDataLoader().getPiccoloAnimations().get("flyt1"));
		forwardFlyAnim.flipAllImage();
		backFlyAnim.resetAnim();
		forwardFlyAnim.resetAnim();
		
		backFlyDownAnim=getGameWorld().getCacheDataLoader().getPiccoloAnimations().get("flydown");
		forwardFlyDownAnim=new Animation(getGameWorld().getCacheDataLoader().getPiccoloAnimations().get("flydown"));
		forwardFlyDownAnim.flipAllImage();
		backFlyDownAnim.resetAnim();
		forwardFlyDownAnim.resetAnim();
	
		backJumpAnim=getGameWorld().getCacheDataLoader().getPiccoloAnimations().get("jumpt1");
		forwardJumpAnim=new Animation(getGameWorld().getCacheDataLoader().getPiccoloAnimations().get("jumpt1"));
		forwardJumpAnim.flipAllImage();
		
		backJumpAnim.resetAnim();
		forwardJumpAnim.resetAnim();
	
		backKick1Anim=getGameWorld().getCacheDataLoader().getPiccoloAnimations().get("kickt1");
		forwardKick1Anim=new Animation(getGameWorld().getCacheDataLoader().getPiccoloAnimations().get("kickt1"));
		forwardKick1Anim.flipAllImage();
		backKick1Anim.resetAnim();
		forwardKick1Anim.resetAnim();
		
		backKick2Anim=getGameWorld().getCacheDataLoader().getPiccoloAnimations().get("kickt2");
		forwardKick2Anim=new Animation(getGameWorld().getCacheDataLoader().getPiccoloAnimations().get("kickt2"));
		forwardKick2Anim.flipAllImage();
		backKick2Anim.resetAnim();
		forwardKick2Anim.resetAnim();
	
		backKameDownAnim=getGameWorld().getCacheDataLoader().getPiccoloAnimations().get("kamedown");
		forwardKameDownAnim=new Animation(getGameWorld().getCacheDataLoader().getPiccoloAnimations().get("kamedown"));
		forwardKameDownAnim.flipAllImage();
		backKameDownAnim.resetAnim();
		forwardKameDownAnim.resetAnim();
		
		backKameUpAnim=getGameWorld().getCacheDataLoader().getPiccoloAnimations().get("kameup");
		forwardKameUpAnim=new Animation(getGameWorld().getCacheDataLoader().getPiccoloAnimations().get("kameup"));
		forwardKameUpAnim.flipAllImage();
		backKameUpAnim.resetAnim();
		forwardKameUpAnim.resetAnim();
		
		backKame2Anim=getGameWorld().getCacheDataLoader().getPiccoloAnimations().get("kamet2");
		forwardKame2Anim=new Animation(getGameWorld().getCacheDataLoader().getPiccoloAnimations().get("kamet2"));
		forwardKame2Anim.flipAllImage();
		backKame2Anim.resetAnim();
		forwardKame2Anim.resetAnim();
		
		backHand1Anim=getGameWorld().getCacheDataLoader().getPiccoloAnimations().get("handt1");
		forwardHand1Anim=new Animation(getGameWorld().getCacheDataLoader().getPiccoloAnimations().get("handt1"));
		forwardHand1Anim.flipAllImage();
		backHand1Anim.resetAnim();
		forwardHand1Anim.resetAnim();
		
		backBeHurtStandAnim=getGameWorld().getCacheDataLoader().getPiccoloAnimations().get("behurtstand");
		forwardBeHurtStandAnim=new Animation(getGameWorld().getCacheDataLoader().getPiccoloAnimations().get("behurtstand"));
		forwardBeHurtStandAnim.flipAllImage();
		backBeHurtStandAnim.resetAnim();
		forwardBeHurtStandAnim.resetAnim();
		
		backBeHurtSEAnim=getGameWorld().getCacheDataLoader().getPiccoloAnimations().get("behurtserious");
		forwardBeHurtSEAnim=new Animation(getGameWorld().getCacheDataLoader().getPiccoloAnimations().get("behurtserious"));
		forwardBeHurtSEAnim.flipAllImage();
		backBeHurtSEAnim.resetAnim();
		forwardBeHurtSEAnim.resetAnim();
		
		beforeDeath=getGameWorld().getCacheDataLoader().getFireAnimations().get("fire1");
		beforeDeath.resetAnim();
		
		kamesmall=getGameWorld().getCacheDataLoader().getSounds().get("kamesmall");
		kamedown=getGameWorld().getCacheDataLoader().getSounds().get("kamedown");
		screamDeath=getGameWorld().getCacheDataLoader().getSounds().get("screamDeath");
		screamGoku=getGameWorld().getCacheDataLoader().getSounds().get("screamGoku");
		
		setSpeedX(0);
		setSpeedY(0);
		
		timeForBeHurt=450*1000000;
		timeForDeathAnim=600*1000000;
		
	}
	public boolean isKamingUp() {
		return KamingUp;
	}
	public void setKamingUp(boolean kamingUp) {
		KamingUp = kamingUp;
	}
	public boolean isKamingDown() {
		return KamingDown;
	}
	public void setKamingDown(boolean kamingDown) {
		KamingDown = kamingDown;
	}
	public boolean isKaming2() {
		return Kaming2;
	}
	public void setKaming2(boolean kaming2) {
		Kaming2 = kaming2;
	}
	public boolean isKicking1() {
		return Kicking1;
	}
	public void setKicking1(boolean kicking1) {
		Kicking1 = kicking1;
	}
	public boolean isJumping() {
		return jumping;
	}
	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	@Override
	public void Update() {
	UpdateAuto();	
	if(isAttacking()) System.out.println("attack true");
	else System.out.println("attack false");
	}
	
	
	public void attack1() {
		Human mainCharacter=getGameWorld().getMainCharater();
		double kcx=getPosX()-mainCharacter.getPosX();
		double kcy=getPosY()-mainCharacter.getPosY();
		if(!isJumping()) {
		
		lastTimeAttack=System.nanoTime();
	
		if(kcx>=300 && kcx<=800) {
		
		if(kcy>0) {
			setKamingUp(true);
			
			setAttacking(true);
			kameUp();
			kamedown.play();
		}
		if(kcy==0) {
			setKaming2(true);
			setAttacking(true);
		}
		
		}else {
			if(kcx<300) {
			Random rd =new Random();
			int t=rd.nextInt(3);
			switch(t) {
			case 0:
				setKicking1(true);
				setAttacking(true);
				break;
			case 1:
				setKicking2(true);
				setAttacking(true);
				break;
			case 2:
				setHanding1(true);
				kameSmall();
				kamesmall.play();
				setAttacking(true);
				break;
			}
			}
		}
		
		}
		if(isJumping()) {
			if(kcx>=300) {
			setKamingDown(true);
			kameDown();
			kamedown.play();
			setAttacking(true);
			lastTimeAttack=System.nanoTime();
		}
		}
	}
	
	public void UpdateAuto() {
		Human mainCharacter=getGameWorld().getMainCharater();
		
		double kcx=getPosX()-mainCharacter.getPosX();
		double kcy=getPosY()-mainCharacter.getPosY();
		setPosX(getPosX()+getSpeedX());
		
		if(getPosX()-mainCharacter.getPosX()<=0) setDirect(RIGHT_DIR);
		else setDirect(LEFT_DIR);
		if(getState()==ALIVE) {
		if(mainCharacter.isAttacking() && !mainCharacter.isKame()) {
			if(kcx>0 && mainCharacter.getDirect()==RIGHT_DIR) {
				if(kcx>=40 && kcx<=130 && Math.abs(kcy)<=20) {
					setState(BEHURT);
					
					beHurt(mainCharacter.getDamage());
					
					timeStartBehurt=System.nanoTime();
				}
			}else {
				if(kcx<0 && mainCharacter.getDirect()==LEFT_DIR) {
					if(Math.abs(kcx)>=80 && Math.abs(kcx)<=130 && Math.abs(kcy)<=20) {
						setState(BEHURT);
						beHurt(mainCharacter.getDamage());
						timeStartBehurt=System.nanoTime();
					}
				}
			}
		}
		beHurtKame();
		
		}
		switch(getState()) {
		case ALIVE:
			if(isAttacking()) attackAuto();
			else {
			if(isJumping()) {
				jumpAuto();	
				Random rd=new Random();
				if(rd.nextInt(10)==0)
				attack1();
				}
			else {
					if(kcx>600) runAuto();
					else {
					Random rd=new Random();
					int m= rd.nextInt(4);
					switch(m) {
					case 0:
						runAuto();
						break;
					case 1:
						//setSpeedX(0);
						attack1();
						break;
					case 2:
						//setSpeedX(10);
						if(System.nanoTime()-lastTimeJumping>=separateTimeJump)
						jump1();
						break;
					case 3:
						stopRunAuto();
						break;
					
					}
				}
			}
			}
				
			break;
			
		case BEHURT:
			if(System.nanoTime()-timeStartBehurt>=timeForBeHurt) {
				if(getBlood()<=0) {
					setState(BEFORE_DEATH);
					screamDeath.play();
					timeBeforeDeath=System.nanoTime();
				}else {
					backBeHurtSEAnim.resetAnim();
					forwardBeHurtSEAnim.resetAnim();
					backBeHurtStandAnim.resetAnim();
					forwardBeHurtStandAnim.resetAnim();
					setState(ALIVE);
				}
			}
			break;
		case BEFORE_DEATH:
			if(System.nanoTime()-timeBeforeDeath>=timeForDeathAnim) setState(DEATH);
			break;
		case DEATH:
			
			break;
			
		}
		
	}
	
	public void beHurt(int damage) {
		setBlood(getBlood()-damage);
		if(getBlood()<=20) setTypeBeHurt(BeHurtSerious);
		else setTypeBeHurt(BeHurtStand);
		if(getBlood()>0) {
		screamGoku.play();
		}
	}
	
	@Override
	public void run() {
	if(getDirect()==LEFT_DIR) {
		setSpeedX(-1);
		
	}else {
		setSpeedX(1);
	}
		
	}

	@Override
	public void attack(int keyCode) {
	//	if(isAttacking()) return;
		
		
	}
	
	public void beHurtKame() {
		ArrayList<Kameha> kamehaList=getGameWorld().getKameManager().getKamehaList();
		Kameha kameha;
		double kcx,kcy;
	
		for(int i=0;i<kamehaList.size();i++) {
			kameha=kamehaList.get(i);
			if(kameha.getTeamType()!=getTeamType()) {
				kcx=kameha.getPosX()-getPosX();
				kcy=kameha.getPosY()-getPosY();
				if(Math.abs(kcx)<=60 && Math.abs(kcy)<=60) {
					setState(BEHURT);
					beHurt(kameha.getDamage());
					
				}
			}
		}
	}
	public void attackAuto() {
		
			if(isKamingUp()) {
			if(System.nanoTime()-lastTimeAttack>=timeForKamingUp) {
				setKamingUp(false);
				setAttacking(false);
				//System.out.println("attack false");
				backKameUpAnim.resetAnim();
				forwardKameUpAnim.resetAnim();
			}
			}
			if(isKamingDown()) {
				if(System.nanoTime()-lastTimeAttack>=timeForKamingDown) {
					setKamingDown(false);
					setAttacking(false);
					//System.out.println("attack false");
					backKameDownAnim.resetAnim();
					forwardKameDownAnim.resetAnim();
				}	
			}
			if(isKaming2()) {
				long t=System.nanoTime()-lastTimeAttack;
				if(t>=timeToStartKame && t<=timeToStartKame+50*1000000) {
					kame2();
				}
					if(System.nanoTime()-lastTimeAttack>=timeForKaming2) {
					setKaming2(false);
					setAttacking(false);
					//System.out.println("attack false");
					backKame2Anim.resetAnim();
					forwardKame2Anim.resetAnim();
				}
			}
			if(isKicking1()) {
				if(System.nanoTime()-lastTimeAttack>=timeForKicking1) {
					setKicking1(false);
					setAttacking(false);
					//System.out.println("attack false");
					backKick1Anim.resetAnim();
					forwardKick1Anim.resetAnim();
				}
			}
			if(isKicking2()) {
				if(System.nanoTime()-lastTimeAttack>=timeForKicking2) {
					setKicking2(false);
					//System.out.println("attack false");
					backKick2Anim.resetAnim();
					forwardKick2Anim.resetAnim();
					setAttacking(false);
				}
			}
			if(isHanding1()) {
				if(System.nanoTime()-lastTimeAttack>=timeForHanding1) {
					setHanding1(false);
					//System.out.println("attack false");
					backHand1Anim.resetAnim();
					forwardHand1Anim.resetAnim();
					setAttacking(false);
				}
			}
		}
		
		
		
	public int getKamed2() {
		return kamed2;
	}
	public void setKamed2(int kamed2) {
		this.kamed2 = kamed2;
	}
	public void stopRunAuto() {
		setSpeedX(0);
	}
	
	private void kameDown() {
	
		KameGoku kame1=new KameGoku(getPosX(),getPosY() ,20,3 , getGameWorld());
		kame1.setDirect(getDirect());
		if(getDirect()==LEFT_DIR) {
			kame1.setPosX(getPosX()+10);
			kame1.setSpeedX(-10);
			kame1.setSpeedY(10);
		}else {
			kame1.setPosX(getPosX()-10);
			kame1.setSpeedX(10);
			kame1.setSpeedY(10);
		}
		kame1.setPosY(getPosY()-15);
		kame1.setTeamType(ENEMY_TEAM);
		
		getGameWorld().getKameManager().addKame(kame1);
		setKame(true);
		
		
		//kame1.setStartKame(System.nanoTime());
	}
	public void kame2() {
		
		KameGoku kame1=new KameGoku(getPosX(),getPosY() ,20,1 , getGameWorld());
		kame1.setDirect(getDirect());
		if(getDirect()==LEFT_DIR) {
			kame1.setPosX(getPosX()-210);
		}else {
			kame1.setPosX(getPosX()+210);
		}
		kame1.setPosY(getPosY()+15);
		kame1.setTeamType(ENEMY_TEAM);
		kame1.setSpeedX(0);
		kame1.setSpeedY(0);
		kame1.setStartKame(System.nanoTime());
		
		getGameWorld().getKameManager().addKame(kame1);
		setKame(true);
		
	}
	public void kameUp() {
		KameGoku kame2=new KameGoku(getPosX(), getPosY(), 10, 3, getGameWorld());
		kame2.setDirect(getDirect());
		if(getDirect()==LEFT_DIR) {
			kame2.setPosX(getPosX()-20);
			kame2.setSpeedX(-10);
		}else {
			kame2.setPosX(getPosX()+20);
			kame2.setSpeedX(10);
		}
		kame2.setPosY(getPosY()-20);
		kame2.setSpeedY(-10);
		kame2.setTeamType(ENEMY_TEAM);
		
	//	kame2.setStartKame(System.nanoTime());
		getGameWorld().getKameManager().addKame(kame2);
		System.out.println("Kame up is called");
		setKame(true);
	}
	private void kameSmall() {
		
		
		KameGoku kame1=new KameGoku(getPosX(), getPosY(), 10, 2, getGameWorld());
		kame1.setDirect(getDirect());
		if(getDirect()==LEFT_DIR) {
			kame1.setPosX(getPosX()-10);
			kame1.setSpeedX(-10);
		}else {
			kame1.setPosX(getPosX()+10);
			kame1.setSpeedX(10);
		}
		kame1.setPosY(getPosY()+20);
		kame1.setTeamType(ENEMY_TEAM);
		
		kame1.setSpeedY(0);
		getGameWorld().getKameManager().addKame(kame1);
		setKame(true);
	}
	public void runAuto() {
		if(getDirect()==LEFT_DIR) {
			setSpeedX(-3);	
		}else {
			setSpeedX(3);
		}
		setPosX(getPosX()+getSpeedX());
	}
	public void jump1() {
		setJumping(true);
		setSpeedY(-25);
		setPosY(getPosY()+getSpeedY());
	}
	public void jumpAuto() {
		setSpeedY(getSpeedY()+getMass());
		setPosY(getPosY()+getSpeedY());
		if(getPosY()>=posLand) {
			setPosY(posLand);
			setSpeedY(0);
			setJumping(false);
			lastTimeJumping=System.nanoTime();
			backJumpAnim.resetAnim();
			forwardJumpAnim.resetAnim();
		}
	
	}
	
	
	@Override
	public void jump() {
	if(isAttacking()) return ;
	if(isJumping()) {
		if(countJump==1) {
			forwardJumpAnim.resetAnim();
			backJumpAnim.resetAnim();
			countJump=2;
			
		}
	}
	setJumping(true);
	countJump=1;	
	}

	@Override
	public void stopRun() {
		setSpeedX(0);
		forwardFlyAnim.resetAnim();
		backFlyAnim.resetAnim();
	}

	@Override
	public void dick() {
		
		
	}

	@Override
	public void standUp() {
		
		
	}

	@Override
	public Rectangle getBoundForCollisionWithMap() {
		
		return null;
	}

	@Override
	public void draw(Graphics g) {
		Camera camera=getGameWorld().getCamera();
		switch(getState()) {
		case ALIVE:
			if(isAttacking()) {
				if(isKamingUp()) {
					if(getDirect()==LEFT_DIR) {
						backKameUpAnim.Update();
						backKameUpAnim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY()-10);
					}else {
						forwardKameUpAnim.Update();
						forwardKameUpAnim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY()-10);
					}
				}
				if(isKamingDown()) {
					if(getDirect()==LEFT_DIR) {
						backKameDownAnim.Update();
						backKameDownAnim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
					}else {
						forwardKameDownAnim.Update();
						forwardKameDownAnim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
						}	
				}
				if(isKaming2()) {
					if(getDirect()==LEFT_DIR) {
						backKame2Anim.Update();
						backKame2Anim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
					}else {
						forwardKame2Anim.Update();
						forwardKame2Anim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
					
				}
				}
				if(isKicking1()) {
					if(getDirect()==LEFT_DIR) {
						backKick1Anim.Update();
						backKick1Anim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY()-20);
					}else {
						forwardKick1Anim.Update();
						forwardKick1Anim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY()-20);
					
				}
				}
				if(isKicking2()) {
					if(getDirect()==LEFT_DIR) {
						backKick2Anim.Update();
						backKick2Anim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY()-20);
					}else {
						forwardKick2Anim.Update();
						forwardKick2Anim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY()-20);
					
				}	
			
				}
				if(isHanding1()) {
					if(getDirect()==LEFT_DIR) {
						backHand1Anim.Update();
						backHand1Anim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
					}else {
						forwardHand1Anim.Update();
						forwardHand1Anim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
				}	
				}
				
			}else {
				if(isJumping()) {
					if(getDirect()==LEFT_DIR) {
						backJumpAnim.Update();
						backJumpAnim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
					}else {
						forwardJumpAnim.Update();
						forwardJumpAnim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
					
				}	
					
				}else {
					if(getSpeedX()!=0) {
						if(getTypeAction()==1) {
							if(getDirect()==LEFT_DIR) {
								backFlyDownAnim.Update();
								backFlyDownAnim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
							}else {
								forwardFlyDownAnim.Update();
								forwardFlyDownAnim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
							}	
						}
					else {
							if(getTypeAction()==2) {
								if(getDirect()==LEFT_DIR) {
									backFlyAnim.Update();
									backFlyAnim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
								}else {
									forwardFlyAnim.Update();
									forwardFlyAnim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
								}	
						}
					}
					}else {
						if(getDirect()==LEFT_DIR) {
							backIdleAnim.Update();
							backIdleAnim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
						}else {
							forwardIdleAnim.Update();
							forwardIdleAnim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
						}	
					}
				}
			}
			break;
		case BEHURT:
			switch(getTypeBeHurt()) {
			case BeHurtStand:
				if(getDirect()==LEFT_DIR)
				{
					backBeHurtStandAnim.Update();
					backBeHurtStandAnim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY()-10);
				}else
				{
					forwardBeHurtStandAnim.Update();
					forwardBeHurtStandAnim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
				}
				
				break;
			case BeHurtSerious:
				if(getDirect()==LEFT_DIR)
				{
					backBeHurtSEAnim.Update();
					backBeHurtSEAnim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
				}else
				{
					forwardBeHurtSEAnim.Update();
					forwardBeHurtSEAnim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
				}
				break;
			}
			
			break;
		case BEFORE_DEATH:
			if(System.nanoTime()-timeBeforeDeath<timeForDeathAnim) {
				beforeDeath.Update();
				beforeDeath.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
				
			}
			break;
		case DEATH:
			
			break;
		}
		
	}
	

}
