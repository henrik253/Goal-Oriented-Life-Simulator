package application.model.gameobjects;

import application.model.gameobjects.actions.*;
import application.model.gameobjects.character.GameCharacter;
import application.utils.Vector2D;

public class GameObjectFactory {

	// EATING, MEETING, SLEEP, SPEND_MONEY, STUDY, WORK, GAME_CHARACTER, OBSTACLE
	public static GameObject createGameObject(GameObjectTag tag, final Vector2D position) {
		return switch (tag) {
		case EATING -> {
			yield new Eating(position);
		}
		case MEETING -> {
			yield new MeetCharacter(position);
		}
		case SLEEP -> {
			yield new Sleep(position);
		}
		case SPEND_MONEY -> {
			yield new SpendMoney(position);
		}
		case STUDY -> {
			yield new Study(position);
		}
		case WORK -> {
			yield new Work(position);
		}
		case GAME_CHARACTER -> {
			
			yield new GameCharacter(position);
		}
		case OBSTACLE -> {
			yield new Obstacle(position);
		}
		default -> throw new IllegalArgumentException("Unexpected value: " + tag);
		};
	}
}
