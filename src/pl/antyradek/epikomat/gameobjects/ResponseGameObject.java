package pl.antyradek.epikomat.gameobjects;

/**
 * Element tablicy w {@link Response}, który tryma informację do wyświetlenia
 * jednego przedmiotu i jego akcji.
 * 
 * @author arq
 *
 */
public class ResponseGameObject
{
	/**
	 * Nazwa przedmiotu, która będzie wyświetlona
	 */
	private final String name;

	/**
	 * Nazwy akcji do wykonania na przedmiocie
	 */
	private final String[] actionsNames;

	/**
	 * Stwórz przechowywacz danych do wyświetlenia jednego przedmiotu
	 * 
	 * @param name
	 *            Nazwa przedmiotu
	 * @param actions
	 *            nazwy akcji na przedmiocie
	 */
	public ResponseGameObject(String name, String[] actions)
	{
		this.name = name;
		this.actionsNames = actions;
	}

	public String getName()
	{
		return name;
	}

	public String[] getActions()
	{
		return actionsNames;
	}
}
