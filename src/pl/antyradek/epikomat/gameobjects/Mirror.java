package pl.antyradek.epikomat.gameobjects;

import java.io.FileNotFoundException;

import pl.antyradek.epikomat.bus.Response;
import pl.antyradek.epikomat.model.Room;

/** Lustro w pokoju z zegarem, można się w nim przejrzeć. Takie samo, jak obraz.
 * @author Radosław Świątkeiwcz */
public class Mirror extends GameObject
{
	/** Wklęsle lustro
	 * 
	 * @param room Pokój dla jakiego tworzymy
	 * @throws FileNotFoundException Gdy nie znaleziono zasobów */
	public Mirror(final Room room) throws FileNotFoundException
	{
		super(room, "Mirror");
		addAction(new ExamineAction());
	}

	@Override
	protected Response examine()
	{
		return new Response(getResource("Description"));
	}
}
