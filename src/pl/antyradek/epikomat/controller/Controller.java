package pl.antyradek.epikomat.controller;

import pl.antyradek.epikomat.debug.*;
import pl.antyradek.epikomat.resources.Resources;
import pl.antyradek.epikomat.view.*;

/**
 * Główny kontroler całego programu. Ma najszerszą wiedzę, ale sam mało robi
 * poza przesyłaniem zapytań z prawa na lewo.
 * 
 * @author Radosław Świątkiewicz
 *
 */
public class Controller
{
	View view;
	
	public Controller()
	{
		view = new View();
		if(!Resources.isGood())
		{
			Debug.logErr("Błąd zasobów!");
			System.exit(-1);
		}
		
	}
	
	/**
	 * Główne wystartowanie aplikacji
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		Debug.log("Witamy wszystkich");
		Controller controller = new Controller();
		

	}

}
