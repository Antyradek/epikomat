package pl.antyradek.epikomat.model;

import pl.antyradek.epikomat.gameobjects.Response;
import pl.antyradek.epikomat.resources.GameResources;

/**
 * Gra, inaczej poziom, rozgrywka. Osobna opowieść.
 * 
 * @author arq
 *
 */
public abstract class Game
{

	/**
	 * Zasoby teg gry
	 */
	private final GameResources resources;

	/**
	 * Zbuduj poziom
	 */
	protected abstract void buildLevel();

	/**
	 * Stwórz wymaganą grę budując poziom
	 */
	public Game(String resourceBundleFilename)
	{
		resources = new GameResources(resourceBundleFilename);
		buildLevel();
	}

	/**
	 * Tekst dla przedmiotu charakterystyczny dla tej gry
	 * 
	 * @param key
	 *            Klucz do tekstu z pliku zasobów
	 * @return Odpowiadająca mu wartość
	 */
	public String getResource(String key)
	{
		return resources.getResource(key);
	}

	/**
	 * Zwraca początkowy stan, czyli co wyświetlić na początku gry
	 * 
	 * @return Log i przedmioty na początek gry
	 */
	public abstract Response getInitialState();

	/**
	 * Pokój w kturym gracz znajduje się obecnie
	 */
	Room currentRoom;

	/**
	 * Ustaw pokój
	 * 
	 * @param newRoom
	 *            W tym pokoju będzie się teraz znajdował gracz
	 */
	protected void setCurrentRoom(Room newRoom)
	{
		currentRoom = newRoom;
	}

	/**
	 * Zwróć pokój w kturym znajduje się gracz
	 * 
	 * @return
	 */
	protected Room getCurrentRoom()
	{
		return currentRoom;
	}

}
