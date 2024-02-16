package application.model.gameobjects.actions;

import application.model.gameobjects.GameObject;
import application.model.gameobjects.GameObjectTag;
import application.utils.Vector2D;

// Actions are distributed across the map 

public abstract class Action implements GameObject {
	protected Vector2D position;

	protected boolean available;
	
	protected GameObjectTag tag;

	public Action(Vector2D position) {
		this.position = position;
		ActionHandler.getActionHandler().addAction(this);
		available = true;
	}

	public abstract double getSatisfaction();

	@Override
	public Vector2D getPosition() {
		return position;
	}

	public synchronized boolean isAvailable() {
		return available;
	}

	public synchronized void setAvailable(boolean used) {
		this.available = used;
	}

	@Override
	public String toString() {
		return "A"; // A for Action
	}
}
