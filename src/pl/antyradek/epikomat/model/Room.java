package pl.antyradek.epikomat.model;

import java.util.ArrayList;
import java.util.List;

import pl.antyradek.epikomat.gameobjects.GameObject;

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
	 * Ogólny opis pokoju w którym znajduje się gracz
	 */
	private String roomDescription;

	/**
	 * Konstruuje pusty pokój
	 */
	public Room(String roomDescription)
	{
		list = new ArrayList<GameObject>();
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
}
