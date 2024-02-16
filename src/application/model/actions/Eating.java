package application.model.actions;

import application.model.Vector2D;

public class Eating extends Action
{

    public Eating(Vector2D position)
    {
        super(position);

    }

    @Override
    public double getSatisfaction()
    {
        return 1.0;
    }

}
