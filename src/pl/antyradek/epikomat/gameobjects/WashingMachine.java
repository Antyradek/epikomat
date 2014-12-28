package pl.antyradek.epikomat.gameobjects;

import pl.antyradek.epikomat.debug.Debug;
import pl.antyradek.epikomat.model.Game;

/**
 * Pralka jest dobrym przykładem maszyny stanów. Różne stany mogą, lub nie mogą
 * przechodzić między sobą. Takie rozwiązanie jest długie i nieelastyczne.
 * Pozwala jedynie na łatwą rozbudowę.
 * 
 * @author arq
 *
 */
public class WashingMachine extends GameObject implements OpenCloseable,
		TurnONOFFable
{
	/**
	 * Stan pralki
	 */
	private State state;

	/**
	 * Pralka jest zamknięta i wyłączona
	 */
	public WashingMachine(Game game)
	{
		super(game);
		state = new OFF();
	}

	@Override
	public Response open()
	{
		return state.open();
	}

	@Override
	public Response close()
	{
		return state.close();
	}

	@Override
	public Response turnON()
	{
		return state.turnON();
	}

	@Override
	public Response turnOFF()
	{
		return state.turnOFF();
	}

	/**
	 * Zamień stan
	 * 
	 * @param newState
	 *            Na ten
	 */
	private void changeState(State newState)
	{
		Debug.log("Zmieniono stan Pralki na " + newState.getClass().toString());
		this.state = newState;
	}

	/**
	 * Baza dla stanu pralki
	 * 
	 * @author arq
	 *
	 */
	private abstract class State implements OpenCloseable, TurnONOFFable
	{

	}

	/**
	 * Włączona pralka może być jedynie wyłączona
	 * 
	 * @author arq
	 *
	 */
	private class ON extends State
	{

		@Override
		public Response open()
		{
			return new Response(game.getResource("WashingMachineONOpen"));
		}

		@Override
		public Response close()
		{
			return new Response(game.getResource("WashingMachineONClose"));
		}

		@Override
		public Response turnOFF()
		{
			changeState(new OFF());
			return new Response(game.getResource("WashingMachineONOFF"));
		}

		@Override
		public Response turnON()
		{
			return new Response(game.getResource("WashingMachineONON"));
		}

	}

	/**
	 * Wyłączona może być włączona, lub otwarta
	 * 
	 * @author arq
	 *
	 */
	private class OFF extends State
	{
		@Override
		public Response open()
		{
			changeState(new Opened());
			return new Response(game.getResource("WashingMachineOFFOpen"));
		}

		@Override
		public Response close()
		{
			return new Response(game.getResource("WashingMachineOFFClose"));
		}

		@Override
		public Response turnOFF()
		{
			return new Response(game.getResource("WashingMachineOFFOFF"));
		}

		@Override
		public Response turnON()
		{
			changeState(new ON());
			return new Response(game.getResource("WashingMachineOFFON"));
		}
	}

	/**
	 * Otwarta pralka nie może być włączona
	 * 
	 * @author arq
	 *
	 */
	private class Opened extends State
	{

		@Override
		public Response open()
		{
			return new Response(game.getResource("WashingMachineOpenedOpen"));
		}

		@Override
		public Response close()
		{
			changeState(new OFF());
			return new Response(game.getResource("WashingMachineOpenedClose"));
		}

		@Override
		public Response turnOFF()
		{
			return new Response(game.getResource("WashingMachineOpenedOFF"));
		}

		@Override
		public Response turnON()
		{
			return new Response(game.getResource("WashingMachineOpenedON"));
		}

	}

	@Override
	public String getGameObjectName()
	{
		return game.getResource("WashingMachineName");
	}

	@Override
	public String[] getActionNames()
	{
		String[] ret = new String[4];
		ret[0] = game.getResource("WashingMachineActionNameOpen");
		ret[1] = game.getResource("WashingMachineActionNameClose");
		ret[2] = game.getResource("WashingMachineActionNameON");
		ret[3] = game.getResource("WashingMachineActionNameOFF");
		return ret;
	}

	@Override
	public Response executeAction(int actionIndex)
	{
		switch (actionIndex)
		{
		case 0:
			return open();
		case 1:
			return close();
		case 2:
			return turnON();
		case 3:
			return turnOFF();
		}
		return null;
	}
}
