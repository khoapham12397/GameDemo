package khoa.effect;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {
	
	int countImage;
	private String name;
	int currentFrame;
	long currentTime;
	
	private ArrayList<FrameImage> frameImages;
	private ArrayList<Long> timeSeparate;
	private ArrayList<Boolean> ignoreFrames;
	private boolean repeat;
	
	 public void flipAllImage(){
	        
	        for(int i = 0;i < frameImages.size(); i++){
	            
	            BufferedImage image = frameImages.get(i).getImage();
	            
	            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
	            tx.translate(-image.getWidth(null),0);

	            AffineTransformOp op = new AffineTransformOp(tx,
	            AffineTransformOp.TYPE_BILINEAR);
	            image = op.filter(image, null);
	            
	            frameImages.get(i).setImage(image);
	            
	        }
	        
	    }
	    
	
	public Animation() {
		frameImages=new ArrayList<FrameImage>();
		timeSeparate=new ArrayList<Long>();
	}
	public Animation(Animation anim) {
		 
		this.name=new String(anim.name);
		this.frameImages=new ArrayList<FrameImage>();
		FrameImage frameImage;
		for(int i=0;i<anim.getFrameImages().size();i++) {
			frameImage=new FrameImage(anim.getFrameImages().get(i));
			this.frameImages.add(frameImage);
		}
		this.currentFrame=0;
		this.currentTime=0;
		this.timeSeparate=new ArrayList<Long>();
		Long m;
		for(int i=0;i<anim.getTimeSeparate().size();i++) {
			m=new Long(anim.getTimeSeparate().get(i).longValue());
			this.timeSeparate.add(m);
		}
	}
	
	public void addFrame(FrameImage frameImage) {
		frameImages.add(frameImage);
	}
	public void Update() {
		if(currentTime==0) {
			
			currentFrame=0;
			currentTime=System.nanoTime();
			
		}else {
			if(currentFrame==frameImages.size()-1) {
				if(repeat) {
					if(System.nanoTime()-currentTime>=50*1000000) {
						currentFrame=0;
						currentTime=System.nanoTime();
					}
				}
			}else {
				if(System.nanoTime()-currentTime>=timeSeparate.get(currentFrame)) {
					
					
					currentFrame+=1;
					currentTime=System.nanoTime();
				}
			}
		}
	
	}
	public int getCountImage() {
		return countImage;
	}

	public void setCountImage(int countImage) {
		this.countImage = countImage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(long currentTime) {
		this.currentTime = currentTime;
	}

	public ArrayList<FrameImage> getFrameImages() {
		return frameImages;
	}

	public void setFrameImages(ArrayList<FrameImage> frameImages) {
		this.frameImages = frameImages;
	}

	public ArrayList<Long> getTimeSeparate() {
		return timeSeparate;
	}

	public void setTimeSeparate(ArrayList<Long> timeSeparate) {
		this.timeSeparate = timeSeparate;
	}

	public ArrayList<Boolean> getIgnoreFrames() {
		return ignoreFrames;
	}

	public void setIgnoreFrames(ArrayList<Boolean> ignoreFrames) {
		this.ignoreFrames = ignoreFrames;
	}

	public boolean isRepeat() {
		return repeat;
	}

	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
	}

	public int getCurrentFrame() {
		return currentFrame;
	}

	public void setCurrentFrame(int currentFrame) {
		this.currentFrame=currentFrame;
	}
	public void resetAnim() {
		currentFrame=0;
		currentTime=0;
	}
	public void draw(Graphics g,double x, double y) {
		g.drawImage(frameImages.get(currentFrame).getImage(),(int)x,(int)y,null);
	}
	public boolean isLastFrame() {
		if(currentFrame==frameImages.size()-1) return true;
		return false;
	}
}
