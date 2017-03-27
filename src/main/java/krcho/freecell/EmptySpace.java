package krcho.freecell;

import java.awt.Graphics;
import java.awt.Image;

/**
 * One of four empty space used in top left corner of the game 
 * for doff the card.
 *
 * @author Jozef Krcho
 */
public class EmptySpace extends CardPacks implements java.io.Serializable {

    public EmptySpace(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean putCard(Card card) {
        if (cards.getLast() != null) {
            return false;
        } else {
            cards.push(card);
            return true;
        }
    }

    @Override
    public Card takeCard() {
        return cards.pull();
    }

    @Override
    public void toDraw(Graphics g, Image image) {
        if (cards.getLast() != null) {
            cards.getLast().toDraw(g, image, aX, aY);
        } else {
            g.drawImage(image, aX, aY, aX + Card.getWidth(), aY + Card.getHeight(),
                    5 * Card.getWidth(), 4 * Card.getHeight(), 6 * Card.getWidth(), 5 * Card.getHeight(), null);
        }
    }

    @Override
    public boolean isHit(int x, int y) {
        return (x > aX && x < aX + Card.getWidth() && y > aY && y < aY + Card.getHeight());
    }

}
