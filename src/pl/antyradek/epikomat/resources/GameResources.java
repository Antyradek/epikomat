package pl.antyradek.epikomat.resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import pl.antyradek.epikomat.debug.Debug;

/**
 * Zasób dla tekstu gry. Ze względu na gigantyczną ilość kluczy i tekstu, nie
 * opłaca się stosować wyliczenia, jak dla GUI. Ten zasób jest podawany dla
 * każdego przedmiotu. Każdy może poprosić o dane dla siebie.
 * 
 * @author Radosław Świątkiewicz
 *
 */
public class GameResources
{

	/**
	 * Czy całe urządzenie działa?
	 */
	private boolean successful;

	/**
	 * Nazwa pliku zawierającego wszystkie dane
	 */
	private final String resourceBundleFilename;
	// FIXME: Na wiele języków

	/**
	 * Gdy inicjalizajca zasobów się nie powiodła, zwracamy to
	 */
	private final String defaultStringWhenUnsuccessful = "GAME_RESOURCES_ERROR!";

	/**
	 * Gdy nie może znaleźć klucza, zwraca to
	 */
	private final String defaultStringWhenMissingKey = "MISSING_KEY ";

	/**
	 * Cały zasób
	 */
	private ResourceBundle bundle;

	/**
	 * Stwórz obiekt zasobów dla przedmiotu
	 * 
	 * @param gameName
	 *            Nazwa gry, a także folderu zasobów
	 * @param roomName
	 *            Nazwa pokoju, a także folderu zasobów
	 * @param gameObjectName
	 *            Nazwa Przedmiotu, a także pliku zasobów - bez rozszerzenia
	 *            ".properties"
	 */
	public GameResources(final String gameName, final String roomName,
			final String gameObjectName)
	{
		resourceBundleFilename = "res/GameData/" + gameName + "/" + roomName
				+ "/" + gameObjectName + ".properties";
		successful = true;
		try
		{
			// UTF-8 Tego bardzo potrzebujemy
			bundle = new PropertyResourceBundle(new FileReader(
					getResourcesFilePath()));
		} catch (FileNotFoundException e)
		{
			Debug.logErr("Brak pliku zasobów " + gameName + " " + roomName
					+ " " + gameObjectName);
			successful = false;
		} catch (IOException e)
		{
			Debug.logErr("Błąd czytnika zasobów!");
			successful = false;
		}

	}

	/**
	 * Pobierz Zasób w formie String
	 * 
	 * @param resource
	 *            Klucz do zasobu
	 * @return Odpowiadający tekst, lub informacja, że nie znaleziono
	 */
	public String getResource(final String key)
	{
		if (!successful)
			return defaultStringWhenUnsuccessful;
		String val;
		try
		{
			val = bundle.getString(key);
		} catch (MissingResourceException e)
		{
			val = defaultStringWhenMissingKey + key + " in bundle "
					+ resourceBundleFilename;
		}
		return val;
	}

	/**
	 * Czy cały system działa? To powinno być sprawdzone zanim rozpoczniemy
	 * używać zasobów na poważnie.
	 * 
	 * @return Czy wczytano plik zasobów
	 */
	public boolean isGood()
	{
		return successful;
	}

	/**
	 * Znajdź plik zasobów relatywnie do uruchomionego programu. To ręczne
	 * podejście potrzebne jest, aby zamiast domyślnego czytnika użyć Reader,
	 * który obsługuje UTF-8.
	 * 
	 * @return Bezwzględna ścieżka do pliku zasobów
	 */
	private String getResourcesFilePath() throws FileNotFoundException
	{
		ClassLoader classLoader = Resources.class.getClassLoader();
		URL url = classLoader.getResource(resourceBundleFilename);
		if (url == null)
		{
			throw new FileNotFoundException();
		}
		File classpathRoot = new File(url.getPath());
		return classpathRoot.getPath();
	}

}
