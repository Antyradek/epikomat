package pl.antyradek.epikomat.debug;
/**
 * Klasa służąca głowne do wypisywania informacji do logu (do terminala)
 * @author Radosław Świątkiewicz
 *
 */
public class Debug 
{
	/**
	 * Wypisuje tekst do terminala
	 * @param message
	 */
	public static void log(String message)
	{
		System.out.println(message);
	}
	
	/**
	 * Wypisuje tekst do terminala, ale na czerwono!
	 * @param message
	 */
	public static void logErr(String message)
	{
		//ANSI escape codes
		System.out.println("\033[31m" + message + "\033[0m");
	}
}
