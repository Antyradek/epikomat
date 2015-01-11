package pl.antyradek.epikomat.gameobjects;

import java.io.FileNotFoundException;

import pl.antyradek.epikomat.model.Room;

/**
 * Zegar z kukułką. Miał domyślnie wywoływać na czas, ale zabrakło czasu.
 * Zamiast tego są javadoc nad każdą metodą... Początkowo nie działa.
 * 
 * @author Radosław Świątkiewicz
 *
 */
public class Clock extends GameObject implements Examinable
{
	/**
	 * Czy zegar działa?
	 */
	private boolean isWorking;

	/**
	 * Zegar nienakręcony
	 * 
	 * @param room
	 *            Katalog zasobów
	 * @throws FileNotFoundException
	 *             Gdy nie wczytano zasobów
	 */
	public Clock(Room room) throws FileNotFoundException
	{
		super(room, "Clock");
		isWorking = false;
	}

	@Override
	public Response examine()
	{
		if (isWorking)
		{
			return new Response(getResource("ONDescription"));
		} else
		{
			return new Response(getResource("OFFDescription"));
		}
	}

	@Override
	public String[] getActionNames()
	{
		String[] ret = new String[1];
		ret[0] = getResource("ActionNameExamine");
		return ret;
	}

	@Override
	public Response executeAction(int actionIndex)
	{
		switch (actionIndex)
		{
		case 0:
			return examine();
		}
		return null;
	}

}
