package application.model.pathplaning;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import application.model.gameobjects.GameObject;
import application.model.gameobjects.GameObjectType;
import application.utils.Vector2D;

public class DepthFirstSearch implements PathPlaning {
	
	
	private Vector2D[] sortedDirections;
	
 	@Override
	public int getDistance(GameObject[][] field, Vector2D from, Vector2D to) {
		return getPath(field, from, to).size();
	}

	public List<Vector2D> getPath(GameObject[][] field, Vector2D from, Vector2D to) {
		boolean[][] visited = new boolean[field.length][field[0].length];
		List<Vector2D> path = new ArrayList<>();
		sortedDirections = sortDirections(from,to);
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

		for (Vector2D direction : sortedDirections) {
			Vector2D next = Vector2D.add(current, direction);
			
			if(next.equals(to)) {
				return true;
			}
			
			if (!outOfBounds(field, next) && field[next.getY()][next.getX()] != null 
					&& field[next.getY()][next.getX()].getGameObjectTag().getType() != null ) {
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
	
	private Vector2D[] sortDirections(Vector2D current, Vector2D to) {
	    Vector2D up = new Vector2D(0, -1), right = new Vector2D(1, 0), left = new Vector2D(-1, 0), down = new Vector2D(0, 1);
	    Vector2D[] directions = {up, right, down, left}; // Grundrichtungen

	    // Berechnen der Distanz vom aktuellen Punkt in jede Richtung zum Ziel
	    List<Vector2D> sortedDirections = Arrays.stream(directions)
	            .sorted((dir1, dir2) -> {
	                Vector2D pos1 = Vector2D.add(current, dir1);
	                Vector2D pos2 = Vector2D.add(current, dir2);
	                int dist1 = Math.abs(pos1.getX() - to.getX()) + Math.abs(pos1.getY() - to.getY());
	                int dist2 = Math.abs(pos2.getX() - to.getX()) + Math.abs(pos2.getY() - to.getY());
	                return Integer.compare(dist1, dist2); // Sortieren nach Distanz
	            })
	            .collect(Collectors.toList());

	    return sortedDirections.toArray(new Vector2D[0]);
	}

//	public static void main(String[] args) {
//		GameObject[][] field = new GameObject[10][10];
//
//		System.out.println(new DepthFirstSearch().getPath(field, new Vector2D(0, 0), new Vector2D(9, 9)));
//	}

}
