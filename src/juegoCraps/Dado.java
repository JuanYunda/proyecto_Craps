package juegoCraps;

import java.util.Random;

/**
 * Class Dado generate a random number between 1 and 6.
 * @autor Juan E. Mazuera Yunda
 * @version v.1.0.0 date 30/11/2021
 */
public class Dado
{
    /**
     * Method that generate a random number to cara.
     * @return number between [1,6].
     */
    private int cara;

    public int getCara()
    {
        Random aleatorio = new Random();
        cara = aleatorio.nextInt(6)+1;
        return cara;
    }
}
