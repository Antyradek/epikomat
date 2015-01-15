package pl.antyradek.epikomat.gameobjects;

import java.io.FileNotFoundException;

import pl.antyradek.epikomat.debug.Debug;
import pl.antyradek.epikomat.model.Room;

/**
 * Pralka jest dobrym przykładem maszyny stanów. Różne stany mogą, lub nie mogą
 * przechodzić między sobą. Takie rozwiązanie jest długie i nieelastyczne.
 * Pozwala jedynie na łatwą rozbudowę. Sam nie wiem, dlaczego pralka.
 * 
 * @author Radosław Świątkiewicz
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
	 * Portal zostanie uruchomiony, gdy otworzymy pralkę
	 */
	private GameObject portal;

	/**
	 * Pralka jest zamknięta i wyłączona. W środku pralki jest portal.
	 * 
	 * @param room
	 *            Dla jakiego pokoju pralka?
	 * @param portal
	 *            Portal w pralce
	 * @throws FileNotFoundException
	 *             Zasoby zrzarł wybielacz
	 */
	public WashingMachine(final Room room, final GameObject portal)
			throws FileNotFoundException
	{
		super(room, "WashingMachine");
		state = new OFF();
		this.portal = portal;
		portal.setVisible(false);
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
	private void changeState(final State newState)
	{
		Debug.log("Zmieniono stan Pralki na " + newState.getClass().toString());
		this.state = newState;
	}

	/**
	 * Baza dla stanu pralki
	 * 
	 * @author Radosław Świątkiewicz
	 *
	 */
	private abstract class State implements OpenCloseable, TurnONOFFable
	{

	}

	/**
	 * Włączona pralka może być jedynie wyłączona, Nie da się otworzyć.
	 * 
	 * @author Radosław Świątkiewicz
	 *
	 */
	private class ON extends State
	{

		@Override
		public Response open()
		{
			return new Response(getResource("ONOpen"), false);
		}

		@Override
		public Response close()
		{
			return new Response(getResource("ONClose"), false);
		}

		@Override
		public Response turnOFF()
		{
			changeState(new OFF());
			return new Response(getResource("ONOFF"), true);
		}

		@Override
		public Response turnON()
		{
			return new Response(getResource("ONON"), false);
		}

	}

	/**
	 * Wyłączona może być włączona, lub otwarta
	 * 
	 * @author Radosław Świątkiewicz
	 *
	 */
	private class OFF extends State
	{
		@Override
		public Response open()
		{
			changeState(new Opened());
			portal.setVisible(true);
			return new Response(getResource("OFFOpen"), true);
		}

		@Override
		public Response close()
		{
			return new Response(getResource("OFFClose"), false);
		}

		@Override
		public Response turnOFF()
		{
			return new Response(getResource("OFFOFF"), false);
		}

		@Override
		public Response turnON()
		{
			changeState(new ON());
			return new Response(getResource("OFFON"), false);
		}
	}

	/**
	 * Otwarta pralka nie może być włączona
	 * 
	 * @author Radosław Świątkiewicz
	 *
	 */
	private class Opened extends State
	{

		@Override
		public Response open()
		{
			return new Response(getResource("OpenedOpen"), false);
		}

		@Override
		public Response close()
		{
			changeState(new OFF());
			portal.setVisible(false);
			return new Response(getResource("OpenedClose"), true);
		}

		@Override
		public Response turnOFF()
		{
			return new Response(getResource("OpenedOFF"), false);
		}

		@Override
		public Response turnON()
		{
			return new Response(getResource("OpenedON"), false);
		}

	}

	@Override
	public String[] getActionNames()
	{
		String[] ret = new String[4];
		ret[0] = getResource("ActionNameOpen");
		ret[1] = getResource("ActionNameClose");
		ret[2] = getResource("ActionNameON");
		ret[3] = getResource("ActionNameOFF");
		return ret;
	}

	@Override
	public Response executeAction(final int actionIndex)
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
