package pl.antyradek.epikomat.gameobjects;

import java.io.FileNotFoundException;

import pl.antyradek.epikomat.bus.Response;
import pl.antyradek.epikomat.model.Room;

/** Portal w pralce do którego można wejść. Portal jest niaktywny, jeśli przalka jest zamknieta. Można z niego wyjść zawsze. Portal prowadzi de facto do pokoju, a nie do innego portala. To pozwala na skomplikowane akcje, ale trzeba pamiętać, żeby stworzyć dwa portale, jeśli ma być możliwe obustronne przejście.
 * 
 * @author Radosław Świątkiewicz */
public class Portal extends GameObject
{
	/** Inny pokój do którego portal prowadzi */
	private final Room otherRoom;

	/** Portal z przejściem do innego pokoju
	 * 
	 * @param room Pokój z portalem
	 * @param otherRoom Pokój do którego prowadzi portal
	 * @throws FileNotFoundException Gdy zasoby zniknęły w portalu */
	public Portal(final Room room, final Room otherRoom) throws FileNotFoundException
	{
		super(room, "Portal");
		this.otherRoom = otherRoom;
		addAction(new ExamineAction());
		addAction(new WalkAction());
	}

	@Override
	protected Response examine()
	{
		return new Response(getResource("Description"));
	}

	@Override
	protected Response walk()
	{
		getRoom().getGame().setCurrentRoom(otherRoom);
		return new Response(getResource("Walk"));
	}

}
