package application.model.gameobjects.actions;

import application.model.gameobjects.GameObjectTag;
import application.model.gameobjects.character.GameCharacter;
import application.model.pathplaning.PathPlaning;
import application.utils.Vector2D;

public class MeetCharacter extends Action {

	public MeetCharacter(Vector2D position) {
		super(position);
		setTime(TIME);
	}

	public static int TIME = (int) (Math.random() * 3);

	@Override
	public GameObjectTag getGameObjectTag() {
		return GameObjectTag.MEETING;
	}

	@Override
	public void satisfyCharacter(GameCharacter gameCharacter) {
		gameCharacter.setIntellectuallySatisfaction(gameCharacter.getIntellectuallySatisfaction()
				+ getSatisfaction(gameCharacter) * gameCharacter.getSocialWeight() * countCharactersAroundAction());
	}
	
	@Override
	public boolean runAction() {
		if (time == 0) {
			time = initalTime;
			available = true;
		} else {
			time--;
		}
		return true;
	}

	private int countCharactersAroundAction() {
		int c = 0;
		for (Vector2D direction : PathPlaning.DIRECTIONS) {
			Vector2D newPosition = Vector2D.add(direction, position);
			if (!outOfBounds(newPosition) && gameField.getField()[newPosition.getY()][newPosition.getX()] != null
					&& gameField.getField()[newPosition.getY()][newPosition.getX()]
							.getGameObjectTag() == GameObjectTag.GAME_CHARACTER) {
				c++;
			}
		}
		return c; 
	}

	private boolean outOfBounds(Vector2D position) {
		return position.getX() < 0 || position.getX() >= gameField.getField()[0].length || position.getY() < 0
				|| gameField.getField().length >= position.getY();
	}
}
