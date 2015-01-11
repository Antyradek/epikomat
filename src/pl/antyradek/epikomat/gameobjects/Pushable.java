package pl.antyradek.epikomat.gameobjects;

/**
 * Może być wciśnięty. Przedmioty implementujące ten interfejs to najszęściej
 * guziki i wszystkie przedmioty z pojedyńczą akcją.
 * 
 * @author Radosław Świątkiewicz
 *
 */
public interface Pushable
{
	/**
	 * Wciśnij ten przedmiot/wykonaj inaczej nazwaną akcję
	 * 
	 * @return Dopisanie do logu po wykonaniu i sukces
	 */
	Response push();
}
