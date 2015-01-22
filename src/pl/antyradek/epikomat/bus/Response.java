package pl.antyradek.epikomat.bus;

import java.util.ArrayList;
import java.util.List;

/** Dane budowane w kilku miejscach i wysyłane do widoku. Przesyłanie propaguje przez warswy, każda dopisuje coś od siebie i przesyła dalej. Stworzenie w przedmiocie (przesłanie do innego przedmiotu, jeśli reagują ze sobą). Dpisanie listy przedmiotów w pokoju. Ustawienie w Grze. Wysłanie w Modelu.
 * 
 * @author Radosław Świątkiewicz */
public class Response
{
	/** Dane do dopisania do logu */
	private String logAppend;
	/** Czy czyści log przed dodaniem nowego tekstu? */
	private boolean clearsLog;
	/** Czy ostatnio wykonana akcja się powiodła? */
	private final boolean actionSuccessful;
	/** Tablica nazw przedmuotów do wyświetlenia w widoku */
	private List<GameObjectId> gameObjects;

	/** Zostanie coś dopisane do logu. Brak informacji o przedmiotach w pokoju. Akcja się udała.
	 * 
	 * @param logAppend Co dopisać do logu? */
	public Response(final String logAppend)
	{
		this(logAppend, true);
	}

	/** Zostanie coś dopisane do logu. Brak informacji o przedmiotach w pokoju.
	 * 
	 * @param logAppend Co dopisać do logu?
	 * @param actionSuccessful Czy akcja się udała? */
	public Response(final String logAppend, final boolean actionSuccessful)
	{
		this.logAppend = logAppend;
		this.clearsLog = false;
		this.gameObjects = new ArrayList<GameObjectId>();
		this.actionSuccessful = actionSuccessful;
	}

	/** Dodaj przedmiot do listy wyświetlanych.
	 * @param name Nazwa przedmiotu
	 * @param gameObjectId Id tego przedmiotu */
	public void addGameObject(final GameObjectId gameObjectId)
	{
		gameObjects.add(gameObjectId);
	}

	/** Zwróć ilość przedmiotów do wyświetlenia
	 * 
	 * @return Ilość przedmiotów do wyświetlenia */
	public int getGameObjectsCount()
	{
		return gameObjects.size();
	}

	/** Akcja się udała
	 * 
	 * @return Czy akcja na tym przedmiocie się udała? */
	public boolean getActionSuccessfull()
	{
		return actionSuccessful;
	}

	/** Zwróć listę ID przedmiotów
	 * @return Lista przedmiotów */
	public List<GameObjectId> getGameObjectsIds()
	{
		return gameObjects;
	}

	/** Ustawia, czy nadpisać dane w logu. Domyślnie nie.
	 * 
	 * @param clearsLog Czy nadisać dane? (Czy wyczyścić pole tekstowe przed dopisaniem) */
	public void setClearsLog(final boolean clearsLog)
	{
		this.clearsLog = clearsLog;
	}

	/** Zwraca dane, które mają być dopisane do logu
	 * 
	 * @return Dane do dopisania do logu */
	public String getLogAppend()
	{
		return logAppend;
	}

	/** Czy ma czyścić log? Domyślnie nie.
	 * 
	 * @return Czy czyści log */
	public boolean getClearsLog()
	{
		return clearsLog;
	}

	/** Dodaj kolejny tekst do logu, zostanie dopisany po spacji.
	 * @param log Tekst do dopisania po spacji. */
	public void appendLog(final String log)
	{
		logAppend += " " + log;
	}
}
