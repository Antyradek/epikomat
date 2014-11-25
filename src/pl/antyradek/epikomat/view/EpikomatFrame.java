package pl.antyradek.epikomat.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InvalidClassException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import pl.antyradek.epikomat.resources.Resource;
import pl.antyradek.epikomat.resources.Resources;

/**
 * Główna ramka, komunikuje się bezpośrednio z View.
 * @author arq
 *
 */
class EpikomatFrame extends JFrame
{
	/**
	 * To zabezpiecza przed odwołaniami do różnych klas, czy jakoś tak.
	 * Żeby nie rzucał {@link InvalidClassException}.
	 */
	private static final long serialVersionUID = 777L;

	public EpikomatFrame()
	{
		super(Resources.getString(Resource.WINDOW_TITLE));
		JLabel label = new JLabel("Zawartość");
		
		buildMenuBar();
		
		getContentPane().add(label);
		pack();
		setSize(512, 512);
		setVisible(true);
	}
	
	/**
	 * Zbuduj całe menu i dodaj
	 */
	private void buildMenuBar()
	{
		JMenuBar menuBar = new JMenuBar();
		JMenu helpMenu = new JMenu(Resources.getString(Resource.HELP_MENU_TITLE));
		
		//przycisk pomocy
		JMenuItem helpMenuItem = new JMenuItem(Resources.getString(Resource.HELP_MENU_ITEM_TEXT));
		//dodaj akcję pkazania pomocy
		helpMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				showHelp();
			}
		});
		helpMenu.add(helpMenuItem);
		
		//przycisk autorów
		JMenuItem authorsMenuItem = new JMenuItem(Resources.getString(Resource.AUTHORS_MENU_ITEM_TEXT));
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
		///
		setJMenuBar(menuBar);
	}
	
	/**
	 * Pokaż pomoc
	 */
	private void showHelp()
	{
		//Nazwa na belce okna jest taka sama, jak przycisk menu
		JOptionPane.showMessageDialog(this, Resources.getString(Resource.HELP_TEXT), Resources.getString(Resource.HELP_MENU_ITEM_TEXT), JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Pokaż informację o autorze (zostawiłem liczbę mnogą na przyszłość :) )
	 */
	private void showAuthors()
	{
		JOptionPane.showMessageDialog(this, Resources.getString(Resource.AUTHORS_TEXT), Resources.getString(Resource.AUTHORS_MENU_ITEM_TEXT), JOptionPane.PLAIN_MESSAGE);
	}
}



