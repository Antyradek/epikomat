package pl.antyradek.epikomat.gameobjects;

/**
 * Ten przedmiot może być zamknięty, nie koniecznie otwarty.
 * 
 * @author Radosław Świątkiewicz
 *
 */
public interface Closeable
{
	/**
	 * Spróbuj zamknąć ten przedmiot
	 * 
	 * @return Dopisanie do logu dla zamknięcia i powodzenie
	 */
	public Response close();
}
