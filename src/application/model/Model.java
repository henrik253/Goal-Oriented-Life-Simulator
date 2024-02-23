package application.model;

import application.model.gameobjects.GameObject;
import application.model.gameobjects.GameObjectFactory;
import application.model.gameobjects.GameObjectTag;
import application.model.gameobjects.character.GameCharacter;
import application.utils.Vector2D;

public class Model implements GUIInterface {

	private GameField gameField = GameField.getGameField();
	private boolean gameRunning = false;

	public Model() {

	}


	@Override
	public void setGameObjectMap(GameObjectTag[][] gameObjectMap) {
		gameField.setGameField(gameObjectMap);
	}
	
	@Override
	public void startGame() {
		gameField.startElementsOnField();
		gameRunning = true;
	}

	@Override
	public void update() {
		if (!gameRunning) {
			throw new IllegalStateException("Game did not start");
		}
		gameField.updateStartables(); 

	}

	@Override
	public void stopGame() {
		gameRunning = false;
	}

	@Override
	public double[] getGameCharacterInformation(Vector2D position) {
		GameObject gameObject = gameField.get(position);

		if (!(gameObject instanceof GameCharacter)) {
			throw new IllegalArgumentException("No GameCharacter at " + position);
		}

		// ... cast and then write information into arr
		return null;
	}

	@Override
	public void changeGameCharacterInformation(Vector2D position, double[] information) {
		GameObject gameObject = gameField.get(position);

		if (!(gameObject instanceof GameCharacter)) {
			throw new IllegalArgumentException("No GameCharacter at " + position);
		}

		// ... cast and then set Character
	}

	@Override
	public GameObjectTag[][] getField() {
		return gameField.getGameObjectTagField();
	}


	@Override
	public boolean isRunning() {
		return gameRunning;
	}


}
