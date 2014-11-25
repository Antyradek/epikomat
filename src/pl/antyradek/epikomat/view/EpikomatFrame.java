package pl.antyradek.epikomat.view;

import java.io.InvalidClassException;

import javax.swing.JFrame;
import javax.swing.JLabel;

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
		getContentPane().add(label);
		pack();
		setSize(512, 512);
		setVisible(true);
	}

}
