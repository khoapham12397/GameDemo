package khoa.object;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import khoa.effect.Animation;
import khoa.effect.FrameImage;

public class FighterGuy extends Human {

	public Animation getForwardWalkAnim() {
		return forwardWalkAnim;
	}

	

	Animation forwardIdleAnim,backIdleAnim;
	Animation forwardRunAnim,backRunAim;
	Animation forwardJumpAnim,backJumpAnim;
	Animation forwardPunchAnim,backPunchAnim;
	Animation forwardKickAnim,backKickAnim;
    Animation forwarrdAttackAnim,backAttackAnim;
	Animation forwardWalkAnim,backWalkAnim;
    Animation forwardFFAnim,backFFAnim;
	public FighterGuy(double posX, double posY, int width, int height, int damage1, int blood,GameWorld gameWorld) throws IOException {
		super(posX, posY, width, height, damage1, blood,gameWorld);
		forwardWalkAnim=new Animation();
		forwardWalkAnim.resetAnim();
		forwardFFAnim =new Animation();
		createAnim();
		setSpeedX(0);
	}
	public void createAnim() {
		FrameImage frameImage;
		try {
			FileReader fr=new FileReader("Data/txtData/fighterGuy.txt");
			BufferedReader br=new BufferedReader(fr);
			BufferedImage bigImage,image;
			bigImage=ImageIO.read(new File("Data/Image/fighter_guy.png"));
			String name,line;
			String[] s1;
			int x=0,y=0,w=0,h=0;
			for(int i=0;i<6;i++) {
				frameImage=new FrameImage();
				while((line=br.readLine()).equals("")) ;
				frameImage.setName(line);
				
				while((line=br.readLine()).equals(""));
				s1=line.split(" ");
				x=Integer.parseInt(s1[1]);
				
				while((line=br.readLine()).equals(""));
				s1=line.split(" ");
				y=Integer.parseInt(s1[1]);
				
				while((line=br.readLine()).equals(""));
				s1=line.split(" ");
				w=Integer.parseInt(s1[1]);
				
				while((line=br.readLine()).equals(""));
				s1=line.split(" ");
				h=Integer.parseInt(s1[1]);
				
				frameImage.setWidth(w);
				frameImage.setHeight(h);
				frameImage.setImage(bigImage.getSubimage(x, y, w, h));
				forwardWalkAnim.addFrame(frameImage);
				forwardWalkAnim.getTimeSeparate().add((long)50*1000000);
				
			}
			forwardWalkAnim.setRepeat(true);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void stopRun() {
		setSpeedX(0);
	}
	@Override
	public void run() {
		setSpeedX(5);
		
	}

	@Override
	public void attack(int keyCode) {
		
	}

	@Override
	public void jump() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void draw(Graphics g) {
		if(getSpeedX()!=0) {
		forwardWalkAnim.Update();
		forwardWalkAnim.draw(g,(int)getPosX(),(int)getPosY());
		}
		else {
			forwardWalkAnim.setCurrentFrame(0);
			forwardWalkAnim.draw(g, getPosX(), getPosY());
		}
	}
	@Override
	public void Update() {
		setPosX(getPosX()+getSpeedX());
		
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
