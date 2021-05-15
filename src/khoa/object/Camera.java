package khoa.object;

public class Camera extends Object{
	private double speedX;
	private Human mainCharacter;
	public boolean locked;
	public Camera(double posX, double posY, int width, int height, GameWorld gameWorld) {
		super(posX, posY, width, height, gameWorld);
		mainCharacter=getGameWorld().getMainCharater();
		locked=false;
	}

	@Override
	public void Update() {
		/*if(mainCharacter.getPosX()-getPosX()>=200) {
			setPosX(mainCharacter.getPosX()-200);
			
		}
		if(mainCharacter.getPosX()-getPosX()<=100) {
			if(mainCharacter.getSpeedX()<0) {
				setSpeedX(mainCharacter.getSpeedX());
				setPosX(getPosX()+getSpeedX());
				
			}else {
				setSpeedX(-mainCharacter.getSpeedX());
				setPosX(getPosX()+getSpeedX());
			}
		}*/
		if(!locked) {
		setSpeedX(mainCharacter.getSpeedX());
		setPosX(getPosX()+getSpeedX());
		}
	}

	public double getSpeedX() {
		return speedX;
	}

	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}

}
