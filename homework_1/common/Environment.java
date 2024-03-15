package ija.ija2023.homework1.common;

// Environment
public interface Environment {

    public boolean createObstacleAt(int x, int y); // Vytvoří překážku na zadané pozici.

    public boolean obstacleAt(int x, int y); // Dle zadaných souřadnic ověří, zda je na zadané pozici překážka.

    public boolean Robot_onPos(Robot robot); // Metoda pro vložení robota na jeho pozici.

    public boolean Pos_exist(Position position); // Zjistí, zda je pozice existuje v prostředí.


    public boolean obstacleAt(Position position);


    public boolean robotAt(Position position);
}