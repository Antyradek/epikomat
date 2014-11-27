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
		System.out.println("\033[31m" + obj.toString() + "\033[0m");
	}
}
