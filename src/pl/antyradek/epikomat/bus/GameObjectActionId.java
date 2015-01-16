package pl.antyradek.epikomat.bus;

/** Jednoznacznie określa metodę przedmiotu na zewnątrz. Jest to informacja o akcji i nazwie wysyłana do Widoku. Przedmiot zawiera mapę, dzięki której zna metodę, jakiej obiekt dotyczy. Widok zwraca ten obiekt do modelu.
 * @author Radosław Świątkiewicz */
public class GameObjectActionId
{
	/** Nazwa akcji widziana przez użytkownika */
	private final String name;

	/** Nowy informator o akcji przedmiotu
	 * @param name Nazwa akcji */
	public GameObjectActionId(final String name)
	{
		this.name = name;
	}

	/** Zwróć nazwę tej akcji
	 * @return Nazwa akcji */
	public String getName()
	{
		return name;
	}

}
