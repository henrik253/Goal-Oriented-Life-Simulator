package application.model.gameobjects;

public enum GameObjectTag {
	EATING(GameObjectType.ACTION), MEETING(GameObjectType.ACTION), SLEEP(GameObjectType.ACTION),
	SPEND_MONEY(GameObjectType.ACTION), STUDY(GameObjectType.ACTION), WORK(GameObjectType.ACTION),
	GAME_CHARACTER(GameObjectType.CHARACTER), WALL(GameObjectType.OBSTACLE);

	private final GameObjectType type;

	private GameObjectTag(GameObjectType type) {
		this.type = type;
	}

	public GameObjectType getGameObjectType() {
		return type;
	}
}
