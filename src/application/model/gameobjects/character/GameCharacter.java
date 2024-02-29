package application.model.gameobjects.character;

import java.util.List;

import java.util.LinkedList;
import application.model.GameField;
import application.model.gameobjects.GameObject;
import application.model.gameobjects.GameObjectTag;
import application.model.gameobjects.Moveable;
import application.model.gameobjects.Startable;
import application.model.gameobjects.actions.Action;
import application.model.gameobjects.actions.ActionHandler;
import application.model.pathplaning.*;
import application.utils.Vector2D;

public final class GameCharacter implements GameObject, Moveable, Startable, Cloneable{
	// Behaviour of the Model
	// 1. Each Character has characteristics and needs => Overall Level
	// 1.1 Social Interactions => Level of social satisfaction and relationships
	// 1.2 Cooking, Eating Food => Starvation Level
	// 1.3 Go to Work => Earning Money
	// 1.4 Go to School => Level of intellectually
	// 1.5 Spending Money => For Clothes or Food
	// 1.6 Go to Sleep => Melatonin Level
	// Satisfaction = social satisfaction * w1 + hunger Satisfaction * w2 + self
	// satisfaction * w3 + intellectually satisfaction * w4
	// + Sleep satisfaction * w5

	private double overallSatisfaction;

	private double socialSatisfaction = 0.5, socialWeight = 1.0;

	private double moneySatisfaction = 0.5, moneyWeight = 1.0;

	private double intellectuallySatisfaction = 0.5, intellectuallyWeight = 1.0;

	private double hungerSatisfaction = 0.5, hungerWeight = 1.0;

	private double sleepSatisfaction = 0.5, sleepWeight = 1.0;

	private static final double SATISFACTIONS = 5;

	private Vector2D position;

	private final GameField gameField = GameField.getGameField();

	private final ActionHandler actionHandler = ActionHandler.getActionHandler();

	private PathPlaning pathPlaning = new DepthFirstSearch();

	private List<Vector2D> path;

	private Action currentAction = null;

	public GameCharacter(Vector2D position) {
		this.position = position;
		updateOverallSatisfaction();
	}

	public void updateOverallSatisfaction() {
		overallSatisfaction = calculateOverallSatisfaction();
	}

	public double calculateOverallSatisfaction() {
		return (socialSatisfaction * socialWeight + moneySatisfaction * moneyWeight
				+ intellectuallySatisfaction * intellectuallyWeight + hungerSatisfaction * hungerWeight
				+ sleepSatisfaction * sleepWeight) / SATISFACTIONS;
	}

	@Override
	public void start() {

	}

	@Override
	public void update() {
		// TODO current Problem in Path planing is that 1. Characters block eatch other,
		// and Actions block
		// the path.
		// Path is planned right, but then other Characters and Actions block the Path

		// If no action get a new Action
		if (currentAction == null) {
			List<Action> actions = actionHandler.getAvailableActions();
			try {
				currentAction = CharacterSatisfaction.getNextAction(this, actions);
				System.err.println("Next action " + currentAction + " for Character at " + position);
			} catch (Exception e) {
				return;
			}
		}
		
		

		// Check if Character is besides a Action, so he can do the Action or finish it
		if (Vector2D.calcDistance(currentAction.getPosition(), position) == 1) {
			boolean finished = currentAction.runAction();
			if (finished) {
				currentAction.satisfyCharacter(this);
				currentAction = null;
				path = null;
				
				System.err.println("Updated Character and Satisfactions");
				System.err.println(toDetailedString());
				
				return;
			}
		}

		// if no current Path, get a new path;
		if (path == null) {
			try {
				path = pathPlaning.getPath(gameField.getField(), position, currentAction.getPosition());
				path.remove(0);
			} catch (Exception e) {
				return;
			}
		}

		try {
		Vector2D next = path.remove(0);
		boolean validMove = moveTo(next);
		path = validMove ? path : null;
		}
		catch(Exception e) {
			return;
		}
		

	}

	@Override
	public Vector2D getPosition() {
		return position;
	}

