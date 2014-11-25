package pl.antyradek.epikomat.resources;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.MissingResourceException;
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
	private static final String resourceBundleFilename = "res/resources_pl"; //FIXME: Zmienić na wiele języków
	
	/**
	 * Gdy inicjalizajca zasobów się nie powiodła, zwracamy to
	 */
	private static final String defaultStringWhenUnsuccessful = "RESOURCES_ERROR!";
	
	/**
	 * Czytanie z pliku zasobów
	 */
	static
	{
		Resource testedResource = null; //zasób w trakcie testowania
		try
		{
			//czytanie pliku 
			//UWAGA!!! FileInputStream nie wie, jak sukcesywnie UTF-8. Ten wie, tego lubimy.
			bundle = new PropertyResourceBundle(new FileReader(getResourcesFilePath()));
			for(Resource resource : Resource.values())
			{
				testedResource = resource;
				bundle.getString(resource.getKey());
			}
			successful = true;
		} catch (IOException e)
		{
			//błąd czytania pliku
			successful = false;
			bundle = null;
			Debug.logErr("Nie znaleziono zasobów!");
		} catch (MissingResourceException e)
		{
			//brak niektórych wartości
			successful = false;
			Debug.logErr("Zasoby są błędne! Brak klucza: " + testedResource.getKey());
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
	    Debug.log(classpathRoot.getPath());
	    return classpathRoot.getPath();
	}
}
