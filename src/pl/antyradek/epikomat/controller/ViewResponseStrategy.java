package pl.antyradek.epikomat.controller;

/**
 * Wyślij dane o klikniętej akcji do Modelu
 * 
 * @author arq
 *
 */
public class ViewResponseStrategy extends Strategy
{

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
