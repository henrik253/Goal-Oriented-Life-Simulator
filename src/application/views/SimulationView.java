package application.views;

import javafx.scene.layout.Pane;

public class SimulationView extends Pane
{

    public SimulationView(double width, double height)
    {
        setPrefWidth(width);
        setPrefHeight(height);
        setId("SimulationView");
        init();
    }

    private void init()
    {

    }
}
