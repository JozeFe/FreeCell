package krcho.freecell;

import java.awt.Graphics;
import java.awt.Image;

/**
 * Card entity in the game.
 *
 * @author Jozef Krcho
 */
public class Card implements java.io.Serializable {

    private static final int WIDTH = 79;
    private static final int HEIGHT = 123;
    private final int cardValue, cardColor;

    /**
     * card value from 0 to 12, 0 = eso, 1 = one, 12 = King 
     * color 1 = ♣, 2 = ♦, 3 = ♥, 4 = ♠
     *
     * @param value card value
     * @param color card color
     */
    public Card(int value, int color) {
        this.cardValue = value;
        this.cardColor = color;
    }

    @Override
    public String toString() {
        return "karta";
    }

    /**
     * draw card to pane with graphics g, on position x a y
     *
     * @param g Pane graphics
     * @param image cards image
     * @param x position x
     * @param y position y
     */
    public void toDraw(Graphics g, Image image, int x, int y) {
        int ix = cardValue * WIDTH;
        int iy = cardColor * HEIGHT;
        g.drawImage(image, x, y, x + WIDTH, y + HEIGHT,
                ix, iy, ix + WIDTH, iy + HEIGHT, null);
    }

    public static int getWidth() {
        return WIDTH;
    }

    public static int getHeight() {
        return HEIGHT;
    }

    public int getCardValue() {
        return cardValue;
    }

    public int getColor() {
        return cardColor;
    }

    /**
     *
     * @return return true if cardColor is 1 (♦) or 2 (♥) else false
     */
    public boolean isRed() {
        if (cardColor == 1 || cardColor == 2) {
            return true;
        }
        return false;
    }
}
