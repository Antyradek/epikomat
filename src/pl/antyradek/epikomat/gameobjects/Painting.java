package pl.antyradek.epikomat.gameobjects;

import java.io.FileNotFoundException;

import pl.antyradek.epikomat.model.Room;

/**
 * Malowidło, któremu można się przyjrzeć. Uwaga na ramę.
 * 
 * @author Radosław Świątkiewicz
 *
 */
public class Painting extends GameObject implements Examinable
{

	/**
	 * Malowidło obejrzenia
	 * 
	 * @param room
	 *            Na rzecz tego pokoju
	 * @throws FileNotFoundException
	 *             Gdy brak zasobów
	 */
	public Painting(Room room) throws FileNotFoundException
	{
		super(room, "Painting");
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
		if (actionIndex == 0)
		{
			return examine();
		} else
			return null;
	}

	@Override
	public Response examine()
	{
		return new Response(getResource("Description"));
	}

}
