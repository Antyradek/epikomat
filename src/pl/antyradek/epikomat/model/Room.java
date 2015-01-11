package pl.antyradek.epikomat.model;

import java.util.ArrayList;
import java.util.List;

import pl.antyradek.epikomat.controller.ViewResponseAction;
import pl.antyradek.epikomat.debug.Debug;
import pl.antyradek.epikomat.gameobjects.GameObject;
import pl.antyradek.epikomat.gameobjects.Response;
import pl.antyradek.epikomat.resources.GameResources;

/**
 * Jeden pokój w którym znajdują się wszystkie przedmioty. Gracz przechodzi z
 * pokoju do pokoju
 * 
 * @author Radosław Świątkiewicz
 *
 */
public class Room
{
	/**
	 * Lista przetrzymująca wszystkie przedmioty
	 */
	private List<GameObject> list;

	/**
	 * Lista przetrzymująca wszystkie aktywne (w czasie zapytania) przedmioty,
	 * które zostały wyświetlone.
	 */
	private List<GameObject> activeList;

	/**
	 * Zasoby pokoju, czyli jego opis
	 */
	private final GameResources roomResources;

	/**
	 * Gra w której pokój uczestniczy
	 */
	private final Game game;

	/**
	 * Nazwa pokoju (folder zasobów)
	 */
	private final String roomName;

	/**
	 * Pusty pokój
	 * 
	 * @param game
	 *            W tej grze
	 * @param roomName
	 *            Z takim katalogiem zasobów
	 */
	public Room(Game game, String roomName)
	{
		list = new ArrayList<GameObject>();
		activeList = new ArrayList<GameObject>();
		this.game = game;
		this.roomName = roomName;
		roomResources = new GameResources(game.getGameName(), roomName, "Room");
		if (roomResources.isGood())
		{
			Debug.logSuccess("Zasoby dla pokoju: " + roomName + " wczytane");
		} else
		{
			Debug.logErr("Zasoby dla pokoju " + roomName + " niewczytane!");
		}
	}

	/**
	 * Zwraca grę, w której pokój się znajduje
	 * 
	 * @return Gra w której użyto tego pokoju
	 */
	public Game getGame()
	{
		return game;
	}

	/**
	 * Nzwa pokoju (katalog zasobów)
	 * 
	 * @return Katalog zasobów dla tego pokoju
	 */
	public String getRoomName()
	{
		return roomName;
	}

	/**
	 * Dodaj przedmiot do tego pokoju. W efekcie, jeśli poprosić pokój o listę
	 * przedmiotów, znajdzie się tam i ten
	 * 
	 * @param gameObject
	 *            Przedmiot do dodania
	 */
	public void add(GameObject gameObject)
	{
		if (gameObject.getRoom() != this)
		{
			// nie koniecznie oznacza to błędy, tylko zasoby przedmiotu są gdzie
			// indziej, niż występuje
			Debug.logErr("UWAGA! Pokój stworzony na rzecz "
					+ gameObject.getRoom().roomName
					+ " dodany do innego pokoju " + roomName);
		}
		list.add(gameObject);
	}

	/**
	 * Usuń z listy ten przedmiot
	 * 
	 * @param gameObject
	 *            Przedmiot do usunięcia
	 */
	public void remove(GameObject gameObject)
	{
		list.remove(gameObject);
	}

	/**
	 * Zwraca wszystkie dodane przedmioty do tego pokoju
	 * 
	 * @return Tablica przedmiotów dodanych za pomocą add()
	 */
	public GameObject[] getGameObjects()
	{
		return (GameObject[]) list.toArray();
	}

	/**
	 * Zwraca opis pokoju, znajduje się w pliku Room w każdym katalogu
	 * 
	 * @return Opis pokoju
	 */
	public String getRoomDescription()
	{
		return roomResources.getResource("Description");
	}

	/**
	 * Dodaje informacje o przedmiotach w pokoju
	 * 
	 * @param rawResponse
	 *            Dane bez informacji o przedmiotach
	 * @return Zmieniona wartość
	 */
	public Response addGameObjectsList(Response rawResponse)
	{
		activeList.clear();
		for (GameObject gameObject : list)
		{
			if (gameObject.isVisible())
			{
				activeList.add(gameObject);
				rawResponse.addGameObject(gameObject.getGameObjectName(),
						gameObject.getActionNames());
			}

		}
		return rawResponse;
	}

	/**
	 * Wykonuje akcję na przedmiotach w pokoju, które były aktywne w trakcie
	 * odpytywania. Kolejność jest ważna.
	 * 
	 * @param action
	 *            Akcja wykonana na jakim pzedmiocie
	 * @return Dane o wykonaniu na przedmiocie
	 */
	public Response executeAction(ViewResponseAction action)
	{
		int gameObjectIndex = action.getGameObjectIndex();
		int actionIndex = action.getActionIndex();
		// przedmion na którym wykonano akcję
		GameObject wantedGameObject = activeList.get(gameObjectIndex);
		// znowu pobiera nazwy dla logu
		String[] actionNames = wantedGameObject.getActionNames();
		Debug.log("Wykonywanie akcji: " + actionNames[actionIndex] + " na "
				+ wantedGameObject.getGameObjectName());
		return wantedGameObject.executeAction(actionIndex);
	}
}
