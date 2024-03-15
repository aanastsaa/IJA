package ija.ija2023.homework1.room;

import ija.ija2023.homework1.common.Environment;
import ija.ija2023.homework1.common.Position;
import ija.ija2023.homework1.common.Robot;

/**
 * ControlledRobot
 */
public class ControlledRobot implements Robot {
    private Environment environment;
    private Position position;
    private int angle;

    private ControlledRobot(Environment environment, Position position) {
        this.environment = environment;
        this.position = position;
        this.angle = 0;
    }

    public static ControlledRobot create(Environment environment, Position position) {
        if ( (environment == null || position == null) || (environment.obstacleAt(position) || environment.robotAt(position)) )
            return null;

        ControlledRobot New_Robot = new ControlledRobot(environment, position);
        environment.Robot_onPos(New_Robot);
        return New_Robot;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public int angle() {
        return angle;
    }

    @Override
    public void turn() {
        angle = (angle + 45) % 360;
    }

    @Override
    public boolean canMove() {
        double angleRadians = Math.toRadians(-angle + 90);
        int cos = (int) Math.round(Math.cos(angleRadians));
        int sin = (int) Math.round(Math.sin(angleRadians)) * (-1);
        Position New_Pos_up = new Position(sin + position.getX(), cos + position.getY());

        if (environment.Pos_exist(New_Pos_up) == false || environment.obstacleAt(New_Pos_up) == true || environment.robotAt(New_Pos_up) == true)
        {
            return false;
        }
        else
        {
            return true;
        }
    }


    @Override
    public boolean move() {
        if (canMove() == true) {
            double angleRadians = Math.toRadians(-angle + 90);
            int cos = (int) Math.round(Math.cos(angleRadians));
            int sin = (int) Math.round(Math.sin(angleRadians)) * (-1);

            position = new Position(sin + position.getX(), cos + position.getY());
            return true;
        } else {
            return false;
        }
    }

}