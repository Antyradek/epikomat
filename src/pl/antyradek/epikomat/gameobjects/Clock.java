package pl.antyradek.epikomat.gameobjects;

import pl.antyradek.epikomat.model.Game;

/**
 * Zegar z kukułką. Prezentuje zabawy z wywoływaniem na czas.
 * 
 * @author arq
 *
 */
public class Clock extends GameObject implements Examinable
{
	/**
	 * Czy zegar działa?
	 */
	private boolean isWorking;

	public Clock(Game game)
	{
		super(game);
		isWorking = false;
	}

	@Override
	public Response examine()
	{
		if (isWorking)
		{
			return new Response(game.getResource("ClockONDescription"));
		} else
		{
			return new Response(game.getResource("ClockOFFDescription"));
		}
	}

	@Override
	public String getGameObjectName()
	{
		return game.getResource("ClockName");
	}

	@Override
	public String[] getActionNames()
	{
		String[] ret = new String[1];
		ret[0] = game.getResource("ClockActionNameExamine");
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
