package pl.antyradek.epikomat;

import pl.antyradek.epikomat.controller.Controller;
import pl.antyradek.epikomat.debug.Debug;
import pl.antyradek.epikomat.resources.Resources;

/** Początek wszystkiego Wspaniałość nad Wspaniałościami. Wywoływalna klasa budująca.
 * 
 * @author Radosław Świątkiewcz */
public final class Epikomat
{
	/** Uruchamia grę
	 * <ul>
	 * <li>0 Zamknięto poprawnie</li>
	 * <li>-1 Zasoby GUI niepoprawne</li>
	 * <li>-2 Zasoby gry niepoprawne</li>
	 * </ul>
	 * 
	 * @param args Nie przyjmuje argumentów */
	public static void main(String[] args)
	{
		if(!Resources.isGood())
		{
			Debug.logErr("Błąd zasobów!");
			System.exit(-1);
		}
		Controller controller = new Controller();
		controller.run();
	}

}
