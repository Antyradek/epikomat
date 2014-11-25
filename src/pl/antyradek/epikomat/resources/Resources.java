package pl.antyradek.epikomat.resources;

import java.io.File;
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
	 * Gdy inicjalizajca zasobów się nie powiodła, zwracamy to
	 */
	private static final String defaultStringWhenUnsuccessful = "RESOURCES_ERROR!";
	
	/**
	 * Czytanie z pliku zasobów
	 */
	static
	{
		try
		{
			//czytanie pliku
			bundle = new PropertyResourceBundle(new FileInputStream(getResourcesFilePath()));
			successful = true;
		} catch (IOException e)
		{
			successful = false;
			bundle = null;
			Debug.logErr("Nie znaleziono zasobów!");
		}
		
	}
	
	/**
	 * Pobierz Resource w formie String
	 * @param resource
	 * @return
	 */
	public static String getString(Resource resource)
	{
		if(!successful) return defaultStringWhenUnsuccessful;
		return bundle.getString(resource.getKey());
	}

	/**
	 * Czy cały system działa? To powinno być sprawdzone zanim rozpoczniemy używać zasobów na poważnie.
	 * @return
	 */
	public static boolean isGood()
	{
		return successful;
	}
	
	/**
	 * Znajdź plik zasobów relatywnie do uruchomionego programu
	 * @return Bezwzględna ścieżka do pliku zasobów
	 */
	private static String getResourcesFilePath() {
		//FIXME: Dlaczego plik musi być w takim miejscu? Chcę, aby był obok tej klasy i kopiował się przy kompilacji
	    ClassLoader classLoader = Resources.class.getClassLoader();
	    File classpathRoot = new File(classLoader.getResource(resourceBundleFilename).getPath());
	    return classpathRoot.getPath();
	}
}
