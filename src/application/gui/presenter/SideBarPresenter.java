package application.gui.presenter;

import application.gui.views.SideBarView;

public class SideBarPresenter {

	public MainPresenter getMainPresenter() {
		return mainPresenter;
	}

	public void setMainPresenter(MainPresenter mainPresenter) {
		this.mainPresenter = mainPresenter;
	}

	public SideBarView getSideBarView() {
		return sideBarView;
	}

	public void setSideBarView(SideBarView sideBarView) {
		this.sideBarView = sideBarView;
	}

	private MainPresenter mainPresenter;
	private SideBarView sideBarView;
}
