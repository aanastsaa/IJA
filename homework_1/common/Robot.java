package ija.ija2023.homework1.common;

public interface Robot {
    Position getPosition();
    boolean move();
    boolean canMove();
    void turn();
    int angle();
}
