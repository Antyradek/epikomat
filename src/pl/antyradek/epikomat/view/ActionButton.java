package pl.antyradek.epikomat.view;

import javax.swing.JButton;

import pl.antyradek.epikomat.bus.GameObjectActionId;

/**
 * Przycisk akcji posiadający informację o indeksie akcji i indeksie przedmiotu.
 * 
 * @author Radosław Świątkiewicz
 */
class ActionButton extends JButton
{
	/** Zaspokojenie Eclipsa (i zdaje się bezpieczeństwo jednoznacznego odwołania) */
	private static final long serialVersionUID = 12345L;
	/** Id akcji do wykonania */
	private final GameObjectActionId gameObjectActionId;

	/**
	 * Przycisk akcji
	 * 
	 * @param gameObjectActionId O tym Id akcji
	 */
	ActionButton(final GameObjectActionId gameObjectActionId)
	{
		super(gameObjectActionId.getName());
		this.gameObjectActionId = gameObjectActionId;
	}

	/**
	 * Id akcji przedmiotu
	 * 
	 * @return Id akcji przedmiotu, określające akcji przedmiotu w Modelu
	 */
	GameObjectActionId getGameObjectActionId()
	{
		return gameObjectActionId;
	}
}
