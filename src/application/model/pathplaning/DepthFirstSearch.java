package application.model.pathplaning;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import application.model.gameobjects.GameObject;
import application.model.gameobjects.GameObjectType;
import application.utils.Vector2D;

public class DepthFirstSearch implements PathPlaning {
	
	
	@Override
	public int getDistance(GameObject[][] field, Vector2D from, Vector2D to) {
		return getPath(field, from, to).size();
	}

	public List<Vector2D> getPath(GameObject[][] field, Vector2D from, Vector2D to) {
		boolean[][] visited = new boolean[field.length][field[0].length];
		List<Vector2D> path = new ArrayList<>();
		if (dfs(field, from, to, visited, path)) {
			return path;
		}
		return Collections.emptyList(); // or indicate path not found
	}

	private boolean dfs(GameObject[][] field, Vector2D current, Vector2D to, boolean[][] visited, List<Vector2D> path) {
		if (outOfBounds(field, current) || visited[current.getY()][current.getX()]) {
			return false;
		}

		visited[current.getY()][current.getX()] = true;
		path.add(current);

		if (current.equals(to)) {
			return true; // Destination reached
		}

		for (Vector2D direction : DIRECTIONS) {
			Vector2D next = Vector2D.add(current, direction);
			if (!outOfBounds(field, next) && field[next.getY()][next.getX()] != null 
					&& field[next.getY()][next.getX()].getGameObjectTag().getType() == GameObjectType.OBSTACLE) {
				continue;
			}
			if (!outOfBounds(field, next) && dfs(field, next, to, visited, path)) {
				return true; // Path found
			}
		}

		path.remove(path.size() - 1); // Backtrack
		return false;
	}

	private boolean outOfBounds(GameObject[][] field, Vector2D position) {
		return position.getX() < 0 || position.getY() < 0 || position.getX() >= field[0].length
				|| position.getY() >= field.length;
	}

//	public static void main(String[] args) {
//		GameObject[][] field = new GameObject[10][10];
//
//		System.out.println(new DepthFirstSearch().getPath(field, new Vector2D(0, 0), new Vector2D(9, 9)));
//	}

}
