package tank.connection;

import tank.dto.KeyEventDto;
import tank.dto.MouseEventDto;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class OutputConnection {
    private static String HOST = "192.168.1.105";
    private int PORT = 8001;
    private Socket socketOut;
    private OutputStream outputStream;
    private ObjectOutputStream objectOutputStream;


    public void startConnection() {
        try {
            System.out.println("Start");
            socketOut = new Socket(HOST, PORT);
            outputStream = socketOut.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);
            System.out.println("OutputConnection establishment");
        } catch (Exception e) {
            try {
                Thread.sleep(500);
                System.out.println("Try connection");
                startConnection();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }


    public void keyPressed(KeyEventDto e) {
        if (objectOutputStream != null) {
            try {
                objectOutputStream.writeObject(e);
                System.out.println("keyPressed");
            } catch (IOException ex) {
                System.out.println("OutputConnection isn't connection");
                startConnection();
            }
            System.out.println("End try-catch");
        }
        System.out.println("End keyPressed");
    }

    //
    public void keyReleased(KeyEventDto e) {
        if (objectOutputStream != null) {
            try {
                objectOutputStream.writeObject(e);
                System.out.println("keyReleased");
            } catch (IOException ex) {
                ex.printStackTrace();
                startConnection();
            }
            System.out.println("End try-catch");
        }
        System.out.println("End keyReleased");
    }


    public void mouseClicked(MouseEventDto e) {
        if (objectOutputStream != null) {
            try {
                objectOutputStream.writeObject(e);
                System.out.println("mouseClicked");
            } catch (IOException ex) {
                ex.printStackTrace();
                startConnection();
            }
            System.out.println("End try-catch");
        }
        System.out.println("End mouseClicked");
    }


    public void closeSocket() {
        try {
            socketOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getPORT() {
        return PORT;
    }

    public void setPORT(int PORT) {
        this.PORT = PORT;
    }
}
