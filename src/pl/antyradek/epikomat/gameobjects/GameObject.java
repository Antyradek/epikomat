package pl.antyradek.epikomat.gameobjects;

import pl.antyradek.epikomat.model.Game;

/**
 * Baza dla każdego przedmiotu w grze, implementacja wewnętrzna przedmiotów jest
 * dowolna, co pozwala na zwiększoną funkcjonalność kosztem obszerności kodu.
 * 
 * @author arq
 *
 */
public abstract class GameObject
{
	/**
	 * Gra, w jakiej występuje ten przedmiot
	 */
	protected final Game game;

	/**
	 * Jeśli przedmiot jest niewidoczny, nie wyświetla się w liście przedmiotów
	 */
	private boolean isVisible;

	/**
	 * Przedmiot będzie korzystał z tej gry do zasobów
	 * 
	 * @param game
	 *            Gra zawierająca ten przedmiot
	 */
	public GameObject(Game game)
	{
		this.game = game;
		isVisible = true;
	}

	/**
	 * Zwraca nazwę przedmiotu, ta nazwa używana jest do wyświetlenia w GUI
	 * 
	 * @return Nazwa przedmotu
	 */
	public abstract String getGameObjectName();

	/**
	 * Zwraca nazwy akcji w kolejności. Jeśli ten indeks zostanie wysłany z
	 * powrotem, wykonana zostanie określona akcja.
	 * 
	 * @return
	 */
	public abstract String[] getActionNames();

	/**
	 * Wykonuje akcję o indeksie nazwy pobranej z getActionNames()
	 * 
	 * @param actionIndex
	 *            Numer w tablicy nazw
	 * @return Dopisanie do logu
	 */
	public abstract Response executeAction(int actionIndex);

	/**
	 * Ustaw widoczność przedmiotu
	 * 
	 * @param visible
	 */
	public void setVisible(boolean visible)
	{
		isVisible = visible;
	}

	/**
	 * Czy ten przedmiot jest widoczny?
	 * 
	 * @return
	 */
	public boolean isVisible()
	{
		return isVisible;
	}
}
