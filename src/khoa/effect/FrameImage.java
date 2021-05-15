package khoa.effect;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class FrameImage {
	
	private String name;
	private int width;
	private int height;
	private BufferedImage image;
	public FrameImage(FrameImage fi) {
		
		this.image=new BufferedImage(fi.getImage().getWidth(), fi.getImage().getHeight(), fi.getImage().getType());
		this.width=fi.getImage().getWidth();
		this.height=fi.getImage().getHeight();
		Graphics g=image.getGraphics();
		g.drawImage(fi.getImage(), 0, 0, null);
	
	}
	public FrameImage(){
		
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	public FrameImage(String name,int width,int height,BufferedImage image) {
		this.name=name;
		this.width=width;
		this.height=height;
		//this.image=new BufferedImage(image.getWidth(),image.getHeight(),);
	}
	
	
}
