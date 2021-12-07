package juegoCraps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIGridBagLayout extends JFrame
{
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
        headerProject = new Header("Tablero de Juego", Color.pink);
        constraints.gridx=0;
        constraints.gridy=0;
        constraints.gridwidth=2;
        constraints.fill=GridBagConstraints.HORIZONTAL;
        this.add(headerProject,constraints);

        ayuda = new JButton("?");
        ayuda.addActionListener(escucha);
        ayuda.setBackground(Color.pink);
        constraints.gridx=0;
        constraints.gridy=1;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.NONE;
        constraints.anchor=GridBagConstraints.LINE_START;
        this.add(ayuda,constraints);

        salir = new JButton("salir");
        salir.addActionListener(escucha);
        salir.setBackground(Color.pink);
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
        resultadosDados.setFont(new Font(Font.DIALOG,Font.BOLD,10));
        constraints.gridx=1;
        constraints.gridy=2;
        constraints.gridwidth=1;
        constraints.fill=GridBagConstraints.BOTH;
        constraints.anchor=GridBagConstraints.CENTER;
        this.add(resultadosDados,constraints);

        lanzar = new JButton("Lanzar");
        lanzar.addActionListener(escucha);
        lanzar.setBackground(Color.pink);
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

        }
    }
}
