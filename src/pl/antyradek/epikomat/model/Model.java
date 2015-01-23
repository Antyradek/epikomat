package pl.antyradek.epikomat.model;

import java.util.HashMap;
import java.util.Map;

import pl.antyradek.epikomat.bus.AvailableGame;
import pl.antyradek.epikomat.bus.AvailableGameWashingMachineAdventure;
import pl.antyradek.epikomat.bus.Response;
import pl.antyradek.epikomat.events.ViewResponseEvent;
import pl.antyradek.epikomat.exceptions.GameStartException;

/**
 * Cała wspaniałość aplikacji Trzyma informację o grze
 * 
 * @author Radosław Świątkiewicz
 */
public class Model
{
	/** Obecnie grana gra */
	private Game currentGame;
	/** Most między opisami gier, a rzeczywistymi grami do zagrania */
	private final Map<Class<? extends AvailableGame>, GameStartStrategy> availableGamesMap;

	/**
	 * Model bez uruchomionej gry
	 */
	public Model()
	{
		this.availableGamesMap = new HashMap<Class<? extends AvailableGame>, GameStartStrategy>();
		this.availableGamesMap.put(AvailableGameWashingMachineAdventure.class, new WashingMachineAdventureGameStartStrategy());
	}

	/**
	 * Uruchom grę z dostęnych
	 * 
	 * @param game Gra do uruchomienia
	 * @throws GameStartException Gra nie może być wystartowana
	 */
	public void startGame(final AvailableGame game) throws GameStartException
	{
		availableGamesMap.get(game.getClass()).runGame();
	}

	/**
	 * Początkowy stan gry
	 * 
	 * @return Dane do wyświetlenia na samym początku gry
	 */
	public Response getInitialState()
	{
		return currentGame.getInitialState();
	}

	/**
	 * Wykonaj event na działającej grze
	 * 
	 * @param event Event z Widoku niosą ca informację o indeksach akcji i przedmiotu
	 * @return Odpowiedź na to wywołanie
	 */
	public Response executeEvent(final ViewResponseEvent event)
	{
		return currentGame.executeAction(event);
	}

	/**
	 * Strategia uruchomienia gry. Run uruchamia grę
	 * 
	 * @author Radosław Świątkiewicz
	 */
	private abstract class GameStartStrategy
	{
		/**
		 * Spróbuj uruchomić grę
		 * 
		 * @throws GameStartException Gdy nie można uruchomić gry
		 */
		public abstract void runGame() throws GameStartException;
	}

	/**
	 * Ooh, jaka długa nazwa. Strategia uruchomienia Pralkowej przygody.
	 * 
	 * @author Radosław Świątkiewicz
	 */
	private class WashingMachineAdventureGameStartStrategy extends GameStartStrategy
	{
		@Override
		public void runGame() throws GameStartException
		{
			currentGame = new WashingMachineAdventure();
		}
	}
}
