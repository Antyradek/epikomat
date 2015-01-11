package pl.antyradek.epikomat.exceptions;

/**
 * Wyjątek, jeśli uruchomienie gry się nie powiedzie
 * 
 * @author Radosław Świątkiewicz
 *
 */
public class GameStartException extends Exception
{

	/**
	 * Takie coś, żeby się nie zepsuło i żeby Eclipse się nie buntował
	 */
	private static final long serialVersionUID = -1607920420091161384L;

	/**
	 * Bez dodatkowej informacji
	 */
	public GameStartException()
	{

	}

	/**
	 * Z dodatkową informacją
	 * 
	 * @param message
	 *            W postaci tej wiadomości
	 */
	public GameStartException(String message)
	{
		super(message);

	}

	/**
	 * Z informacją na temat powodu
	 * 
	 * @param cause
	 *            Powód rzucania wyjątku
	 */
	public GameStartException(Throwable cause)
	{
		super(cause);

	}

	/**
	 * Obie informacje
	 * 
	 * @param message
	 *            Informacja o wyrzuceniu
	 * @param cause
	 *            Powód wyrzucenia
	 */
	public GameStartException(String message, Throwable cause)
	{
		super(message, cause);

	}

	/**
	 * Rozbudowany wyjątek
	 * 
	 * @param message
	 *            Informacja o wyrzucaniu
	 * @param cause
	 *            Powód wyrzucenia
	 * @param enableSuppression
	 *            Zatrzymanie programu
	 * @param writableStackTrace
	 *            Wypisywalna lista wywołań
	 */
	public GameStartException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
