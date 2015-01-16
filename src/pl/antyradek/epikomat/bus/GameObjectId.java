package pl.antyradek.epikomat.bus;

import java.util.ArrayList;
import java.util.List;

/** Element w Response, który trzyma informację do wyświetlenia jednego przedmiotu i jego akcji.
 * 
 * @author Radosław Świątkiewicz */
public class GameObjectId
{
	/** Nazwa przedmiotu, która będzie wyświetlona */
	private final String name;
	/** Lista akcji do wykonania. Podajemy ten obiekt z powrotem, aby wykonać akcję. */
	private final List<GameObjectActionId> actionIdList;

	/** Przechowywacz danych do wyświetlenia jednego przedmiotu
	 * @param name Nazwa przedmiotu */
	public GameObjectId(final String name)
	{
		this.actionIdList = new ArrayList<GameObjectActionId>();
		this.name = name;
	}

	/** Zwróć nazwę.
	 * @return Nazwa przedmiotu do wyświetlenia */
	public String getName()
	{
		return name;
	}

	/** Zwraca tablicę ID akcji, które można wykonać na tym przedmiocie
	 * @return Tablica akcji, które można wykonać na przedmiocie */
	public GameObjectActionId[] getActionIds()
	{
		return (GameObjectActionId[]) actionIdList.toArray();
	}

	/** Dodaj możiwą akcję do tego przedmiotu
	 * @param actionId ID akcji, którą można wykonać na przedmiocie */
	public void addAction(GameObjectActionId actionId)
	{
		actionIdList.add(actionId);
	}
}
