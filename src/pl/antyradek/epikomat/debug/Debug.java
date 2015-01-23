package pl.antyradek.epikomat.debug;

/**
 * Klasa służąca głowne do wypisywania informacji do logu (do terminala). Może być użyta do informowania o błędach, kolejnych akcjach i sukcesach.
 * 
 * @author Radosław Świątkiewicz
 */
public class Debug
{
	/**
	 * Wypisuje linię tekstu do terminala
	 * 
	 * @param obj Obiekt do wypisania, wywołuje jego metodę toString()
	 */
	public static void log(final Object obj)
	{
		System.out.println(obj);
	}

	/**
	 * Wypisuje tekst do terminala, ale na czerwono używając ANSI ESCAPE CODES
	 * 
	 * @param obj Na nim wywołany zosanie toString();
	 */
	public static void logErr(final Object obj)
	{
		// ANSI escape codes
		System.out.println("\033[31m" + obj.toString() + "\033[39m");
	}

	/**
	 * Wypisuje tekst do terminala na zielono. Oznacza udane akcje. używa ANSI ESCAPE CODES
	 * 
	 * @param obj Na nim wywołuje toString()
	 */
	public static void logSuccess(final Object obj)
	{
		System.out.println("\033[32m" + obj.toString() + "\033[39m");
	}
}
