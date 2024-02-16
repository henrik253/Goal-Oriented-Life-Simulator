package application.model.gameobjects.actions;

import application.model.gameobjects.GameObjectTag;
import application.utils.Vector2D;

public class Sleep extends Action {

	public Sleep(Vector2D position) {
		super(position);
	}

	@Override
	public double getSatisfaction() {
		return 1.0;
	}

	@Override
	public GameObjectTag getGameObjectTag() {
		return GameObjectTag.SLEEP;
	}

}
