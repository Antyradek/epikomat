package pl.antyradek.epikomat.model;

import pl.antyradek.epikomat.gameobjects.Response;
import pl.antyradek.epikomat.gameobjects.WashingMachine;

/**
 * Niezwykła przygoda z udziałem różnego typu pralek
 * 
 * @author arq
 *
 */
public class WashingMachineAdventure extends Game
{

	/**
	 * Pokój z którego startujemy (ten z pralką)
	 */
	private Room startingRoom;

	public WashingMachineAdventure()
	{
		super("WashingMachineAdventure");
	}

	@Override
	protected void buildLevel()
	{
		WashingMachine startingWM = new WashingMachine(this);
		startingRoom = new Room(getResource("StartingRoomDescription"));
		startingRoom.add(startingWM);
	}

	/**
	 * Zwraca nazwę tej gry
	 * 
	 * @return
	 */
	public static String getGameName()
	{
		return "Pralkowa przygoda";
	}

	@Override
	public Response getInitialState()
	{
		Response initRes = new Response(startingRoom.getRoomDescription());
		initRes.setClearsLog(true);
		return initRes;
	}
}
