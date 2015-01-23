package pl.antyradek.epikomat.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.BlockingQueue;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import pl.antyradek.epikomat.bus.GameObjectActionId;
import pl.antyradek.epikomat.bus.GameObjectId;
import pl.antyradek.epikomat.bus.Response;
import pl.antyradek.epikomat.debug.Debug;
import pl.antyradek.epikomat.events.AppCloseEvent;
import pl.antyradek.epikomat.events.ViewEvent;
import pl.antyradek.epikomat.events.ViewResponseEvent;
import pl.antyradek.epikomat.resources.Resources;

/**
 * Całkowity Widok. Zawiera referencje do okna i innych widokowych badziewi. Tym komunikuje się aplikacja z oknem. Ten odpowiada za rozdzielenie wątków.
 * 
 * @author Radosław Świątkiewicz
 */
public class View
{
	/** Ramka JFrame, czyli GUI */
	private EpikomatFrame frame;
	/** Kolejka do której wkładamy polecenia z widoku. Kontroler je odbiera. */
	private final BlockingQueue<ViewEvent> queue;

	/**
	 * Widok
	 * 
	 * @param queue Kolejka do której wsadza dane
	 */
	public View(final BlockingQueue<ViewEvent> queue)
	{
		this.queue = queue;
		// ustaw Odpowiedni LF
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{
			// Domyślnie spada na Metal
			Debug.logErr("Nie może ustawić odpowiedzniego L&F dla systemu");
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
		// mam nadzieję, że działa. u mnie na XFCE nie widać

	}

	/**
	 * Stwórz nową ramkę w tym samym wątku. Ta metoda powinna być wywołana przez {@link SwingUtilities}.invokeLater()
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
	 * Usaw nowy stan widoku
	 * 
	 * @param newState Zwrot Modelu z bezpiecznymi informacjami
	 */
	public void setState(final Response newState)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				addLog(newState.getLogAppend(), newState.getClearsLog());
				frame.resetGameObjectList();
				for(GameObjectId gameObjectId : newState.getGameObjectsIds())
				{
					frame.addGameObject(gameObjectId);
				}
			}
		});
	}

	/**
	 * Zamknij okno. Ta metoda działa, gdy naciśniemy 'X'.
	 */
	private void closeWindow()
	{
		Debug.log("Zamykamy okno");
		try
		{
			queue.put(new AppCloseEvent());
		}
		catch(InterruptedException e)
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
		if(frame != null)
		{
			frame.dispose();
		}
	}

	/**
	 * Dodaj dane do logu w bezpieczny wątkowo sposób
	 * 
	 * @param textToAppend Tekst do dodania
	 * @param clear Czy usunąć cały log przedtem?
	 */
	private void addLog(final String textToAppend, final boolean clear)
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				if(clear)
				{
					frame.setLog(textToAppend);
				}
				else
				{
					frame.appendLog(textToAppend);
				}

			}
		});
	}

	/**
	 * Dodaj do kolejki informację o akcji
	 * 
	 * @param gameObjectActionId Id klikniętej akcji
	 */
	void sendActionToQueue(final GameObjectActionId gameObjectActionId)
	{
		try
		{
			queue.put(new ViewResponseEvent(gameObjectActionId));
		}
		catch(InterruptedException e)
		{
			Debug.logErr("Błąd wkładania akcji do kolejki. Wyobrażasz sobie dalszą grę, bo ja nie.");
			e.printStackTrace();
		}
	}
}
