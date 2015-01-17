package pl.antyradek.epikomat.gameobjects;

import java.io.FileNotFoundException;

import pl.antyradek.epikomat.bus.Response;
import pl.antyradek.epikomat.model.Room;

/** Guzik do otwierania pralki zdalnie. Jeśli pralka nie może być otwarta, guzik ulega uszkodzeniu. Brak maszyny stanów, a prosta wartość bool. Nazwa przedmiotu zmienia się ze stanem.
 * 
 * @author Radosław Świątkiewicz */
public class Button extends GameObject
{
	/** Przedmiot do otwarcia */
	private final GameObject gameObjectToOpen;
	/** Czy guzik pracuje, czy jeszcze się nie zepsuł */
	private boolean isWorking;

	/** Przycisk do naciskania
	 * 
	 * @param room Katalog zasobów
	 * @param gameObjectToOpen Pralka, która spróbuje być otwarta
	 * @throws FileNotFoundException Gdy nie wczytano zasobów */
	public Button(final Room room, final GameObject gameObjectToOpen) throws FileNotFoundException
	{
		super(room, "Button");
		this.gameObjectToOpen = gameObjectToOpen;
		this.isWorking = true;
		addAction(new PushAction());
		addAction(new ExamineAction());
	}

	@Override
	public Response push()
	{
		if(isWorking)
		{
			Response ret = gameObjectToOpen.open();
			if(ret.getActionSuccessfull())
			{
				// przycisk działa i właśnie otworzył pralkę
				return new Response(getResource("WorkingPush"), true);
			}else
			{
				// przycisk działał do puki nie próbowałeś otworzyć nim pralki,
				// teraz się zepsuł
				isWorking = false;
				return new Response(getResource("BreakingPush"), false);
			}
		}else
		{
			// nawet, jeśli da się teraz otworzyć pralkę, to i tak guzik nie
			// działa i kopie cię prądem
			return new Response(getResource("BrokenPush"), false);
		}

	}

	/** Dla tego przedmiotu nazwa zmienia się ze stanem */
	@Override
	public String getName()
	{
		if(isWorking)
		{
			return getResource("WorkingName");
		}else
		{
			return getResource("BrokenName");
		}
	}

	@Override
	public Response examine()
	{
		if(isWorking)
		{
			return new Response(getResource("DescriptionWorking"));
		}else
		{
			return new Response(getResource("DescriptionBroken"));
		}
	}

}
