package pl.antyradek.epikomat.gameobjects;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import pl.antyradek.epikomat.bus.GameObjectActionId;
import pl.antyradek.epikomat.bus.GameObjectId;
import pl.antyradek.epikomat.bus.Response;
import pl.antyradek.epikomat.debug.Debug;
import pl.antyradek.epikomat.model.Room;
import pl.antyradek.epikomat.resources.GameResources;

/** Baza dla każdego przedmiotu w grze, implementacja wewnętrzna przedmiotów jest dowolna, co pozwala na zwiększoną funkcjonalność kosztem obszerności kodu niestey.
 * 
 * @author Radosław Świątkiewicz */
abstract class GameObject
{
	/** Pokój w którym znajduje się ten przedmiot */
	private final Room room;
	/** Zasoby dla tego przedmiotu */
	private final GameResources resources;
	/** Jeśli przedmiot jest niewidoczny, nie wyświetla się w liście przedmiotów */
	private boolean isVisible;
	/** Łączy id z odpowiednią metodą */
	private final Map<GameObjectActionId, GameObjectAction> actionsMap;

	/** Przedmiot wczyta odpowiednie zasoby. Domyślnie jest widoczny i pokaże się na liście przedmiotow.
	 * @param room Katalog zasobów pokoju
	 * @param resourcesFolder Plik zasobów dla tego przedmiotu
	 * @throws FileNotFoundException Gdy nie uda się wczytać zasobów */
	public GameObject(final Room room, final String resourcesFolder) throws FileNotFoundException
	{
		this.room = room;
		this.resources = new GameResources(room.getGame().getGameName(), room.getRoomName(), resourcesFolder);
		if(this.resources.isGood())
		{
			Debug.logSuccess("Wczytano zasoby przedmiotu " + resourcesFolder);
		}else
		{
			Debug.logErr("Błąd zasobów przedmiotu " + resourcesFolder);
			throw new FileNotFoundException(resourcesFolder);
		}
		this.isVisible = true;
		this.actionsMap = new HashMap<GameObjectActionId, GameObjectAction>();
	}

	/** Dodaj możliwą do wykonania akcję do przedmiotu. Powinna ona być nadpisana przez dziedziczącego. Dodana tutaj akcja będzie widoczna w widoku.
	 * @param action Podane tu akcje zostały nadpisane przez dziedziczący przedmiot i będą widoczne w Widoku */
	protected void addAction(GameObjectAction action)
	{
		// chciałem to wykonać w konstruktorze za pomocą listy argumetów, ale to zdaje się działa tylko dla typów prostych
		actionsMap.put(new GameObjectActionId(action.getName()), action);
	}

	/** Id tego przedmiotu zawierające jego akcje i nazwę
	 * @return Id przesyłane do Widoku */
	public GameObjectId getId()
	{
		GameObjectId gameObjectId = new GameObjectId(getName());
		// dodajemy akcje do Id
		for(GameObjectActionId actionId : actionsMap.keySet())
		{
			gameObjectId.addAction(actionId);
		}
		return gameObjectId;
	}

	/** Zwraca nazwę przedmiotu zdefiniowaną pod kluczem "Name". Przedmioty dziedziczące mogą nadpisać tę metodę i wprowadzić zmienne nazwy.
	 * @return Nazwa przedmiotu domyślna pod "Name", lub nadpisana */
	protected String getName()
	{
		return getResource("Name");
	}

	/** Wykonuje akcję o podanym Id
	 * @param id Id akcji zawarte w {@link GameObjectId}
	 * @return Dopisanie do logu, gdy wykonana została akcja, lub null, jeśli takiej akcji nie dało się wykonać */
	public Response executeAction(final GameObjectActionId id)
	{
		return actionsMap.get(id).execute();
	}

	/** Weź zasób dla przedmiotu o podanym kluczu.
	 * @param key Klucz to tekstu w odpowiednim pliku
	 * @return Dane po kluczu, lub odpowiednia informacja, jak podano w {@link GameResources} */
	protected String getResource(final String key)
	{
		return resources.getResource(key);
	}

	/** Pokój tego przedmiotu, dla jakiego został stworzony, nie koniecznie w jakim się znajduje.
	 * @return Pokój dla którego został stworzony */
	protected Room getRoom()
	{
		return room;
	}

	/** Ustaw widoczność przedmiotu
	 * 
	 * @param visible Widoczność ma być taka, jesli jest <code>false</code>, przedmiot się nie wyświetli na liście */
	public void setVisible(final boolean visible)
	{
		isVisible = visible;
	}

