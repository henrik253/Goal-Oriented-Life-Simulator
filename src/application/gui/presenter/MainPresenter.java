package application.gui.presenter;

import application.model.Model;

public class MainPresenter {

	private Model model;

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public SideBarPresenter getSiderBarPresenter() {
		return siderBarPresenter;
	}

	public void setSiderBarPresenter(SideBarPresenter siderBarPresenter) {
		this.siderBarPresenter = siderBarPresenter;
	}

	public SimulationPresenter getSimulationPresenter() {
		return simulationPresenter;
	}

	public void setSimulationPresenter(SimulationPresenter simulationPresenter) {
		this.simulationPresenter = simulationPresenter;
	}

	private SideBarPresenter siderBarPresenter;
	private SimulationPresenter simulationPresenter;
}
