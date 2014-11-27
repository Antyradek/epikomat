package pl.antyradek.epikomat.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.BlockingQueue;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import pl.antyradek.epikomat.controller.AppAction;
import pl.antyradek.epikomat.controller.AppCloseAction;
import pl.antyradek.epikomat.debug.Debug;
import pl.antyradek.epikomat.resources.Resources;

/**
 * Całkowity Widok. Zawiera referencje do okna i innych widokowych badziewii.
 * 
 * @author arq
 *
 */
public class View
{
	/**
	 * Ramka JFrame, czyli GUI
	 */
	private EpikomatFrame frame;

	/**
	 * Kolejka do której wkładamy polecenia z widoku. Kontroler je odbiera.
	 */
	private final BlockingQueue<AppAction> queue;

	public View(final BlockingQueue<AppAction> queue)
	{
		this.queue = queue;
		// ustaw Odpowiedni LF
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e)
		{
			// Domyślnie spada na Metal
			Debug.logErr("Nie może ustawić odpowiedzniego LF dla systemu");
		}
		// zbuduj ramkę w bezpiecznym wątku
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				buildFrame();
			}
		});
		// dodaj akcję zamykania okna
		/*
		 * frame.addWindowListener(new WindowAdapter() { public void
		 * windowClosing(WindowEvent e) { closeWindow(); } });
		 */

	}

	/**
	 * Stwórz nową ramkę, bezpiecznie w nowym wątku
	 */
	private void buildFrame()
	{
		frame = new EpikomatFrame(this);
		frame.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				closeWindow();

			}
		});
		frame.setIconImage(Resources.getAppIcon());
	}

	/**
	 * Zamknij okno. Ta matoda działa, gdy naciśniemy 'X'.
	 */
	private void closeWindow()
	{
		Debug.log("Zamykamy okno");
		try
		{
			queue.put(new AppCloseAction());
		} catch (InterruptedException e)
		{
			Debug.logErr("Błąd wkładania polecenia zamknięcia do kolejki! Będziesz zdaje się musiał ukatrupić proces...");
			e.printStackTrace();
		}
	}

	/**
	 * Wyłącz wszystkie okna
	 */
	public void dispose()
	{
		frame.dispose();
	}
}
