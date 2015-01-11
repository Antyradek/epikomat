package pl.antyradek.epikomat.gameobjects;

/**
 * Przez ten przedmiot można przejść, zmienia on obecny pokój gry.
 * 
 * @author Radosław Świątkiewicz
 *
 */
public interface Walkable
{
	/**
	 * Przejdź przez ten przedmiot, w efekcie zmieni się pokój. To się zawsze
	 * uda.
	 * 
	 * @return Dopisanie do logu i pozytywna informacja o sukcesie
	 */
	Response walkThrough();
}
