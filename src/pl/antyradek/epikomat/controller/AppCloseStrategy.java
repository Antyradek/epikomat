package pl.antyradek.epikomat.controller;

import pl.antyradek.epikomat.debug.Debug;

/**
 * Zapisanie potrzebnego stanu aplikacji i wyjście
 * 
 * @author arq
 *
 */
class AppCloseStrategy extends Strategy
{
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
