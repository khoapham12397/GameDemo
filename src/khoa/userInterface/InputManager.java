package khoa.userInterface;

import java.awt.event.KeyEvent;

import khoa.object.GameWorld;
import khoa.object.Human;
import khoa.object.ParticularObject;

public class InputManager {
	
	GameWorld gameWorld;
	public InputManager(GameWorld gameWorld) {
		this.gameWorld=gameWorld;
		
	}
	public void keyPressed(int Keycode) {
		Human mainCharacter=gameWorld.getMainCharater();
		if(mainCharacter.getState()==ParticularObject.ALIVE) {
		switch(Keycode) {
		case KeyEvent.VK_RIGHT:
			gameWorld.getMainCharater().setDirect(ParticularObject.RIGHT_DIR);
			gameWorld.getMainCharater().run();
		
			break;
		case KeyEvent.VK_LEFT:
			gameWorld.getMainCharater().setDirect(ParticularObject.LEFT_DIR);
			gameWorld.getMainCharater().run();
			break;
		case KeyEvent.VK_SPACE:
			gameWorld.getMainCharater().jump();
			//gameWorld.getSasuke().jump();
		case KeyEvent.VK_DOWN:
			gameWorld.getMainCharater().dick();
			
			System.out.println("down");
			break;
		case KeyEvent.VK_UP:
			gameWorld.getMainCharater().standUp();
			break;
		}
		
		
			gameWorld.getMainCharater().attack(Keycode);
		}
			//gameWorld.getSasuke().attack(Keycode);
	}
	public void keyReleased(int KeyCode) {
		switch(KeyCode) {
		case KeyEvent.VK_RIGHT:
			gameWorld.getMainCharater().stopRun();
			break;
		case KeyEvent.VK_LEFT:
			gameWorld.getMainCharater().stopRun();
			break;
			
		}
		
	}
	
}
