package khoa.object;

import java.awt.Graphics;

import khoa.effect.Animation;

public class KameTrunks extends Kameha{

	private Animation forwardKameAnim,backKameAnim;
	private Animation forwardKameUpAnim,backKameUpAnim;
	
	private int type;
	public KameTrunks(double posX, double posY,int type, GameWorld gameWorld) {
		super(posX, posY,20,gameWorld);
		this.type=type;
		
		forwardKameAnim=getGameWorld().getCacheDataLoader().getKamehaAnimations().get("kameha");
		backKameAnim=getGameWorld().getCacheDataLoader().getKamehaAnimations().get("kameha");
		//backKameAnim.flipAllImage();
		backKameAnim.resetAnim();
		forwardKameAnim.resetAnim();
		
		forwardKameUpAnim=getGameWorld().getCacheDataLoader().getKamehaAnimations().get("kameup");
		backKameUpAnim=getGameWorld().getCacheDataLoader().getKamehaAnimations().get("kameup");
		//backKameUpAnim.flipAllImage();
		forwardKameUpAnim.resetAnim();
		backKameUpAnim.resetAnim();
		
	}
	
	@Override
	public void Update() {
		super.Update();
	}
	
	@Override 
	public void draw(Graphics g) {
		Camera camera=getGameWorld().getCamera();
		if(type==1) {
	
			if(getDirect()==LEFT_DIR) {
				backKameAnim.Update();
				backKameAnim.draw(g, getPosX()-(int)camera.getPosX(), getPosY()-(int)camera.getPosY());
			}else {
				forwardKameAnim.Update();
				forwardKameAnim.draw(g, getPosX()-(int)camera.getPosX(), getPosY()-(int)camera.getPosY());
			}
		
		}
		else {
			
				if(getDirect()==LEFT_DIR) {
					backKameUpAnim.Update();
					backKameUpAnim.draw(g, getPosX()-(int)camera.getPosX(), getPosY());
				}else {
					forwardKameUpAnim.Update();
					forwardKameUpAnim.draw(g, getPosX()-(int)camera.getPosX(), getPosY());
				}
			
		}
	}
}
