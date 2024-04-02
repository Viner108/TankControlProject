package tank.connection;

import tank.dto.TankDto;
import tank.logic.LogicForConnection;
import tank.model.Tank;
import tank.view.Scena;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class InputConnection extends Thread {
    private static String HOST = "192.168.1.105";
    private int PORT = 8002;
    private Socket socketInput;
    private InputStream inputStream;
    private ObjectInputStream objectInputStream;
    private Scena scena;
    LogicForConnection logicForConnection = new LogicForConnection();


    @Override
    public void run() {
        startConnection();
        while (true) {
            if (socketInput == null) {
                startConnection();
            }
            if (socketInput != null && !socketInput.isClosed()) {
                read();
            }
        }
    }

    public void startConnection() {
        try {
            System.out.println("Start");
            socketInput = new Socket(HOST, PORT);
            inputStream = socketInput.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);
            System.out.println("InputConnection establishment");
            read();
        } catch (Exception e) {
            try {
                Thread.sleep(500);
                System.out.println("Try connection");
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public TankDto read() {
        TankDto tankDto = null;
        try {
            Tank tank = null;
            while (true) {
                tankDto = (TankDto) objectInputStream.readObject();
                System.out.println(tankDto.toString());
                tank = logicForConnection.createTank(tankDto);
                if (tank.isAlive) {
                    scena.putTank(tank);
                }else {
                    scena.removeTank(tank);
                }
            }
        } catch (EOFException ex) {
            System.out.println("End of stream");
            startConnection();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("InputConnection isn't connection");
            startConnection();
        }
        return tankDto;
    }


    public void closeSocket() {
        try {
            socketInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Scena getScena() {
        return scena;
    }

    public void setScena(Scena scena) {
        this.scena = scena;
    }

    public int getPORT() {
        return PORT;
    }

    public void setPORT(int PORT) {
        this.PORT = PORT;
    }
}
