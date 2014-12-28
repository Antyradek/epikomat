package pl.antyradek.epikomat.view;

import javax.swing.JButton;

/**
 * Przycisk akcji posiadający informację o indeksie akcji i indeksie przedmiotu
 * 
 * @author arq
 *
 */
public class ActionButton extends JButton
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 12345L;

	/**
	 * Indeks przedmiotu
	 */
	private final int gameObjectIndex;

	/**
	 * Indeks akcji
	 */
	private final int actionIndex;

	/**
	 * Przycisk akcji
	 * 
	 * @param name
	 *            O tej nazwie
	 * @param gameObjectIndex
	 * @param actionIndex
	 */
	public ActionButton(String name, int gameObjectIndex, int actionIndex)
	{
		super(name);
		this.gameObjectIndex = gameObjectIndex;
		this.actionIndex = actionIndex;
	}

	public int getGameObjectIndex()
	{
		return gameObjectIndex;
	}

	public int getActionIndex()
	{
		return actionIndex;
	}

}
