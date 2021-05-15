package khoa.effect;

import java.awt.BufferCapabilities;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;

import javax.imageio.ImageIO;

public class CacheDataLoader {
	
	private String frameTrunks ="Data/txtData/trunks.txt";
	private String frameFighterGuy="Data/txtData/fighter_guy.txt";
	private String AnimSasuke="Data/txtData/sasuke.txt";
	private String AnimTrunks="Data/txtData/TrunksAnim.txt";
	private String backgroundmapfile = "Data/txtData/background_map.txt";
	private String framefile="Data/txtData/frame.txt";
	private String physmaptxt="Data/txtData/phys_map.txt";
	private String piccolotxt="Data/txtData/Piccolo.txt";
	

	private int[][] map;
	
	public int[][] getMap() {
		return map;
	}
	public Hashtable<String, FrameImage> getFrameImages() {
		return frameImages;
	}
	private Hashtable<String,FrameImage> frameImages;
	private Hashtable<String,Animation> TrunksAnimations;
	private Hashtable<String,Animation> SasukeAnimations;
	private Hashtable<String,Animation> KamehaAnimations;
	private Hashtable<String,Animation> PiccoloAnimations;
	private Hashtable<String,Animation> KameGokuAnimations;
	private Hashtable<String,AudioClip> sounds;
	private Hashtable<String,Animation> fireAnimations;
	private Hashtable<String,Animation> blackDragonAnim;
	private BufferedImage bk;
	
	private int[][] background_map;
	
