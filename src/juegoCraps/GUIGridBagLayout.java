package juegoCraps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIGridBagLayout extends JFrame
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
    private JButton lanzar, ayuda, salir;
    private JPanel panelDados;
    private ImageIcon imagenDado;
    private JTextArea resultadosDados, mensajeSalida;
    private Escucha escucha;
    private ModelCraps game;
    
    public GUIGridBagLayout()
    {
        initGUI();

        //Default JFrame configuration
        this.setTitle("Craps Game");
        //this.setSize(200,100); //set the window size
        this.setUndecorated(true); //this line need be written before set window's size
        this.setBackground(Color.pink);
        this.pack(); //flexible window
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initGUI()
    {
        //Set up JFrame Container's Layout
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        //Create Listener Object and Control Object
        escucha = new Escucha();
        game = new ModelCraps();
        //Set up JComponents
        headerProject = new Header("Tablero de Juego", Color.darkGray);
        constraints.gridx=0;
        constraints.gridy=0;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        this.add(headerProject,constraints);

        ayuda = new JButton("?");
        ayuda.addActionListener(escucha);
        ayuda.setBackground(Color.gray);
        ayuda.setForeground(Color.WHITE);
        constraints.gridx=0;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_START;
        this.add(ayuda,constraints);

        salir = new JButton("salir");
        salir.addActionListener(escucha);
        salir.setBackground(Color.gray);
        salir.setForeground(Color.WHITE);
        constraints.gridx=1;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_END;
        this.add(salir,constraints);

        imagenDado = new ImageIcon(getClass().getResource("/resources/dado.png"));
        dado1 = new JLabel(imagenDado);
        dado2 = new JLabel(imagenDado);
        panelDados = new JPanel();
        panelDados.setPreferredSize(new Dimension(300,180));
        panelDados.setBorder(BorderFactory.createTitledBorder("Tus Dados"));
        panelDados.setBackground(null);
        panelDados.add(dado1);
        panelDados.add(dado2);
        constraints.gridx=0;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        this.add(panelDados,constraints);

        resultadosDados = new JTextArea(4,31);
        resultadosDados.setBorder(BorderFactory.createTitledBorder("Resultados"));
        resultadosDados.setText("Lanza los dados");
        resultadosDados.setBackground(null);
        resultadosDados.setEditable(false);
        constraints.gridx=1;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        this.add(resultadosDados,constraints);

        lanzar = new JButton("Lanzar");
        lanzar.addActionListener(escucha);
        lanzar.setBackground(Color.pink);
        lanzar.setForeground(Color.WHITE);
        constraints.gridx=0;
        constraints.gridy=3;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        this.add(lanzar,constraints);

        mensajeSalida = new JTextArea(4,31);
        mensajeSalida.setText("Oprima el boton -?- para saber las reglas del juego");
        mensajeSalida.setBackground(null);
        mensajeSalida.setBorder(BorderFactory.createTitledBorder("Juego"));
        mensajeSalida.setFont(new Font(Font.DIALOG,Font.BOLD,10));
        mensajeSalida.setAlignmentX(this.CENTER_ALIGNMENT);
        mensajeSalida.setEditable(false);
        constraints.gridx=0;
        constraints.gridy=4;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.CENTER;
        this.add(mensajeSalida,constraints);
    }


    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            GUIGridBagLayout miProjectGUI = new GUIGridBagLayout();
        });
    }


    private class Escucha implements ActionListener
    {
        private int[] caras;
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource()==lanzar)
            {
                game.calculateShot();
                caras = game.getCaras();
                imagenDado = new ImageIcon(getClass().getResource("/resources/" + caras[0] + ".png"));
                dado1.setIcon(imagenDado);
                imagenDado = new ImageIcon(getClass().getResource("/resources/" + caras[1] + ".png"));
                dado2.setIcon(imagenDado);

                game.determinateGame();

                resultadosDados.setText(game.getEstadoMessage()[0]);
                mensajeSalida.setText(game.getEstadoMessage()[1]);
            }
            else if(e.getSource()==ayuda)
            {
            JOptionPane.showMessageDialog(null,MESAJE_INICIO);
            }
            else
            {
                System.exit(0);
            }
        }
    }
}
