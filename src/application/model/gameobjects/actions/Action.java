package application.model.gameobjects.actions;

import application.model.GameField;
import application.model.gameobjects.GameObject;
import application.model.gameobjects.GameObjectTag;
import application.model.gameobjects.Startable;
import application.model.gameobjects.character.GameCharacter;
import application.utils.Vector2D;

public abstract class Action implements GameObject, Startable {
	protected Vector2D position;

	protected boolean available;

	protected int initalTime = 1;
	protected int time = 1;

	protected GameObjectTag tag;

	protected GameField gameField = GameField.getGameField();
	
	protected double satisfaction = 1.0;

	public Action(Vector2D position) {
		this.position = position;
		available = true;

	}

	public double getSatisfaction(GameCharacter gameCharacter) {
		return satisfaction;
	}

	public abstract void satisfyCharacter(GameCharacter gameCharacter);

	@Override
	public Vector2D getPosition() {
		return position;
	}

	@Override
	public void setPosition(Vector2D position) {
		this.position = position;
	}

	@Override
	public void start() {
		ActionHandler.getActionHandler().addAction(this);
	}

	@Override
	public void update() {

	}

	public synchronized boolean isAvailable() {
		return available;
	}

	public boolean runAction() {
		available = false;
		if (time == 0) {
			time = initalTime;
			available = true;
			return true;
		} else {
			time--;
		}
		return false;
	}

	@Override
	public String toString() {
		return "" + getGameObjectTag().name().charAt(0);
	}
	
	public void setTime(int time) {
		this.time = time;
		initalTime = time;
	}
	
}