	public void loadData() throws IOException {
		LoadFrame();
		loadTrunksAnim();
		loadSasukeAnim();
		loadKameAnim();
		loadBackground();
		loadPhysMap();
		loadPiccoloAnim();
		loadKameGoku();
		loadFireAnim();
		loadBlackDragonAnim();
		try {
			LoadBackgroundMap();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loadSounds();
	}
	public Hashtable<String, Animation> getFireAnimations() {
		return fireAnimations;
	}
	public Hashtable<String, Animation> getTrunksAnimations() {
		return TrunksAnimations;
	}
	public CacheDataLoader() throws IOException {
		
		TrunksAnimations=new Hashtable<String,Animation>();
		SasukeAnimations=new Hashtable<String,Animation>();
		KamehaAnimations=new Hashtable<String,Animation>();
		PiccoloAnimations=new Hashtable<String,Animation>();
		KameGokuAnimations=new Hashtable<String,Animation>();
		sounds=new Hashtable<String,AudioClip>();
		fireAnimations=new Hashtable<String,Animation>();
		blackDragonAnim=new Hashtable<String,Animation>();
		loadData();
	}
	
	
	public void loadBackground() {
		try {
			bk=ImageIO.read(new File("D:/HINH DO HOA/bg3.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public BufferedImage getBk() {
		return bk;
	}
	public void loadGuyFrame() {
		
	}
	public Hashtable<String, Animation> getKamehaAnimations() {
		return KamehaAnimations;
	}
	public void loadTrunksFrame() {
		try {
			FileReader fr=new FileReader(frameTrunks);
			BufferedReader br=new BufferedReader(fr);
			FrameImage frameImage;
			String line;
			String[] str;
			BufferedImage image,bigImage;
			bigImage=ImageIO.read(new File("Data/Image/Future Trunks.png"));
			int n;
			while((line=br.readLine()).equals(""));
			
			n=Integer.parseInt(line);
			for(int i=0;i<n;i++) {
				frameImage=new FrameImage();
				
				while((line=br.readLine()).equals(""));
				frameImage.setName(line);
				
				
				while((line=br.readLine()).equals(""));
				str=line.split(" ");
				int x=Integer.parseInt(str[1]);
				while((line=br.readLine()).equals(""));
				str=line.split(" ");
				int y=Integer.parseInt(str[1]);
				
				while((line=br.readLine()).equals(""));
				str=line.split(" ");
				int w=Integer.parseInt(str[1]);
				
				while((line=br.readLine()).equals(""));
				str=line.split(" ");
				int h=Integer.parseInt(str[1]);
				
				image=bigImage.getSubimage(x, y, w, h);
				
				frameImage.setImage(image);
				frameImage.setWidth(w);
				frameImage.setHeight(h);
				
				frameImages.put(frameImage.getName(), frameImage);
			}
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void loadTrunksAnim() {
		try {
			FileReader fr=new FileReader(AnimTrunks);
			BufferedReader br=new BufferedReader(fr);
			String line;
			String[] str;
			BufferedImage bigImage,image;
			bigImage=ImageIO.read(new File("Data/Image/Future Trunks.png"));
			
			int n;
			Animation anim;
			while((line=br.readLine()).equals(""));
			n=Integer.parseInt(line);
			
			for(int i=0;i<n;i++) {
				int m,xstart,ystart,w,h;
				anim=new Animation();
				while((line=br.readLine()).equals(""));
				str=line.split(" ");
				anim.setName(str[0]);
				m=Integer.parseInt(str[1]);
				xstart=Integer.parseInt(str[2]);
				ystart=Integer.parseInt(str[3]);
				w=Integer.parseInt(str[4]);
				h=Integer.parseInt(str[5]);
				FrameImage frameTemp;
				for(int j=0;j<m;j++) {
					frameTemp=new FrameImage();
					frameTemp.setName(anim.getName()+String.valueOf(j+1));
					image=bigImage.getSubimage(xstart+w*j, ystart, w, h);
					frameTemp.setImage(image);
					anim.addFrame(frameTemp);
					if(anim.getName().equals("jumpt1")) {
						
						anim.getTimeSeparate().add((long)100*1000000);
						
					}else {
						if(anim.getName().equals("kameup")) {
							anim.getTimeSeparate().add((long)80*1000000);
						}
					else {
					anim.getTimeSeparate().add((long)50*1000000);
					}
					
				}
				anim.resetAnim();
				
				TrunksAnimations.put(anim.getName(), anim);
			}
			
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Hashtable<String, Animation> getPiccoloAnimations() {
		return PiccoloAnimations;
	}
	public void loadPiccoloAnim() {
		try {
			FileReader fr=new FileReader(piccolotxt);
			BufferedReader br=new BufferedReader(fr);
			String line;
			String[] str;
			BufferedImage bigImage,image;
			bigImage=ImageIO.read(new File("Data/Image/goku1.png"));
			
			int n;
			Animation anim;
			while((line=br.readLine()).equals(""));
			n=Integer.parseInt(line);
			
			for(int i=0;i<n;i++) {
				int m,xstart,ystart,w,h;
				anim=new Animation();
				while((line=br.readLine()).equals(""));
				str=line.split(" ");
				anim.setName(str[0]);
				m=Integer.parseInt(str[1]);
				xstart=Integer.parseInt(str[2]);
				ystart=Integer.parseInt(str[3]);
				w=Integer.parseInt(str[4]);
				h=Integer.parseInt(str[5]);
				FrameImage frameTemp;
				for(int j=0;j<m;j++) {
					frameTemp=new FrameImage();
					frameTemp.setName(anim.getName()+String.valueOf(j+1));
					image=bigImage.getSubimage(xstart+w*j, ystart, w, h);
					frameTemp.setImage(image);
					anim.addFrame(frameTemp);
					if(anim.getName().equals("jumpt1")) {
					anim.getTimeSeparate().add((long)100*1000000);
					}else
						{
						if(anim.getName().equals("kamet2")){
							anim.getTimeSeparate().add((long)70*1000000);
						}else {
							
						if(anim.getName().equals("behurtstand")||anim.getName().equals("behurtstand") ) {
							anim.getTimeSeparate().add((long)120*1000000);
						}else
						anim.getTimeSeparate().add((long)60*1000000);
						}
						}
						
					
				}
				anim.resetAnim();
				
				PiccoloAnimations.put(anim.getName(), anim);
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Hashtable<String, Animation> getKameGokuAnimations() {
		return KameGokuAnimations;
	}
	public Hashtable<String, Animation> getSasukeAnimations() {
		return SasukeAnimations;
	}
	public void loadSasukeAnim() {
		try {
			FileReader fr=new FileReader(AnimSasuke);
			BufferedReader br=new BufferedReader(fr);
			String line;
			String[] str;
			BufferedImage bigImage,image;
			bigImage=ImageIO.read(new File("Data/Image/sasuke.png"));
			
			int n;
			Animation anim;
			while((line=br.readLine()).equals(""));
			n=Integer.parseInt(line);
			
			for(int i=0;i<n;i++) {
				int m,xstart,ystart,w,h;
				anim=new Animation();
				while((line=br.readLine()).equals(""));
				str=line.split(" ");
				anim.setName(str[0]);
				m=Integer.parseInt(str[1]);
				xstart=Integer.parseInt(str[2]);
				ystart=Integer.parseInt(str[3]);
				w=Integer.parseInt(str[4]);
				h=Integer.parseInt(str[5]);
				FrameImage frameTemp;
				for(int j=0;j<m;j++) {
					frameTemp=new FrameImage();
					frameTemp.setName(anim.getName()+String.valueOf(j+1));
					image=bigImage.getSubimage(xstart+w*j, ystart, w, h);
					frameTemp.setImage(image);
					anim.addFrame(frameTemp);
					if(anim.getName().equals("run"))
					anim.getTimeSeparate().add((long)100*1000000);
					else {
						if(anim.getName().equals("sladet2")) 
						{
							if(j==0)  anim.getTimeSeparate().add((long)50*1000000);
							
						else 
						anim.getTimeSeparate().add((long)100*1000000);
						
					
						}else anim.getTimeSeparate().add((long)100*1000000);
				}
				anim.resetAnim();
				SasukeAnimations.put(anim.getName(), anim);
			}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int[][] getBackground_map() {
		return background_map;
	}
	public FrameImage getFrameImage(String name){

        FrameImage frameImage = new FrameImage(frameImages.get(name));
        return frameImage;

    }
	public void loadKameAnim() {
		
		Animation anim=new Animation();
		Animation anim2=new Animation();
		BufferedImage image,bigImage;
		FrameImage frameImage; 
		try {
			bigImage=ImageIO.read(new File("Data/Image/getKame.png"));
			image=bigImage.getSubimage(360, 1249, 91, 81);
			frameImage=new FrameImage();
			frameImage.setImage(image);
			frameImage.setWidth(91);
			frameImage.setHeight(81);
			
			anim.addFrame(frameImage);
			anim.setName("kameha");
			for(int i=0;i<3;i++) {
				image=bigImage.getSubimage(51*i, 1261,51 , 59);
				frameImage=new FrameImage();
				frameImage.setImage(image);
				frameImage.setWidth(image.getWidth());
				frameImage.setHeight(image.getHeight());
				anim.addFrame(frameImage);
				anim2.addFrame(frameImage);
			}
			for(int i=0;i<=3;i++) {
				if(i==0) anim.getTimeSeparate().add((long)150*1000000);
				else {
				anim2.getTimeSeparate().add((long)100*1000000);	
				anim.getTimeSeparate().add((long)50*1000000);
				}
			}
			anim2.setName("kameup");
			Animation anim3=new Animation(anim2);
			anim3.setName("kamedown");
			KamehaAnimations.put(anim.getName(), anim);
			KamehaAnimations.put(anim2.getName(), anim2);
			KameGokuAnimations.put(anim3.getName(), anim2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
public void LoadBackgroundMap() throws IOException{
        
        FileReader fr = new FileReader(backgroundmapfile);
        BufferedReader br = new BufferedReader(fr);
        
        String line = null;
        
        line = br.readLine();
        int numberOfRows = Integer.parseInt(line);
        line = br.readLine();
        int numberOfColumns = Integer.parseInt(line);
            
        
        background_map = new int[numberOfRows][numberOfColumns];
        
        for(int i = 0;i < numberOfRows;i++){
            line = br.readLine();
            String [] str = line.split(" |  ");
            for(int j = 0;j<numberOfColumns;j++)
                background_map[i][j] = Integer.parseInt(str[j]);
        }
        
        for(int i = 0;i < numberOfRows;i++){
            
            for(int j = 0;j<numberOfColumns;j++)
                System.out.print(" "+background_map[i][j]);
            
            System.out.println();
        }
        
        br.close();
        
    }
public void LoadFrame() throws IOException{
    
    frameImages = new Hashtable<String, FrameImage>();
    
    FileReader fr = new FileReader(framefile);
    BufferedReader br = new BufferedReader(fr);
    
    String line = null;
    
    if(br.readLine()==null) {
        System.out.println("No data");
        throw new IOException();
    }
    else {
        
        fr = new FileReader(framefile);
        br = new BufferedReader(fr);
        
        while((line = br.readLine()).equals(""));
        
        int n = Integer.parseInt(line);
        
        for(int i = 0;i < n; i ++){
            
            FrameImage frame = new FrameImage();
            while((line = br.readLine()).equals(""));
            frame.setName(line);
            
            while((line = br.readLine()).equals(""));
            String[] str = line.split(" ");
            String path = str[1];
            
            while((line = br.readLine()).equals(""));
            str = line.split(" ");
            int x = Integer.parseInt(str[1]);
            
            while((line = br.readLine()).equals(""));
            str = line.split(" ");
            int y = Integer.parseInt(str[1]);
            
            while((line = br.readLine()).equals(""));
            str = line.split(" ");
            int w = Integer.parseInt(str[1]);
            
            while((line = br.readLine()).equals(""));
            str = line.split(" ");
            int h = Integer.parseInt(str[1]);
            
            BufferedImage imageData = ImageIO.read(new File(path));
            BufferedImage image = imageData.getSubimage(x, y, w, h);
            frame.setImage(image);
            
            frameImages.put(frame.getName(), frame);
        }
        
    }
    
    br.close();
    
}
public void loadKameGoku() throws IOException {
	String line,path="Data/txtData/kamegoku.txt";
	String[] str;
	int n,x,y,w,h;
	FileReader fr=new FileReader(path);
	BufferedReader br=new BufferedReader(fr);
	BufferedImage image,bigImage;
	Animation anim;
	FrameImage frameImage;
	bigImage=ImageIO.read(new File("Data/Image/gohan.png"));
	while((line=br.readLine()).equals(""));
	n=Integer.parseInt(line);
	for(int i=0;i<n;i++) {
		anim=new Animation();
		while((line=br.readLine()).equals(""));
		str=line.split(" ");
		anim.setName(str[0]);
		int m=Integer.parseInt(str[1]);
		x=Integer.parseInt(str[2]);
		y=Integer.parseInt(str[3]);
		w=Integer.parseInt(str[4]);
		h=Integer.parseInt(str[5]);
		
		
		for(int j=0;j<m;j++) {
			frameImage=new FrameImage();
			image=bigImage.getSubimage(x+j*w, y, w, h);
			frameImage.setImage(image);
			frameImage.setHeight(h);
			frameImage.setWidth(w);
			anim.addFrame(frameImage);
		}
		for(int k=0;k<m;k++) {
			anim.getTimeSeparate().add((long)70*1000000);
		}
		anim.resetAnim();
		KameGokuAnimations.put(anim.getName(), anim);
	}
}

public void loadPhysMap() throws IOException   {
	FileReader fr=new FileReader(physmaptxt);
	BufferedReader br=new BufferedReader(fr);
	int row,col,i,j;
	String line;
	String[] str;
	while((line=br.readLine()).equals(""));
	row=Integer.parseInt(line);
	while((line=br.readLine()).equals(""));
	col=Integer.parseInt(line);
	map=new int[row][col];
	
	for( i=0;i<row;i++) {
		while((line=br.readLine()).equals(""));
		str=line.split(" ");
		for(j=0;j<col;j++) {
			map[i][j]=Integer.parseInt(str[j]);
		}
	}
}
public void loadSounds() throws IOException {
	
	FileReader fr=new FileReader("Data/txtData/sounds.txt");
	BufferedReader br=new BufferedReader(fr);
	String name,line,path;
	AudioClip audio;
	String[] str; 
	int n;
	while((line=br.readLine()).equals(""));
	n=Integer.parseInt(line);
	for(int i=0;i<n;i++) {
		audio=null;
		while((line=br.readLine()).equals(""));
		str=line.split(" ");
		name=str[0];
		path=str[1];
		audio=Applet.newAudioClip(new URL("file","",path));
		sounds.put(name, audio);
	}
	
}
public void loadFireAnim() throws IOException {
	FileReader fr=new FileReader("Data/txtData/fire.txt");
	BufferedReader br=new BufferedReader(fr);
	BufferedImage image,bigImage;
	bigImage=ImageIO.read(new File("Data/Image/fire.png"));
	FrameImage frameImage;
	String line;
	int n,x,y,w,h;
	String[] str; 
	Animation anim;
	while((line=br.readLine()).equals(""));
	n=Integer.parseInt(line);
	for(int i=0;i<n;i++) {
		anim=new Animation();
		while((line=br.readLine()).equals(""));
		str=line.split(" ");
		anim.setName(str[0]);
		int m=Integer.parseInt(str[1]);
		x=Integer.parseInt(str[2]);
		y=Integer.parseInt(str[3]);
		w=Integer.parseInt(str[4]);
		h=Integer.parseInt(str[5]);
		
		
		for(int j=0;j<m;j++) {
			frameImage=new FrameImage();
			image=bigImage.getSubimage(x+j*w, y, w, h);
			frameImage.setImage(image);
			frameImage.setHeight(h);
			frameImage.setWidth(w);
			anim.addFrame(frameImage);
		}
		for(int k=0;k<m;k++) {
			anim.getTimeSeparate().add((long)60*1000000);
		}
		anim.resetAnim();
		fireAnimations.put(anim.getName(), anim);
	
	}
}

public void loadBlackDragonAnim() throws IOException {
	FileReader fr=new FileReader("Data/txtData/blackdragon.txt");
	BufferedReader br=new BufferedReader(fr);
	BufferedImage image,bigImage;
	bigImage=ImageIO.read(new File("Data/Image/blackdragon.png"));
	FrameImage frameImage;
	String line;
	int n,x,y,w,h;
	String[] str; 
	Animation anim;
	while((line=br.readLine()).equals(""));
	n=Integer.parseInt(line);
	for(int i=0;i<n;i++) {
		anim=new Animation();
		while((line=br.readLine()).equals(""));
		str=line.split(" ");
		anim.setName(str[0]);
		int m=Integer.parseInt(str[1]);
		x=Integer.parseInt(str[2]);
		y=Integer.parseInt(str[3]);
		w=Integer.parseInt(str[4]);
		h=Integer.parseInt(str[5]);
		
		
		for(int j=0;j<m;j++) {
			frameImage=new FrameImage();
			image=bigImage.getSubimage(x+j*w, y, w, h);
			frameImage.setImage(image);
			frameImage.setHeight(h);
			frameImage.setWidth(w);
			anim.addFrame(frameImage);
		}
		for(int k=0;k<m;k++) {
			if(anim.getName().equals("flyingfire") || anim.getName().equals("behurt"))
			anim.getTimeSeparate().add((long)130*1000000);
			else anim.getTimeSeparate().add((long)60*1000000);;
		}
		anim.resetAnim();
		blackDragonAnim.put(anim.getName(), anim);
	
	}
}
public Hashtable<String, Animation> getBlackDragonAnim() {
	return blackDragonAnim;
}
public Hashtable<String, AudioClip> getSounds() {
	return sounds;
}
	
}
