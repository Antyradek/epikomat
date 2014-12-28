package pl.antyradek.epikomat.gameobjects;

import java.util.ArrayList;
import java.util.List;

/**
 * Przedmiot generuje zwrot posiadający informacje do Modelu i jak ma się
 * zmienić Widok
 * 
 * @author arq
 *
 */
public class Response
{
	/**
	 * Dane do dopisania do logu
	 */
	private final String logAppend;

	/**
	 * Czy czyści log przed dodaniem nowego tekstu?
	 */
	private boolean clearsLog;

	/**
	 * Tablica nazw przedmuotów do wyświetlenia
	 */
	private List<ResponseGameObject> gameObjects;

	/**
	 * Nic się nie zmieni w przedmiotach, jedynie zostanie coś dopisane do logu
	 * 
	 * @param logAppend
	 *            Co dopisać do logu?
	 */
	public Response(String logAppend)
	{
		this.logAppend = logAppend;
		clearsLog = false;
		gameObjects = new ArrayList<ResponseGameObject>();
	}

	/**
	 * Dodaj przedmiot do listy wyświetlanych
	 * 
	 * @param name
	 *            Nazwa przedmiotu
	 * @param actions
	 *            Nazwy akcji
	 */
	public void addGameObject(String name, String[] actions)
	{
		gameObjects.add(new ResponseGameObject(name, actions));
	}

	/**
	 * Zwróć ilość przedmiotów do wyświetlenia
	 * 
	 * @return
	 */
	public int getGameObjectsCount()
	{
		return gameObjects.size();
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
	 * @return
	 */
	public String getNameOfGameObject(int index)
	{
		return gameObjects.get(index).getName();
	}

	/**
	 * Ustawia, czy nadpisać dane w logu. Domyślnie nie.
	 * 
	 * @param clearsLog
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
	 * @return
	 */
	public boolean getClearsLog()
	{
		return clearsLog;
	}
}
