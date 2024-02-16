package application.gui.views;

import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class SideBarView extends Pane
{

    public SideBarView(double witdh, double height)
    {
        this.setPrefHeight(height);
        this.setPrefWidth(witdh);
        this.setId("SiderBarView");
    }

   
}
