package khoa.object;

import java.awt.Graphics;
import java.util.ArrayList;

public class ParticularObjectManager {
	
	public ArrayList<ParticularObject> particularObjects;
	private GameWorld gameWorld;
	public ParticularObjectManager(GameWorld gameWorld) {
		this.gameWorld=gameWorld;
		particularObjects=new ArrayList<ParticularObject>();
	}
	public void addObject(ParticularObject object) {
		particularObjects.add(object);
		
	}
	
	public void Update() {
		Camera camera=gameWorld.getCamera();
		for(int i=0;i<particularObjects.size();i++) {
			if(particularObjects.get(i).getState()==ParticularObject.DEATH) {
				if(!particularObjects.get(i).getName().equals("blackDragon"))
					particularObjects.remove(i);
					
				if(particularObjects.get(i).getName().equals("blackDragon")) {
					if(!particularObjects.get(i).outOfCamera())
						particularObjects.get(i).Update();
				}
			}else {
				if(particularObjects.get(i).getName().equals("blackDragon") && particularObjects.get(i).getPosX()<camera.getPosX())
				{
					particularObjects.get(i).setState(ParticularObject.DEATH);
					particularObjects.get(i).Update();
				}
				
				if(!particularObjects.get(i).outOfCamera())
				particularObjects.get(i).Update();
				
			}
		}
	}
	public void removeObj(ParticularObject object) {
		particularObjects.remove(object);
	}
	
	public void draw(Graphics g) {
		for(int i=0;i<particularObjects.size();i++) {
			if(!particularObjects.get(i).outOfCamera())
			particularObjects.get(i).draw(g);
		}
	}
}
