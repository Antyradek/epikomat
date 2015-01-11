package pl.antyradek.epikomat.view;

import javax.swing.JButton;

/**
 * Przycisk akcji posiadający informację o indeksie akcji i indeksie przedmiotu.
 * 
 * @author Radosław Świątkiewicz
 *
 */
public class ActionButton extends JButton
{

	/**
	 * Zaspokojenie Eclipsa (i zdaje się bezpieczeństwo jednoznacznego
	 * odwołania)
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
	 *            O tym indeksie przedmiotu
	 * @param actionIndex
	 *            O tym indeksie akcji
	 */
	public ActionButton(String name, int gameObjectIndex, int actionIndex)
	{
		super(name);
		this.gameObjectIndex = gameObjectIndex;
		this.actionIndex = actionIndex;
	}

	/**
	 * Indeks przedmiotu, jednoznacznie określa przedmiot w modelu
	 * 
	 * @return Indeks przedmiotu
	 */
	public int getGameObjectIndex()
	{
		return gameObjectIndex;
	}

	/**
	 * Indeks akcji na przedmiocie, jednoznacznie określa akcję
	 * 
	 * @return Indeks akcji
	 */
	public int getActionIndex()
	{
		return actionIndex;
	}

}
