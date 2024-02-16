package application.model.gameobjects;

import application.utils.Vector2D;

public interface GameObject
{
    public Vector2D getPosition();
    public GameObjectTag getGameObjectTag();
}
