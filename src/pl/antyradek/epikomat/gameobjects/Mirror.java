package pl.antyradek.epikomat.gameobjects;

import java.io.FileNotFoundException;

import pl.antyradek.epikomat.model.Room;

/**
 * Lustro w pokoju z zegarem, można się w nim przejrzeć. Takie samo, jak obraz.
 * 
 * @author Radosław Świątkeiwcz
 *
 */
public class Mirror extends GameObject implements Examinable
{

	/**
	 * Wklęsle lustro
	 * 
	 * @param room
	 *            Pokój dla jakiego tworzymy
	 * @throws FileNotFoundException
	 *             Gdy nie znaleziono zasobów
	 */
	public Mirror(Room room) throws FileNotFoundException
	{
		super(room, "Mirror");
	}

	@Override
	public Response examine()
	{
		return new Response(getResource("Description"));
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
