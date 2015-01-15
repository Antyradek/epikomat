package pl.antyradek.epikomat.view;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InvalidClassException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import pl.antyradek.epikomat.resources.Resource;
import pl.antyradek.epikomat.resources.Resources;

/**
 * Główna ramka, komunikuje się bezpośrednio z View.
 * 
 * @author Radosław Świątkiewicz
 *
 */
class EpikomatFrame extends JFrame
{
	/**
	 * To zabezpiecza przed odwołaniami do różnych klas, czy jakoś tak. Żeby nie
	 * rzucał {@link InvalidClassException}.
	 */
	private static final long serialVersionUID = 777L;

	/**
	 * Główna klasa widoku
	 */
	private final View view;

	/**
	 * Pole tekstowe, gdzie wyświetla się główny tekst gry (nazywany logiem, ale
	 * prawdziwy log to terminal)
	 */
	private final JTextArea logArea;

	/**
	 * Panel do wyświetlania przedmiotów w pokoju
	 */
	private JPanel gameObjectsList;

	/**
	 * Komponent przewijacza
	 */
	private JScrollPane gameObjectsListScrollPane;

	/**
	 * Ilość przedmiotów wyświetlanych
	 */
	private int gameObjectsCount;

	/**
	 * Ramka aplikacji
	 * 
	 * @param view
	 *            To jej interfejs, przez niego jest komunikacja z resztą
	 */
	public EpikomatFrame(final View view)
	{
		// stworzenie okna
		super(Resources.getString(Resource.WINDOW_TITLE));
		this.view = view;
		// zamykaniem zajmujemy się w osobno poprzez wysłanie akcji do kolejki
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		// stworzenie logu w TextArea najlepiej się sprawuje w kwestii zawijania
		// wierszy w tym przypadku
		logArea = new JTextArea();
		logArea.setLineWrap(true);
		logArea.setWrapStyleWord(true); // zawijenie wierszy na słowach
		logArea.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(logArea,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		// stworzenie ekwipunku (potem będzie działać, na czas oddania projektu
		// niech nie brzydzi okna)
		//
		// JComponent inventory = new JPanel();
		// inventory.setBackground(Color.ORANGE); // test na pokazanie

		// stworzenie przedmiotów z pokoju
		gameObjectsList = new JPanel();
		gameObjectsList.setLayout(new BoxLayout(gameObjectsList,
				BoxLayout.Y_AXIS));
		gameObjectsListScrollPane = new JScrollPane(gameObjectsList,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		resetGameObjectList();

		// stworzenie menu
		buildMenuBar();

		// ustawienie Layout Manager
		getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints constaints = new GridBagConstraints();
		// dodanie logu
		constaints.weightx = 3;
		constaints.weighty = 1;
		constaints.fill = GridBagConstraints.BOTH;
		constaints.gridwidth = 2;
		constaints.gridx = 0;
		constaints.gridy = 0;
		getContentPane().add(logScrollPane, constaints);
		// dodanie przedmiotów
		constaints.fill = GridBagConstraints.BOTH;
		constaints.gridwidth = 1;
		constaints.weightx = 1;
		constaints.weighty = 1;
		constaints.gridx = 2;
		constaints.gridy = 0;
		getContentPane().add(gameObjectsListScrollPane, constaints);
		// dodanie ekwipunku
		// constaints.fill = GridBagConstraints.BOTH;
		// constaints.gridwidth = 1;
		// constaints.weightx = 1;
		// constaints.weighty = 1;
		// constaints.gridx = 3;
		// constaints.gridy = 0;
		// getContentPane().add(inventory, constaints);

		pack();
		// okno na pełnym ekranie
		setExtendedState(MAXIMIZED_BOTH);
		setVisible(true);
	}

	/**
	 * Zbuduj całe menu i dodaj
	 */
	private void buildMenuBar()
	{
		JMenuBar menuBar = new JMenuBar();
		JMenu helpMenu = new JMenu(
				Resources.getString(Resource.HELP_MENU_TITLE));

		// przycisk pomocy
		JMenuItem helpMenuItem = new JMenuItem(
				Resources.getString(Resource.HELP_MENU_ITEM_TEXT));
		// dodaj akcję pkazania pomocy
		helpMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				showHelp();
			}
		});
		helpMenu.add(helpMenuItem);

