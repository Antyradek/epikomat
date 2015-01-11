package pl.antyradek.epikomat.controller;

/**
 * Strategia aplikacji, czyli co ma robić w jakim momencie. Inaczej mówiąc,
 * jakie metody na wywołać na Kontrolerze.
 * 
 * @author Radosław Świątkiewicz
 *
 */
abstract class Strategy
{
	/**
	 * Kontroler, na którym wykonujemy strategie
	 */
	protected final Controller controller;

	/**
	 * Strategia wykonuje coś na Kontrolerze. To coś jest zdefiniowane w
	 * doWithAppAction()
	 * 
	 * @param controller
	 *            Kontroler, którym sterujemy, na którym wywołujmy odpowiednie
	 *            metody.
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
