package application.model.gameobjects.actions;

import application.model.gameobjects.GameObjectTag;
import application.model.gameobjects.character.GameCharacter;
import application.utils.Vector2D;

public class SpendMoney extends Action
{
	
	public static int TIME = (int) (Math.random() * 3);
	
    public SpendMoney(Vector2D position)
    {
        super(position);
    	setTime(TIME);
    }

 
	public GameObjectTag getGameObjectTag() {
		return GameObjectTag.SPEND_MONEY;
	}

	@Override
	public void satisfyCharacter(GameCharacter gameCharacter) {
		gameCharacter.setMoneySatisfaction(gameCharacter.getMoneySatisfaction() - ( getSatisfaction(gameCharacter) * gameCharacter.getMoneyWeight()));
		gameCharacter.setSocialSatisfaction(gameCharacter.getSocialSatisfaction() + (getSatisfaction(gameCharacter) * gameCharacter.getSocialWeight()* 2));
	}
}
