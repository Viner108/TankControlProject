package tank.view;

import tank.connection.OutputConnection;
import tank.dto.KeyEventDto;
import tank.dto.MouseEventDto;
import tank.model.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

public class Scena extends JPanel implements KeyListener, MouseListener {
    Map<Integer, Tank> tanks = new HashMap<>();
    private OutputConnection outputConnection;

    public Scena() {
        super();
        this.setFocusable(true);
        this.requestFocusInWindow();
        grabFocus();
        addMouseListener(this);
        addKeyListener(this);
        setBackground(new Color(34, 139, 34));
    }

    public Map<Integer, Tank> getTanks() {
        return tanks;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        tanks.values().forEach(t -> t.draw(g));
        repaint();
    }

    public void putTank(Tank tank) {
        tanks.put(tank.id, tank);
    }

    public void removeTank(Tank tank) {
        tanks.remove(tank.id, tank);
    }

    public OutputConnection getOutputConnection() {
        return outputConnection;
    }

    public void setOutputConnection(OutputConnection outputConnection) {
        this.outputConnection = outputConnection;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        outputConnection.keyPressed(KeyEventDto.fromKeyEvent(e,true));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        outputConnection.keyPressed(KeyEventDto.fromKeyEvent(e,false));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("keyTyped");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        outputConnection.mouseClicked(MouseEventDto.fromMouseEvent(e));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("mousePressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("mouseReleased");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("mouseEntered");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("mouseExited");
    }
}
