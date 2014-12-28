package pl.antyradek.epikomat.gameobjects;

import pl.antyradek.epikomat.model.Game;

/**
 * Malowidło, któremu można się przyjrzeć
 * 
 * @author arq
 *
 */
public class Painting extends GameObject implements Examinable
{

	public Painting(Game game)
	{
		super(game);
	}

	@Override
	public String getGameObjectName()
	{
		return game.getResource("PaintingName");
	}

	@Override
	public String[] getActionNames()
	{
		String[] ret = new String[1];
		ret[0] = game.getResource("PaintingActionNameExamine");
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
		return new Response(game.getResource("PaintingDescription"));
	}

}
