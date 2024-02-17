package application.model.pathplaning;

import java.util.List;

import application.model.gameobjects.GameObject;
import application.utils.Vector2D;

public interface PathPlaning {

	public int getDistance(GameObject[][] field, Vector2D position, Vector2D to);

	public List<Vector2D> getPath(GameObject[][] field, Vector2D position,Vector2D to);

}
