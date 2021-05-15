package khoa.object;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import khoa.effect.Animation;

public class Trunks extends Human {
	
	private int xstart=400;
	
	private AudioClip sladeSounds;
	private AudioClip beHurtSounds;
	private Animation forwardSlade1Anim,backSlade1Anim;
	private Animation forwardSlade2Anim,backSlade2Anim;
	private Animation forwardSlade3Anim,backSlade3Anim;
	private Animation forwardSladeDickAnim,backSladeDickAnim;
	private Animation forwardIdleAnim,backIdleAnim;
	private Animation forwardDickAnim,backDickAnim;
	private Animation forwardJumpAnim,backJumpAnim;
	private Animation forwardFlyAnim,backFlyAnim;
	private Animation forwardKame1Anim,backKame1Anim;
	private Animation forwardKameUpAnim,backKameUpAnim;
	private Animation forwardBeHurtStandAnim,backBeHurtStandAnim;
	private Animation forwardBeHurtSEAnim,backBeHurtSEAnim;
	private AudioClip sladeAudio;
	private int MAXSPEEDY=-23;
	public final int BeHurtStand=1;
	public final int BehurtSE=2;
	private long timeForSlade1=350*1000000;
	private long timeForSlade2=450*1000000;
	private long timeForSlade3=350*1000000;
	
	private long timeForKaming1=700*1000000;
	private long timeForKamingup=700*1000000;
	
	
	private int countJump;
	
	private boolean slading1;
	
	private boolean slading2;
	private boolean flying;
	private boolean slading3;
	
	
	private boolean sladedick;
	private boolean combo1;
	private boolean combo2;
	private long lastTimeCombo1;
	private long lastTimeBehurt;
	private boolean kaming1;
	private boolean kamingup;
	
	public boolean isKamingup() {
		return kamingup;
	}

	public void setKamingup(boolean kamingup) {
		this.kamingup = kamingup;
	}

	public boolean isKaming1() {
		return kaming1;
	}

	public void setKaming1(boolean kaming1) {
		this.kaming1 = kaming1;
	}

	
	public boolean isCombo1() {
		return combo1;
	}

	public void setCombo1(boolean combo1) {
		this.combo1 = combo1;
	}

	
	private long timeStartCombo1=100*1000000;
	private int typeBeHurt;

