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
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class SideBarView extends Pane {

	private class GameObjectRepresentation extends VBox {
		
		
		private GameObjectTag tag;
		private String name;
		private Color color;
		private double size;

		private Button button;
		private Text text;

		public GameObjectRepresentation(GameObjectTag tag, String name, Color color, double size) {
			this.tag = tag;
			this.name = name;
			this.color = color;
			this.size = size;
			this.setPadding(new Insets(10,10,10,10));
			button = new Button();
			button.setPrefSize(size, size);
			button.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, new Insets(0))));
			button.setOnAction(event -> buttonPressed());
			text = new Text(name);

			getChildren().addAll(text, button);
		}
		
		private void buttonPressed() {
			sideBarPresenter.gameObjectButtonPressed(tag); 
		}

	}

	private SideBarPresenter sideBarPresenter;

	private FlowPane gameObjects = new FlowPane();

	public SideBarView(double witdh, double height) {
		this.setPrefHeight(height);
		this.setPrefWidth(witdh);
		this.setMaxWidth(witdh);
		this.setId("SiderBarView");
		init();
	
	}

	private void init() {
	
		GameObjectRepresentation obstacle = new GameObjectRepresentation(GameObjectTag.OBSTACLE, "Obstacle",
				Color.BLACK, 50);
		
		GameObjectRepresentation character = new GameObjectRepresentation(GameObjectTag.GAME_CHARACTER, "Character",
				Color.RED, 50);
		
		GameObjectRepresentation actionEating = new GameObjectRepresentation(GameObjectTag.EATING, "EATING",
				Color.ALICEBLUE, 50);
		
		GameObjectRepresentation actionSleep = new GameObjectRepresentation(GameObjectTag.OBSTACLE, "Sleep",
				Color.AQUA, 50);
		
		
		gameObjects.getChildren().addAll(obstacle,character,actionEating,actionSleep);
		
		getChildren().add(gameObjects);
	}

	public SideBarPresenter getSideBarPresenter() {
		return sideBarPresenter;
	}

	public void setSideBarPresenter(SideBarPresenter sideBarPresenter) {
		this.sideBarPresenter = sideBarPresenter;
	}

}
