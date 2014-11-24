package pl.antyradek.epikomat.resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.PropertyResourceBundle;

import pl.antyradek.epikomat.debug.Debug;

/**
 * Zasoby językowe, wszystkich tekstów i innych. Będą pobierane z pliku. 
 * Na podobnej zasadzie działa Android.
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
	 * Bundle obsługujący wszystko
	 */
	private static PropertyResourceBundle bundle;
	
	/**
	 * Nazwa pliku zawierającego wszystkie dane
	 */
	private static final String resourceBundleFilename = "resources_pl"; //FIXME: Zmienić na wiele języków
	
	
	/**
	 * Czytanie z pliku zasobów
	 */
	static
	{
		try
		{
			//czytanie pliku
			bundle = new PropertyResourceBundle(new FileInputStream(resourceBundleFilename));
			successful = true;
		} catch (IOException e)
		{
			successful = false;
			bundle = null;
			Debug.logErr("Nie znaleziono zasobów!");
		}
		
	}
	
	public static String getWindowName()
	{
		//TODO: jak to zrobić? Chyba lepiej przez jedną metodę
	}

}
