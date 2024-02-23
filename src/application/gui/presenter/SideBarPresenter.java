package application.gui.presenter;

import application.gui.views.SideBarView;
import application.model.gameobjects.GameObjectTag;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SideBarPresenter
{

    private MainPresenter mainPresenter;

    private SideBarView sideBarView;

    public MainPresenter getMainPresenter()
    {
        return mainPresenter;
    }

    public void setMainPresenter(MainPresenter mainPresenter)
    {
        this.mainPresenter = mainPresenter;
    }

    public SideBarView getSideBarView()
    {
        return sideBarView;
    }

    public void setSideBarView(SideBarView sideBarView)
    {
        this.sideBarView = sideBarView;
    }

    public void gameObjectButtonPressed(GameObjectTag tag)
    {
    	mainPresenter.gameObjectButtonPressed(tag);
    }

	public void startGame() {
		mainPresenter.startSimulation();
	}
}
