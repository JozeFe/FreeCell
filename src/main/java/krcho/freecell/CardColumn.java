package krcho.freecell;

import java.awt.Graphics;
import java.awt.Image;

/**
 * One card column in game.
 *
 * @author Jozef Krcho
 */
public class CardColumn extends CardPacks implements java.io.Serializable {

    public CardColumn(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean putCard(Card card) {
        if (cards.getLast() == null) {
            cards.push(card);
            cardCount++;
            return true;
        } else if (cards.getLast().isRed() != card.isRed()
                && cards.getLast().getCardValue() - 1 == card.getCardValue()) {
            cards.push(card);
            cardCount++;
            return true;
        }
        return false;
    }

    @Override
    public Card takeCard() {
        if (cardCount > 0) {
            cardCount--;
        }
        return cards.pull();
    }

    @Override
    public void toDraw(Graphics g, Image image) {
        int i = 0;
        for (Card card : cards) {
            card.toDraw(g, image, aX, aY + (i * 30));
            i++;
        }
    }

    @Override
    public boolean isHit(int x, int y) {
        return (x > aX && x < aX + Card.getWidth() && y > (aY + (cardCount - 1) * 30)
                && y < (aY + (cardCount - 1) * 30 + Card.getHeight()));
    }

    @Override
    public int getX() {
        return aX;
    }

    @Override
    public int getY() {
        return aY + cardCount * 30;
    }
}
