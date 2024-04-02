package tank.logic;

import tank.dto.TankDto;
import tank.model.Tank;
import tank.model.Tore;

import java.util.Map;

public class LogicForConnection {


    public Tank createTank(TankDto tankDto) {
        Tank tank = new Tank(tankDto.getId(), tankDto.getX(), tankDto.getY());
        tank.setAlpha(tankDto.getAlpha());
        tank.setDeltaAlpha(tankDto.getDeltaAlpha());
        tank.setSpeedAlpha(tankDto.getSpeedAlpha());
        tank.isFocusable = tankDto.isFocusable;
        tank.isAlive = tankDto.isAlive;
        Tore tore = new Tore(tankDto.getTore().X, tankDto.getTore().Y);
        tore.setAlpha(tankDto.getTore().alpha);
        tank.setTore(tore);
        return tank;
    }
}
