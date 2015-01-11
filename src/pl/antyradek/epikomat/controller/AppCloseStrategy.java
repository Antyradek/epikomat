package pl.antyradek.epikomat.controller;

import pl.antyradek.epikomat.debug.Debug;

/**
 * Zapisanie potrzebnego stanu aplikacji i wyjście. Wyjście odbywa się poprzez
 * otworzenie blokującej pętli i dojściu do końca main().
 * 
 * @author Radosław Świątkiewicz
 *
 */
class AppCloseStrategy extends Strategy
{
	/**
	 * Ustawia argument dla wywołania
	 * 
	 * @param controller
	 *            Na tym wywoła stopRunning(), jeśli tutaj wywołamy
	 *            doWithAppAction()
	 */
	public AppCloseStrategy(Controller controller)
	{
		super(controller);
	}

	@Override
	public void doWithAppAction(AppAction appAction)
	{
		Debug.log("Strategia wyłączania aplikacji");
		controller.stopRunning();
	}

}
