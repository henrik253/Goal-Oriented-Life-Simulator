package application.model.pathplaning;

import java.util.List;
import java.util.LinkedList;

import application.model.gameobjects.GameObject;
import application.utils.Vector2D;

public class DepthFirstSearch implements PathPlaning {

	private static final Vector2D[] DIRECTIONS = { new Vector2D(0, 1), new Vector2D(0, -1), new Vector2D(1, 0),
			new Vector2D(-1, 0), new Vector2D(1, 1), new Vector2D(-1, 1), new Vector2D(1, -1), new Vector2D(-1, -1) };

	@Override
	public int getDistance(GameObject[][] field, Vector2D from, Vector2D to) {
		return getPath(field, from, to).size();
	}

	@Override
	public List<Vector2D> getPath(GameObject[][] field, Vector2D from, Vector2D to) {
		boolean[][] visited = new boolean[field.length][field[0].length];
		List<Vector2D> path = new LinkedList<>();
		List<Vector2D> stack = new LinkedList<>();
		Vector2D currentPos;
		if (outOfBounds(field, from)) {
			throw new IndexOutOfBoundsException("Position " + from + " is out of bounds!");
		}

		stack.add(from);

		while (!stack.isEmpty()) {
			currentPos = stack.remove(stack.size() - 1);
			path.add(currentPos);

			if (!outOfBounds(field, currentPos) && !visited[currentPos.getY()][currentPos.getX()]) {

				visited[currentPos.getY()][currentPos.getX()] = true;
				boolean foundNeighbour = false;
				for (Vector2D direction : DIRECTIONS) {
					Vector2D neighbour = Vector2D.add(currentPos, direction);

					if (!outOfBounds(field, neighbour) && !visited[neighbour.getY()][neighbour.getX()]
							&& field[neighbour.getY()][neighbour.getX()] == null) {

						foundNeighbour = true;
						stack.add(neighbour);

						if (neighbour.equals(to)) {
							path.add(neighbour);
							return path;
						}
					}
				}
				if (!foundNeighbour) {
					path.remove(currentPos);
				}
			}
		}
		return path;
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
