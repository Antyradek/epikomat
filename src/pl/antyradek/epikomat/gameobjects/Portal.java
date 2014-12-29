package pl.antyradek.epikomat.gameobjects;

import pl.antyradek.epikomat.model.Game;
import pl.antyradek.epikomat.model.Room;

/**
 * Portal w pralce do którego będzie można przejść (w przyszłości)
 * 
 * @author arq
 *
 */
public class Portal extends GameObject implements Examinable, Walkable
{
	/**
	 * Inny pokój do którego mamy przejść
	 */
	Room otherRoom;

	/**
	 * Portal z przejściem do innego pokoju
	 * 
	 * @param game
	 * @param otherRoom
	 *            Pokój do którego przechodzimy
	 */
	public Portal(Game game, Room otherRoom)
	{
		super(game);
		this.otherRoom = otherRoom;
	}

	@Override
	public String getGameObjectName()
	{
		return game.getResource("PortalName");
	}

	@Override
	public String[] getActionNames()
	{
		String[] ret = new String[2];
		ret[0] = game.getResource("PortalActionNameExamine");
		ret[1] = game.getResource("PortalActionNameWalk");
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
		return new Response(game.getResource("PortalDescription"));
	}

	@Override
	public Response walkThrough()
	{
		game.setCurrentRoom(otherRoom);
		return new Response(game.getResource("PortalWalk"));
	}

}
