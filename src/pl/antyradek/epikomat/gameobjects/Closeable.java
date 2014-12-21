package pl.antyradek.epikomat.gameobjects;

/**
 * Ten przedmiot może być zamknięty
 * 
 * @author arq
 *
 */
public interface Closeable
{
	/**
	 * Spróbuj zamknąć ten przedmiot
	 * 
	 * @return
	 */
	public Response close();
}
