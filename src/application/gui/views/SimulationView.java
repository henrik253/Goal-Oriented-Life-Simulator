package application.gui.views;

import application.gui.presenter.SimulationPresenter;
import application.model.gameobjects.GameObjectTag;
import javafx.scene.layout.Pane;

public class SimulationView extends Pane
{

    private SimulationPresenter simulationPresenter;

    private Pane grid = new Pane();

    private

    public SimulationView(double width, double height, int rows, int columns)
    {
        setPrefWidth(width);
        setPrefHeight(height);
        setId("SimulationView");
        init();
    }

    private void init()
    {

        getChildren().add(grid);
    }

    private void drawGrid()
    {
        grid.setPrefSize(getPrefWidth(), getPrefHeight());

    }

    public void updateView(GameObjectTag[][] field)
    {

    }

    public SimulationPresenter getSimulationPresenter()
    {
        return simulationPresenter;
    }

    public void setSimulationPresenter(SimulationPresenter simulationPresenter)
    {
        this.simulationPresenter = simulationPresenter;
    }

}
