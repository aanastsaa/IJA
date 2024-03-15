/*
 * IJA 2023/24: Úloha 1
 * Testovací třída.
 */
package ija.ija2023.homework1;

import ija.ija2023.homework1.common.Environment;
import ija.ija2023.homework1.common.Position;
import ija.ija2023.homework1.common.Robot;
import ija.ija2023.homework1.room.ControlledRobot;
import ija.ija2023.homework1.room.Room;
import org.junit.Test;
import org.junit.Assert;

/**
 * Testovací třída pro první úkol z předmětu IJA 2023/24.
 * @author Radek Kočí
 */
public class Homework1Test {

    /**
     * Test vytvoření místnosti s překážkami.
     * 2b
     */
    @Test
    public void test01() {
        Environment room = Room.create(5, 8);

        Assert.assertTrue(room.createObstacleAt(1, 2));
        Assert.assertTrue(room.createObstacleAt(1, 4));
        Assert.assertTrue(room.createObstacleAt(1, 5));
        Assert.assertTrue(room.createObstacleAt(2, 5));

        Assert.assertFalse("Na [1,3] neni prekazka",room.obstacleAt(1, 3));
        Assert.assertTrue("Na [1,5] je prekazka",room.obstacleAt(1, 5));
    }

    /**
     * Test vytvoření místnosti s překážkami.
     * Testuje odolnost vůči chobnému vstupu.
     * 1b
     */
    @Test
    public void test02() {
        Assert.assertThrows(
                "Rozmery misttnosti musi byt vetsi nez 0 - generuje vyjimku IllegalArgumentException",
                IllegalArgumentException.class,
                () -> { Room.create(-1, 8); }
        );
    }

    /**
     * Test vytvoření vytvoření a umístění robota do místnosti.
     * 2b
     */
    @Test
    public void test03() {
        Environment room = this.createTestEnvironment();

        Position p = new Position(1,5);
        Robot r = ControlledRobot.create(room, p);
        Assert.assertNull("Robot nevytvoren, na pozici p=" + p + " je prekazka", r);

        p = new Position(4,2);
        r = ControlledRobot.create(room, p);
        Assert.assertNotNull("Robot vytvoren na pozici p=" + p, r);
    }

    /**
     * Test otáčení robota.
     * 1b
     */
    @Test
    public void test04() {
        Environment room = this.createTestEnvironment();

        Position p1 = new Position(4,2);
        Robot r1 = ControlledRobot.create(room, p1);
        Assert.assertNotNull("Robot vytvoren na pozici p=" + p1, r1);
        Assert.assertEquals("Pozice robota " + r1, p1, r1.getPosition());

        Position p2 = new Position(1,0);
        Robot r2 = ControlledRobot.create(room, p2);
        Assert.assertNotNull("Robot vytvoren na pozici p=" + p1, r2);
        Assert.assertEquals("Pozice robota " + r2, p2, r2.getPosition());

        Assert.assertEquals("Úhel natočení robota " + r1, 0, r1.angle());
        Assert.assertEquals("Úhel natočení robota " + r2, 0, r2.angle());
        r1.turn();
        Assert.assertEquals("Úhel natočení robota " + r1, 45, r1.angle());
        Assert.assertEquals("Úhel natočení robota " + r2, 0, r2.angle());
        r1.turn();
        r1.turn();
        r1.turn();
        r1.turn();
        r1.turn();
        r1.turn();
        Assert.assertEquals("Úhel natočení robota " + r1, 315, r1.angle());
        Assert.assertEquals("Úhel natočení robota " + r2, 0, r2.angle());
        r1.turn();
        Assert.assertEquals("Úhel natočení robota " + r1, 0, r1.angle());
        Assert.assertEquals("Úhel natočení robota " + r2, 0, r2.angle());
    }

