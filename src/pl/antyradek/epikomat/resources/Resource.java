package pl.antyradek.epikomat.resources;

/**
 * ID danych za których pomocą odwołujemy się do zasobów
 * @author arq
 *
 */
public enum Resource
{
	WINDOW_TITLE			("WindowTitle"),			//Nazwa okna na belce
	HELP_MENU_TITLE			("HelpMenuTitle"),			//Tekst na menu pomocy
	HELP_MENU_ITEM_TEXT		("HelpMenuItemText"),		//Tekst na guzik Pomoc w menu pomocy
	HELP_TEXT				("HelpText"),				//TekstPomocy
	AUTHORS_MENU_ITEM_TEXT	("AuthorsMenuItemText"),	//Tekst na guzik autora
	AUTHORS_TEXT			("AuthorsText");			//tekst dialogu z autorem

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
