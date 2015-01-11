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
	public ViewResponseStrategy(Controller controller)
	{
		super(controller);
	}

	@Override
	void doWithAppAction(AppAction appAction)
	{
		controller.executeAction((ViewResponseAction) appAction);
	}

}
