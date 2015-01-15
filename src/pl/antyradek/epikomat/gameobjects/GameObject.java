package pl.antyradek.epikomat.gameobjects;

import java.io.FileNotFoundException;

import pl.antyradek.epikomat.debug.Debug;
import pl.antyradek.epikomat.model.Room;
import pl.antyradek.epikomat.resources.GameResources;

/**
 * Baza dla każdego przedmiotu w grze, implementacja wewnętrzna przedmiotów jest
 * dowolna, co pozwala na zwiększoną funkcjonalność kosztem obszerności kodu
 * niestey.
 * 
 * @author Radosław Świątkiewicz
 *
 */
public abstract class GameObject
{
	/**
	 * Pokój w którym znajduje się ten przedmiot
	 */
	private final Room room;

	/**
	 * Zasoby dla tego przedmiotu
	 */
	private final GameResources resources;

	/**
	 * Jeśli przedmiot jest niewidoczny, nie wyświetla się w liście przedmiotów
	 */
	private boolean isVisible;

	/**
	 * Przedmiot wczyta odpowiednie zasoby. Domyślnie jest widoczny i pokaże się
	 * na liście przedmiotow.
	 * 
	 * @param room
	 *            Katalog pokoju
	 * @param name
	 *            Plik zasobów dla tego przedmiotu
	 * @throws FileNotFoundException
	 *             Gdy nie uda się wczytać
	 */
	public GameObject(final Room room, final String name)
			throws FileNotFoundException
	{
		this.room = room;
		resources = new GameResources(room.getGame().getGameName(),
				room.getRoomName(), name);
		if (resources.isGood())
		{
			Debug.logSuccess("Wczytano zasoby przedmiotu " + name);
		} else
		{
			Debug.logErr("Błąd zasobów przedmiotu " + name);
			throw new FileNotFoundException(name);
		}
		isVisible = true;
	}

	/**
	 * Zwraca nazwę przedmiotu, ta nazwa używana jest do wyświetlenia w GUI
	 * 
	 * @return Nazwa przedmotu
	 */
	public String getGameObjectName()
	{
		return getResource("Name");
	}

	/**
	 * Zwraca nazwy akcji w kolejności. Jeśli ten indeks zostanie wysłany z
	 * powrotem, wykonana zostanie określona akcja.
	 * 
	 * @return Tablica nazw akcji w przedmiocie
	 */
	public abstract String[] getActionNames();

	/**
	 * Wykonuje akcję o indeksie nazwy pobranej z getActionNames()
	 * 
	 * @param actionIndex
	 *            Numer w tablicy nazw
	 * @return Dopisanie do logu, gdy wykonana została akcja, lub null, jeśli
	 *         takiej akcji nie dało się wykonać
	 */
	public abstract Response executeAction(final int actionIndex);

	/**
	 * Weź zasób dla przedmiotu o podanym kluczu.
	 * 
	 * @param key
	 *            Klucz to tekstu w odpowiednim pliku
	 * @return Dane po kluczu, lub odpowiednia informacja, jak podano w
	 *         {@link GameResources}
	 */
	protected String getResource(final String key)
	{
		return resources.getResource(key);
	}

	/**
	 * Pokój tego przedmiotu, dla jakiego został stworzony, nie koniecznie w
	 * jakim się znajduje.
	 * 
	 * @return Pokój dla którego został stworzony
	 */
	public Room getRoom()
	{
		return room;
	}

	/**
	 * Ustaw widoczność przedmiotu
	 * 
	 * @param visible
	 *            Widoczność ma być taka, jesli jest <code>false</code>,
	 *            przedmiot się nie wyświetli na liście
	 */
	public void setVisible(final boolean visible)
	{
		isVisible = visible;
	}

	/**
	 * Czy ten przedmiot jest widoczny?
	 * 
	 * @return Widoczność przedmiotu
	 */
	public boolean isVisible()
	{
		return isVisible;
	}
}
