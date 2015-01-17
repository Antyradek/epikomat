package pl.antyradek.epikomat.model;

import java.io.FileNotFoundException;

import pl.antyradek.epikomat.bus.Response;
import pl.antyradek.epikomat.debug.Debug;
import pl.antyradek.epikomat.exceptions.GameStartException;
import pl.antyradek.epikomat.gameobjects.Button;
import pl.antyradek.epikomat.gameobjects.Clock;
import pl.antyradek.epikomat.gameobjects.Mirror;
import pl.antyradek.epikomat.gameobjects.Painting;
import pl.antyradek.epikomat.gameobjects.Portal;
import pl.antyradek.epikomat.gameobjects.WashingMachine;

/** Niezwykła przygoda z udziałem pralki. Prezentuje różne aspekty silnika. W pokoju jest przycisk, pralka i obraz. Przycik otwiera pralkę i psuje się, gdy tego nie może zrobić. Pralka zawiera w środku portal. Prowadzi do pokoju z lustrem i zegarem.
 * 
 * @author Radosław Świątkiewicz */
class WashingMachineAdventure extends Game
{

	/** Pokój z którego startujemy (ten z pralką) */
	private Room pillowRoom;

	/** Pokój z zegarem */
	private Room clockRoom;

	/** Cała gra
	 * 
	 * @throws GameStartException Gdy zasoby gdzieś wcięło */
	WashingMachineAdventure() throws GameStartException
	{
		super("WashingMachineAdventure");
	}

	@Override
	protected void buildLevel() throws GameStartException
	{
		try
		{
			// pokoje
			pillowRoom = new Room(this, "PillowRoom");
			clockRoom = new Room(this, "ClockRoom");
			// ustaw pokój poduszkowy
			swapCurrentRoom(pillowRoom);
			// stwórz pzedmioty z poduszkowego
			Portal portal = new Portal(pillowRoom, clockRoom);
			WashingMachine startingWM = new WashingMachine(pillowRoom, portal);
			Painting painting = new Painting(pillowRoom);
			Button button = new Button(pillowRoom, startingWM);
			// stwórz przedmioty z zegarowego
			Portal clockRoomPortal = new Portal(clockRoom, pillowRoom);
			Mirror mirror = new Mirror(clockRoom);
			Clock clock = new Clock(clockRoom);
			// mogło by się zdawać, że lepiej jeśli przedmiot sam się doda na
			// listę pokoju, ale wtedy nie mamy pełnej kontroli nad kolejnością
			pillowRoom.add(startingWM);
			pillowRoom.add(portal);
			pillowRoom.add(painting);
			pillowRoom.add(button);
			clockRoom.add(clockRoomPortal);
			clockRoom.add(clock);
			clockRoom.add(mirror);
		}catch(FileNotFoundException e)
		{
			Debug.logErr("Błąd budowy poziomu - brakuje zasobów: " + e.getMessage());
			throw new GameStartException("Błąd zasobu przedmiotu: " + e.getMessage());
		}
	}

	@Override
	public Response getInitialState()
	{
		// początkowy opis pokoju
		Response initRes = new Response(getCurrentRoom().getRoomDescription());
		initRes.setClearsLog(true);
		// przedmioty w pierwszym pokoju
		initRes = getCurrentRoom().addGameObjectsList(initRes);
		return initRes;
	}
}