		// przycisk autorów
		JMenuItem authorsMenuItem = new JMenuItem(
				Resources.getString(Resource.AUTHORS_MENU_ITEM_TEXT));
		authorsMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				showAuthors();
			}
		});
		helpMenu.add(authorsMenuItem);

		menuBar.add(helpMenu);
		// /
		setJMenuBar(menuBar);
	}

	/**
	 * Pokaż okno dialogowe pomocy. Nie ma sensu wysyłać tego do modelu. Nie
	 * zmieni w żaden sposób stanu aplikacji.
	 */
	private void showHelp()
	{
		// Nazwa na belce okna jest taka sama, jak przycisk menu
		JOptionPane.showMessageDialog(this,
				Resources.getString(Resource.HELP_TEXT),
				Resources.getString(Resource.HELP_MENU_ITEM_TEXT),
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Pokaż informację o autorze (zostawiłem liczbę mnogą na przyszłość... )
	 */
	private void showAuthors()
	{
		JOptionPane.showMessageDialog(this,
				Resources.getString(Resource.AUTHORS_TEXT),
				Resources.getString(Resource.AUTHORS_MENU_ITEM_TEXT),
				JOptionPane.PLAIN_MESSAGE);
	}

	/**
	 * Dodaj dane do logu nie czyszcząc
	 * 
	 * @param appendText
	 *            Tekst do dopisania
	 */
	public void appendLog(final String appendText)
	{
		logArea.setText(logArea.getText() + "\n\n" + appendText);
	}

	/**
	 * Ustaw log zupełnie na nowo usuwając poprzednią wartość
	 * 
	 * @param newtext
	 *            Ustaw tekst, a nie dopisuj
	 */
	public void setLog(final String newtext)
	{
		logArea.setText(newtext);
	}

	/**
	 * Dodaj do panelu przedmiotów
	 * 
	 * @param name
	 *            Przedmiot o tej nazwie
	 * @param actions
	 *            Z tymi akcjami
	 */
	public void addGameObject(final String name, final String[] actions)
	{
		JPanel newJpanel = new JPanel();
		newJpanel.setLayout(new BoxLayout(newJpanel, BoxLayout.Y_AXIS));
		newJpanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		JLabel nameLabel = new JLabel(name);
		// nameLabel.setMaximumSize(newJpanel.getMaximumSize());
		nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		newJpanel.add(nameLabel);
		for (int actionIndex = 0; actionIndex < actions.length; actionIndex++)
		{
			ActionButton button = new ActionButton(actions[actionIndex],
					gameObjectsCount, actionIndex);
			button.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
				{
					ActionButton actionButton = (ActionButton) e.getSource();
					int gameObjectIndex = actionButton.getGameObjectIndex();
					int actionIndex = actionButton.getActionIndex();

					view.sendActionToQueue(gameObjectIndex, actionIndex);

				}
			});
			// button.setMaximumSize(gameObjectsList.getSize());
			button.setAlignmentX(Component.CENTER_ALIGNMENT);
			newJpanel.add(button);
		}

		gameObjectsList.add(newJpanel);
		gameObjectsCount++;
	}

	/**
	 * Wyczyść listę przedmiotów
	 */
	public void resetGameObjectList()
	{
		gameObjectsCount = 0;
		// jeśli to jest, to blokuje się interfejs
		// O wiele dziwniejsze jest to, że działa bez tego
		// gameObjectsList.removeAll();
		// gameObjectsList.validate();

		// słyszałem, że w C# może wyciekać pamięć, ciekawe, czy pozbywa się
		// starego.
		gameObjectsList = new JPanel();
		gameObjectsList.setLayout(new BoxLayout(gameObjectsList,
				BoxLayout.Y_AXIS));
		gameObjectsListScrollPane.setViewportView(gameObjectsList);
	}
}
