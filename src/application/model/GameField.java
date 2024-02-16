package application.model;

import application.model.gameobjects.GameObject;
import application.model.gameobjects.GameObjectTag;
import application.model.gameobjects.Startable;
import application.utils.Vector2D;

// Singleton
public class GameField {

	private static GameField GAME_FIELD = new GameField();

	private GameObject[][] field;

	private GameField() {
		field = new GameObject[500][500];
	}

	public static GameField getGameField() {
		return GAME_FIELD;
	}

	public void addGameObject(GameObject gameObject) {
		Vector2D position = gameObject.getPosition();
		if (field[position.getY()][position.getX()] != null) {
			throw new IllegalArgumentException("GameObject at position: " + position);
		}
		field[position.getY()][position.getX()] = gameObject;
	}

	public void removeGameObject(Vector2D position) {
		if (field[position.getY()][position.getX()] == null) {
			throw new IllegalArgumentException("GameField at " + position + " is already null");
		}

		field[position.getY()][position.getX()] = null;
	}

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

	public GameObject get(Vector2D position) {
		GameObject gameObject = field[position.getY()][position.getX()];
		if (gameObject == null) {
			throw new IllegalArgumentException("No GameObject on " + position);
		}
		return gameObject;
	}

	public void resize() {
		// new GameField Object, or change the current used GameField but then this
		// method static
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

	@Override
	public String toString() {
		String result = "";
		for (int row = 0; row < field.length; row++) {
			for (int col = 0; col < field[row].length; col++) {
				result += field[row][col].toString() + " | ";
			}
			result += "\n";
		}
		return result;
	}

}
