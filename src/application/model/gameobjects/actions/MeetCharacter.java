package application.model.gameobjects.actions;

import application.model.gameobjects.GameObjectTag;
import application.utils.Vector2D;

public class MeetCharacter extends Action
{

    public MeetCharacter(Vector2D position)
    {
        super(position);
    }

    @Override
    public double getSatisfaction()
    {
        // TODO Auto-generated method stub
        return 1.0;
    }

	@Override
	public GameObjectTag getGameObjectTag() {
		return GameObjectTag.MEETING;
	}

}
