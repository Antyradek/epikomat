package pl.antyradek.epikomat.gameobjects;

/**
 * Może być wyłączony
 * 
 * @author Radosław Świątkiewicz
 *
 */
public interface TurnOFFable
{
	/**
	 * Spróbuj wyłączyć
	 * 
	 * @return Dopisanie do logu, i informacja o sukcesie
	 */
	public Response turnOFF();
}
