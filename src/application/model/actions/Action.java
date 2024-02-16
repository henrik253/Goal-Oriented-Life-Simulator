package application.model.actions;

import java.util.List;

import application.model.Vector2D;

// Actions are spread across the map 

public abstract class Action
{
    protected Vector2D position;

    protected boolean available;

    public Action(Vector2D position)
    {
        this.position = position;
    }

    public abstract double getSatisfaction();

    public Vector2D getPosition()
    {
        return position;
    }

    public boolean isAvailable()
    {
        return available;
    }

    public void setAvailable(boolean used)
    {
        this.available = used;
    }

}
