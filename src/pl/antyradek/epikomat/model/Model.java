package pl.antyradek.epikomat.model;

import pl.antyradek.epikomat.controller.ViewResponseAction;
import pl.antyradek.epikomat.exceptions.GameStartException;
import pl.antyradek.epikomat.gameobjects.Response;

/**
 * Cała wspaniałość aplikacji Trzyma informację o grze
 * 
 * @author Radosław Świątkiewicz
 *
 */
public class Model
{
	/**
	 * Obecnie grana gra
	 */
	private Game currentGame;

	/**
	 * Foldery odpowiednich gier. Wywołanie odpowiedniego indeksu rozpoczyna grę
	 */
	// private File[] gameDirs;

	/**
	 * Uruchom grę o numerze indeksu odpowiedniej nazwy z
	 * getAvaliableGameNames()
	 * 
	 * Takie rozwiązanie pozwala na uniknięcie tworzenia obiektów gier tylko po
	 * to, żeby dostać ich nazwy. Wygląda źle, wiem. W przyszłości ma być
	 * zmieniona na
	 * 
	 * @param gameID
	 */
	public void startGame(final int gameID) throws GameStartException
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
		// Wypisywanie folderów nie ma sensu, gdyż i tak wszystko kończy się na
		// stworzeniu odpowiedniej klasy gry. Łączenie tekstu z nazwą klasy jest
		// niebezpieczne, poza tym i tak trzeba to wkompilować w program.

		// ClassLoader classLoader = Resources.class.getClassLoader();
		// URL url = classLoader.getResource("res/GameData/");
		// File directory = new File(url.getPath());
		//
		// List<File> list = new ArrayList<File>();
		// List<String> namesList = new ArrayList<String>();
		// File[] allFiles = directory.listFiles();
		//
		// // wyciągnij tylko foldery (nie powinno tam być nic innego, ale
		// zawsze)
		// for (File file : allFiles)
		// {
		// if (file.isDirectory())
		// {
		// list.add(file);
		// namesList.add(file.getName());
		// }
		// }
		// gameDirs = (File[]) list.toArray();
		// return (String[]) namesList.toArray();

		String ret[] = new String[1];
		ret[0] = "Pralkowa Przygoda";
		return ret;
	}

	/**
	 * Początkowy stan gry
	 * 
	 * @return Dane do wyświetlenia na samym początku gry
	 */
	public Response getInitialState()
	{
		return currentGame.getInitialState();
	}

	/**
	 * Wykonaj akcję na działającej grze
	 * 
	 * @param action
	 *            Akcja z Widoku niosą ca informację o indeksach akcji i
	 *            przedmiotu
	 * @return Odpowiedź na to wywołanie
	 */
	public Response executeAction(final ViewResponseAction action)
	{
		return currentGame.executeAction(action);
	}
}
