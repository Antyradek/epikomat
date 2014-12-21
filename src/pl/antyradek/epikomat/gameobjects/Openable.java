package pl.antyradek.epikomat.gameobjects;

/**
 * Ten przedmiot może być otwarty
 * 
 * @author arq
 *
 */
public interface Openable
{
	/**
	 * Spróbuj otworzyć ten przedmiot
	 * 
	 * @return
	 */
	Response open();
}
