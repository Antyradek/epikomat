package pl.antyradek.epikomat.bus;

/** Jednoznacznie określa metodę przedmiotu na zewnątrz. Jest to informacja o akcji i nazwie wysyłana do Widoku. Przedmiot zawiera mapę, dzięki której zna metodę, jakiej obiekt dotyczy. Widok zwraca ten obiekt do modelu.
 * @author Radosław Świątkiewicz */
public class GameObjectActionId
{
	/** Nazwa akcji widziana przez użytkownika */
	private final String name;
	/** Id przedmiotu do której akcja należy */
	private final GameObjectId gameObjectId;

	/** Nowy informator o akcji przedmiotu
	 * @param name Nazwa akcji
	 * @param gameObjectId Id przedmiotu do którego akcja należy */
	public GameObjectActionId(final String name, final GameObjectId gameObjectId)
	{
		this.name = name;
		this.gameObjectId = gameObjectId;
	}

	/** Zwróć nazwę tej akcji
	 * @return Nazwa akcji */
	public String getName()
	{
		return name;
	}

	/** Zwróć ID przedmiotu do którego należy ta akcja
	 * @return Id przedmiotu */
	public GameObjectId getGameObjectId()
	{
		return gameObjectId;
	}

}
