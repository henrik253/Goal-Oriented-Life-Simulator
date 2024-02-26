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

public final class GameCharacter implements GameObject, Moveable, Startable {
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

	private int currentPath = -1;

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
		//TODO current Problem in Path planing is that 1. Characters block eatch other, and Actions block
		// the path.
		// Path is planned right, but then other Characters and Actions block the Path
		
		// If no action get a new Action
		if (currentAction == null) {
			List<Action> actions = actionHandler.getAvailableActions();
			currentAction = CharacterSatisfaction.getNextAction(this, actions);
		}

		// Check if Character is besides a Action, so he can do the Action or finish it
		if (Vector2D.calcDistance(currentAction.getPosition(), position) == 1) {
			boolean finished = currentAction.runAction();
			if (finished) {
				currentAction.satisfyCharacter(this);
				currentPath = -1;
				currentAction = null;
				return;
			}
		}

		// if no current Path, get a new path;
		if (currentPath == -1) {
			try {
				path = pathPlaning.getPath(gameField.getField(), position, currentAction.getPosition());
				path.remove(0);
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}

		// Try to move along the path, if it fails no move on the path
		Vector2D next = path.get(++currentPath);
		boolean validMove = moveTo(next);

		if (!validMove) {
			currentPath--;
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

}
