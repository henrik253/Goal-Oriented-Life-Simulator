package application.model.actions;

import java.util.LinkedList;
import java.util.List;

import application.model.GameCharacter;
import application.model.Vector2D;

public class ActionHandler
{

    private static List<Action> actions = new LinkedList<>();

    public static double computeSatisfaction(GameCharacter character, Action action)
    {
        // The satisfaction is computed by the satisfaction Level of the Action

        return action.getSatisfaction();
    }

    public static void addAction(Action action)
    {
        actions.add(action);
    }

    public static void removeAction(Action action)
    {
        actions.remove(action);
    }

}
