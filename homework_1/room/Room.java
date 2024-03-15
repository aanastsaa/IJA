package ija.ija2023.homework1.room;

import java.util.ArrayList;

import ija.ija2023.homework1.common.Environment;
import ija.ija2023.homework1.common.Position;
import ija.ija2023.homework1.common.Robot;

/**
 * Room
 */
public class Room implements Environment {
    private int X;
    private int Y;
    private ArrayList<Robot> robot_exist;
    private ArrayList<Barrier> barriers;

    private Room(int X, int Y) {
        this.X = X;
        this.Y = Y;
        robot_exist  = new ArrayList<>();
        barriers = new ArrayList<>();
    }

    public static Room create(int x, int y) {
        if (x <= 0 || y <= 0) {
            throw new IllegalArgumentException("Rozmery misttnosti musi byt vetsi nez 0");
        }
        return new Room(x, y);
    }

    public static class Barrier {
        private final Position position;

        public Barrier(Position position) {
            this.position = position;
        }

        public Position getPos() {
            return position;
        }
    }

    @Override
    public boolean Robot_onPos(Robot robot) {
        Position robotPos = robot.getPosition();
        return placeEntity(robot, robotPos);
    }

    @Override
    public boolean Pos_exist(Position pos) {
        return pos.getY() < Y && pos.getX() < X && pos.getY() >= 0 && pos.getX() >= 0;
    }

    @Override
    public boolean createObstacleAt(int x, int y) {
        Position target_Pos = new Position(x, y);
        return placeEntity(new Barrier(target_Pos), target_Pos);
    }

    @Override
    public boolean obstacleAt(int x, int y) {
        return obstacleAt(new Position(x, y));
    }

    @Override
    public boolean obstacleAt(Position position) {
        return entityAtPosition(position, barriers);
    }

    @Override
    public boolean robotAt(Position position) {
        return entityAtPosition(position, robot_exist);
    }

    // private methods for placing entities and checking if they exist
    private boolean placeEntity(Object entity, Position position) {
        if (!Pos_exist(position) || entityAtPosition(position, entity instanceof Robot ? robot_exist : barriers)) {
            return false;
        }
        if (entity instanceof Robot) {
            robot_exist.add((Robot) entity);
        } else if (entity instanceof Barrier) {
            barriers.add((Barrier) entity);
        }
        return true;
    }

    // private methods for checking if entity exists at position and if position exists
    private boolean entityAtPosition(Position position, ArrayList<?> entities) {
        if (!Pos_exist(position)) return false;

        for (Object entity : entities) {
            if (entity instanceof Barrier && ((Barrier) entity).getPos().equals(position)) return true;
            if (entity instanceof Robot && ((Robot) entity).getPosition().equals(position)) return true;
        }

        return false;
    }
}
