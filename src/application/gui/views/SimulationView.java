package application.gui.views;

import application.gui.Settings;
import application.gui.presenter.SimulationPresenter;
import application.model.gameobjects.GameObjectTag;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class SimulationView extends Pane {

	private SimulationPresenter simulationPresenter;
	private GameObjectTag tag = GameObjectTag.WALL; // by default Obstacle

	private Pane grid = new Pane();
	private double gridElementWidth, gridElementHeight;
	private int rows, columns;

	private GridElement[][] gridElementMap;
	private GameObjectTag[][] gameObjectMap;

	private class GridElement extends Rectangle {
		public GridElement(Color color) {
			super(gridElementWidth, gridElementHeight);
			this.setFill(color);
		}
	}

	public SimulationView(double width, double height, int rows, int columns) {
		setPrefWidth(width);
		setPrefHeight(height);
		setId("SimulationView");
		gridElementWidth = width / columns;
		gridElementHeight = height / rows;
		this.rows = rows;
		this.columns = columns;
		gridElementMap = new GridElement[rows][columns];
		gameObjectMap = new GameObjectTag[rows][columns];
		init();
	}

	private void init() {
		initGrid();
		drawGrid();
	}

	private void initGrid() {
		getChildren().add(grid);

		grid.setOnMouseClicked(event -> {
			if (event.getButton() == MouseButton.SECONDARY) {
				removeElementEvent(event);
			} else {
				addElementEvent(event);
			}
		});

		grid.setOnMouseDragged(event -> {
			if (event.getButton() == MouseButton.SECONDARY) {
				removeElementEvent(event);
			} else {
				addElementEvent(event);
			}
		});

	}

	private void addElementEvent(MouseEvent event) {
		try {
			int col = (int) (event.getX() / gridElementWidth);
			int row = (int) (event.getY() / gridElementHeight);

			GridElement gridElement = getGridElementFromTag(tag);
			gridElement.setTranslateX(col * gridElementWidth);
			gridElement.setTranslateY(row * gridElementHeight);

			if (gridElementMap[row][col] != null) {
				grid.getChildren().remove(gridElementMap[row][col]);
			}

			grid.getChildren().add(gridElement);
			gridElementMap[row][col] = gridElement;
			gameObjectMap[row][col] = tag;
		} catch (IndexOutOfBoundsException e) {
		}

	}

	private void removeElementEvent(MouseEvent event) {
		try {
			int col = (int) (event.getX() / gridElementWidth);
			int row = (int) (event.getY() / gridElementHeight);

			if (gridElementMap[row][col] != null) {
				grid.getChildren().remove(gridElementMap[row][col]);
				gridElementMap[row][col] = null;
				gameObjectMap[row][col] = null;
			}
		} catch (IndexOutOfBoundsException e) {
		}
	}

	public void clearElementsOnGrid() {
		grid.getChildren().clear();
	}

	public void drawGrid() {
		grid.setPrefSize(getPrefWidth(), getPrefHeight());

		double width = grid.getPrefWidth() / columns;
		double height = grid.getPrefHeight() / rows;
		for (int i = 0; i <= columns; i++) {
			Line line = new Line(i * width, 0, i * width, grid.getPrefHeight());
			line.setFill(Color.GRAY);
			getChildren().add(line);
		}
		for (int i = 0; i <= rows; i++) {
			Line line = new Line(0, i * height, grid.getPrefWidth(), i * height);
			line.setFill(Color.GRAY);
			getChildren().add(line);
		}
	}

	public void draw(GameObjectTag[][] map) {
		clearElementsOnGrid();
		drawGrid();
		gameObjectMap = map;
		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map[row].length; col++) {
				GameObjectTag tag = map[row][col];
				if (tag != null) {
					GridElement gridElement = getGridElementFromTag(tag);
					gridElement.setTranslateX(col * gridElementWidth);
					gridElement.setTranslateY(row * gridElementHeight);
					grid.getChildren().add(gridElement);
				}
			}
		}
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

	private GridElement getGridElementFromTag(GameObjectTag tag) {
		return switch (tag) {
		case GAME_CHARACTER -> {
			yield new GridElement(Settings.COLOR_GAME_CHARACTER);
		}
		case EATING -> {
			yield new GridElement(Settings.COLOR_ACTION_EATING);
		}
		case MEETING -> {
			yield new GridElement(Settings.COLOR_ACTION_MEETING);
		}
		case WALL -> {
			yield new GridElement(Settings.COLOR_OBSTACLE_WALL);
		}
		case SLEEP -> {
			yield new GridElement(Settings.COLOR_ACTION_SLEEP);
		}
		case SPEND_MONEY -> {
			yield new GridElement(Settings.COLOR_ACTION_SPEND_MONEY);
		}
		case STUDY -> {
			yield new GridElement(Settings.COLOR_ACTION_STUDY);
		}
		case WORK -> {
			yield new GridElement(Settings.COLOR_ACTION_WORK);
		}
		default -> {
			throw new IllegalArgumentException("GameObjectTag not exists");
		}
		};
	}

	public GameObjectTag[][] getGameObjectMap() {
		return gameObjectMap;
	}

}
