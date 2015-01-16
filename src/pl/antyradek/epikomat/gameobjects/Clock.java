package pl.antyradek.epikomat.gameobjects;

import java.io.FileNotFoundException;

import pl.antyradek.epikomat.bus.Response;
import pl.antyradek.epikomat.model.Room;

/** Zegar z kukułką. Miał domyślnie wywoływać na czas, ale zabrakło czasu. Zamiast tego są javadoc nad każdą metodą... Początkowo nie działa.
 * 
 * @author Radosław Świątkiewicz */
public class Clock extends GameObject
{
	/** Czy zegar działa? */
	private boolean isWorking;

	/** Zegar nienakręcony
	 * @param room Katalog zasobów
	 * @throws FileNotFoundException Gdy nie wczytano zasobów */
	public Clock(final Room room) throws FileNotFoundException
	{
		super(room, "Clock");
		this.isWorking = false;
		addAction(new ExamineAction());
	}

	@Override
	public Response examine()
	{
		if(isWorking)
		{
			return new Response(getResource("ONDescription"));
		}else
		{
			return new Response(getResource("OFFDescription"));
		}
	}
}
