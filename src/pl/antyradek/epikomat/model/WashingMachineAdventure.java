package pl.antyradek.epikomat.model;

import pl.antyradek.epikomat.gameobjects.Clock;
import pl.antyradek.epikomat.gameobjects.Painting;
import pl.antyradek.epikomat.gameobjects.Portal;
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

	/**
	 * Pokój z zegarem
	 */
	private Room clockRoom;

	public WashingMachineAdventure()
	{
		super("WashingMachineAdventure");
	}

	@Override
	protected void buildLevel()
	{
		startingRoom = new Room(getResource("StartingRoomDescription"));
		clockRoom = new Room(getResource("ClockRoomDescription"));
		// portal w pralce
		Portal portal = new Portal(this, clockRoom);
		// portal w zegarowym
		Portal clockRoomPortal = new Portal(this, startingRoom);
		WashingMachine startingWM = new WashingMachine(this, portal);
		swapCurrentRoom(startingRoom);
		Painting painting = new Painting(this);
		Clock clock = new Clock(this);
		// dodanie przedmiotów w liście
		startingRoom.add(startingWM);
		startingRoom.add(portal);
		startingRoom.add(painting);
		clockRoom.add(clockRoomPortal);
		clockRoom.add(clock);
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
		// początkowy opis pokoju
		Response initRes = new Response(startingRoom.getRoomDescription());
		initRes.setClearsLog(true);
		// przedmioty w pierwszym pokoju
		initRes = startingRoom.addGameObjectsList(initRes);
		return initRes;
	}
}
