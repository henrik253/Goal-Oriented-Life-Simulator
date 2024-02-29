package application.model.gameobjects.actions;

import application.model.gameobjects.GameObjectTag;
import application.model.gameobjects.character.GameCharacter;
import application.utils.Vector2D;

public class Sleep extends Action {

	public static int TIME = (int) (Math.random() * 20);
	
	public Sleep(Vector2D position) {
		super(position);
		setTime(TIME);
	}

	
	@Override
	public GameObjectTag getGameObjectTag() {
		return GameObjectTag.SLEEP;
	}
	
	@Override
	public void satisfyCharacter(GameCharacter gameCharacter) {
		gameCharacter.setSleepSatisfaction(gameCharacter.getSleepSatisfaction() + getSatisfaction(gameCharacter) * gameCharacter.getSleepWeight());
	}

}
