package pl.antyradek.epikomat.controller;

/**
 * Strategia aplikacji, czyli co ma robić
 * 
 * @author arq
 *
 */
abstract class Strategy
{
	/**
	 * Kontroler, na którym wykonujemy strategie
	 */
	protected final Controller controller;

	/**
	 * Strategia steruje wykonaniem w Kontrolerze
	 * 
	 * @param controller
	 *            Kontroler, który sterujemy
	 */
	public Strategy(Controller controller)
	{
		this.controller = controller;
	}

	/**
	 * Uruchamia strategię używając informacji zawartych w appAction.
	 * 
	 * @param appAction
	 *            Informacje potrzebne do strategii
	 */
	abstract void doWithAppAction(AppAction appAction);
}
