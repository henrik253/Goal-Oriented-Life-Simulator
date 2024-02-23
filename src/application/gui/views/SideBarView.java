package application.gui.views;

import java.util.LinkedList;
import java.util.List;

import application.gui.presenter.SideBarPresenter;
import application.model.gameobjects.GameObjectTag;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

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

    private class GameObjectWrapper extends Pane
    {
        private static List<GameObjectWrapper> instances = new LinkedList<>();

        private static final int INSETS = 50;

        private VBox wrapper = new VBox(INSETS);

        private Text text = new Text();

        private Button button = new Button();

        private GameObjectTag tag;

        public GameObjectWrapper(GameObjectTag tag, double width, double height)
        {
            instances.add(this);
            button.setBorder(Border.stroke(Color.BLACK));
            this.tag = tag;
           // this.setPrefSize(width, height);
            button.setPrefSize(width, height);
            button.setId(tag.toString() + "_BUTTON");
            button.setOnMouseClicked(this::onMouseClick);
            text.setId(tag.toString() + "_TEXT");
            wrapper.getChildren().addAll(this.text, button);
            getChildren().add(wrapper);
        }

        private void onMouseClick(MouseEvent event)
        {
            sideBarPresenter.gameObjectButtonPressed(tag);
            instances.forEach(instance -> instance.button.setBorder(Border.stroke(Color.BLACK)));
            button.setBorder(Border.stroke(Color.GREEN));
        }
    }

    private void init()
    {
        obstacleBar = new VBox();
        int length = GameObjectTag.values().length;
        for (GameObjectTag tag : GameObjectTag.values())
        {
            GameObjectWrapper obj = new GameObjectWrapper(tag, this.getPrefWidth() / length, this.getPrefWidth() / length);
            obstacleBar.getChildren().add(obj);

        }

        getChildren().add(obstacleBar);
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
