package application.model;

import application.model.gameobjects.GameObjectTag;
import application.utils.Vector2D;

public interface GUIInterface {

	public void setGameObjectMap(GameObjectTag[][] gameObjectMap);

	public void startGame();
	
	public void update(); 
	
	public void stopGame();
	
	public boolean isRunning();

	public double[] getGameCharacterInformation(Vector2D position);

	public void changeGameCharacterInformation(Vector2D position, double[] information);

	public GameObjectTag[][] getField();
}
