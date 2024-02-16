package application.model;

import application.model.gameobjects.GameCharacter;
import application.model.gameobjects.GameObject;
import application.model.gameobjects.GameObjectFactory;
import application.model.gameobjects.GameObjectTag;
import application.utils.Vector2D;

public class Model implements GUIInterface {

	private GameField gameField = GameField.getGameField();

	public Model() {

	}

	@Override
	public void addGameObject(GameObjectTag tag, Vector2D position) {
		GameObject gameObject = GameObjectFactory.createGameObject(tag, position); // Actions are added to ActionHandler
																					// by Instantiating
		gameField.addGameObject(gameObject);
	}

	@Override
	public void removeGameObject(Vector2D position) {
		gameField.removeGameObject(position);
	}

	@Override
	public void startGame() {
		gameField.startElementsOnField();
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

}