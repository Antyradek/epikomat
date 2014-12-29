package pl.antyradek.epikomat.gameobjects;

/**
 * Przez ten przedmiot można przejść, zmienia on obecny pokój gry.
 * 
 * @author arq
 *
 */
public interface Walkable
{
	/**
	 * Przejdź przez ten przedmiot, w efekcie zmieni się pokój.
	 * 
	 * @return
	 */
	Response walkThrough();
}
