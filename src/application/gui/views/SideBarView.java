package application.gui.views;

import application.gui.presenter.SideBarPresenter;
import application.model.gameobjects.GameObjectTag;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class SideBarView extends Pane
{

    private SideBarPresenter sideBarPresenter;

    private VBox obstacleBar;

    public SideBarView(double witdh, double height)
    {
        this.setPrefHeight(height);
        this.setPrefWidth(witdh);
        this.setId("SiderBarView");
        init(); 
    }

    private void init()
    {
        obstacleBar = new VBox();
 
        Button gameCharacter = addGameObject(GameObjectTag.GAME_CHARACTER,Color.RED);
        Button obstacle = addGameObject(GameObjectTag.OBSTACLE,Color.BLACK);
        Button eating = addGameObject(GameObjectTag.EATING,Color.BLUE);
        obstacleBar.getChildren().addAll(gameCharacter,obstacle,eating); 
        
        getChildren().add(obstacleBar);
    }



    public Button addGameObject(GameObjectTag tag, Color color)
    {
        Button button = new Button();
        button.setOnAction(event -> sideBarPresenter.gameObjectButtonPressed(tag));
        button.setText(tag.toString());
        button.setBackground(new Background(new BackgroundFill(color,CornerRadii.EMPTY,new Insets(5))));
        button.setPrefWidth(20);
        button.setPrefHeight(20);
        return button; 
    }

    public SideBarPresenter getSideBarPresenter()
    {
        return sideBarPresenter;
    }

    public void setSideBarPresenter(SideBarPresenter sideBarPresenter)
    {
        this.sideBarPresenter = sideBarPresenter;
    }

}
