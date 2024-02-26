package application.model.gameobjects.character;

import java.util.List;

import application.model.gameobjects.actions.Action;

public class CharacterSatisfaction {
	
	
	// Includes the amount of the Time an Action takes to do in the computation
	public static Action getNextAction(GameCharacter gameCharacter,List<Action> actions) {
		if(actions.isEmpty()) {
			throw new IllegalArgumentException("No Actions on Map");
		}
		System.out.println(actions);
		return actions.get((int) (Math.random() * actions.size()));
	}
	
	// Includes the length of the way in the computation
	public static Action getHighestSatisfactionAndTimeAndWayAction(GameCharacter gameCharacter,List<Action> actions) {
		return null;
	}
	
	
	public static double computeSatisfaction(GameCharacter gameCharacter, Action action) {
			
		return Math.random();
	}
}
