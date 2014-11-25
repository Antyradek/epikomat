package pl.antyradek.epikomat.resources;

/**
 * ID danych za których pomocą odwołujemy się do zasobów
 * @author arq
 *
 */
public enum Resource
{
	WINDOW_TITLE		("WindowTitle");

	/**
	 * Wewnętrzny klucz, jaki ma być podany dla {@link Resources}
	 */
	private String key;
	private Resource(String key)
	{
		this.key = key;
	}
	
	String getKey()
	{
		return key;
	}

}
