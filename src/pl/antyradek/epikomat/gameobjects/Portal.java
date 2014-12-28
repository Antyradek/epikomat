package pl.antyradek.epikomat.gameobjects;

import pl.antyradek.epikomat.model.Game;

/**
 * Portal w pralce do którego będzie można przejść (w przyszłości)
 * 
 * @author arq
 *
 */
public class Portal extends GameObject implements Examinable
{

	public Portal(Game game)
	{
		super(game);
	}

	@Override
	public String getGameObjectName()
	{
		return game.getResource("PortalName");
	}

	@Override
	public String[] getActionNames()
	{
		String[] ret = new String[1];
		ret[0] = game.getResource("PortalActionNameExamine");
		return ret;
	}

	@Override
	public Response executeAction(int actionIndex)
	{
		if (actionIndex == 0)
			return examine();
		else
			return null;
	}

	@Override
	public Response examine()
	{
		return new Response(game.getResource("PortalDescription"));
	}

}
