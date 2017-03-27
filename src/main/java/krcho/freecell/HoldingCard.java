package krcho.freecell;

import java.awt.Graphics;
import java.awt.Image;

/**
 * Actually holding (with mouse), It remember her old card box. 
 * And x, y screen position.
 *
 * @author Jozef Krcho
 */
public class HoldingCard implements java.io.Serializable {

    private Card card;
    private int x, y;
    private int offsetX, offsetY;
    private int oldPosition;

    public HoldingCard() {
        card = null;
        x = 0;
        y = 0;
        offsetX = 0;
        offsetY = 0;
    }

    /**
     * Draw the card, on position x,y - offsetX,Y. 
     * Offset is x,y value distance from top left card corner. 
     *
     * @param g graphics
     * @param image image
     */
    public void toDraw(Graphics g, Image image) {
        card.toDraw(g, image, x - offsetX, y - offsetY);
    }

    /**
     * Set up actual holding card.
     *
     * @param card card
     */
    public void setCard(Card card) {
        this.card = card;
    }

    /**
     * card x position
     *
     * @param x x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * card y position
     *
     * @param y y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Set old card position. For purpose of return. 
     *
     * @param position old box position
     */
    public void setOldPosition(int position) {
        this.oldPosition = position;
    }

    /**
     * Set up x offset from top left card corner. 
     *
     * @param offsetX size 
     */
    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    /**
     * Set up y offset from top left card corner. 
     *
     * @param offsetY size
     */
    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public Card getCard() {
        return card;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getOldPosition() {
        return oldPosition;
    }
}
