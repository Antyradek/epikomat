package pl.antyradek.epikomat.resources;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;

import pl.antyradek.epikomat.debug.Debug;

/** Zasoby językowe, wszystkich GUI. Będą pobierane z pliku. Na podobnej zasadzie działa Android. Wszystkie klucze są zdefiniowane w kodzie w {@link Resource}. Przed użyciem są sprawdzane.
 * 
 * @author Radosław Świątkiwicz */
public final class Resources
{
	/** Czy zasoby są poprawne? */
	private static boolean successful;
	/** Nazwa pliku zawierającego wszystkie dane, bez rozszerzenia .properties w stosunku do root projektu (folder bin, lub src) */
	private static final String resourceBundleFilename = "res/GUI";
	// FIXME: Na wiele języków
	/** Nazwa pliku ikony */
	private static final String iconFilename = "res/icon.png";
	/** Wczytana ikona */
	private static Image icon;
	/** Gdy inicjalizajca zasobów się nie powiodła, zwracamy to */
	private static final String defaultStringWhenUnsuccessful = "RESOURCES_ERROR!";
	/** Cały zasób jako podstawowa implementacja od Javy */
	private static ResourceBundle bundle;

	/** Czytanie z pliku zasobów */
	static
	{
		successful = true;
		try
		{
			// ikona
			ClassLoader classLoader = Resources.class.getClassLoader();
			URL imgURL;
			imgURL = classLoader.getResource(iconFilename);
			if(imgURL == null)
			{
				Debug.logErr("Brak ikony!");
				throw new MissingResourceException("Błąd szukania ikony", Resources.class.toString(), iconFilename);
			}
			ImageIcon imageIcon = new ImageIcon(imgURL);
			icon = imageIcon.getImage();
			if(icon == null)
			{
				Debug.logErr("Nie znaleziono ikony!");
				successful = false;
			}

			// Chciałem automagicznie, ale ktoś nie potrafi napisać narzędzi
			// obsługujących UTF-8. Taka jedna firma zaczynająca się na na "O"
			bundle = new PropertyResourceBundle(new FileReader(getResourcesFilePath()));
			// sprawdzanie kluczy.
			for(Resource resource : Resource.values())
			{
				if(!bundle.containsKey(resource.getKey()))
				{
					// błąd, brak zdefiniowanego klucza!
					successful = false;
					Debug.logErr("Błąd zasobów! Brak klucza " + resource.getKey());
					throw new MissingResourceException("Błąd szukania klucza", Resources.class.toString(), resource.getKey());
				}
			}
			Debug.logSuccess("Zasoby są poprawne");
		}catch(MissingResourceException e)
		{
			Debug.logErr("Błąd klucza!");
			successful = false;
		}catch(FileNotFoundException e)
		{
			Debug.logErr("Plik zasobów nie znaleziony!");
			successful = false;
		}catch(IOException e)
		{
			Debug.logErr("Błąd Wejścia-Wyjścia przy czytaniu zasobów!");
			successful = false;
		}

	}

	/** Pobierz Resource w formie tekstowej dając enumerator jako argument. Jeśli zasoby są poprawne, to się zawsze uda.
	 * 
	 * @param resource Enumerator zasobu o jaki prosimy
	 * @return Tekst odpowiadający kluczowi odpowiadającemu podanemu enumeratorowi, lub tekst, że zasoby nie są poprawne. */
	public static String getString(final Resource resource)
	{
		if(!successful)
			return defaultStringWhenUnsuccessful;
		return bundle.getString(resource.getKey());
	}

	/** Czy cały system działa? To powinno być sprawdzone zanim rozpoczniemy używać zasobów na poważnie.
	 * 
	 * @return Informacja, czy zasoby są poprawne i można użyć każdego enumeratora */
	public static boolean isGood()
	{
		return successful;
	}

	/** Zwraca ikonkę aplikacji
	 * 
	 * @return Ikona aplikacji, lub null, jeśli nie jest poprawnie */
	public static Image getAppIcon()
	{
		// FIXME: Jeśli nie znalazł. zwraca domyślną
		return icon;
	}

	/** Znajdź plik zasobów relatywnie do uruchomionego programu. To ręczne podejście potrzebne jest, aby zamiast domyślnego czytnika użyć Reader, który obsługuje UTF-8.
	 * 
	 * @return Bezwzględna ścieżka do pliku zasobów GUI.properties */
	private static String getResourcesFilePath()
	{
		ClassLoader classLoader = Resources.class.getClassLoader();
		File classpathRoot = new File(classLoader.getResource(resourceBundleFilename + ".properties").getPath());
		return classpathRoot.getPath();
	}
}
