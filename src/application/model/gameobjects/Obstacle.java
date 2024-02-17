package application.model.gameobjects;

import application.utils.Vector2D;

public class Obstacle implements GameObject {

	private Vector2D position;

	public Obstacle(Vector2D position) {
		this.position = position;
	}

	public Vector2D getPosition() {
		return position;
	}

	@Override
	public void setPosition(Vector2D position) {
		this.position = position;
	}

	@Override
	public GameObjectTag getGameObjectTag() {
		return GameObjectTag.OBSTACLE;
	}

}
