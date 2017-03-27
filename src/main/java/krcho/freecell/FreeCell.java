package krcho.freecell;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import krcho.stack.Stack;

/**
 * Game main class, with all main logic
 *
 * @author Jozef Krcho
 */
public final class FreeCell implements java.io.Serializable {

    private static final int TOP_BORDER = 20;
    private static final int TOP_COLUMN_BORDER = 150;

    private transient Image cardImage;
    private final HoldingCard holdingCard;
    private final ArrayList<CardPacks> cardPacks;
    private Stack<Move> moves;
    private boolean gameOver;

    public FreeCell() {
        this.setCardType("/obrazky/karty1.png");
        holdingCard = new HoldingCard();
        cardPacks = new ArrayList<>();
        moves = new Stack<>();
        gameOver = false;

        // generate 8 card columns with a 15 pixel distance between each other, and 150 pixels from top
        for (int i = 0; i < 8; i++) {
            cardPacks.add(new CardColumn(15 + i * (Card.getWidth() + 15), TOP_COLUMN_BORDER));
        }
        for (int i = 0; i < 4; i++) {
            cardPacks.add(new EmptySpace(15 + i * (Card.getWidth() + 15), TOP_BORDER));
        }
        for (int i = 0; i < 4; i++) {
            cardPacks.add(new WinnerPack(15 + (i + 4) * (Card.getWidth() + 15), TOP_BORDER, i));
        }
    }

    /**
     * Generate 52 random cards without repeating
     */
    public void newGame() {
        gameOver = false;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 52; i++) {
            list.add(i);
        }
        // reset old game
        for (CardPacks k : cardPacks) {
            while (k.takeCard() != null) {
            }
        }
        moves = new Stack<>();

        Random rand = new Random();
        while (list.size() > 0) {
            int i = rand.nextInt(list.size());
            i = list.remove(i);
            cardPacks.get(list.size() % 8).pushCard(new Card(i % 13, i / 13));
        }
    }

    /**
     * Generate 52 random cards without repeating, and cards with low value
     * are in the bottom of the card column
     */
    public void newEasyGame() {
        gameOver = false;
        ArrayList<Integer> bigValues = new ArrayList<>();
        ArrayList<Integer> lowValues = new ArrayList<>();
        for (int i = 0; i < 52; i++) {
            if (i % 13 > 7) {
                bigValues.add(i);
            } else {
                lowValues.add(i);
            }
        }
        // reset old game
        for (CardPacks k : cardPacks) {
            while (k.takeCard() != null) {
            }
        }
        moves = new Stack<>();

        Random rand = new Random();
        while (bigValues.size() > 0) {
            int i = rand.nextInt(bigValues.size());
            i = bigValues.remove(i);
            cardPacks.get(bigValues.size() % 8).pushCard(new Card(i % 13, i / 13));
        }
        while (lowValues.size() > 0) {
            int i = rand.nextInt(lowValues.size());
            i = lowValues.remove(i);
            cardPacks.get(lowValues.size() % 8).pushCard(new Card(i % 13, i / 13));
        }
    }

    /**
     * set image of card back
     *
     * @param path path to card image
     */
    public void setCardType(String path) {
        cardImage = new ImageIcon(this.getClass().getResource(path)).getImage();
    }

    /**
     * draw all cards
     *
     * @param g
     */
    public void toDraw(Graphics g) {
        for (CardPacks k : cardPacks) {
            k.toDraw(g, cardImage);
        }
        if (holdingCard.getCard() != null) {
            holdingCard.toDraw(g, cardImage);
        }
    }

    /**
     * grab gard on specific position
     *
     * @param x x
     * @param y y
     */
    public void grabCard(int x, int y) {
        if (holdingCard.getCard() == null && !gameOver) {
            int sp = 0;
            for (CardPacks k : cardPacks) {
                if (k.isHit(x, y)) {
                    holdingCard.setCard(k.takeCard());
                    holdingCard.setOffsetX(x - k.getX());
                    holdingCard.setOffsetY(y - k.getY());
                    holdingCard.setX(x);
                    holdingCard.setY(y);
                    holdingCard.setOldPosition(sp);
                    if (holdingCard.getCard() != null) {
                        AudioPlayer.getInstance().playPick();
                    }
                }
                sp++;
            }
        } else {
            holdingCard.setX(x);
            holdingCard.setY(y);
        }
    }

    /**
     * release card on specific position
     *
     * @param x x
     * @param y y
     */
    public void releaseCard(int x, int y) {
        if (holdingCard.getCard() != null) {
            int newPosition = 0;
            for (CardPacks k : cardPacks) {
                if (k.isHit(x, y)) {
                    if (k.putCard(holdingCard.getCard())) {
                        moves.push(new Move(holdingCard.getOldPosition(), newPosition));
                        AudioPlayer.getInstance().playDrop();
                        holdingCard.setCard(null);
                        return;
                    }
                }
                newPosition++;
            }
            cardPacks.get(holdingCard.getOldPosition()).pushCard(holdingCard.getCard());
            holdingCard.setCard(null);
        }
    }

    public void undo() {
        if (!moves.isEmpty() && !gameOver) {
            AudioPlayer.getInstance().playDrop();
            Move posledny = moves.pull();
            Card posledna = cardPacks.get(posledny.getNewPosition()).takeCard();
            cardPacks.get(posledny.getOldPosition()).pushCard(posledna);
        }
    }

    public boolean isOver() {
        int poc = 0;
        for (int i = 12; i < 16; i++) {
            if (cardPacks.get(i).getTopCard() != null) {
                if (cardPacks.get(i).getTopCard().getCardValue() == 12) {
                    poc++;
                }
            }
        }
        if (poc == 4) {
            gameOver = true;
            AudioPlayer.getInstance().playVictory();
        }
        return gameOver;
    }
}
