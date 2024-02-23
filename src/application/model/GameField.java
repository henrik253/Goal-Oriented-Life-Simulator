package application.model;

import java.util.LinkedList;
import java.util.List;

import application.model.gameobjects.GameObject;
import application.model.gameobjects.GameObjectFactory;
import application.model.gameobjects.GameObjectTag;
import application.model.gameobjects.Startable;
import application.utils.Vector2D;

// Singleton
public class GameField {

	private static GameField gameField = new GameField();

	private GameObject[][] field;
	
	private List<Startable> startables = new LinkedList<>();
	
	private GameField() {
		field = new GameObject[500][500];
	}

	public static GameField getGameField() {
		return gameField;
	}

	public void moveGameObject(GameObject gameObject, final Vector2D position) {
		final Vector2D pos = gameObject.getPosition();
		
		if(gameObject.getPosition().equals(position)) {
			throw new IllegalArgumentException("gameObject already on " + position);
		}
		
		if (field[position.getY()][position.getX()] != null) {
			throw new IllegalArgumentException("Already a gameobject on " + position);
		}

		gameObject.setPosition(position);
		field[position.getY()][position.getX()] = gameObject;
		field[pos.getY()][pos.getX()] = null;
	}

	// unnötig?
//	public void addGameObject(GameObject gameObject) {
//		Vector2D position = gameObject.getPosition();
//		if (field[position.getY()][position.getX()] != null) {
//			throw new IllegalArgumentException("GameObject at position: " + position);
//		}
//		field[position.getY()][position.getX()] = gameObject;
//	}
//	// unnötig?
//	public void removeGameObject(Vector2D position) {
//		if (field[position.getY()][position.getX()] == null) {
//			throw new IllegalArgumentException("GameField at " + position + " is already null");
//		}
//
//		field[position.getY()][position.getX()] = null;
//	}

	public void startElementsOnField() {
		boolean noneStartElement = true;
	
		for (int row = 0; row < field.length; row++) {
			for (int col = 0; col < field[row].length; col++) {
				GameObject gameObject = field[row][col];
				if (gameObject instanceof Startable) {
					((Startable) gameObject).start();
					noneStartElement = false;
				}
			}
		}
		
		if (noneStartElement) {
			throw new IllegalStateException("No Startable on GameField");
		}
	}

	public void updateStartables() {
		startables.forEach( startable -> startable.update());
	}

	public GameObject get(Vector2D position) {
		GameObject gameObject = field[position.getY()][position.getX()];
		if (gameObject == null) {
			throw new IllegalArgumentException("No GameObject on " + position);
		}
		return gameObject;
	}

	public void setGameField(GameObjectTag[][] gameObjectMap) {
		field = new GameObject[gameObjectMap.length][gameObjectMap[0].length];
		for (int row = 0; row < field.length; row++) {
			for (int col = 0; col < field[row].length; col++) {
				GameObjectTag gameObjectTag = gameObjectMap[row][col];
				if (gameObjectTag == null) {
					continue;
				}
				GameObject gameObject = GameObjectFactory.createGameObject(gameObjectTag, new Vector2D(col, row));
				if(gameObject instanceof Startable) {
					startables.add((Startable) gameObject);
				}
				field[row][col] = gameObject;
			}
		}

	}

	public GameObjectTag[][] getGameObjectTagField() {
		GameObjectTag[][] tagField = new GameObjectTag[field.length][field[0].length];
		for (int row = 0; row < field.length; row++) {
			for (int col = 0; col < field[row].length; col++) {
				if (field[row][col] != null) {
					tagField[row][col] = field[row][col].getGameObjectTag();
				}
			}
		}
		return tagField;
	}

	public GameObject[][] getField() {
		return field;
	}

	@Override
	public String toString() {
		String result = "MAP \n";

		for (int row = 0; row < field.length; row++) {
			for (int col = 0; col < field[row].length; col++) {
				if (field[row][col] == null) {
					continue;
				}
				result += field[row][col].toString() + " | ";
			}
			result += "\n";
		}
		return result;
	}

}