	public Trunks(double posX, double posY, GameWorld gameWorld) throws IOException {
		super(posX, posY, 100, 150 , 10, 100,gameWorld);
		sladeAudio=Applet.newAudioClip(new URL("file","","Data/Audio/slade.wav"));
		setSpeedX(0);
		setSpeedY(0);
		setMass(1.5);
		backIdleAnim= getGameWorld().getCacheDataLoader().getTrunksAnimations().get("idle");
		backIdleAnim.resetAnim();
		forwardIdleAnim=new Animation( getGameWorld().getCacheDataLoader().getTrunksAnimations().get("idle"));
		forwardIdleAnim.flipAllImage();
		forwardIdleAnim.resetAnim();
		
		backDickAnim=getGameWorld().getCacheDataLoader().getTrunksAnimations().get("dick");
		backDickAnim.resetAnim();
		forwardDickAnim=new Animation( getGameWorld().getCacheDataLoader().getTrunksAnimations().get("dick"));
		forwardDickAnim.flipAllImage();
		forwardDickAnim.resetAnim();
		
		backSlade1Anim=getGameWorld().getCacheDataLoader().getTrunksAnimations().get("sladet1");
		forwardSlade1Anim= new Animation(getGameWorld().getCacheDataLoader().getTrunksAnimations().get("sladet1"));
		forwardSlade1Anim.flipAllImage();
		forwardSlade1Anim.resetAnim();
		backSlade1Anim.resetAnim();
		
		backFlyAnim=getGameWorld().getCacheDataLoader().getTrunksAnimations().get("flyt1");
		forwardFlyAnim=new Animation(getGameWorld().getCacheDataLoader().getTrunksAnimations().get("flyt1"));
		forwardFlyAnim.flipAllImage();
		backFlyAnim.resetAnim();
		forwardFlyAnim.resetAnim();
		
		
		
		backSlade2Anim=getGameWorld().getCacheDataLoader().getTrunksAnimations().get("sladet2");
		forwardSlade2Anim=new Animation(getGameWorld().getCacheDataLoader().getTrunksAnimations().get("sladet2"));
		forwardSlade2Anim.flipAllImage();
		forwardSlade2Anim.resetAnim();
		backSlade2Anim.resetAnim();
		
		backSlade3Anim=getGameWorld().getCacheDataLoader().getTrunksAnimations().get("sladet3");
		forwardSlade3Anim=new Animation( getGameWorld().getCacheDataLoader().getTrunksAnimations().get("sladet3"));
		forwardSlade3Anim.flipAllImage();
		forwardSlade3Anim.resetAnim();
		backSlade3Anim.resetAnim();
		
		backJumpAnim=getGameWorld().getCacheDataLoader().getTrunksAnimations().get("jumpt1");
		forwardJumpAnim=new Animation( getGameWorld().getCacheDataLoader().getTrunksAnimations().get("jumpt1"));
		forwardJumpAnim.flipAllImage();
		forwardJumpAnim.resetAnim();
		backJumpAnim.resetAnim();
		
		
		backSladeDickAnim=getGameWorld().getCacheDataLoader().getTrunksAnimations().get("sladedick");
		forwardSladeDickAnim=new Animation(getGameWorld().getCacheDataLoader().getTrunksAnimations().get("sladedick"));
		forwardSladeDickAnim.flipAllImage();
		forwardSladeDickAnim.resetAnim();
		backSladeDickAnim.resetAnim();
		
		backKame1Anim=getGameWorld().getCacheDataLoader().getTrunksAnimations().get("kamet1");
		forwardKame1Anim=new Animation( getGameWorld().getCacheDataLoader().getTrunksAnimations().get("kamet1"));
		forwardKame1Anim.flipAllImage();
		forwardKame1Anim.resetAnim();
		backKame1Anim.resetAnim();
		
		backKameUpAnim=getGameWorld().getCacheDataLoader().getTrunksAnimations().get("kameup");
		forwardKameUpAnim=new Animation( getGameWorld().getCacheDataLoader().getTrunksAnimations().get("kameup"));
		forwardKameUpAnim.flipAllImage();
		forwardKameUpAnim.resetAnim();
		backKameUpAnim.resetAnim();
		
		backBeHurtStandAnim=getGameWorld().getCacheDataLoader().getTrunksAnimations().get("behurtstand");
		forwardBeHurtStandAnim=new Animation( getGameWorld().getCacheDataLoader().getTrunksAnimations().get("behurtstand"));
		forwardBeHurtStandAnim.flipAllImage();
		forwardBeHurtStandAnim.resetAnim();
		backBeHurtStandAnim.resetAnim();
		
		backBeHurtSEAnim=getGameWorld().getCacheDataLoader().getTrunksAnimations().get("behurtserious");
		forwardBeHurtSEAnim=new Animation( getGameWorld().getCacheDataLoader().getTrunksAnimations().get("behurtserious"));
		forwardBeHurtSEAnim.flipAllImage();
		forwardBeHurtSEAnim.resetAnim();
		backBeHurtSEAnim.resetAnim();
		
		sladeSounds=getGameWorld().getCacheDataLoader().getSounds().get("slade");
		beHurtSounds=getGameWorld().getCacheDataLoader().getSounds().get("mainHurt");
		
		//forwardFlyAnim.setRepeat(true);
		//backFlyAnim.setRepeat(true);
		setAttacking(false);
		setSlading1(false);
		setSlading2(false);
		setSlading3(false);
		setDicking(false);
		timeForBeHurt=150*1000000;
		setName("Trunks");
		setCountKill(0);
	}

	public boolean isCombo2() {
		return combo2;
	}

	public void setCombo2(boolean combo2) {
		this.combo2 = combo2;
	}

	@Override
	public void run() {
		Camera camera=getGameWorld().getCamera();
		
		if(getDirect()==RIGHT_DIR)
		setSpeedX(15);
		else 
		{
		if(getPosX()>=xstart) 
			{
		    	setSpeedX(-15);
			}
			else setSpeedX(0);
		}
		setDicking(false);
		if(camera.locked) {
			if(getDirect()==RIGHT_DIR) {
				if(getPosX()-camera.getPosX()>=920) setSpeedX(0);
				
			}
			if(getDirect()==LEFT_DIR) {
				if(getPosX()-camera.getPosX()<=50) setSpeedX(0);
			}
		}
		forwardDickAnim.resetAnim();
		backDickAnim.resetAnim();
		
	}

