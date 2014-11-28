package pl.antyradek.epikomat.resources;

import java.awt.Image;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;

import pl.antyradek.epikomat.debug.Debug;

/**
 * Zasoby językowe, wszystkich tekstów i innych. Będą pobierane z pliku. Na
 * podobnej zasadzie działa Android.
 * 
 * @author arq
 *
 */
public final class Resources
{
	/**
	 * Czy całe urządzenie działa?
	 */
	private static boolean successful;

	/**
	 * Nazwa pliku zawierającego wszystkie dane
	 */
	private static final String resourceBundleFilename = "res/GUI";
	// FIXME: Na wiele języków

	/**
	 * Nazwa pliku ikony
	 */
	private static final String iconFilename = "res/icon.png";

	/**
	 * Ikona wczytana, lub nie, jeśli błąd
	 */
	private static Image icon;

	/**
	 * Gdy inicjalizajca zasobów się nie powiodła, zwracamy to
	 */
	private static final String defaultStringWhenUnsuccessful = "RESOURCES_ERROR!";

	/**
	 * Cały zasób
	 */
	private static ResourceBundle bundle;

	/**
	 * Czytanie z pliku zasobów
	 */
	static
	{
		successful = true;
		try
		{
			// ikona
			ClassLoader classLoader = Resources.class.getClassLoader();
			URL imgURL;
			imgURL = classLoader.getResource(iconFilename);
			if (imgURL == null)
			{
				Debug.logErr("Brak ikony!");
				throw new MissingResourceException("Błąd szukania ikony",
						Resources.class.toString(), iconFilename);
			}
			ImageIcon imageIcon = new ImageIcon(imgURL);
			icon = imageIcon.getImage();
			if (icon == null)
			{
				Debug.logErr("Nie znaleziono ikony!");
				successful = false;
			}

			bundle = ResourceBundle.getBundle(resourceBundleFilename);
			for (Resource resource : Resource.values())
			{
				if (!bundle.containsKey(resource.getKey()))
				{
					// błąd, brak zdefiniowanego klucza!
					successful = false;
					Debug.logErr("Błąd zasobów! Brak klucza "
							+ resource.getKey());
					throw new MissingResourceException("Błąd szukania klucza",
							Resources.class.toString(), resource.getKey());
				}
			}
			Debug.logSuccess("Zasoby są poprawne");
		} catch (MissingResourceException e)
		{
			successful = false;
		}

	}

	/**
	 * Pobierz Resource w formie String
	 * 
	 * @param resource
	 * @return
	 */
	public static String getString(Resource resource)
	{
		if (!successful)
			return defaultStringWhenUnsuccessful;
		return bundle.getString(resource.getKey());
	}

	/**
	 * Czy cały system działa? To powinno być sprawdzone zanim rozpoczniemy
	 * używać zasobów na poważnie.
	 * 
	 * @return
	 */
	public static boolean isGood()
	{
		return successful;
	}

	/**
	 * Zwraca ikonkę aplikacji
	 * 
	 * @return
	 */
	public static Image getAppIcon()
	{
		// FIXME: Jeśli nie znalazł. zwraca domyślną
		return icon;
	}
}
