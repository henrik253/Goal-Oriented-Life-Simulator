package application.model;

public class GameCharacter implements GameObject
{
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

    private Vector2D position;

    public GameCharacter(Vector2D position)
    {
        this.position = position;
        updateOverallSatisfaction();
    }

    public void updateOverallSatisfaction()
    {
        overallSatisfaction = calculateOverallSatisfaction();
    }

    public double calculateOverallSatisfaction()
    {
        return (socialSatisfaction * socialWeight + moneySatisfaction * moneyWeight + intellectuallySatisfaction * intellectuallyWeight + hungerSatisfaction * hungerWeight + sleepSatisfaction * sleepWeight) / 5;
    }

    @Override
    public Vector2D getPosition()
    {
        return position;
    }

}
