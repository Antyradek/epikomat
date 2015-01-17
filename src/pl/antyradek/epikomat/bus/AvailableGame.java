package pl.antyradek.epikomat.bus;

/** Dostępna gra do uruchomienia w Modelu
 * @author Radosław Świątkiewicz */
public abstract class AvailableGame
{
	/** Nazwa gry */
	private final String name;

	/** Stwórz nowy Id gry do zagrania
	 * @param name Nazwa do wyświetlenia */
	public AvailableGame(final String name)
	{
		this.name = name;
	}

	/** Zwraca nazwę gry
	 * @return Nazwa gry do przeczytania */
	public String getName()
	{
		return name;
	}
}