	@Override
	public boolean moveTo(final Vector2D position) {
		try {
			gameField.moveGameObject(this, position);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "C"; // C for Character
	}
	
	
	public String toDetailedString() {
	    return "GameCharacter{" +
	            "overallSatisfaction=" + overallSatisfaction + "\n" +
	            ", socialSatisfaction=" + socialSatisfaction +"\n" +
	            ", socialWeight=" + socialWeight +"\n" +
	            ", moneySatisfaction=" + moneySatisfaction +"\n" +
	            ", moneyWeight=" + moneyWeight +"\n" +
	            ", intellectuallySatisfaction=" + intellectuallySatisfaction +"\n" +
	            ", intellectuallyWeight=" + intellectuallyWeight +"\n" +
	            ", hungerSatisfaction=" + hungerSatisfaction +"\n" +
	            ", hungerWeight=" + hungerWeight +"\n" +
	            ", sleepSatisfaction=" + sleepSatisfaction +"\n" +
	            ", sleepWeight=" + sleepWeight +"\n" +
	            ", position=" + position +"\n" +
	            ", path=" + (path != null ? pathToString() : "null") +"\n" +
	            ", currentAction=" + (currentAction != null ? currentAction.toString() : "null") + "\n" +
	            '}';
	}

	private String pathToString() {
	    StringBuilder sb = new StringBuilder("[");
	    for (int i = 0; i < path.size(); i++) {
	        sb.append(path.get(i).toString());
	        if (i < path.size() - 1) {
	            sb.append(", ");
	        }
	    }
	    sb.append("]");
	    return sb.toString();
	}

	@Override
	public GameObjectTag getGameObjectTag() {
		return GameObjectTag.GAME_CHARACTER;
	}

	public double getOverallSatisfaction() {
		return overallSatisfaction;
	}

	public void setOverallSatisfaction(double overallSatisfaction) {
		this.overallSatisfaction = overallSatisfaction;
	}

	public double getSocialSatisfaction() {
		return socialSatisfaction;
	}

	public void setSocialSatisfaction(double socialSatisfaction) {
		this.socialSatisfaction = socialSatisfaction;
	}

	public double getSocialWeight() {
		return socialWeight;
	}

	public void setSocialWeight(double socialWeight) {
		this.socialWeight = socialWeight;
	}

	public double getMoneySatisfaction() {
		return moneySatisfaction;
	}

	public void setMoneySatisfaction(double moneySatisfaction) {
		this.moneySatisfaction = moneySatisfaction;
	}

	public double getMoneyWeight() {
		return moneyWeight;
	}

	public void setMoneyWeight(double moneyWeight) {
		this.moneyWeight = moneyWeight;
	}

	public double getIntellectuallySatisfaction() {
		return intellectuallySatisfaction;
	}

	public void setIntellectuallySatisfaction(double intellectuallySatisfaction) {
		this.intellectuallySatisfaction = intellectuallySatisfaction;
	}

	public double getIntellectuallyWeight() {
		return intellectuallyWeight;
	}

	public void setIntellectuallyWeight(double intellectuallyWeight) {
		this.intellectuallyWeight = intellectuallyWeight;
	}

	public double getHungerSatisfaction() {
		return hungerSatisfaction;
	}

	public void setHungerSatisfaction(double hungerSatisfaction) {
		this.hungerSatisfaction = hungerSatisfaction;
	}

	public double getHungerWeight() {
		return hungerWeight;
	}

	public void setHungerWeight(double hungerWeight) {
		this.hungerWeight = hungerWeight;
	}

	public double getSleepSatisfaction() {
		return sleepSatisfaction;
	}

	public void setSleepSatisfaction(double sleepSatisfaction) {
		this.sleepSatisfaction = sleepSatisfaction;
	}

	public double getSleepWeight() {
		return sleepWeight;
	}

	public void setSleepWeight(double sleepWeight) {
		this.sleepWeight = sleepWeight;
	}

	@Override
	public void setPosition(Vector2D position) {
		this.position = position;
	}
	
	 @Override
	    public GameCharacter clone() {
	        try {
	            GameCharacter cloned = (GameCharacter) super.clone();
	            
	            // Deep clone mutable objects
	            cloned.position = new Vector2D(this.position.getX(), this.position.getY());
	            if (this.path != null) {
	                cloned.path = new LinkedList<>();
	                for (Vector2D point : this.path) {
	                    cloned.path.add(new Vector2D(point.getX(), point.getY()));
	                }
	            }
	            
	            // Note: gameField and actionHandler are shared among instances and not cloned
	            // If PathPlaning is mutable and needs to be deep cloned, you should do it here
	            // Assuming PathPlaning is either immutable or doesn't require deep cloning for this context
	            
	            return cloned;
	        } catch (CloneNotSupportedException e) {
	            throw new AssertionError(); // Can never happen
	        }
	    }

}
