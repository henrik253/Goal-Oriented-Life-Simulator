package application.gui.presenter;

import application.gui.views.SimulationView;

public class SimulationPresenter {

	private MainPresenter mainPresenter;
	private SimulationView simulationView;

	public MainPresenter getMainPresenter() {
		return mainPresenter;
	}

	public void setMainPresenter(MainPresenter mainPresenter) {
		this.mainPresenter = mainPresenter;
	}

	public SimulationView getSimulationView() {
		return simulationView;
	}

	public void setSimulationView(SimulationView simulationView) {
		this.simulationView = simulationView;
	}

}