	/** Czy ten przedmiot jest widoczny?
	 * 
	 * @return Widoczność przedmiotu */
	public boolean isVisible()
	{
		return isVisible;
	}

	/** Spróbuj zamknąć ten przedmiot
	 * @return Dopisanie do logu dla zamknięcia i powodzenie */
	public Response close()
	{
		return null;
	}

	/** Spróbuj otworzyć ten przedmiot
	 * @return Dopisanie do logu i informacja o sukcesie */
	public Response open()
	{
		return null;
	}

	/** Wciśnij ten przedmiot/wykonaj inaczej nazwaną akcję
	 * @return Dopisanie do logu po wykonaniu i sukces */
	public Response push()
	{
		return null;
	}

	/** Przyjrzyj się z bliska
	 * @return Dopisanie do logu z dokładnym opisem, zawsze powodzenie */
	public Response examine()
	{
		return null;
	}

	/** Spróbuj wyłączyć
	 * @return Dopisanie do logu, i informacja o sukcesie */
	public Response turnOff()
	{
		return null;
	}

	/** Spróbuj włączyć
	 * @return Dopsanie do logu i informacja o sukcesie */
	public Response turnOn()
	{
		return null;
	}

	/** Przejdź przez ten przedmiot, w efekcie zmieni się pokój. To się zawsze uda.
	 * @return Dopisanie do logu i pozytywna informacja o sukcesie */
	public Response walk()
	{
		return null;
	}

	/** Baza dla klas definiujących możliwe akcje na przedmiocie. Wywołanie Run() wywołuje odpowiednią metodę.
	 * @author Radosław Świątkiewicz */
	protected abstract class GameObjectAction
	{
		/** Klucz przy nazwie tej akcji w pliku zasobów przedmiotu */
		private final String resourceActionNameKey;

		/** Akcja przedmiotu z określoną nazwą
		 * @param resourceActionNameKey Klucz nazwy akcji w pliku zasobów */
		public GameObjectAction(String resourceActionNameKey)
		{
			this.resourceActionNameKey = resourceActionNameKey;
		}

		/** Zwraca nazwę akcji
		 * @return Nazwa akcji, jaka jest zdefiniowana w pliku zasobów */
		public String getName()
		{
			return getResource(resourceActionNameKey);
		}

		/** Wywołaj metodę odpowiedniej akcji
		 * @return Dopisanie do logu i sukces */
		public abstract Response execute();
	}

	/** Akcja Naciśnięcia
	 * @author Radosław Świątkiewicz */
	protected class PushAction extends GameObjectAction
	{
		/** Zwyczjny konstruktor */
		public PushAction()
		{
			super("ActionNamePush");
		}

		@Override
		public Response execute()
		{
			return push();
		}
	}

	/** Akcja Otwarcia
	 * @author Radosław Świątkiewicz */
	protected class OpenAction extends GameObjectAction
	{
		public OpenAction()
		{
			super("ActionNameOpen");
		}

		@Override
		public Response execute()
		{
			return open();
		}
	}

	/** Akcja Zamykania
	 * @author Radosław Świątkiewicz */
	protected class CloseAction extends GameObjectAction
	{
		public CloseAction()
		{
			super("ActionNameClose");
		}

		@Override
		public Response execute()
		{
			return close();
		}
	}

	/** Akcja Przypatrywania się
	 * @author Radosław Świątkiewicz */
	protected class ExamineAction extends GameObjectAction
	{
		public ExamineAction()
		{
			super("ActionNameExamine");
		}

		@Override
		public Response execute()
		{
			return examine();
		}
	}

	/** Akcja Włączania
	 * @author Radosław Świątkiewicz */
	protected class TurnOnAction extends GameObjectAction
	{
		public TurnOnAction()
		{
			super("ActionNameTurnOn");
		}

		@Override
		public Response execute()
		{
			return turnOn();
		}
	}

	/** Akcja Wyłączania
	 * @author Radosław Świątkiewicz */
	protected class TurnOffAction extends GameObjectAction
	{
		public TurnOffAction()
		{
			super("ActionNameTurnOff");
		}

		@Override
		public Response execute()
		{
			return turnOff();
		}
	}

	/** Akcja przejścia przez
	 * @author Radosław Świątkewicz */
	protected class WalkAction extends GameObjectAction
	{
		public WalkAction()
		{
			super("ActionNameWalk");
		}

		@Override
		public Response execute()
		{
			return walk();
		}
	}
}
