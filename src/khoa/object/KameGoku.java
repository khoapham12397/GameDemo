package khoa.object;

import java.awt.Graphics;

import khoa.effect.Animation;

public class KameGoku extends Kameha{

	private Animation forwardLongKame,backLongKame;
	private Animation forwardSmallKame,backSmallKame;
	private Animation forwardDownKame,backDownKame;
	private Animation forwardLKameUpAnim,backLKameUpAnim;
	private int type ;
	private long timeExist=300*1000000;
	public long getStartKame() {
		return startKame;
	}

	public void setStartKame(long startKame) {
		this.startKame = startKame;
	}

	public long getTimeExist() {
		return timeExist;
	}
	private long startKame;
	public KameGoku(double posX, double posY, int damage,int type,GameWorld gameWorld) {
		super(posX, posY, damage, gameWorld);
		forwardLongKame=getGameWorld().getCacheDataLoader().getKameGokuAnimations().get("longkame");
		backLongKame=new Animation(getGameWorld().getCacheDataLoader().getKameGokuAnimations().get("longkame"));
		backLongKame.flipAllImage();
		forwardLongKame.resetAnim();
		backLongKame.resetAnim();
		
		forwardSmallKame=getGameWorld().getCacheDataLoader().getKameGokuAnimations().get("smallkame");
		backSmallKame=new Animation(getGameWorld().getCacheDataLoader().getKameGokuAnimations().get("smallkame"));
		backSmallKame.flipAllImage();
		forwardSmallKame.resetAnim();
		backSmallKame.resetAnim();
		
		
		forwardLKameUpAnim=getGameWorld().getCacheDataLoader().getKameGokuAnimations().get("longkameup");
		backLKameUpAnim=new Animation(getGameWorld().getCacheDataLoader().getKameGokuAnimations().get("longkameup"));
		backLKameUpAnim.flipAllImage();
		forwardLKameUpAnim.resetAnim();
		backLKameUpAnim.resetAnim();
		
		forwardDownKame=getGameWorld().getCacheDataLoader().getKameGokuAnimations().get("kamedown");
		backDownKame=getGameWorld().getCacheDataLoader().getKameGokuAnimations().get("kamedown");
		//backDownKame.flipAllImage();
		forwardDownKame.resetAnim();
		backDownKame.resetAnim();
		this.type=type;
	}
	
	public void Update() {
		if(this.outOfCamera()) {
			getGameWorld().getKameManager().removeKame(this);
		}else {
			setPosX(getPosX()+getSpeedX());
			setPosY(getPosY()+getSpeedY());
		}
		if(type==1 ) {
			if(System.nanoTime()-getStartKame()>timeExist) 
			{
				getGameWorld().getKameManager().removeKame(this);
			}
			setPosX(getPosX()+getGameWorld().getPiccolo().getSpeedX());
			
		}
		
	}
	@Override
	public void draw(Graphics g) {
		Camera camera =getGameWorld().getCamera();
		switch(type) {
		case 1:
			
			if(getDirect()==LEFT_DIR) {
				backLongKame.Update();
				backLongKame.draw(g, getPosX() -camera.getPosX(), getPosY()-camera.getPosY());
			}else {
				forwardLongKame.Update();
				forwardLongKame.draw(g, getPosX() -camera.getPosX(), getPosY()-camera.getPosY());
			}
			
			break;
		case 2:
			if(getDirect()==LEFT_DIR) {
				backSmallKame.Update();
				backSmallKame.draw(g, getPosX() -camera.getPosX(), getPosY()-camera.getPosY());
			}else {
				forwardSmallKame.Update();
				forwardSmallKame.draw(g, getPosX() -camera.getPosX(), getPosY()-camera.getPosY());
			}
			break;
		case 3:
			if(getDirect()==LEFT_DIR) {
				backDownKame.Update();
				backDownKame.draw(g, getPosX() -camera.getPosX(), getPosY()-camera.getPosY());
			}else {
				forwardDownKame.Update();
				forwardDownKame.draw(g, getPosX() -camera.getPosX(), getPosY()-camera.getPosY());
			}
			
			break;
		/*case 4:
			if(getDirect()==LEFT_DIR) {
				backLKameUpAnim.Update();
				backLKameUpAnim.draw(g, getPosX() -camera.getPosX(), getPosY()-camera.getPosY());
			}else {
				forwardLKameUpAnim.Update();
				forwardLKameUpAnim.draw(g, getPosX() -camera.getPosX(), getPosY()-camera.getPosY());
			}
			
			break;*/
		}
		
		
	}

}
