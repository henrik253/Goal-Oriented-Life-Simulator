package application.model.gameobjects;

import application.model.gameobjects.actions.*;
import application.model.gameobjects.character.GameCharacter;
import application.utils.Vector2D;

public class GameObjectFactory {
	
	private static final int RANGE = 5; 
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
			yield createGameCharacter(position);
		}
		case WALL -> {
			yield new Obstacle(position);
		}
		default -> throw new IllegalArgumentException("Unexpected value: " + tag);
		};
	}
	
	public static GameCharacter createGameCharacter(Vector2D position) {
		GameCharacter gameCharacter = new GameCharacter(position);
		
		gameCharacter.setHungerWeight(Math.random());
		gameCharacter.setIntellectuallyWeight(Math.random());
		gameCharacter.setMoneyWeight(Math.random());
		gameCharacter.setSleepWeight(Math.random());
		gameCharacter.setSocialWeight(Math.random());
		
		gameCharacter.setHungerSatisfaction(Math.random() * RANGE);
		gameCharacter.setIntellectuallySatisfaction(Math.random() * RANGE);
		gameCharacter.setMoneySatisfaction(Math.random() * RANGE);
		gameCharacter.setSleepSatisfaction(Math.random() * RANGE);
		gameCharacter.setSocialSatisfaction(Math.random() * RANGE);
		
		System.out.println("Created GameCharacter: \n" + gameCharacter.toDetailedString() );
		
		return gameCharacter;
	}
	

}
