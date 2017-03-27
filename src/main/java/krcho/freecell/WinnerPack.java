package krcho.freecell;

import java.awt.Graphics;
import java.awt.Image;

/**
 * One of four winner space in top right corner of the game, where you 
 * continuously putting cards from A - K.
 *
 * @author Jozef Krcho
 */
public class WinnerPack extends CardPacks implements java.io.Serializable {

    private final int color;

    public WinnerPack(int x, int y, int color) {
        super(x, y);
        this.color = color;
    }

    @Override
    public void toDraw(Graphics g, Image image) {
        if (cards.getLast() != null) {
            cards.getLast().toDraw(g, image, aX, aY);
        } else {
            g.drawImage(image, aX, aY, aX + Card.getWidth(), aY + Card.getHeight(),
                    (color + 1) * Card.getWidth(), 4 * Card.getHeight(), (color + 2) * Card.getWidth(), 5 * Card.getHeight(), null);
        }
    }

    @Override
    public boolean putCard(Card karta) {
        if (cards.getLast() == null) {
            if (karta.getColor()== color && karta.getCardValue() == 0) {
                cards.push(karta);
                return true;
            } else {
                return false;
            }
        } else {
            Card k = cards.getLast();
            if (karta.getColor() == color && k.getCardValue() == (karta.getCardValue() - 1)) {
                cards.push(karta);
                return true;
            }
        }
        return false;
    }

    @Override
    public Card takeCard() {
        return cards.pull();
    }

    @Override
    public boolean isHit(int x, int y) {
        return (x > aX && x < aX + Card.getWidth() && y > aY && y < aY + Card.getHeight());
    }

}