	@Override
	public void attack(int keyCode) {
		if(isAttacking()) return;
		if(isJumping()) {
			forwardJumpAnim.resetAnim();
			backJumpAnim.resetAnim();
			setDicking(false);
		if(keyCode==KeyEvent.VK_Z) {
			setSlading2(true);
			setAttacking(true);
			lastTimeAttack=System.nanoTime();
			forwardFlyAnim.resetAnim();
			backFlyAnim.resetAnim();
		}
		if(keyCode==KeyEvent.VK_X) {
			if(isCombo1()) {
				setKaming1(true);
				setKame(true);
				setAttacking(true);
				lastTimeAttack=System.nanoTime();
				forwardFlyAnim.resetAnim();
				backFlyAnim.resetAnim();
				
			}
		}
		
		
		}else {
			
		switch(keyCode) {
		case KeyEvent.VK_A:
			setAttacking(true);
			forwardFlyAnim.resetAnim();
			backFlyAnim.resetAnim();
			if(!isDicking()) {
			setSlading1(true);
			}
			else {
			setSladedick(true);	
			}
			lastTimeAttack=System.nanoTime();
			sladeSounds.play();
			break;
		case KeyEvent.VK_Z:
			if(isCombo1()) {
				setKamingup(true);
				setKame(true);
				setAttacking(true);
				lastTimeAttack=System.nanoTime();
				forwardFlyAnim.resetAnim();
				backFlyAnim.resetAnim();
				
			}else {
				
			setSlading2(true);
			setAttacking(true);
			lastTimeAttack=System.nanoTime();
			forwardFlyAnim.resetAnim();
			backFlyAnim.resetAnim();
			sladeSounds.play();
			}
			break;
		case KeyEvent.VK_X:
			if(isCombo1()) {
				setKaming1(true);
				setKame(true);
				setAttacking(true);
				lastTimeAttack=System.nanoTime();
				forwardFlyAnim.resetAnim();
				backFlyAnim.resetAnim();
				
			}
			else {
			setSlading3(true);
			setAttacking(true);
			lastTimeAttack=System.nanoTime();
			forwardFlyAnim.resetAnim();
			backFlyAnim.resetAnim();
			sladeSounds.play();
		}
		}
		}
	}
	
	
	public boolean isSladedick() {
		return sladedick;
	}

	public void setSladedick(boolean sladedick) {
		this.sladedick = sladedick;
	}

