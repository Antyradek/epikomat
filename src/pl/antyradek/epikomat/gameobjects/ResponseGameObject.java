package pl.antyradek.epikomat.gameobjects;

/**
 * Element tablicy w {@link Response}, który trzyma informację do wyświetlenia
 * jednego przedmiotu i jego akcji.
 * 
 * @author Radosław Świątkiewicz
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
	 * Przechowywacz danych do wyświetlenia jednego przedmiotu
	 * 
	 * @param name
	 *            Nazwa przedmiotu
	 * @param actions
	 *            Nazwy akcji na przedmiocie
	 */
	public ResponseGameObject(String name, String[] actions)
	{
		this.name = name;
		this.actionsNames = actions;
	}

	/**
	 * Nazwa
	 * 
	 * @return Nazwa przedmiotu do wyświetlenia
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Tablica akcji. Kolejność jednoznacznie definiuje akcję i indeks tej
	 * tablicy jest używany do zwracania informacji, którą akcję wykonaliśmy.
	 * 
	 * @return Tablica nazw akcji, jakie może wykonywać ten przedmiot
	 */
	public String[] getActions()
	{
		return actionsNames;
	}
}
