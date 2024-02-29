package application.model.gameobjects.actions;

import application.model.gameobjects.GameObjectTag;
import application.model.gameobjects.character.GameCharacter;
import application.utils.Vector2D;

public class Eating extends Action {
	
	public static int TIME = (int) (Math.random() * 5);
	
	public Eating(Vector2D position) {
		super(position);
		setTime(TIME);
	}


	@Override
	public GameObjectTag getGameObjectTag() {
		return GameObjectTag.EATING;
	}

	@Override
	public void satisfyCharacter(GameCharacter gameCharacter) {
		gameCharacter.setHungerSatisfaction(gameCharacter.getHungerSatisfaction() + getSatisfaction(gameCharacter));
	}

}
