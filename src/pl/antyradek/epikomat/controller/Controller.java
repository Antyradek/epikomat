package pl.antyradek.epikomat.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import pl.antyradek.epikomat.bus.AvailableGameWashingMachineAdventure;
import pl.antyradek.epikomat.debug.Debug;
import pl.antyradek.epikomat.events.AppCloseEvent;
import pl.antyradek.epikomat.events.ViewEvent;
import pl.antyradek.epikomat.events.ViewResponseEvent;
import pl.antyradek.epikomat.exceptions.GameStartException;
import pl.antyradek.epikomat.model.Model;
import pl.antyradek.epikomat.view.View;

/**
 * Główny kontroler całego programu. Ma najszerszą wiedzę, ale sam mało robi poza przesyłaniem zapytań z prawa na lewo.
 * 
 * @author Radosław Świątkiewicz
 */
public class Controller
{
	/** Widok tworzący ramkę i uważający na wątki */
	private final View view;
	/** Kolejka od widoku. Widok wkłada, my wyciągamy. */
	private final BlockingQueue<ViewEvent> queue;
	/** Mapa strategii, czyli mapa Akcji z widoku na zespół funkcji dla tej akcji */
	private final Map<Class<? extends ViewEvent>, Strategy> strategyMap;
	/** Główny model aplikacji, on uruchamia grę i ma informacje o wszystkim */
	private final Model model;
	/** Czy aplikacja jeszcze żyje? */
	private boolean alive;

	/**
	 * Kontroler spróbuje się stworzyć, ale wyjdzie z aplikcji, jeśli brak zasobów w grze
	 * 
	 * @param queue Kolejka do komunikacji między Widokiem, a Kontrolerem
	 * @param view Widok
	 * @param model Model
	 */
	public Controller(final BlockingQueue<ViewEvent> queue, final View view, final Model model)
	{
		this.alive = true;
		this.queue = queue;
		this.view = view;
		this.model = model;
		this.strategyMap = new HashMap<Class<? extends ViewEvent>, Strategy>();
		addStategies();

		// startuj grę TODO ekran wybierania
		try
		{
			this.model.startGame(new AvailableGameWashingMachineAdventure());
			this.view.setState(model.getInitialState());
		}
		catch(GameStartException e)
		{
			Debug.logErr("Błąd wystartowania gry 0");
			stopRunning();
			System.exit(-2);
		}
	}

	/**
	 * Wywołuje aplikację do ciągłego działania
	 */
	public void run()
	{
		while(alive)
		{
			runOnce();
		}
	}

	/**
	 * Działaj, wykonuj pojedyńczą iterację przez życie. Pobranie z kolejki danych, wykonanie na modelu (lub kontrolerze), wysłanie do widoku i powtórz.
	 */
	private void runOnce()
	{
		try
		{
			ViewEvent appAction = queue.take();
			strategyMap.get(appAction.getClass()).doWithViewEvent(appAction);
		}
		catch(InterruptedException e)
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
		strategyMap.put(AppCloseEvent.class, new AppCloseStrategy());
		strategyMap.put(ViewResponseEvent.class, new ViewResponseStrategy());
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
	 * @param event Event do wykonania na modelu
	 */
	private void executeEvent(final ViewResponseEvent event)
	{
		view.setState(model.executeEvent(event));
	}

	/**
	 * Strategia aplikacji, czyli co ma robić w jakim momencie. Inaczej mówiąc, jakie metody na wywołać na Kontrolerze.
	 * 
	 * @author Radosław Świątkiewicz
	 */
	private abstract class Strategy
	{
		/**
		 * Uruchamia strategię używając informacji zawartych w appAction.
		 * 
		 * @param appAction Informacje potrzebne do strategii
		 */
		abstract void doWithViewEvent(final ViewEvent appAction);
	}

	/**
	 * Zapisanie potrzebnego stanu aplikacji i wyjście. Wyjście odbywa się poprzez otworzenie blokującej pętli i dojściu do końca main().
	 * 
	 * @author Radosław Świątkiewicz
	 */
	private class AppCloseStrategy extends Strategy
	{
		@Override
		public void doWithViewEvent(final ViewEvent appAction)
		{
			Debug.log("Strategia wyłączania aplikacji");
			stopRunning();
		}

	}

	/**
	 * Wyślij dane o klikniętej akcji do Modelu.
	 * 
	 * @author Radosław Świątkiewicz
	 */
	private class ViewResponseStrategy extends Strategy
	{
		@Override
		void doWithViewEvent(final ViewEvent event)
		{
			executeEvent((ViewResponseEvent) event);
		}

	}

}
