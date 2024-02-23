package application.gui.presenter;

import application.gui.views.SimulationView;
import application.model.gameobjects.GameObjectTag;

public class SimulationPresenter {

	private MainPresenter mainPresenter;
	private SimulationView simulationView;
	
	private GameObjectTag tag = GameObjectTag.OBSTACLE; // by default Obstacle 
	
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

	public void setGameObjectTag(GameObjectTag tag) {
		this.tag = tag; 
	}

}
