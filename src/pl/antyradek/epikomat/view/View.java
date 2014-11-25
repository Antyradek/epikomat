package pl.antyradek.epikomat.view;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import pl.antyradek.epikomat.debug.Debug;

/**
 * Całkowity Widok. Zawiera referencje do okna i innych widokowych badziewii.
 * 
 * @author arq
 *
 */
public class View
{
	EpikomatFrame frame;

	public View()
	{
		//ustaw Odpowiedni LF
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e)
		{
			//Domyślnie spada na Metal
			Debug.logErr("Nie może ustawić odpowiedzniego LF dla systemu");
		}
		//zbuduj ramkę w bezpiecznym wątku
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				buildFrame();
			}
		});
	}
	
	/**
	 * Stwórz nową ramkę, bezpiecznie w nowym wątku
	 */
	private void buildFrame()
	{
		frame = new EpikomatFrame();
	}
}
