package pl.antyradek.epikomat.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import pl.antyradek.epikomat.bus.AvailableGameWashingMachineAdventure;
import pl.antyradek.epikomat.debug.Debug;
import pl.antyradek.epikomat.events.AppCloseEvent;
import pl.antyradek.epikomat.events.ViewEvent;
import pl.antyradek.epikomat.events.ViewResponseEvent;
import pl.antyradek.epikomat.exceptions.GameStartException;
import pl.antyradek.epikomat.model.Model;
import pl.antyradek.epikomat.view.View;

/** Główny kontroler całego programu. Ma najszerszą wiedzę, ale sam mało robi poza przesyłaniem zapytań z prawa na lewo. Tą klasę wywołujemy dla startu programu.
 * @author Radosław Świątkiewicz */
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

	/** Kontroler spróbuje się stworzyć, ale wyjdzie z aplikcjai, jeśli brak zasobów w grze */
	public Controller()
	{
		this.alive = true;
		this.queue = new LinkedBlockingQueue<ViewEvent>();
		this.view = new View(queue);
		this.model = new Model();
		this.strategyMap = new HashMap<Class<? extends ViewEvent>, Strategy>();
		addStategies();

		// startuj grę TODO ekran wybierania
		try
		{
			this.model.startGame(new AvailableGameWashingMachineAdventure());
		}catch(GameStartException e)
		{
			Debug.logErr("Błąd wystartowania gry 0");
			stopRunning();
			System.exit(-2);
		}
		this.view.setState(model.getInitialState());
	}

	/** Wywołuje aplikację do ciągłego działania */
	public void run()
	{
		while(alive)
		{
			runOnce();
		}
	}

	/** Działaj, wykonuj pojedyńczą iterację przez życie. Pobranie z kolejki danych, wykonanie na modelu (lub kontrolerze), wysłanie do widoku i powtórz. */
	private void runOnce()
	{
		try
		{
			ViewEvent appAction = queue.take();
			strategyMap.get(appAction.getClass()).doWithViewEvent(appAction);
		}catch(InterruptedException e)
		{
			Debug.logErr("Błąd odebrania elementu przez aplikację! Zabij mnie!");
			e.printStackTrace();
		}
	}

	/** Dodaj wszystkie potrzebne strategie do mapy */
	private void addStategies()
	{
		strategyMap.put(AppCloseEvent.class, new AppCloseStrategy(this));
		strategyMap.put(ViewResponseEvent.class, new ViewResponseStrategy(this));
	}

	/** Wyłącza aplikację i niszczy okno */
	public void stopRunning()
	{
		alive = false;
		view.dispose();
	}

	/** Wywołuje akcję na modelu. Najczęstsza metoda.
	 * 
	 * @param action */
	private void executeAction(final ViewResponseEvent action)
	{
		view.setState(model.executeAction(action));
	}

	/** Strategia aplikacji, czyli co ma robić w jakim momencie. Inaczej mówiąc, jakie metody na wywołać na Kontrolerze.
	 * 
	 * @author Radosław Świątkiewicz */
	private abstract class Strategy
	{
		/** Kontroler, na którym wykonujemy strategie */
		protected final Controller controller;

		/** Strategia wykonuje coś na Kontrolerze. To coś jest zdefiniowane w doWithViewEvent()
		 * 
		 * @param controller Kontroler, którym sterujemy, na którym wywołujmy odpowiednie metody. */
		public Strategy(final Controller controller)
		{
			this.controller = controller;
		}

		/** Uruchamia strategię używając informacji zawartych w appAction.
		 * 
		 * @param appAction Informacje potrzebne do strategii */
		abstract void doWithViewEvent(final ViewEvent appAction);
	}

	/** Zapisanie potrzebnego stanu aplikacji i wyjście. Wyjście odbywa się poprzez otworzenie blokującej pętli i dojściu do końca main().
	 * 
	 * @author Radosław Świątkiewicz */
	private class AppCloseStrategy extends Strategy
	{
		/** Ustawia argument dla wywołania
		 * 
		 * @param controller Na tym wywoła stopRunning(), jeśli tutaj wywołamy doWithViewEvent() */
		public AppCloseStrategy(final Controller controller)
		{
			super(controller);
		}

		@Override
		public void doWithViewEvent(final ViewEvent appAction)
		{
			Debug.log("Strategia wyłączania aplikacji");
			controller.stopRunning();
		}

	}

	/** Wyślij dane o klikniętej akcji do Modelu.
	 * @author Radosław Świątkiewicz */
	private class ViewResponseStrategy extends Strategy
	{

		/** Model tego Kontrolera użyjemy
		 * 
		 * @param controller Tego Kontrolera użyjemy */
		public ViewResponseStrategy(final Controller controller)
		{
			super(controller);
		}

		@Override
		void doWithViewEvent(final ViewEvent appAction)
		{
			controller.executeAction((ViewResponseEvent) appAction);
		}

	}

}