    /**
     * Test pohybu robota v místnosti s překážkami.
     * 2b
     */
    @Test
    public void test05() {
        Environment room = this.createTestEnvironment();

        Position p1 = new Position(4,2);
        Robot r1 = ControlledRobot.create(room, p1);
        Assert.assertNotNull("Robot vytvoren na pozici p=" + p1, r1);

        Assert.assertTrue("Robot " + r1 + " se muze presunout o jednu pozici", r1.canMove());
        Assert.assertTrue("Robot " + r1 + " se presune o jednu pozici", r1.move());
        Assert.assertEquals("Úhel natočení robota " + r1, 0, r1.angle());
        p1 = new Position(3, 2);
        Assert.assertEquals("Pozice robota " + r1, p1, r1.getPosition());

        Assert.assertTrue("Robot " + r1 + " se muze presunout o jednu pozici", r1.canMove());
        Assert.assertTrue("Robot " + r1 + " se presune o jednu pozici", r1.move());
        Assert.assertEquals("Úhel natočení robota " + r1, 0, r1.angle());
        p1 = new Position(2, 2);
        Assert.assertEquals("Pozice robota " + r1, p1, r1.getPosition());

        Assert.assertFalse("Robot " + r1 + " se nemuze presunout o jednu pozici", r1.canMove());
        Assert.assertFalse("Robot " + r1 + " se nepresune o jednu pozici", r1.move());
        Assert.assertEquals("Úhel natočení robota " + r1, 0, r1.angle());
        p1 = new Position(2, 2);
        Assert.assertEquals("Pozice robota " + r1, p1, r1.getPosition());

        r1.turn();
        r1.turn();
        Assert.assertTrue("Robot " + r1 + " se muze presunout o jednu pozici", r1.canMove());
        Assert.assertTrue("Robot " + r1 + " se presune o jednu pozici", r1.move());
        Assert.assertEquals("Úhel natočení robota " + r1, 90, r1.angle());
        p1 = new Position(2, 3);
        Assert.assertEquals("Pozice robota " + r1, p1, r1.getPosition());
    }

    /**
     * Test kolize robotu.
     * 1b
     */
    @Test
    public void test06() {
        Environment room = this.createTestEnvironment();

        Position p1 = new Position(4,2);
        Robot r1 = ControlledRobot.create(room, p1);

        Position p2 = new Position(3,2);
        Robot r2 = ControlledRobot.create(room, p2);

        Assert.assertFalse("Robot " + r1 + " se nemuze presunout o jednu pozici", r1.canMove());
        r1.turn();
        r1.turn();
        Assert.assertTrue("Robot " + r1 + " se muze presunout o jednu pozici", r1.canMove());
        Assert.assertTrue("Robot " + r1 + " se presune o jednu pozici", r1.move());
        Assert.assertEquals("Úhel natočení robota " + r1, 90, r1.angle());
        p1 = new Position(4, 3);
        Assert.assertEquals("Pozice robota " + r1, p1, r1.getPosition());
    }

    /**
     * Test hranic mistnosti.
     * 1b
     */
    @Test
    public void test07() {
        Environment room = this.createTestEnvironment();

        Position p1 = new Position(3,1);
        Robot r1 = ControlledRobot.create(room, p1);
        r1.turn();
        r1.turn();
        r1.turn();
        r1.turn();
        r1.turn();
        Assert.assertEquals("Úhel natočení robota " + r1, 225, r1.angle());
        Assert.assertTrue("Robot " + r1 + " se muze presunout o jednu pozici", r1.canMove());
        Assert.assertTrue("Robot " + r1 + " se presune o jednu pozici", r1.move());
        p1 = new Position(4, 0);
        Assert.assertEquals("Pozice robota " + r1, p1, r1.getPosition());

        r1.turn();
        Assert.assertEquals("Úhel natočení robota " + r1, 270, r1.angle());
        Assert.assertFalse("Robot " + r1 + " se nemuze presunout o jednu pozici (hranice)", r1.canMove());
    }

    /**
     * Pomocná metoda pro vytvoření testovacího prostředí.
     * @return Testovací prostředí.
     */
    private Environment createTestEnvironment() {
        Environment room = Room.create(5, 8);

        room.createObstacleAt(1, 2);
        room.createObstacleAt(1, 4);
        room.createObstacleAt(1, 5);
        room.createObstacleAt(2, 5);

        return room;
    }

}