package pl.antyradek.epikomat.model;

import pl.antyradek.epikomat.gameobjects.Response;

/**
 * Cała wspaniałość aplikacji
 * 
 * @author arq
 *
 */
public class Model
{
	/**
	 * Obecnie grana gra
	 */
	private Game currentGame;

	/**
	 * Uruchom grę o numerze indeksu odpowiedniej nazwy z
	 * getAvaliableGameNames()
	 * 
	 * Takie rozwiązanie pozwala na uniknięcie tworzenia obiektów gier tylko po
	 * to, żeby dostać ich nazwy.
	 * 
	 * @param gameID
	 */
	public void startGame(int gameID)
	{
		Game newGame = null;
		switch (gameID)
		{
		case 0:
			newGame = new WashingMachineAdventure();
			break;
		}
		currentGame = newGame;
	}

	/**
	 * Weź dostępne nazwy gier, aby uruchomić, startGame() z indeksem
	 * odpowiedniej nazwy
	 * 
	 * @return
	 */
	public String[] getAvaliableGameNames()
	{
		String ret[] = new String[1];
		ret[0] = "Pralkowa Przygoda"; // FIXME Na wiele języków i jakoś
										// normalnie
		return ret;
	}

	/**
	 * Początkowy stan gry
	 * 
	 * @return
	 */
	public Response getInitialState()
	{
		return currentGame.getInitialState();
	}
}
