package application.model.pathplaning;

import java.util.List;
import java.util.Set;

import application.model.gameobjects.GameObject;
import application.utils.Vector2D;

public interface PathPlaning {

	Set<Vector2D> DIRECTIONS = Set.of(new Vector2D(0, -1), new Vector2D(1, 0), new Vector2D(-1, 0), new Vector2D(0, 1));

	int getDistance(GameObject[][] field, Vector2D position, Vector2D to);
	
	List<Vector2D> getPath(GameObject[][] field, Vector2D position, Vector2D to); // path should include start & end

}
