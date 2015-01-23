package pl.antyradek.epikomat.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.antyradek.epikomat.bus.GameObjectId;
import pl.antyradek.epikomat.bus.Response;
import pl.antyradek.epikomat.debug.Debug;
import pl.antyradek.epikomat.events.ViewResponseEvent;
import pl.antyradek.epikomat.gameobjects.GameObject;
import pl.antyradek.epikomat.resources.GameResources;

/**
 * Jeden pokój w którym znajdują się wszystkie przedmioty. Gracz przechodzi z pokoju do pokoju
 * 
 * @author Radosław Świątkiewicz
 */
public class Room
{
	/** Lista przetrzymująca wszystkie przedmioty */
	private List<GameObject> list;
	/** Zasoby pokoju, czyli jego opis */
	private final GameResources roomResources;
	/** Gra w której pokój uczestniczy */
	private final Game game;
	/** Nazwa pokoju (folder zasobów) */
	private final String roomName;
	/** Mapa do skojarzenia przedmotów i ich Id, gdy zostaną odebrane od Widoku */
	private final Map<GameObjectId, GameObject> idMap;

	/**
	 * Pusty pokój
	 * 
	 * @param game W tej grze
	 * @param roomName Z takim katalogiem zasobów
	 */
	public Room(final Game game, final String roomName)
	{
		this.list = new ArrayList<GameObject>();
		this.idMap = new HashMap<GameObjectId, GameObject>();
		this.game = game;
		this.roomName = roomName;
		this.roomResources = new GameResources(game.getGameName(), roomName, "Room");
		if(this.roomResources.isGood())
		{
			Debug.logSuccess("Zasoby dla pokoju: " + roomName + " wczytane");
		}
		else
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
	 * Dodaj przedmiot do tego pokoju. W efekcie, jeśli poprosić pokój o listę przedmiotów, znajdzie się tam i ten
	 * 
	 * @param gameObject Przedmiot do dodania
	 */
	void add(final GameObject gameObject)
	{
		if(gameObject.getRoom() != this)
		{
			// nie koniecznie oznacza to błędy, tylko zasoby przedmiotu są gdzie indziej, niż występuje
			Debug.logErr("UWAGA! Pokój stworzony na rzecz " + gameObject.getRoom().roomName + " dodany do innego pokoju " + roomName);
		}
		list.add(gameObject);
	}

	/**
	 * Usuń z listy ten przedmiot
	 * 
	 * @param gameObject Przedmiot do usunięcia
	 */
	public void remove(final GameObject gameObject)
	{
		list.remove(gameObject);
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
	 * @param rawResponse Dane bez informacji o przedmiotach
	 * @return Zmieniona wartość
	 */
	Response addGameObjectsList(final Response rawResponse)
	{
		idMap.clear();
		for(GameObject gameObject : list)
		{
			if(gameObject.isVisible())
			{
				GameObjectId id = gameObject.getId();
				idMap.put(id, gameObject);
				rawResponse.addGameObject(id);
			}
		}
		return rawResponse;
	}

	/**
	 * Wykonuje akcję na przedmiotach w pokoju, które były aktywne w trakcie odpytywania.
	 * 
	 * @param event Zwrot sprecyzowany przez Widok
	 * @return Dane o wykonaniu na przedmiocie
	 */
	Response executeAction(final ViewResponseEvent event)
	{
		Debug.log("Wykonywanie na " + event.getGameObjectActionId().getGameObjectId().getName() + " akcji " + event.getGameObjectActionId().getName());
		return idMap.get(event.getGameObjectActionId().getGameObjectId()).executeAction(event.getGameObjectActionId());
	}
}
