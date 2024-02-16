package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import application.views.*;


public class Main extends Application
{
    private static final double SCENE_WIDTH = 1620, SCENE_HEIGHT = 1080;

    private static final double SIDE_BAR_WIDTH = SCENE_WIDTH / 4,
                    SIDE_BAR_HEIGHT = SCENE_HEIGHT;

    private BorderPane root;

    private Pane sideBarView;

    @Override
    public void start(Stage primaryStage)
    {
        root = new BorderPane();
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        primaryStage.setScene(scene);
        initGUI();
        addElementsToRoot();

        primaryStage.show();
    }

    private void initGUI()
    {
        sideBarView = new SideBarView(SIDE_BAR_WIDTH, SIDE_BAR_HEIGHT);

    }

    private void addElementsToRoot()
    {
        root.setLeft(sideBarView);
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
