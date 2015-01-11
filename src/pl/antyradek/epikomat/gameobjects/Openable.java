package pl.antyradek.epikomat.gameobjects;

/**
 * Ten przedmiot może być otwarty
 * 
 * @author Radołsaw Świątkiewicz
 *
 */
public interface Openable
{
	/**
	 * Spróbuj otworzyć ten przedmiot
	 * 
	 * @return Dopisanie do logu i informacja o sukcesie
	 */
	Response open();
}
