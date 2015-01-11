package pl.antyradek.epikomat.gameobjects;

import java.io.FileNotFoundException;

import pl.antyradek.epikomat.model.Room;

/**
 * Guzik do otwierania pralki zdalnie. Jeśli pralka nie może być otwarta, guzik
 * ulega uszkodzeniu. Brak maszyny stanów, a prosta wartość bool. Nazwa
 * przedmiotu zmienia się ze stanem.
 * 
 * @author Radosław Świątkiewicz
 *
 */
public class Button extends GameObject implements Pushable, Examinable
{
	/**
	 * Pralka którą otwieramy
	 */
	private final WashingMachine washingMachine;

	/**
	 * Czy guzik pracuje, czy jeszcze się nie zepsuł
	 */
	private boolean isWorking;

	/**
	 * Przycisk do naciskania
	 * 
	 * @param room
	 *            Katalog zasobów
	 * @param washingMachine
	 *            Pralka, która spróbuje być otwarta
	 * @throws FileNotFoundException
	 *             Gdy nie wczytano zasobów
	 */
	public Button(Room room, WashingMachine washingMachine)
			throws FileNotFoundException
	{
		super(room, "Button");
		this.washingMachine = washingMachine;
		isWorking = true;
	}

	@Override
	public Response push()
	{
		if (isWorking)
		{
			Response ret = washingMachine.open();
			if (ret.getActionSuccessfull())
			{
				// przycisk działa i właśnie otworzył pralkę
				return new Response(getResource("WorkingPush"), true);
			} else
			{
				// przycisk działał do puki nie próbowałeś otworzyć nim pralki,
				// teraz się zepsuł
				isWorking = false;
				return new Response(getResource("BreakingPush"), false);
			}
		} else
		{
			// nawet, jeśli da się teraz otworzyć pralkę, to i tak guzik nie
			// działa i kopie cię prądem
			return new Response(getResource("BrokenPush"), false);
		}

	}

	@Override
	public String[] getActionNames()
	{
		String[] ret = new String[2];
		ret[0] = getResource("ActionNameExamine");
		ret[1] = getResource("ActionNamePush");
		return ret;
	}

	@Override
	public Response executeAction(int actionIndex)
	{
		switch (actionIndex)
		{
		case 0:
			return examine();
		case 1:
			return push();
		}
		return null;
	}

	@Override
	public Response examine()
	{
		if (isWorking)
		{
			return new Response(getResource("DescriptionWorking"));
		} else
		{
			return new Response(getResource("DescriptionBroken"));
		}
	}

	/**
	 * Dla tego przedmiotu nazwa zmienia się ze stanem
	 */
	@Override
	public String getGameObjectName()
	{
		if (isWorking)
		{
			return getResource("WorkingName");
		} else
		{
			return getResource("BrokenName");
		}
	}

}
