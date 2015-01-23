package pl.antyradek.epikomat.events;

import pl.antyradek.epikomat.bus.GameObjectActionId;

/**
 * Rzecz do kolejki niosąca informację o id przedmiotu, klikniętej akcji i dodatkowych danych akcji (w przyszłości). Najczęściej wsadzana dana do kolejki.
 * 
 * @author Radosław Świątkiewicz
 */
public class ViewResponseEvent extends ViewEvent
{
	/** Indeks akcji w przedmiocie */
	private final GameObjectActionId actionId;

	/**
	 * Naciśnięto akcję
	 * 
	 * @param gameObjectId Na tym przedmiocie
	 * @param actionId Tą akcję z kolei
	 */
	public ViewResponseEvent(final GameObjectActionId actionId)
	{
		this.actionId = actionId;
	}

	/**
	 * Indeks wykonanej akcji
	 * 
	 * @return Id akcji
	 */
	public GameObjectActionId getGameObjectActionId()
	{
		return actionId;
	}
}
