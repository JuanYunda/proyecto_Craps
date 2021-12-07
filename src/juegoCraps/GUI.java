package juegoCraps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used as view craps class.
 * @autor Paola-J Rodriguez-C paola.rodriguez@correounivalle.edu.co
 * @version v.1.0.0 date:06/12/2021
 */
public class GUI extends JFrame
{
    private static final String MESAJE_INICIO="Bienvenido a Craps \n"+
                                "Oprime el botón -Lanzar- para iniciar el juego\n"+
                                "Si su tiro de salida es 7 u 11 ganas con -Natural-\n"+
                                "Si tu tiro de salida es 2, 3 o 12 pierdes con Craps\n"+
                                "Si sacas cualquier otro valor estableceras un punto\n"+
                                "Estando en punto podrás seguir lanzando los dados\n"+
                                "pero ahora ganarás si sacas nuevamente el valor del punto\n"+
                                "establecido sin que previamente hayas sacado 7";

    private Header headerProject;
    private JLabel dado1, dado2;
    private JButton lanzar;
    private JPanel panelDados, panelResultados;
    private ImageIcon imagenDado;
    private JTextArea resultadosDados, mensajeSalida;
    private JSeparator separador;
    private Escucha escucha;
    private ModelCraps game;

    /**
     * Constructor of GUI class
     */
    public GUI()
    {
        initGUI();

        //Default JFrame configuration
        this.setTitle("Craps Game");
        //this.setSize(200,100); //set the window size
        this.pack(); //flexible window
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI()
    {
        //Set up JFrame Container's Layout
        //Create Listener Object and Control Object
        escucha = new Escucha();
        game = new ModelCraps();
        //Set up JComponents
        headerProject = new Header("Tablero de Juego", Color.pink);
        this.add(headerProject,BorderLayout.NORTH); //Change this line if you change JFrame Container's Layout

        imagenDado = new ImageIcon(getClass().getResource("/resources/dado.png"));
        dado1 = new JLabel(imagenDado);
        dado2 = new JLabel(imagenDado);

        lanzar = new JButton("Lanzar");
        lanzar.addActionListener(escucha);

        panelDados = new JPanel();
        panelDados.setPreferredSize(new Dimension(300,180));
        panelDados.setBorder(BorderFactory.createTitledBorder("Tus Dados"));
        panelDados.add(dado1);
        panelDados.add(dado2);
        panelDados.add(lanzar);

        this.add(panelDados,BorderLayout.CENTER);

        mensajeSalida = new JTextArea(8,31);
        mensajeSalida.setText(MESAJE_INICIO);
        mensajeSalida.setBackground(null);
        //mensajeSalida.setBorder(BorderFactory.createTitledBorder("¿Que debes hacer?"));
        JScrollPane scroll = new JScrollPane(mensajeSalida);

        panelResultados = new JPanel();
        panelResultados.setBorder(BorderFactory.createTitledBorder("¿Que debes hacer?"));
        panelResultados.add(scroll);
        panelResultados.setPreferredSize(new Dimension(370,180));

        this.add(panelResultados,BorderLayout.EAST);

        resultadosDados = new JTextArea(4,31);
        separador = new JSeparator();
        separador.setPreferredSize(new Dimension(320,10));
        separador.setBackground(Color.pink);
    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            GUI miProjectGUI = new GUI();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener
    {
        private int[] caras;
        @Override
        public void actionPerformed(ActionEvent e)
        {
            game.calculateShot();
            caras = game.getCaras();
            imagenDado = new ImageIcon(getClass().getResource("/resources/"+caras[0]+".png"));
            dado1.setIcon(imagenDado);
            imagenDado = new ImageIcon(getClass().getResource("/resources/"+caras[1]+".png"));
            dado2.setIcon(imagenDado);

            game.determinateGame();

            panelResultados.removeAll();
            panelResultados.setBorder(BorderFactory.createTitledBorder("Resultados"));
            resultadosDados.setText(game.getEstadoMessage()[0]);
            resultadosDados.setBackground(null);
            mensajeSalida.setRows(4);
            mensajeSalida.setText(game.getEstadoMessage()[1]);
            mensajeSalida.setFont(new Font(Font.DIALOG,Font.BOLD,11));
            panelResultados.add(resultadosDados);
            panelResultados.add(separador);
            panelResultados.add(mensajeSalida);
            revalidate();
            repaint();
        }
    }
}