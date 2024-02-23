package application.gui.views;

import application.gui.presenter.SimulationPresenter;
import application.model.gameobjects.GameObjectTag;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class SimulationView extends Pane {

	private SimulationPresenter simulationPresenter;
	private GameObjectTag tag = GameObjectTag.OBSTACLE; // by default Obstacle
	private Pane grid = new Pane();

	public SimulationView(double width, double height, int rows, int columns) {
		setPrefWidth(width);
		setPrefHeight(height);
		setId("SimulationView");
		init();
	}

	private void init() {

		getChildren().add(grid);
	}

	private void drawGrid() {
		grid.setPrefSize(getPrefWidth(), getPrefHeight());

	}

	public void updateView(GameObjectTag[][] field) {

	}

	public SimulationPresenter getSimulationPresenter() {
		return simulationPresenter;
	}

	public void setSimulationPresenter(SimulationPresenter simulationPresenter) {
		this.simulationPresenter = simulationPresenter;
	}

	public void setGameObjectTag(GameObjectTag tag) {
		this.tag = tag;
	}

	private Node getNodeFromTag(GameObjectTag tag) {
		return switch (tag) {
		case GAME_CHARACTER -> {
			yield new Rectangle();
		}
		case EATING -> {
			yield new Rectangle();
		}
		case MEETING -> {
			yield new Rectangle();
		}
		case OBSTACLE -> {
			yield new Rectangle();
		}
		case SLEEP -> {
			yield new Rectangle();
		}
		case SPEND_MONEY -> {
			yield new Rectangle();
		}
		case STUDY -> {
			yield new Rectangle();
		}
		case WORK -> {
			yield new Rectangle();
		}
		default -> {
			yield new Rectangle();
		}
		};
	}

}
