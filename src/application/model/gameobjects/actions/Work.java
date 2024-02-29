package application.model.gameobjects.actions;

import application.model.gameobjects.GameObjectTag;
import application.model.gameobjects.character.GameCharacter;
import application.utils.Vector2D;

public class Work extends Action {

	public static int TIME = (int) (Math.random() * 10);
	
	public Work(Vector2D position) {
		super(position);
		setTime(TIME);
	}

	@Override
	public GameObjectTag getGameObjectTag() {
		return GameObjectTag.WORK;
	}

	@Override
	public void satisfyCharacter(GameCharacter gameCharacter) {
		gameCharacter.setMoneySatisfaction(gameCharacter.getMoneySatisfaction() + getSatisfaction(gameCharacter));
	}
}