	public boolean isJumping() {
		
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
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

	public boolean isFlying() {
		return flying;
	}

	public void setFlying(boolean flying) {
		this.flying = flying;
	}

	public boolean isSlading3() {
		return slading3;
	}

	public void setSlading3(boolean slading3) {
		this.slading3 = slading3;
	}

	@Override
	public void jump() {
		if(isAttacking()) return;
		if(!isJumping()) {
			setLanding(false);
			countJump=1;
			setJumping(true);
			setSpeedY(MAXSPEEDY);
			if(getSpeedX()<0) {
				setSpeedX(-10);
			}else {
				if(getSpeedX()>0) setSpeedX(10);
			}
		
			setDicking(false);
		}else {
			if(countJump==1) {
			setSpeedY(MAXSPEEDY);
			
			//forwardJumpAnim.Update();
			//backJumpAnim.Update();
			forwardJumpAnim.setCurrentFrame(2);
			backJumpAnim.setCurrentFrame(2);
			if(getSpeedX()<0) {
				setSpeedX(-8);
			}else {
				if(getSpeedX()>0) setSpeedX(8);
			}
			
			countJump=2;
			setDicking(false);
			}
		}
		
		
	}
	
	public void kame() {
		KameTrunks kame=new KameTrunks(getPosX()+20,getPosY()+20,1,getGameWorld());
		kame.setDirect(getDirect());
		kame.setTeamType(LEAGUE_TEAM);
		if(getDirect()==LEFT_DIR) kame.setSpeedX(-50);
		else kame.setSpeedX(50);
		getGameWorld().getKameManager().addKame(kame);
	}
	
	public void kameup() {
	KameTrunks kameup=new KameTrunks(getPosX(),getPosY(),2,getGameWorld());
	kameup.setDirect(getDirect());
	kameup.setTeamType(LEAGUE_TEAM);
	if(getDirect()==LEFT_DIR) {
		kameup.setPosX(getPosX()-20);
		kameup.setSpeedX(-30);
	}else {
		kameup.setPosX(getPosX()+20);
		kameup.setSpeedX(30);
	}
	kameup.setSpeedY(-30);
	getGameWorld().getKameManager().addKame(kameup);
	
	}
	@Override
	public void stopRun() {
		setSpeedX(0);
		backFlyAnim.resetAnim();
		forwardFlyAnim.resetAnim();
	}

	@Override
	public void Update() {
		if(getState()==ALIVE) {
			beHurtBlackDragon();
			updateBeHurtKame();
			
			setPosX(getPosX()+getSpeedX());
			setSpeedY(getSpeedY()+getMass());
			setPosY(getPosY()+getSpeedY());
			if(getPosY()>=300) setPosY(300);
			if(System.nanoTime()-lastTimeCombo1>=timeStartCombo1) {
				setCombo1(false);
			}
			
			if(isAttacking()) {
				if(isSlading1()) {
					if(System.nanoTime()-lastTimeAttack>=timeForSlade1) {
						setSlading1(false);
						setAttacking(false);
						backSlade1Anim.resetAnim();
						forwardSlade1Anim.resetAnim();
					}
				}
				else {
					if(isSlading2()){
						if(System.nanoTime()-lastTimeAttack>=timeForSlade2) {
							setSlading2(false);
							setAttacking(false);
							backSlade2Anim.resetAnim();
							forwardSlade2Anim.resetAnim();
						}
						
					}
				else {
				if(isSlading3()) {
						if(System.nanoTime()-lastTimeAttack>=timeForSlade3) {
							setSlading3(false);
							setAttacking(false);
							backSlade3Anim.resetAnim();
							forwardSlade3Anim.resetAnim();
						}
				}
				else {
					if(isSladedick()) {
						if(forwardSladeDickAnim.isLastFrame() || backSladeDickAnim.isLastFrame()) {
							setSladedick(false);
							setAttacking(false);
							backSladeDickAnim.resetAnim();
							forwardSladeDickAnim.resetAnim();
						}
					}else if(isKaming1()) {
						if(forwardKame1Anim.isLastFrame() || backKame1Anim.isLastFrame()) {
							kame();
						}
						if(System.nanoTime()-lastTimeAttack>=timeForKaming1) {
							setAttacking(false);
							setKaming1(false);
							setKame(false);
							backKame1Anim.resetAnim();
							forwardKame1Anim.resetAnim();
						}
					}else if(isKamingup()) {
						if(forwardKameUpAnim.isLastFrame() || backKameUpAnim.isLastFrame()) {
							kameup();
						}
						if(System.nanoTime()-lastTimeAttack>=timeForKamingup) {
							setAttacking(false);
							setKamingup(false);
							setKame(false);
							backKameUpAnim.resetAnim();
							forwardKameUpAnim.resetAnim();
						}
					}
				}
				
				}
					
			}
			}
			if(isJumping()) {
				if(getPosY()==300) {
					setJumping(false);
					forwardJumpAnim.resetAnim();
					backJumpAnim.resetAnim();
				}
			}
			
		}
		if(getState()==BEHURT) {
			if(System.nanoTime()-lastTimeBehurt>=timeForBeHurt) {
				if(getBlood()<=0) setState(DEATH);
				else {
					setState(ALIVE);
				}
				
			}
		}
		
	}

	@Override
	public void draw(Graphics g) {
	Camera camera=getGameWorld().getCamera();
		switch(getState()) {
		case ALIVE:
			if(isAttacking()) {
				if(isSlading1()) {
					if(getDirect()==LEFT_DIR) {
						backSlade1Anim.Update();
						backSlade1Anim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
					}
					else {
						forwardSlade1Anim.Update();
						forwardSlade1Anim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
					}
				}
				if(isSlading2()) {
					if(getDirect()==LEFT_DIR) {
						backSlade2Anim.Update();
						backSlade2Anim.draw(g, getPosX()-camera.getPosX(), getPosY()-30-camera.getPosY());
					}
					else {
						forwardSlade2Anim.Update();
						forwardSlade2Anim.draw(g, getPosX()-camera.getPosX(), getPosY()-30-camera.getPosY());
					}
				}
				if(isSlading3()) {
					if(getDirect()==LEFT_DIR) {
						backSlade3Anim.Update();
						backSlade3Anim.draw(g, getPosX()-camera.getPosX(), getPosY()-20-camera.getPosY());
					}
					else {
						forwardSlade3Anim.Update();
						forwardSlade3Anim.draw(g, getPosX()-camera.getPosX(), getPosY()-20-camera.getPosY());
					}
				}
				if(isSladedick()) {
					if(getDirect()==LEFT_DIR) {
						backSladeDickAnim.Update();
						backSladeDickAnim.draw(g, getPosX()-camera.getPosX(), getPosY()+20-camera.getPosY());
					}
					else {
						forwardSladeDickAnim.Update();
						forwardSladeDickAnim.draw(g, getPosX()-camera.getPosX(), getPosY()+20-camera.getPosY());
					}
				}
				if(isKaming1()) {
					if(getDirect()==LEFT_DIR) {
						backKame1Anim.Update();
						backKame1Anim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY()-10);
					}
					else {
						forwardKame1Anim.Update();
						forwardKame1Anim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY()-10);
					}
				}
				if(isKamingup()) {
					if(getDirect()==LEFT_DIR) {
						backKameUpAnim.Update();
						backKameUpAnim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
					}
					else {
						forwardKameUpAnim.Update();
						forwardKameUpAnim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
					}
				}
			}else {
				if(isJumping()) {
					if(getDirect()==RIGHT_DIR) {
						forwardJumpAnim.Update();
						forwardJumpAnim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
					}else {
						backJumpAnim.Update();
						backJumpAnim.draw(g, getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
					}
				}
				else {
					if(isDicking()) {
						if(getDirect()==RIGHT_DIR) {
							forwardDickAnim.Update();
							forwardDickAnim.draw(g,getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
							
						}else {
							backDickAnim.Update();
							backDickAnim.draw(g,getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
						}
						
						}else {
					
				if(getSpeedX()!=0) {
					if(getDirect()==RIGHT_DIR) {
						forwardFlyAnim.Update();
						forwardFlyAnim.draw(g,getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
						
					}else {
						backFlyAnim.Update();
						backFlyAnim.draw(g,getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
					}
				}else {
					if(getDirect()==RIGHT_DIR) {
						forwardIdleAnim.Update();
						forwardIdleAnim.draw(g,getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
						
					}else {
						backIdleAnim.Update();
						backIdleAnim.draw(g,getPosX()-camera.getPosX(), getPosY()-camera.getPosY());
				
				}
				}
			}
			}
			}
			break;
		case BEHURT:
			switch(typeBeHurt) {
			case 1:
				if(getDirect()==LEFT_DIR) {
					backBeHurtStandAnim.Update();
					backBeHurtStandAnim.draw(g, getPosX()-camera.getPosX(),getPosY()-camera.getPosY() );
				}else {
					forwardBeHurtStandAnim.Update();
					forwardBeHurtStandAnim.draw(g, getPosX()-camera.getPosX(),getPosY()-camera.getPosY() );
				}
				break;
			case 2:
				if(getDirect()==LEFT_DIR) {
					backBeHurtSEAnim.Update();
					backBeHurtSEAnim.draw(g, getPosX()-camera.getPosX(),getPosY()-camera.getPosY() );
				}else {
					forwardBeHurtSEAnim.Update();
					forwardBeHurtSEAnim.draw(g, getPosX()-camera.getPosX(),getPosY()-camera.getPosY() );
				}
				break;
			}
			break;
		case DEATH:
			
			break;
		}
	}
	public void beHurtBlackDragon() {
		BlackDragon blackDragon=getGameWorld().getBlackDragon();
		double kcx,kcy;
		kcx=blackDragon.getPosX()-getPosX();
		kcy=Math.abs(blackDragon.getPosY()-getPosY());
		if(kcx>=0 && !isAttacking() && blackDragon.isAttacking() && getDirect()==RIGHT_DIR) {
			{
				if(Math.abs(kcx)<=30 && kcy<=30) {
					beHurt(10);
					lastTimeBehurt=System.nanoTime();
				}
			}
		}else {
			if(kcx<0 && !isAttacking() && blackDragon.isAttacking() && getDirect()==LEFT_DIR)
				{
					if(Math.abs(kcx)<=30 && kcy<=30) {
						beHurt(10);
						lastTimeBehurt=System.nanoTime();
					}
				}
		}
	}
	public void updateBeHurtKame() {
		double kcx,kcy;
		Kameha kame;
		ArrayList<Kameha> kameList=getGameWorld().getKameManager().getKamehaList();
		for(int i=0;i<kameList.size();i++) {
			kame=kameList.get(i);
			if(kame.getTeamType()!=getTeamType()) {
				kcx=Math.abs(getPosX()-kame.getPosX());
				kcy=Math.abs(getPosY()-kame.getPosY());
				if(kcx<=50 && kcy <=50)
				{
					beHurt(kame.getDamage());
					//beHurtSounds.play();
					lastTimeBehurt=System.nanoTime();
				}
			}
		}
	}
	public void beHurt(int damage) {
		setBlood(getBlood()-damage);
		if(getBlood()<=30) {
			typeBeHurt=BehurtSE;
		}else {
			typeBeHurt=BeHurtStand;
		}
		setState(BEHURT);
		beHurtSounds.play();
	}
	@Override
	public void dick() {
		setDicking(true);
		System.out.println("down1");
	}

	@Override
	public void standUp() {
	setCombo1(true);	
		lastTimeCombo1=System.nanoTime();
	}

	@Override
	public Rectangle getBoundForCollisionWithMap() {
		Rectangle rect;
		if(isSlading1()) {
			rect=new Rectangle();
			rect.x=(int)getPosX();
			rect.y=(int)getPosY();
			rect.width=167;
			rect.height=138;
			return rect;
		}
		else {
			if(isSlading2()) {
				rect=new Rectangle();
				rect.x=(int)getPosX();
				rect.y=(int)getPosY();
				rect.width=189;
				rect.height=179;
				return rect;
			}
			else {
				if(isSlading3()) {
					rect=new Rectangle();
					rect.x=(int)getPosX();
					rect.y=(int)getPosY();
					rect.width=90;
					rect.height=100;
					return rect;
				}
				else {
					if(isSladedick()) {
							rect=new Rectangle();
							rect.x=(int)getPosX();
							rect.y=(int)getPosY();
							rect.width=196;
							rect.height=109;
							return rect;
						}
					else {
						if(isKaming1()) {
							rect=new Rectangle();
							rect.x=(int)getPosX();
							rect.y=(int)getPosY();
							rect.width=103;
							rect.height=154;
							return rect;
							
						}else {
							if(isKamingup()) {
								rect=new Rectangle();
								rect.x=(int)getPosX();
								rect.y=(int)getPosY();
								rect.width=93;
								rect.height=136;
								return rect;
							}else {
						
						if(isDicking()) {
							rect=new Rectangle();
							rect.x=(int)getPosX();
							rect.y=(int)getPosY();
							rect.width=75;
							rect.height=127;
							return rect;
						}else {
							if(isJumping()) {
								rect=new Rectangle();
								rect.x=(int)getPosX();
								rect.y=(int)getPosY();
								rect.width=93;
								rect.height=159;
								return rect;
							}else {
								if(getSpeedX()!=0) {
									rect=new Rectangle();
									rect.x=(int)getPosX();
									rect.y=(int)getPosY();
									rect.width=100;
									rect.height=101;
									return rect;
								}
								else {
									rect=new Rectangle();
									rect.x=(int)getPosX();
									rect.y=(int)getPosY();
									rect.width=101;
									rect.height=100;
									return rect;
								}
							}
						}
					}
					}
				}
				
			}
		}
		}
	}

	
}
