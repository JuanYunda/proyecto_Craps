package juegoCraps;

/**
 * ModelCraps apply craps rules.
 * estado = 1 Natural Win.
 * estado = 2 Craps Lose.
 * estado = 3 Set point.
 * estado = 4 Point Round Win
 * estado = 5 Point Round Lose
 * @author Juan E. Mazuera Yunda
 * @version V.1.0.0 date 30/11/2021
 */
public class ModelCraps
{
    private Dado dado1, dado2;
    private int tiro, punto, estado, flag;
    private String estadoMessage;
    private int[] caras;

    public ModelCraps()
    {
        dado1 = new Dado();
        dado2 = new Dado();
        caras = new int[2]; //Create 2 positions array.
        flag = 0; //First Round
    }

    /**
     * Set Tiro value according each Dado.
     */
    public void calculateShot()
    {
        caras[0] = dado1.getCara();
        caras[1] = dado2.getCara();
        tiro = caras[0] + caras[1];
    }

    /**
     * Set the game state according estado atribute value.
     * estado = 1 Natural Win.
     * estado = 2 Craps Lose.
     * estado = 3 Set point.
     */
    public void determinateGame()
    {
        if(flag==0)
        {
            //First Round
            if (tiro == 7 || tiro == 11)
            {
                estado = 1; //u win in first round
            }
            else if (tiro == 3 || tiro == 2 || tiro == 12)
            {
                estado = 2; //u lose in first round
            }
            else
            {
                estado = 3; //set point number
                punto = tiro;
                flag = 1;
            }
        }
        else
        {
            //Point Round
            pointRound();
        }
    }

    /**
     * Set the game state according estado atribute value.
     * estado = 4 Point Round Win
     * estado = 5 Point Round Lose
     */
    private void pointRound()
    {
        if(flag==1)
        {
            //First Round
            if (tiro == punto)
            {
                estado = 4; //u win in point round
                flag = 0;
            }
            else if (tiro == 7)
            {
                estado = 5; //u lose in point round
                flag = 0;
            }
        }
    }

    public int getTiro()
    {

        return tiro;
    }

    public int getPunto()
    {

        return punto;
    }

    /**
     * Set a message that say the game state according estado atribute value.
     * @return Message for the View class.
     */
    public String getEstadoMessage()
    {
        switch(estado)
        {
            case 1: estadoMessage="¡¡Has Ganado por Natural!!";
                break;
            case 2: estadoMessage="Has Perdido por Craps. Intentalo de nuevo.";
                break;
            case 3: estadoMessage="¡Has asigando el punto en "+punto+"! Debes seguir lanzando.\n"+
                    "Ahora si sacas 7 antes que "+punto+" perderás, pero si sacas "+punto+" ganarás";
                break;
            case 4: estadoMessage="Volviste a sacar "+punto+". ¡¡Has ganado!!";
                break;
            case 5: estadoMessage="Sacaste 7 antes que "+punto+". Has perdido.";
                break;
        }
        return estadoMessage;
    }

    public int[] getCaras()
    {

        return caras;
    }
}
