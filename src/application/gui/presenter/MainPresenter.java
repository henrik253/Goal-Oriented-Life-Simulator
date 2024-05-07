package application.gui.presenter;

import application.model.Model;
import application.model.gameobjects.GameObjectTag;
import javafx.application.Platform;

public class MainPresenter {
	private SideBarPresenter siderBarPresenter;

	private SimulationPresenter simulationPresenter;

	private volatile int tickRate = 100;

	private Model model;

	public static Thread simulationThread;

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

	public void gameObjectButtonPressed(GameObjectTag tag) {
		simulationPresenter.setGameObjectTag(tag);
	}

	public int getTickRate() {
		return tickRate;
	}

	public void setTickRate(int tickRate) {
		this.tickRate = tickRate;
	}

	public void startSimulation() {
		model.setGameObjectMap(simulationPresenter.getGameObjectMap());
		model.startGame();
		
		simulationThread = new Thread(() -> {
			int count = 0; 
			while (model.isRunning()) {
				model.update();
				GameObjectTag[][] gameObjectMap = model.getField();
				Platform.runLater(() -> simulationPresenter.drawSimulationGrid(gameObjectMap));
				try {
					Thread.sleep(tickRate);
				} catch (InterruptedException e) {
				}
			}
		});
		simulationThread.start();
	}

	public void stopSimulation() {
		model.stopGame();
	}

	public void setGameObjectMapInModel(GameObjectTag[][] gameObjectMap) {
		model.setGameObjectMap(gameObjectMap);
	}
}
