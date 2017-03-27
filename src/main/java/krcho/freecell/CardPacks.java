package krcho.freecell;

import java.awt.Graphics;
import java.awt.Image;
import krcho.stack.Stack;

/**
 * Managing card packs in game. 
 *
 * @author Jozef Krcho
 */
public abstract class CardPacks implements java.io.Serializable {

    protected Stack<Card> cards;
    protected int aX, aY;
    protected int cardCount;

    public CardPacks(int x, int y) {
        cards = new Stack<>();
        aX = x;
        aY = y;
        cardCount = 0;
    }

    /**
     * Insert card. 
     *
     * @param card card
     */
    public void pushCard(Card card) {
        cards.push(card);
        cardCount++;
    }

    /**
     * draw by position
     *
     * @param g graphics
     * @param image image
     */
    public abstract void toDraw(Graphics g, Image image);

    /**
     * Try to put card in the pack, if it's possible return true  otherwise return false.
     *
     * @param card card
     * @return if it's possible return true  otherwise return false
     */
    public abstract boolean putCard(Card card);

    /**
     * take card from pack. 
     *
     * @return top card
     */
    public abstract Card takeCard();

    /**
     * Controlling if x,y is the position of the top card in stack. 
     *
     * @param x x
     * @param y y
     * @return true if yes, otherwise false
     */
    public abstract boolean isHit(int x, int y);

    public int getX() {
        return aX;
    }

    public int getY() {
        return aY;
    }

    public int getCardCount() {
        return cardCount;
    }

    public Card getTopCard() {
        return cards.getLast();
    }
}
