package pl.antyradek.epikomat.gameobjects;

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
	 * Nic się nie zmieni w przedmiotach, jedynie zostanie coś dopisane do logu
	 * 
	 * @param logAppend
	 *            Co dopisać do logu?
	 */
	public Response(String logAppend)
	{
		this.logAppend = logAppend;
		clearsLog = false;
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
