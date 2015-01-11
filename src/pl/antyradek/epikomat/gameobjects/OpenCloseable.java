package pl.antyradek.epikomat.gameobjects;

/**
 * Ten przedmiot możę być zarówno otwarty, jak i zamknięty. Właściwie działa to
 * trochę, jak #define w c++. Minimalizuje jedynie ilość kodu.
 * 
 * @author Radosław Świątkiewicz
 *
 */
public interface OpenCloseable extends Openable, Closeable
{

}
