package pl.antyradek.epikomat.debug;

/**
 * Klasa służąca głowne do wypisywania informacji do logu (do terminala)
 * 
 * @author Radosław Świątkiewicz
 *
 */
public class Debug
{
	/**
	 * Wypisuje tekst do terminala
	 * 
	 * @param message
	 */
	public static void log(Object obj)
	{
		System.out.println(obj);
	}

	/**
	 * Wypisuje tekst do terminala, ale na czerwono!
	 * 
	 * @param obj
	 *            Na nim wywołany zosanie toString();
	 */
	public static void logErr(Object obj)
	{
		// ANSI escape codes
		System.out.println("\033[31m" + obj.toString() + "\033[39m");
	}

	/**
	 * Wypisuje tekst do terminala na zielono. Oznacza udane akcje.
	 * 
	 * @param obj
	 *            Na nim wywołuje toString()
	 */
	public static void logSuccess(Object obj)
	{
		System.out.println("\033[32m" + obj.toString() + "\033[39m");
	}
}
