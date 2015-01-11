package pl.antyradek.epikomat.gameobjects;

import java.io.FileNotFoundException;

import pl.antyradek.epikomat.model.Room;

/**
 * Portal w pralce do którego można wejść. Portal jest niaktywny, jeśli przalka
 * jest zamknieta. Można z niego wyjść zawsze. Portal prowadzi de facto do
 * pokoju, a nie do innego portala. To pozwala na skomplikowane akcje, ale
 * trzeba pamiętać, żeby stworzyć dwa portale, jeśli ma być możliwe obustronne
 * przejście.
 * 
 * @author Radosław Świątkiewicz
 *
 */
public class Portal extends GameObject implements Examinable, Walkable
{
	/**
	 * Inny pokój do którego portal prowadzi
	 */
	Room otherRoom;

	/**
	 * Portal z przejściem do innego pokoju
	 * 
	 * @param room
	 *            Pokój z portalem
	 * @param otherRoom
	 *            Pokój do którego prowadzi portal
	 * @throws FileNotFoundException
	 *             Gdy zasoby zniknęły w portalu
	 */
	public Portal(Room room, Room otherRoom) throws FileNotFoundException
	{
		super(room, "Portal");
		this.otherRoom = otherRoom;
	}

	@Override
	public String[] getActionNames()
	{
		String[] ret = new String[2];
		ret[0] = getResource("ActionNameExamine");
		ret[1] = getResource("ActionNameWalk");
		return ret;
	}

	@Override
	public Response executeAction(int actionIndex)
	{
		switch (actionIndex)
		{
		case 0:
			return examine();
		case 1:
			return walkThrough();
		}
		return null;
	}

	@Override
	public Response examine()
	{
		return new Response(getResource("Description"));
	}

	@Override
	public Response walkThrough()
	{
		getRoom().getGame().setCurrentRoom(otherRoom);
		return new Response(getResource("Walk"));
	}

}
