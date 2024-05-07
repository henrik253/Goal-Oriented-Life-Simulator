package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import application.gui.presenter.MainPresenter;
import application.gui.presenter.SideBarPresenter;
import application.gui.presenter.SimulationPresenter;
import application.gui.views.*;
import application.model.Model;

public class Main extends Application
{
    private static final double SCENE_WIDTH = 1080, SCENE_HEIGHT = 720;

    private static final double SIDE_BAR_WIDTH = SCENE_WIDTH / 4,
                    SIDE_BAR_HEIGHT = SCENE_HEIGHT;

    private static final int ROWS = 10, COLUMNS = 10;

    private BorderPane root;

    private Pane sideBarView, simulationView;

    private MainPresenter mainPresenter;

    private SideBarPresenter sideBarPresenter;

    private SimulationPresenter simulationPresenter;

    private Model model;

    @Override
    public void start(Stage primaryStage)
    {
        root = new BorderPane();
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        initGUI();
        initModel();
        addElementsToRoot();

        mainPresenter.setModel(model);

        primaryStage.show();
    }

    private void initGUI()
    {
        sideBarView = new SideBarView(SIDE_BAR_WIDTH, SIDE_BAR_HEIGHT);
        simulationView = new SimulationView(SCENE_WIDTH - SIDE_BAR_WIDTH, SIDE_BAR_HEIGHT, ROWS, COLUMNS);
        mainPresenter = new MainPresenter();
        sideBarPresenter = new SideBarPresenter();
        simulationPresenter = new SimulationPresenter();

        ((SideBarView) sideBarView).setSideBarPresenter(sideBarPresenter);
        ((SimulationView) simulationView).setSimulationPresenter(simulationPresenter);

        sideBarPresenter.setMainPresenter(mainPresenter);
        sideBarPresenter.setSideBarView(((SideBarView) sideBarView));

        simulationPresenter.setMainPresenter(mainPresenter);
        simulationPresenter.setSimulationView(((SimulationView) simulationView));
        
        mainPresenter.setSimulationPresenter(simulationPresenter);
        mainPresenter.setSiderBarPresenter(sideBarPresenter);
    }

    private void initModel()
    {
        model = new Model();
    }

    private void addElementsToRoot()
    {
        root.setLeft(sideBarView);
        root.setCenter(simulationView);
    }

    public static void main(String[] args)
    {
        launch(args);
        try {
        MainPresenter.simulationThread.interrupt();
        }
        catch(Exception e) {} // Terminating simulation Thread
    }
}
