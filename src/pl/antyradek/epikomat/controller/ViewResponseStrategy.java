package pl.antyradek.epikomat.controller;

/**
 * Wyślij dane o klikniętej akcji do Modelu.
 * 
 * @author Radosław Świątkiewicz
 *
 */
public class ViewResponseStrategy extends Strategy
{

	/**
	 * Model tego Kontrolera użyjemy
	 * 
	 * @param controller
	 *            Tego Kontrolera użyjemy
	 */
	public ViewResponseStrategy(final Controller controller)
	{
		super(controller);
	}

	@Override
	void doWithAppAction(final AppAction appAction)
	{
		controller.executeAction((ViewResponseAction) appAction);
	}

}
