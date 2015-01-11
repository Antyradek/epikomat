package pl.antyradek.epikomat.gameobjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Dane budowane w kilku miejscach i wysyłane do widoku. Przesyłanie propaguje
 * przez warswy, każda dopisuje coś od siebie i przesyła dalej. Stworzenie w
 * przedmiocie (przesłanie do innego przedmiotu, jeśli reagują ze sobą).
 * Dpisanie listy przedmiotów w pokoju. Ustawienie w Grze. Wysłanie w Modelu.
 * 
 * @author Radosław Świątkiewicz
 *
 */
public class Response
{
	/**
	 * Dane do dopisania do logu
	 */
	private String logAppend;

	/**
	 * Czy czyści log przed dodaniem nowego tekstu?
	 */
	private boolean clearsLog;

	/**
	 * Czy ostatnio wykonana akcja się powiodła?
	 */
	private final boolean actionSuccessful;

	/**
	 * Tablica nazw przedmuotów do wyświetlenia w widoku
	 */
	private List<ResponseGameObject> gameObjects;

	/**
	 * Zostanie coś dopisane do logu. Brak informacji o przedmiotach w pokoju.
	 * Akcja się udała.
	 * 
	 * @param logAppend
	 *            Co dopisać do logu?
	 */
	public Response(String logAppend)
	{
		this(logAppend, true);
	}

	/**
	 * Zostanie coś dopisane do logu. Brak informacji o przedmiotach w pokoju.
	 * 
	 * @param logAppend
	 *            Co dopisać do logu?
	 * @param actionSuccessful
	 *            Czy akcja się udała?
	 */
	public Response(String logAppend, boolean actionSuccessful)
	{
		this.logAppend = logAppend;
		clearsLog = false;
		gameObjects = new ArrayList<ResponseGameObject>();
		this.actionSuccessful = actionSuccessful;
	}

	/**
	 * Dodaj przedmiot do listy wyświetlanych. Kolejność jest ważna,
	 * jednoznacznie identyfikuje przedmiot.
	 * 
	 * @param name
	 *            Nazwa przedmiotu
	 * @param actions
	 *            Tablica nazw akcji dla tego przedmiotu
	 */
	public void addGameObject(String name, String[] actions)
	{
		gameObjects.add(new ResponseGameObject(name, actions));
	}

	/**
	 * Zwróć ilość przedmiotów do wyświetlenia
	 * 
	 * @return Ilość przedmiotów do wyświetlenia
	 */
	public int getGameObjectsCount()
	{
		return gameObjects.size();
	}

	/**
	 * Akcja się udała
	 * 
	 * @return Czy akcja na tym przedmiocie się udała?
	 */
	public boolean getActionSuccessfull()
	{
		return actionSuccessful;
	}

	/**
	 * Zwróć tablicę akcji dla tego przedmiotu
	 * 
	 * @param index
	 *            Indeks przedmiotu
	 * @return Tablica akcji do wyświetlenia
	 */
	public String[] getActionsOfGameObject(int index)
	{
		return gameObjects.get(index).getActions();
	}

	/**
	 * Zwróć nazwę tego przedmiotu
	 * 
	 * @param index
	 *            Jednoznaczne określenie przedmiotu
	 * @return Nazwa przedmiotu
	 */
	public String getNameOfGameObject(int index)
	{
		return gameObjects.get(index).getName();
	}

	/**
	 * Ustawia, czy nadpisać dane w logu. Domyślnie nie.
	 * 
	 * @param clearsLog
	 *            Czy nadisać dane? (Czy wyczyścić pole tekstowe przed
	 *            dopisaniem)
	 */
	public void setClearsLog(boolean clearsLog)
	{
		this.clearsLog = clearsLog;
	}

	/**
	 * Zwraca dane, które mają być dopisane do logu
	 * 
	 * @return Dane do dopisania do logu
	 */
	public String getLogAppend()
	{
		return logAppend;
	}

	/**
	 * Czy ma czyścić log? Domyślnie nie.
	 * 
	 * @return Czy czyści log
	 */
	public boolean getClearsLog()
	{
		return clearsLog;
	}

	/**
	 * Dodaj kolejny tekst do logu, zostanie dopisany po spacji.
	 * 
	 * @param log
	 *            Tekt do dopisania po spacji.
	 */
	public void appendLog(String log)
	{
		logAppend += " " + log;
	}
}
