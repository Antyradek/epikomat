package pl.antyradek.epikomat.controller;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import pl.antyradek.epikomat.debug.Debug;
import pl.antyradek.epikomat.model.Model;
import pl.antyradek.epikomat.resources.Resources;
import pl.antyradek.epikomat.view.View;

/**
 * Główny kontroler całego programu. Ma najszerszą wiedzę, ale sam mało robi
 * poza przesyłaniem zapytań z prawa na lewo.
 * 
 * @author Radosław Świątkiewicz
 *
 */
public class Controller
{
	/**
	 * Zarządzanie widokiem
	 */
	private final View view;

	/**
	 * Kolejka od widoku. Widok wkłada, my wyciągamy.
	 */
	private final BlockingQueue<AppAction> queue;

	/**
	 * Mapa strategii, czyli mapa Akcji (V lub M) na działanie
	 */
	private final AbstractMap<Class<? extends AppAction>, Strategy> strategyMap;

	/**
	 * Główny model aplikacji, on rozdziela się na gry.
	 */
	private final Model model;

	/**
	 * Czy aplikacja jeszcze żyje?
	 */
	private boolean alive;

	public Controller()
	{
		alive = true;
		queue = new LinkedBlockingQueue<AppAction>();
		view = new View(queue);
		model = new Model();
		strategyMap = new HashMap<Class<? extends AppAction>, Strategy>();
		addStategies();

		// startuj grę TODO ekran wybierania
		model.startGame(0); // puki co
		view.setState(model.getInitialState());
	}

	/**
	 * Główne wystartowanie aplikacji
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		if (!Resources.isGood())
		{
			Debug.logErr("Błąd zasobów!");
			System.exit(-1);
		}
		Controller controller = new Controller();
		while (controller.alive)
		{
			controller.runOnce();
		}
	}

	/**
	 * Działaj, wykonuj pojedyńczą iterację przez życie
	 */
	private void runOnce()
	{
		try
		{
			AppAction appAction = queue.take();
			strategyMap.get(appAction.getClass()).doWithAppAction(appAction);
		} catch (InterruptedException e)
		{
			Debug.logErr("Błąd odebrania elementu przez aplikację! Zabij mnie!");
			e.printStackTrace();
		}
	}

	/**
	 * Dodaj wszystkie potrzebne strategie do mapy
	 */
	private void addStategies()
	{
		strategyMap.put(AppCloseAction.class, new AppCloseStrategy(this));
		strategyMap.put(ViewResponseAction.class,
				new ViewResponseStrategy(this));
	}

	/**
	 * Wyłącza aplikację
	 */
	public void stopRunning()
	{
		alive = false;
		view.dispose();
	}

	public void executeAction(ViewResponseAction action)
	{
		Debug.log("Wykonano akcję nr: " + action.getActionIndex()
				+ " na przedmiocie nr: " + action.getGameObjectIndex());
		view.setState(model.executeAction(action));
	}

}
