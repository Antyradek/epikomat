package pl.antyradek.epikomat.model;

import java.util.HashMap;
import java.util.Map;

import pl.antyradek.epikomat.bus.AvailableGame;
import pl.antyradek.epikomat.bus.AvailableGameWashingMachineAdventure;
import pl.antyradek.epikomat.bus.Response;
import pl.antyradek.epikomat.events.ViewResponseEvent;
import pl.antyradek.epikomat.exceptions.GameStartException;

/** Cała wspaniałość aplikacji Trzyma informację o grze
 * 
 * @author Radosław Świątkiewicz */
public class Model
{
	/** Obecnie grana gra */
	private Game currentGame;
	/** Most między opisami gier, a rzeczywistymi grami do zagrania */
	private final Map<Class<? extends AvailableGame>, GameStartStrategy> availableGamesMap;

	/** Model bez uruchomionej gry */
	public Model()
	{
		this.availableGamesMap = new HashMap<Class<? extends AvailableGame>, GameStartStrategy>();
		this.availableGamesMap.put(AvailableGameWashingMachineAdventure.class, new WashingMachineAdventureGameStartStrategy());
	}

	/** Foldery odpowiednich gier. Wywołanie odpowiedniego indeksu rozpoczyna grę */
	// private File[] gameDirs;

	/** Uruchom grę o numerze indeksu odpowiedniej nazwy z getAvaliableGameNames()
	 * 
	 * Takie rozwiązanie pozwala na uniknięcie tworzenia obiektów gier tylko po to, żeby dostać ich nazwy. Wygląda źle, wiem. W przyszłości ma być zmieniona na
	 * 
	 * @param gameID Indeks tablicy z grami
	 * @throws GameStartException Gra nie może być wystartowana */
	public void startGame(final AvailableGame game) throws GameStartException
	{
		availableGamesMap.get(game.getClass()).runGame();
	}

	/** Początkowy stan gry
	 * 
	 * @return Dane do wyświetlenia na samym początku gry */
	public Response getInitialState()
	{
		return currentGame.getInitialState();
	}

	/** Wykonaj akcję na działającej grze
	 * 
	 * @param action Akcja z Widoku niosą ca informację o indeksach akcji i przedmiotu
	 * @return Odpowiedź na to wywołanie */
	public Response executeAction(final ViewResponseEvent action)
	{
		return currentGame.executeAction(action);
	}

	/** Strategia uruchomienia gry. Run uruchamia grę
	 * @author Radosław Świątkiewicz
	 * @throws GameStartException Gry nie można uruchomić gry */
	private abstract class GameStartStrategy
	{
		public abstract void runGame() throws GameStartException;
	}

	/** Ooh, jaka długa nazwa. Strategia uruchomienia Pralkowej przygody.
	 * @author Radosław Świątkiewicz */
	private class WashingMachineAdventureGameStartStrategy extends GameStartStrategy
	{
		/** Uruchom grę
		 * @throws GameStartException Gdy nie można uruchomić gry */
		@Override
		public void runGame() throws GameStartException
		{
			currentGame = new WashingMachineAdventure();
		}
	}
}
