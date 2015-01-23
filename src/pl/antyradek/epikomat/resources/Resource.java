package pl.antyradek.epikomat.resources;

/**
 * Enumerator wykorzystywany do pobierania zasobów GUI
 * 
 * @author Radosław Świątkiewicz
 */
public enum Resource
{
	WINDOW_TITLE("WindowTitle"), // Nazwa okna na belce
	HELP_MENU_TITLE("HelpMenuTitle"), // Tekst na menu pomocy
	HELP_MENU_ITEM_TEXT("HelpMenuItemText"), // Tekst na guzik Pomoc w menu pomocy
	HELP_TEXT("HelpText"), // TekstPomocy
	AUTHORS_MENU_ITEM_TEXT("AuthorsMenuItemText"), // Tekst na guzik autora
	AUTHORS_TEXT("AuthorsText"); // tekst dialogu z autorem

	/** Wewnętrzny klucz, jaki ma być podany dla {@link Resources} */
	private String key;

	/**
	 * Ustawia klucz zdefiniowany przy każdej wartości enumeratora
	 * 
	 * @param key Klucz zdefiniowany w pliku zasobów
	 */
	private Resource(final String key)
	{
		this.key = key;
	}

	/**
	 * Zwróć klucz
	 * 
	 * @return Tekstowy klucz odpowiadający określonej wartości
	 */
	String getKey()
	{
		return key;
	}

}
