package application.model;

import application.model.gameobjects.GameObjectTag;
import application.utils.Vector2D;

public interface GUIInterface {

	public void addGameObject(GameObjectTag tag, Vector2D position);

	public void removeGameObject(Vector2D position);

	public void startGame();
	
	public void update(); 
	
	public void stopGame();

	public double[] getGameCharacterInformation(Vector2D position);

	public void changeGameCharacterInformation(Vector2D position, double[] information);

	public GameObjectTag[][] getField();
}
