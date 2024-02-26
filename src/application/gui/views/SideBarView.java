package application.gui.views;

import application.gui.Settings;
import application.gui.presenter.SideBarPresenter;
import application.model.gameobjects.GameObjectTag;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
			this.setPadding(new Insets(10, 10, 10, 10));
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

	private static final double GAME_OBJECT_SIZE = 40;

	private SideBarPresenter sideBarPresenter;

	private FlowPane gameObjects = new FlowPane();

	private Button startButton = new Button("Start");

	public SideBarView(double witdh, double height) {
		this.setPrefHeight(height);
		this.setPrefWidth(witdh);
		this.setMaxWidth(witdh);
		this.setId("SiderBarView");
		gameObjects.setPrefWidth(getPrefWidth() - 20);
		init();

	}
	
	private boolean started = false; 
	
	private void init() {
		initSelectableGameObjects();
		startButton.setOnAction(event -> {
			if(!started) {
			sideBarPresenter.startGame();
			}
			else
			{
				sideBarPresenter.stopGame();
			}
		});
		getChildren().add(startButton);
	}

	private void initSelectableGameObjects() {
		GameObjectRepresentation obstacle = new GameObjectRepresentation(GameObjectTag.WALL, "Wall",
				Settings.COLOR_OBSTACLE_WALL, GAME_OBJECT_SIZE);

		GameObjectRepresentation character = new GameObjectRepresentation(GameObjectTag.GAME_CHARACTER, "Character",
				Settings.COLOR_GAME_CHARACTER, GAME_OBJECT_SIZE);

		// Actions
		GameObjectRepresentation actionEating = new GameObjectRepresentation(GameObjectTag.EATING, "Eat",
				Settings.COLOR_ACTION_EATING, GAME_OBJECT_SIZE);

		GameObjectRepresentation actionSleep = new GameObjectRepresentation(GameObjectTag.WALL, "Sleep",
				Settings.COLOR_OBSTACLE_WALL, GAME_OBJECT_SIZE);

		GameObjectRepresentation actionSpendMoney = new GameObjectRepresentation(GameObjectTag.SPEND_MONEY, "Buy",
				Settings.COLOR_ACTION_SPEND_MONEY, GAME_OBJECT_SIZE);

		GameObjectRepresentation actionStudy = new GameObjectRepresentation(GameObjectTag.STUDY, "Study",
				Settings.COLOR_ACTION_STUDY, GAME_OBJECT_SIZE);

		GameObjectRepresentation actionWork = new GameObjectRepresentation(GameObjectTag.WORK, "Work",
				Settings.COLOR_ACTION_STUDY, GAME_OBJECT_SIZE);

		GameObjectRepresentation actionMeeting = new GameObjectRepresentation(GameObjectTag.MEETING, "Meet",
				Settings.COLOR_ACTION_MEETING, GAME_OBJECT_SIZE);

		gameObjects.getChildren().addAll(obstacle, character, actionEating, actionSleep, actionSpendMoney, actionStudy,
				actionWork, actionMeeting);

		getChildren().add(gameObjects);
	}

	public SideBarPresenter getSideBarPresenter() {
		return sideBarPresenter;
	}

	public void setSideBarPresenter(SideBarPresenter sideBarPresenter) {
		this.sideBarPresenter = sideBarPresenter;
	}

}
