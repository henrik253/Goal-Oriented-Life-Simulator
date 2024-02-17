package application.model.gameobjects.actions;

import application.model.gameobjects.GameObjectTag;
import application.model.gameobjects.character.GameCharacter;
import application.utils.Vector2D;

public class Sleep extends Action {

	public Sleep(Vector2D position) {
		super(position);
	}

	@Override
	public double getSatisfaction(GameCharacter gameCharacter) {
		return 1.0;
	}

	@Override
	public GameObjectTag getGameObjectTag() {
		return GameObjectTag.SLEEP;
	}
	
	@Override
	public void satisfyCharacter(GameCharacter gameCharacter) {
		gameCharacter.setSleepSatisfaction(gameCharacter.getSleepSatisfaction() + getSatisfaction(gameCharacter));
	}

}
