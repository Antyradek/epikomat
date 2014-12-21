package pl.antyradek.epikomat.resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import pl.antyradek.epikomat.debug.Debug;

/**
 * Zasób dla tekstu gry. Ze względu na gigantyczną ilość kluczy i tekstu, nie
 * opłaca się stosować wyliczenia.
 * 
 * @author arq
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

	private final String defaultStringWhenMissingKey = "MISSING_KEY ";

	/**
	 * Cały zasób
	 */
	private ResourceBundle bundle;

	/**
	 * Stwórz obiekt zasobów dla danej gry
	 * 
	 * @param bundleFilename
	 */
	public GameResources(String bundleFilename)
	{
		resourceBundleFilename = "res/" + bundleFilename;
		successful = true;
		try
		{
			// UTF-8 Tego bardzo potrzebujemy
			bundle = new PropertyResourceBundle(new FileReader(
					getResourcesFilePath()));
			Debug.log("Wczytano zasoby gry, ale mogą być braki kluczy!");
		} catch (FileNotFoundException e)
		{
			Debug.logErr("Brak pliku zasobów " + getResourcesFilePath());
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
	 * @return
	 */
	public String getResource(String key)
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
	 * @return
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
	private String getResourcesFilePath()
	{
		ClassLoader classLoader = Resources.class.getClassLoader();
		File classpathRoot = new File(classLoader.getResource(
				resourceBundleFilename + ".properties").getPath());
		return classpathRoot.getPath();
	}

}
