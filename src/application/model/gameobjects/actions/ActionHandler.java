package application.model.gameobjects.actions;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import application.model.gameobjects.character.GameCharacter;
import application.utils.Vector2D;

// Singleton 
public class ActionHandler {

	private static final ActionHandler ACTION_HANDLER = new ActionHandler();

	private List<Action> actions = new CopyOnWriteArrayList<>();

	private ActionHandler() {
	}

	public static ActionHandler getActionHandler() {
		return ACTION_HANDLER;
	}
	
	public List<Action> getAvailableActions() {
		return actions.stream().filter(action -> action.isAvailable()).toList();
	}

	public void addAction(Action action) {
		actions.add(action);
	}

	public void removeAction(Action action) {
		actions.remove(action);
	}

	public void clearActions() {
		actions.clear();
	}

}
