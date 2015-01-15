package pl.antyradek.epikomat.controller;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import pl.antyradek.epikomat.debug.Debug;
import pl.antyradek.epikomat.exceptions.GameStartException;
import pl.antyradek.epikomat.model.Model;
import pl.antyradek.epikomat.resources.Resources;
import pl.antyradek.epikomat.view.View;

/**
 * Główny kontroler całego programu. Ma najszerszą wiedzę, ale sam mało robi
 * poza przesyłaniem zapytań z prawa na lewo. Tą klasę wywołujemy dla startu
 * programu.
 * 
 * @author Radosław Świątkiewicz
 *
 */
public class Controller
{
	/**
	 * Widok tworzący ramkę i uważający na wątki
	 */
	private final View view;

	/**
	 * Kolejka od widoku. Widok wkłada, my wyciągamy.
	 */
	private final BlockingQueue<AppAction> queue;

	/**
	 * Mapa strategii, czyli mapa Akcji z widoku na zespół funkcji dla tej akcji
	 */
	private final AbstractMap<Class<? extends AppAction>, Strategy> strategyMap;

	/**
	 * Główny model aplikacji, on uruchamia grę i ma informacje o wszystkim
	 */
	private final Model model;

	/**
	 * Czy aplikacja jeszcze żyje?
	 */
	private boolean alive;

	/**
	 * Kontroler spróbuje się stworzyć, ale wyjdzie z aplikcjai, jeśli brak
	 * zasobów w grze
	 */
	public Controller()
	{
		alive = true;
		queue = new LinkedBlockingQueue<AppAction>();
		view = new View(queue);
		model = new Model();
		strategyMap = new HashMap<Class<? extends AppAction>, Strategy>();
		addStategies();

		// startuj grę TODO ekran wybierania
		try
		{
			model.startGame(0); // puki co
		} catch (GameStartException e)
		{
			Debug.logErr("Błąd wystartowania gry 0");
			stopRunning();
			System.exit(-2);
		}
		view.setState(model.getInitialState());
	}

	/**
	 * Główne wystartowanie aplikacji. Kody zakończenia:
	 * <ul>
	 * <li>0 Zamknięto poprawnie</li>
	 * <li>-1 Zasoby GUI niepoprawne</li>
	 * <li>-2 Zasoby gry niepoprawne</li>
	 * </ul>
	 * 
	 * @param args
	 *            To, co wpiszemy do terminala (na prawdę muszę pisać takie
	 *            oczywistości?)
	 */
	public static void main(String[] args)
	{
		if (!Resources.isGood())
		{
			Debug.logErr("Błąd zasobów!");
			System.exit(-1);
		}
		Controller controller = new Controller();
		// główna pętla aplikacji
		while (controller.alive)
		{
			controller.runOnce();
		}
	}

	/**
	 * Działaj, wykonuj pojedyńczą iterację przez życie. Pobranie z kolejki
	 * danych, wykonanie na modelu (lub kontrolerze), wysłanie do widoku i
	 * powtórz.
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
	 * Wyłącza aplikację i niszczy okno
	 */
	public void stopRunning()
	{
		alive = false;
		view.dispose();
	}

	/**
	 * Wywołuje akcję na modelu. Najczęstsza metoda.
	 * 
	 * @param action
	 */
	public void executeAction(final ViewResponseAction action)
	{
		Debug.log("Wykonano akcję nr: " + action.getActionIndex()
				+ " na przedmiocie nr: " + action.getGameObjectIndex());
		view.setState(model.executeAction(action));
	}

}
