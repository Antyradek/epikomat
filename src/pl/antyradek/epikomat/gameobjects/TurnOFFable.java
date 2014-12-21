package pl.antyradek.epikomat.gameobjects;

/**
 * Może być wyłączony
 * 
 * @author arq
 *
 */
public interface TurnOFFable
{
	/**
	 * Spróbuj wyłączyć
	 * 
	 * @return
	 */
	public Response turnOFF();
}
