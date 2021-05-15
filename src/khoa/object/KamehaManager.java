package khoa.object;

import java.awt.Graphics;
import java.util.ArrayList;

public class KamehaManager {
	private ArrayList<Kameha> kamehaList;
	private GameWorld gameWorld;
	
	public KamehaManager(GameWorld gameWorld) {
		kamehaList=new ArrayList<Kameha>();
		this.gameWorld=gameWorld;
	}
	public ArrayList<Kameha> getKamehaList() {
		return kamehaList;
	}
	public void addKame(Kameha kame) {
		kamehaList.add(kame);
		
	}
	public void removeKame(Kameha kame) {
		kamehaList.remove(kame);
	}
	
	public void Update() {
		for(int i=0;i<kamehaList.size();i++) {
			if(kamehaList.get(i).outOfCamera()) {
				removeKame(kamehaList.get(i));
			}
			else {
				if(haveCollisionWithObjects(kamehaList.get(i))) {
					removeKame(kamehaList.get(i));
				}else {
					kamehaList.get(i).Update();
				}
			}
		}
	}
	
	public boolean haveCollisionWithObjects(Kameha kame) {
		double kcx,kcy;
		ArrayList<ParticularObject> objectList=gameWorld.getManager().particularObjects;
		for(int i=0;i<objectList.size();i++) {
			kcx=Math.abs(kame.getPosX()-objectList.get(i).getPosX());
			kcy=Math.abs(kame.getPosY()-objectList.get(i).getPosY());
			if(kcx<=30 && kcy<=30 && kame.getTeamType()!=objectList.get(i).getTeamType()) {
				return true;
			}
		}
		
		return false;
	}
	public void draw(Graphics g) {
		for(int i=0;i<kamehaList.size();i++) {
			kamehaList.get(i).draw(g);
		}
	}
}
