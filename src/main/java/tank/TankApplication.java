package tank;


import tank.connection.InputConnection;
import tank.connection.OutputConnection;
import tank.view.Scena;

import javax.swing.*;
import java.awt.*;

public class TankApplication {

    public static void main(String[] args) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] gs = ge.getScreenDevices();
        GraphicsDevice gd0 = gs[0];
        OutputConnection outputConnection = new OutputConnection();

        Scena scena = new Scena();
        outputConnection.startConnection();
        scena.setOutputConnection(outputConnection);

        JFrame frame0 = new JFrame(gd0.getDefaultConfiguration());
        frame0.setTitle("TANKS");
        frame0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame0.setSize(500, 500);
        frame0.setBackground(new Color(34, 139, 34));
        frame0.setResizable(true);
        frame0.add(scena);
        frame0.setVisible(true);
        InputConnection inputConnection = new InputConnection();
        inputConnection.setScena(scena);
        inputConnection.start();
    }


}
