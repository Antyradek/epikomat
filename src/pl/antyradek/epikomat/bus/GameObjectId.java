package pl.antyradek.epikomat.bus;

import java.util.ArrayList;
import java.util.List;

/**
 * Element w Response, który trzyma informację do wyświetlenia jednego przedmiotu i jego akcji.
 * 
 * @author Radosław Świątkiewicz
 */
public class GameObjectId
{
	/** Nazwa przedmiotu, która będzie wyświetlona */
	private String name;
	/** Lista akcji do wykonania. Podajemy ten obiekt z powrotem, aby wykonać akcję. */
	private final List<GameObjectActionId> actionIdList;

	/**
	 * Przechowywacz danych do wyświetlenia jednego przedmiotu
	 * 
	 * @param name Nazwa przedmiotu
	 */
	public GameObjectId(final String name)
	{
		this.actionIdList = new ArrayList<GameObjectActionId>();
		this.name = name;
	}

	/**
	 * Zwróć nazwę.
	 * 
	 * @return Nazwa przedmiotu do wyświetlenia
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Zaktualizuj nazwę przedmiotu. Ponieważ ten Id jest jedyny na przedmiot, potrzeba zaktualizować nazwę (a nie tworzyć nowy przedmiot).
	 * 
	 * @param name Nowa nazwa do ustawienia
	 */
	public void setName(final String name)
	{
		this.name = name;
	}

	/**
	 * Zwraca tablicę ID akcji, które można wykonać na tym przedmiocie
	 * 
	 * @return Tablica akcji, które można wykonać na przedmiocie
	 */
	public List<GameObjectActionId> getActionIds()
	{
		return actionIdList;
	}

	/**
	 * Dodaj możliwą akcję do tego przedmiotu
	 * 
	 * @param actionId ID akcji, którą można wykonać na przedmiocie
	 */
	public void addAction(final GameObjectActionId actionId)
	{
		actionIdList.add(actionId);
	}
}
