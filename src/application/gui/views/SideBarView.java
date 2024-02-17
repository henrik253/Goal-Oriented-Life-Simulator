package application.gui.views;

import application.gui.presenter.SideBarPresenter;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class SideBarView extends Pane {

	private SideBarPresenter sideBarPresenter;

	public SideBarView(double witdh, double height) {
		this.setPrefHeight(height);
		this.setPrefWidth(witdh);
		this.setId("SiderBarView");
	}

	public SideBarPresenter getSideBarPresenter() {
		return sideBarPresenter;
	}

	public void setSideBarPresenter(SideBarPresenter sideBarPresenter) {
		this.sideBarPresenter = sideBarPresenter;
	}

}
