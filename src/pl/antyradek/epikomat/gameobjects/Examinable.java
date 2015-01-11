package pl.antyradek.epikomat.gameobjects;

/**
 * Temu przedmiotowi można się przyjrzeć z bliska
 * 
 * @author Radosław Świątkiewicz
 *
 */
public interface Examinable
{
	/**
	 * Przyjrzyj się z bliska
	 * 
	 * @return Dopisanie do logu z dokładnym opisem, zawsze powodzenie
	 */
	Response examine();
}
