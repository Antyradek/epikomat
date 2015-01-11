package pl.antyradek.epikomat.controller;

/**
 * Rzecz do kolejki niosąca informację o indeksie przedmiotu, klikniętej akcji i
 * dodatkowych danych akcji (w przyszłości). Najczęściej wsadzana dana do
 * kolejki.
 * 
 * @author Radosław Świątkiewicz
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
	 *            Tą akcję z kolei
	 */
	public ViewResponseAction(int gameObjectIndex, int actionIndex)
	{
		this.gameObjectIndex = gameObjectIndex;
		this.actionIndex = actionIndex;
	}

	/**
	 * Indeks przedmiotu na którym wykonano akcję
	 * 
	 * @return Indeks z zachowaniem kolejności
	 */
	public int getGameObjectIndex()
	{
		return gameObjectIndex;
	}

	/**
	 * Indeks wykonanej akcji
	 * 
	 * @return Indeks z zachowaniem kolejności
	 */
	public int getActionIndex()
	{
		return actionIndex;
	}
}
