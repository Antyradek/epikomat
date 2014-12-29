package pl.antyradek.epikomat.model;

import java.util.ArrayList;
import java.util.List;

import pl.antyradek.epikomat.controller.ViewResponseAction;
import pl.antyradek.epikomat.debug.Debug;
import pl.antyradek.epikomat.gameobjects.GameObject;
import pl.antyradek.epikomat.gameobjects.Response;

/**
 * Jeden pokój w którym znajdują się wszystkie przedmioty. Gracz przechodzi z
 * pokoju do pokoju
 * 
 * @author arq
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
	 * Ogólny opis pokoju w którym znajduje się gracz
	 */
	private String roomDescription;

	/**
	 * Konstruuje pusty pokój
	 */
	public Room(String roomDescription)
	{
		list = new ArrayList<GameObject>();
		activeList = new ArrayList<GameObject>();
		this.roomDescription = roomDescription;
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
		list.add(gameObject);
	}

	/**
	 * Usuń z listy ten przedmiot
	 * 
	 * @param gameObject
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
	 * Zwraca opis pokoju
	 * 
	 * @return
	 */
	public String getRoomDescription()
	{
		return roomDescription;
	}

	/**
	 * Dodaje informacje o przedmiotach w pokoju
	 * 
	 * @param rawResponse
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
