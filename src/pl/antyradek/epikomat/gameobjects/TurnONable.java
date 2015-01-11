package pl.antyradek.epikomat.gameobjects;

/**
 * Przedmiot włączalny
 * 
 * @author Radosław Świątkiewicz
 *
 */
public interface TurnONable
{
	/**
	 * Spróbuj włączyć
	 * 
	 * @return Dopsanie do logu i informacja o sukcesie
	 */
	public Response turnON();
}
