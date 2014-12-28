package pl.antyradek.epikomat.controller;

/**
 * Rzecz do kolejki niosąca informację o indeksie przedmiotu, klikniętej akcji i
 * dodatkowych danych akcji (w przyszłości).
 * 
 * @author arq
 *
 */
public class ViewResponseAction extends AppAction
{
	/**
	 * Indeks przedmiotu, którego akcja została wykonana
	 */
	private final int gameObjectIndex;
	/**
	 * Indeks akcji w przedmiocie
	 */
	private final int actionIndex;

	/**
	 * Naciśnięto akcję
	 * 
	 * @param gameObjectIndex
	 *            Na tym przedmiocie
	 * @param actionIndex
	 *            Tą akcję
	 */
	public ViewResponseAction(int gameObjectIndex, int actionIndex)
	{
		this.gameObjectIndex = gameObjectIndex;
		this.actionIndex = actionIndex;
	}

	/**
	 * Indeks przedmiotu na którym wykonano akcję
	 * 
	 * @return
	 */
	public int getGameObjectIndex()
	{
		return gameObjectIndex;
	}

	/**
	 * Indeks wykonanej akcji
	 * 
	 * @return
	 */
	public int getActionIndex()
	{
		return actionIndex;
	}
}
