package pl.antyradek.epikomat.gameobjects;

import java.io.FileNotFoundException;

import pl.antyradek.epikomat.bus.Response;
import pl.antyradek.epikomat.debug.Debug;
import pl.antyradek.epikomat.model.Room;

/** Pralka jest dobrym przykładem maszyny stanów. Różne stany mogą, lub nie mogą przechodzić między sobą. Takie rozwiązanie jest długie i nieelastyczne. Pozwala jedynie na łatwą rozbudowę. Sam nie wiem, dlaczego pralka.
 * 
 * @author Radosław Świątkiewicz */
public class WashingMachine extends GameObject
{
	/** Stan pralki */
	private State state;
	/** Portal zostanie uruchomiony, gdy otworzymy pralkę */
	private final GameObject portal;

	/** Pralka jest zamknięta i wyłączona. W środku pralki jest portal.
	 * 
	 * @param room Dla jakiego pokoju pralka?
	 * @param portal Portal w pralce
	 * @throws FileNotFoundException Zasoby zrzarł wybielacz */
	public WashingMachine(final Room room, final GameObject portal) throws FileNotFoundException
	{
		super(room, "WashingMachine");
		this.state = new Off();
		this.portal = portal;
		this.portal.setVisible(false);
		addAction(new OpenAction());
		addAction(new CloseAction());
		addAction(new TurnOnAction());
		addAction(new TurnOffAction());
	}

	@Override
	protected Response open()
	{
		return state.open();
	}

	@Override
	protected Response close()
	{
		return state.close();
	}

	@Override
	protected Response turnOn()
	{
		return state.turnOn();
	}

	@Override
	protected Response turnOff()
	{
		return state.turnOff();
	}

	/** Zamień stan
	 * 
	 * @param newState Na ten */
	private void changeState(final State newState)
	{
		Debug.log("Zmieniono stan Pralki na " + newState.getClass().toString());
		this.state = newState;
	}

	/** Baza dla stanu pralki
	 * 
	 * @author Radosław Świątkiewicz */
	private abstract class State
	{
		/** Otwórz pralkę
		 * @return Dopisanie do logu i czy się udało */
		public abstract Response open();

		/** Zamknij pralkę
		 * @return Dopisanie do logu i czy się udało */
		public abstract Response close();

		/** Wyłącz pralkę
		 * @return Dopisanie do logu i czy się udało */
		public abstract Response turnOff();

		/** Włącz pralkę
		 * @return Dopisanie do logu i czy się udało */
		public abstract Response turnOn();
	}

	/** Włączona pralka może być jedynie wyłączona, Nie da się otworzyć.
	 * 
	 * @author Radosław Świątkiewicz */
	private class On extends State
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
		public Response turnOff()
		{
			changeState(new Off());
			return new Response(getResource("ONOFF"), true);
		}

		@Override
		public Response turnOn()
		{
			return new Response(getResource("ONON"), false);
		}

	}

	/** Wyłączona może być włączona, lub otwarta
	 * 
	 * @author Radosław Świątkiewicz */
	private class Off extends State
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
		public Response turnOff()
		{
			return new Response(getResource("OFFOFF"), false);
		}

		@Override
		public Response turnOn()
		{
			changeState(new On());
			return new Response(getResource("OFFON"), false);
		}
	}

	/** Otwarta pralka nie może być włączona
	 * 
	 * @author Radosław Świątkiewicz */
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
			changeState(new Off());
			portal.setVisible(false);
			return new Response(getResource("OpenedClose"), true);
		}

		@Override
		public Response turnOff()
		{
			return new Response(getResource("OpenedOFF"), false);
		}

		@Override
		public Response turnOn()
		{
			return new Response(getResource("OpenedON"), false);
		}
	}
}
