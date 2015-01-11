package pl.antyradek.epikomat.model;

import pl.antyradek.epikomat.controller.ViewResponseAction;
import pl.antyradek.epikomat.exceptions.GameStartException;
import pl.antyradek.epikomat.gameobjects.Response;

/**
 * Gra, inaczej poziom, rozgrywka. Osobna opowieść.
 * 
 * @author Radosław Świątkiewicz
 *
 */
public abstract class Game
{

	/**
	 * Nazwa gry, czyli katalog zasobów gry
	 */
	private final String gameName;

	/**
	 * Czy pokój został zmieniony i należy odświerzyć log? Trochę nieprzyjemna
	 * metoda.
	 */
	private boolean roomChanged;

	/**
	 * Zbuduj poziom
	 * 
	 * @throws GameStartException
	 *             Jeśli nie znaleziono choćby jednego zasobu
	 */
	protected abstract void buildLevel() throws GameStartException;

	/**
	 * Stwórz wymaganą grę budując poziom
	 * 
	 * @param gameDirName
	 *            Katalog zasobów dla tej gry
	 * @throws GameStartException
	 *             Gdy nie udało się uruchomić gry
	 */
	public Game(String gameDirName) throws GameStartException
	{
		this.gameName = gameDirName;
		buildLevel();
	}

	/**
	 * Zwraca nazwę folderu z zasobami gry
	 * 
	 * @return Nazwa folderu w której są foldery pokoi
	 */
	public String getGameName()
	{
		return gameName;
	}

	/**
	 * Zwraca początkowy stan, czyli co wyświetlić na początku gry
	 * 
	 * @return Log i przedmioty na początek gry, czyści log
	 */
	public abstract Response getInitialState();

	/**
	 * Pokój w kturym gracz znajduje się obecnie
	 */
	private Room currentRoom;

	/**
	 * Ustaw pokój, zapamiętaj, że zmieniłeś
	 * 
	 * @param newRoom
	 *            W tym pokoju będzie się teraz znajdował gracz
	 */
	public void setCurrentRoom(Room newRoom)
	{
		if (currentRoom != newRoom)
		{
			roomChanged = true;
		}
		currentRoom = newRoom;
	}

	/**
	 * Zamienia pokoje nie informując nikogo o zmianie
	 * 
	 * @param newRoom
	 */
	public void swapCurrentRoom(Room newRoom)
	{
		currentRoom = newRoom;
	}

	/**
	 * Zwróć pokój w którym znajduje się gracz
	 * 
	 * @return Pokój w którym się znajdujemy
	 */
	protected Room getCurrentRoom()
	{
		return currentRoom;
	}

	/**
	 * Wykonuje akcję na obecnym pokoju. Zwracana informacja jest zmieniana,
	 * jeśli zmieniono pokój, dopisywany jest opis pokoju. Ustawiane jest też
	 * czyszcenie logu. To wygląda źle, ale zwalnia z odpowiedzialności każdy z
	 * przedmiotów osobno do dopisywania opisu pokoju i ustawiania czyszczenia
	 * logu.
	 * 
	 * @param action
	 *            Dane właściwie wygenerowane jescze w widkou zawierające indeks
	 *            przedmotu i akcji
	 * @return Gotowe dane zawierające informacje z przedmiotu i przedmioty z
	 *         pokoju
	 */
	public Response executeAction(ViewResponseAction action)
	{
		// informacja od przedmiotu jest niepełna, nie posiada informacji o
		// liście przedmiotów w pokoju
		Response gameObjectResponse = currentRoom.executeAction(action);
		// dopisanie listy przedmiotów, nie robi tego pokój, bo może się zmienić
		// po akcji
		gameObjectResponse = currentRoom.addGameObjectsList(gameObjectResponse);
		if (roomChanged)
		{
			roomChanged = false;
			gameObjectResponse.setClearsLog(true);
			gameObjectResponse.appendLog(currentRoom.getRoomDescription());
		}

		return gameObjectResponse;
	}

}
