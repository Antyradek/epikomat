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
	 * Przedmiot będzie korzystał z tej gry do zasobów
	 * 
	 * @param game
	 *            Gra zawierająca ten przedmiot
	 */
	public GameObject(Game game)
	{
		this.game = game;
	}
}
