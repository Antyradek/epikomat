package pl.antyradek.epikomat.gameobjects;

import java.io.FileNotFoundException;

import pl.antyradek.epikomat.bus.Response;
import pl.antyradek.epikomat.model.Room;

/** Malowidło, któremu można się przyjrzeć. Uwaga na ramę.
 * 
 * @author Radosław Świątkiewicz */
public class Painting extends GameObject
{
	/** Malowidło do obejrzenia
	 * @param room Na rzecz tego pokoju
	 * @throws FileNotFoundException Gdy brak zasobów */
	public Painting(final Room room) throws FileNotFoundException
	{
		super(room, "Painting");
		addAction(new ExamineAction());
	}

	@Override
	public Response examine()
	{
		return new Response(getResource("Description"));
	}
}
