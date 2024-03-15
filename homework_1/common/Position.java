package ija.ija2023.homework1.common;

/**
 * Position
 */
public class Position {
    private int X;
    private int Y;

    public Position(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Position p) {
            return this.X == p.X && this.Y == p.Y;
        }
        return false;
    }

    @Override
    public String toString() {
        return "[" + X + "," + Y + "]";
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + X;
        result = prime * result + Y;
        return result;
    }

}