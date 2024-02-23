package application.model.gameobjects.character;

import java.util.List;

import application.model.gameobjects.actions.Action;

public class CharacterSatisfaction {
	
	
	// Includes the amount of the Time an Action takes to do in the computation
	public static Action getHighestSatisfactionAndTimeAction(GameCharacter gameCharacter,List<Action> actions) {
		return actions.get(0);
	}
	
	// Includes the length of the way in the computation
	public static Action getHighestSatisfactionAndTimeAndWayAction(GameCharacter gameCharacter,List<Action> actions) {
		return null;
	}
	
	
	public static double computeSatisfaction(GameCharacter gameCharacter, Action action) {
			
		return Math.random();
	}
}
